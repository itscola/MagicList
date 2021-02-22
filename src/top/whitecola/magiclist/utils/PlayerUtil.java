package top.whitecola.magiclist.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import top.whitecola.magiclist.MagicList;
import top.whitecola.magiclist.data.Whitelist;
import top.whitecola.magiclist.mojangapi.MojangAPI;
import top.whitecola.magiclist.struct.Confirmer;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class PlayerUtil {
    public static void checkPlayerAndChange(Player p) throws IOException {
        if(!WhiteListUtil.isPlayerStrongeInWhitelist(p)){
            Bukkit.getScheduler().runTask(MagicList.instance,()-> {
                try {
                    setInv(p,true);
                } catch (Exception e) {
                    System.out.println("MagicList >> 设置玩家"+p.getName()+" INV失败.");
                }
            });
            return;
        }
        Whitelist.WLPlayer wlp;
        if((wlp=WhiteListUtil.getWLPlayer(p.getName()))==null && (wlp=WhiteListUtil.getWLPlayer(p.getUniqueId()))!=null){
            // 玩家改名.
            try {
                if(wlp.uuid.toString().replace("-","").equals(MojangAPI.getUUIDByName(p.getName()))){
                    WhiteListUtil.removeFromWhitelist(wlp);
                    wlp.name = p.getName();
                    WhiteListUtil.addToWhitelist(wlp);
                    MagicList.instance.whitelist.saveConfig();
                    return;
                }
            }catch (IOException e){
                throw e;
            }catch (Exception e) {
                MagicList.instance.confirmList.put(p,new Confirmer(wlp,1));
                //加入改名验证队列
            }
        }

        if((wlp=WhiteListUtil.getWLPlayer(p.getName()))!=null && (wlp=WhiteListUtil.getWLPlayer(p.getUniqueId()))==null){
            //玩家UUID错误.
            String uuid;
            try {
                if((!(uuid=MojangAPI.getUUIDByName(wlp.name)).equals(""))&& uuid.equalsIgnoreCase(p.getUniqueId().toString().replace("-",""))){


                    WhiteListUtil.removeFromWhitelist(wlp);
                    wlp.uuid = UUID.fromString(getTrueUUID(uuid));
                    WhiteListUtil.addToWhitelist(wlp);
                    MagicList.instance.whitelist.saveConfig();
                    return;

                }
            }catch (IOException e){
                throw e;
            }catch (Exception e) {
                MagicList.instance.confirmList.put(p,new Confirmer(wlp,1));
                //加入改名验证队列
            }

        }


    }

    public static String getTrueUUID(String beforeUUID){
        String uuid = beforeUUID.substring(0, 8) +"-"+ beforeUUID.substring(9, 13)+"-"+ beforeUUID.substring(14, 18) +"-"+ beforeUUID.substring(19, 23) +"-"+ beforeUUID.substring(24);
        return uuid;
    }

    public static void setInv(Player p,boolean inv) throws Exception{
        Method getHandle = p.getClass().getDeclaredMethod("getHandle", new Class<?>[0]);
        getHandle.setAccessible(true);
        Object NMSPlayer = getHandle.invoke(p, new Object[0]);
        Field abilities = NMSPlayer.getClass().getSuperclass().getDeclaredField("abilities");
        abilities.setAccessible(true);
        Object abiObj = abilities.get(NMSPlayer);
        Class<?> abiClass = abiObj.getClass();
        Field isInvulnerable = abiClass.getDeclaredField("isInvulnerable");
        isInvulnerable.setAccessible(true);
        isInvulnerable.set(abiObj, inv);
        abilities.set(NMSPlayer, abiObj);
        Method upd = NMSPlayer.getClass().getDeclaredMethod("updateAbilities", new Class<?>[0]);
        upd.setAccessible(true);
        upd.invoke(NMSPlayer, new Object[0]);
    }
}
