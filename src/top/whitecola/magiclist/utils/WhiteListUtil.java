package top.whitecola.magiclist.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;
import top.whitecola.magiclist.MagicList;
import top.whitecola.magiclist.annotaions.OK;
import top.whitecola.magiclist.data.Whitelist;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class WhiteListUtil extends ListUtil{

    public static void fastRemove(CommandSender sender, Whitelist.WLPlayer wlp){
        try {
            WhiteListUtil.removeFromWhitelist(wlp);
            MSGUtil.sendPluginMessage("成功将"+wlp.name+"["+wlp.qq+"]移除白名单.");
        } catch (IOException e) {
            MSGUtil.sendPluginMessage("操作失败,储存文件或许已顺坏.",sender);
        }
    }

    public static Whitelist.WLPlayer fastGetWLPlayerByQQ(String str){
        long qq = 0;
        try {
            qq = Long.parseLong(str);
        }catch (IllegalArgumentException e){}
        return qq==0?null:getWLPlayer(qq);
    }

    @OK
    public static List<String> getQQAndIDFromWhitelist(){
        return ListUtil.getQQAndIDFromlist(MagicList.instance.whitelist.getConfig().players);
    }
//
//
//    public static @Nullable Whitelist.WLPlayer getWLPlayerByQQOrID(String temp) throws IllegalArgumentException{
//        return ListUtil.getWLPlayerByQQOrIDFromList(temp,MagicList.instance.whitelist.getConfig().players);
//    }

    @OK
    public static @Nullable void addToWhitelist(String name,long qq) throws IOException{
        ListUtil.addToList(new Whitelist.WLPlayer(name,qq, Bukkit.getOfflinePlayer(name).getUniqueId()),MagicList.instance.whitelist.getConfig().players);
        MagicList.instance.whitelist.saveConfig();
    }

    @OK
    public static @Nullable void addToWhitelist(Whitelist.WLPlayer wlp) throws IOException{
        ListUtil.addToList(wlp,MagicList.instance.whitelist.getConfig().players);
        MagicList.instance.whitelist.saveConfig();
    }

    @OK
    public static @Nullable void removeFromWhitelist(Whitelist.WLPlayer wlp) throws IOException{
        ListUtil.removeFromList(wlp,MagicList.instance.whitelist.getConfig().players);
        MagicList.instance.whitelist.saveConfig();
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPayer(Player p){
        if(p==null)
            return null;

        Whitelist.WLPlayer wlp;
        if((wlp = getWLPlayer(p.getUniqueId()))!=null && (wlp = getWLPlayer(p.getName()))!=null){
            return wlp;
        }
        return null;
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPlayer(long qq){
        return ListUtil.getWLPlayer(qq,MagicList.instance.whitelist.getConfig().players);
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPlayer(String name){
        return ListUtil.getWLPlayer(name,MagicList.instance.whitelist.getConfig().players);
    }

    @OK
    public static @Nullable Whitelist.WLPlayer getWLPlayer(UUID uuid){
        return ListUtil.getWLPlayer(uuid,MagicList.instance.whitelist.getConfig().players);
    }

    @OK
    public static boolean isPlayerStrongeInWhitelist(Player p){
        if(getWLPayer(p)!=null)
            return true;

        return false;
    }

    @Deprecated
    public static boolean isPlayerInWhitelist(String name){
        if(getWLPlayer(name)!=null)
            return true;

        return false;
    }

    @Deprecated
    public static boolean isPlayerInWhitelistByQQ(String qq){
        if(fastGetWLPlayerByQQ(qq)!=null)
            return true;

        return false;
    }

    @Deprecated
    public static boolean isPlayerInWhitelist(long qq){
        if(getWLPlayer(qq)!=null)
            return true;

        return false;
    }

}
