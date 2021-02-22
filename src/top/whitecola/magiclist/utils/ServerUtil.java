package top.whitecola.magiclist.utils;

import org.bukkit.Bukkit;
import top.whitecola.magiclist.MagicList;

public class ServerUtil {

    public static boolean isStrongOnlineMode(){
        return MagicList.instance.config.getConfig().isSavingUUID.equalsIgnoreCase("%aotu%")?Bukkit.getOnlineMode():(MagicList.instance.config.getConfig().isSavingUUID.equals("Online") || Bukkit.getOnlineMode()) && !MagicList.instance.config.getConfig().isSavingUUID.equalsIgnoreCase("Offline");
    }
}
