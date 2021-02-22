package top.whitecola.magiclist.data;

import java.util.concurrent.CopyOnWriteArrayList;

public class Blacklist {
    public CopyOnWriteArrayList<Whitelist.WLPlayer> blackList = new CopyOnWriteArrayList<>();
}
