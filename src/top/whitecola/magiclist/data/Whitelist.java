package top.whitecola.magiclist.data;


import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class Whitelist {
    public CopyOnWriteArrayList<WLPlayer> players = new CopyOnWriteArrayList<>();

    public static class WLPlayer{
        public UUID uuid;
        public String name;
        public long qq;

        public WLPlayer(String name,long qq,UUID uuid){
            this.name = name;
            this.qq = qq;
            this.uuid = uuid;
        }


        public @Nullable Player toPlayer(){
            return Bukkit.getPlayer(this.name);
        }

        public @Nullable OfflinePlayer toOfflinePlayer(){
            return Bukkit.getOfflinePlayer(uuid);
        }

        public long getQQ(){
            return this.qq;
        }

        public String getName(){
            return this.name;
        }

        public UUID getUuid(){
            return this.uuid;
        }

    }


}
