package top.whitecola.magiclist.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.*;
import top.whitecola.magiclist.MagicList;
import top.whitecola.magiclist.utils.EventUtil;
import top.whitecola.magiclist.utils.PlayerUtil;
import top.whitecola.magiclist.utils.WhiteListUtil;

public class PlayerListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent e) throws Throwable {
        EventUtil.checkAndCancel(e, e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerConsumeItem(PlayerItemConsumeEvent e) throws Throwable {
        EventUtil.checkAndCancel(e, e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerPickupItem(PlayerPickupItemEvent e) throws Throwable {
        if(!WhiteListUtil.isPlayerStrongeInWhitelist(e.getPlayer())){
            e.setCancelled(true);
        }
        EventUtil.checkAndCancel(e, e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerDropItem(PlayerDropItemEvent e) throws Throwable {
        EventUtil.checkAndCancel(e, e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerInteract(PlayerInteractEvent e) throws Throwable {
        if(!WhiteListUtil.isPlayerStrongeInWhitelist(e.getPlayer())){
            if(e.getAction()== Action.PHYSICAL){
                e.setCancelled(true);
                return;
            }
        }
        EventUtil.checkAndCancel(e, e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerChat(PlayerChatEvent e) throws Throwable {
        EventUtil.checkAndCancel(e, e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerDamageEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            EventUtil.checkAndCancel(e, p);
        }


    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void onGameModeChange(PlayerGameModeChangeEvent e) {
        if (!WhiteListUtil.isPlayerStrongeInWhitelist(e.getPlayer())) {
            Bukkit.getScheduler().runTask(MagicList.instance, ()-> {
                try {
                    PlayerUtil.setInv(e.getPlayer(), true);
                } catch (Exception ex) {
                    System.out.println("MagicList >> …Ë÷√ÕÊº“"+e.getPlayer().getName()+" INV ß∞‹.");
                }
            });
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = false)
    public void onPlayerJoin(PlayerJoinEvent e) throws Throwable {
        PlayerUtil.checkPlayerAndChange(e.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onCommandProcess(PlayerCommandPreprocessEvent e) throws Throwable {
        EventUtil.checkAndCancel(e, e.getPlayer());
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent e) throws Throwable {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuitGame(PlayerQuitEvent e){

    }

}
