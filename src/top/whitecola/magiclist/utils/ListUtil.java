package top.whitecola.magiclist.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import top.whitecola.magiclist.annotaions.OK;
import top.whitecola.magiclist.data.Whitelist;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


public class ListUtil {
    public static List<String> getQQAndIDFromlist(List<Whitelist.WLPlayer> list){
        List<String> temp = list.stream().filter(i-> i.qq!=0).map(i->i.qq+"").collect(Collectors.toList());
        temp.addAll(list.stream().map(Whitelist.WLPlayer::getName).collect(Collectors.toList()));
        return temp;
    }

//    public static @Nullable Whitelist.WLPlayer getWLPlayerByQQOrIDFromList(String temp,List<Whitelist.WLPlayer> list) throws IllegalArgumentException{
//        long qq = 0;
//        if(NumberUtils.isNumber(temp)){
//            try {
//                qq = Long.parseLong(temp);
//            }catch (IllegalArgumentException e){
//                throw e;
//            }
//        }
//        return (qq!=0)?ListUtil.getWLPlayer(qq,list):ListUtil.getWLPlayer(temp,list);
//    }

    @OK
    public static @Nullable void addToList(String name,long qq,List<Whitelist.WLPlayer> list) throws IOException {
        list.add(new Whitelist.WLPlayer(name,qq, Bukkit.getOfflinePlayer(name).getUniqueId()));
    }

    @OK
    public static @Nullable void addToList(Whitelist.WLPlayer wlp,List<Whitelist.WLPlayer> list) throws IOException{
        list.add(wlp);
    }

    @OK
    public static @Nullable void removeFromList(Whitelist.WLPlayer wlp,List<Whitelist.WLPlayer> list) throws IOException{
        list.remove(wlp);
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPayer(Player p,List<Whitelist.WLPlayer> list){
        if(p==null)
            return null;

        Whitelist.WLPlayer wlp = getWLPlayer(p.getUniqueId(),list);
        if(wlp==null)
            return getWLPlayer(p.getName(),list);

        return wlp;
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPlayer(long qq,List<Whitelist.WLPlayer> list){
        for(Whitelist.WLPlayer wlp : list){
            if(wlp==null)
                continue;
            if (wlp.qq == qq)
                return wlp;
        }

        return null;
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPlayer(String name,List<Whitelist.WLPlayer> list){
        for(Whitelist.WLPlayer wlp : list){
            if(wlp==null)
                continue;

            if(wlp.name.replace("#"," ").equalsIgnoreCase(name))
                return wlp;

        }
        return null;
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPlayer(UUID uuid,List<Whitelist.WLPlayer> list){
        for(Whitelist.WLPlayer wlp : list){
            if(wlp==null)
                continue;

            if(wlp.uuid==null)
                return null;

            if(wlp.uuid.equals(uuid))
                return wlp;

        }
        return null;
    }


    @OK
    public static boolean isPlayerInWhitelist(Player p,List<Whitelist.WLPlayer> list){
        if(getWLPayer(p,list)!=null)
            return true;

        return false;
    }

    @OK
    public static boolean isPlayerInWhitelist(String name,List<Whitelist.WLPlayer> list){
        if(getWLPlayer(name,list)!=null)
            return true;

        return false;
    }

    @OK
    public static boolean isPlayerInWhitelist(long qq,List<Whitelist.WLPlayer> list){
        if(getWLPlayer(qq,list)!=null)
            return true;

        return false;
    }
}
