package top.whitecola.magiclist.utils;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import top.whitecola.magiclist.MagicList;

public class EventUtil {
    public static boolean checkAndCancel(Cancellable e, Player p) {
        if(MagicList.instance.confirmList.containsKey(p))
            return checkAndCancel(e, p, MagicList.instance.language.getConfig().onPlayerNeedToConfirm);

        if(!WhiteListUtil.isPlayerStrongeInWhitelist(p))
            return checkAndCancel(e, p, MagicList.instance.language.getConfig().NoWhitelistPlayer);

        return false;
    }

    private static boolean checkAndCancel(Cancellable e, Player p, String str) {
        e.setCancelled(true);
        p.sendMessage(str);
        return true;
    }

}
