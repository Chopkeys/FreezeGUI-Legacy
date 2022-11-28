package freezeguilegacy.freezeguilegacy.events;
import freezeguilegacy.freezeguilegacy.FreezeGUILegacy;
import freezeguilegacy.freezeguilegacy.util.FreezeFunc;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import freezeguilegacy.freezeguilegacy.commands.FreezeCommand;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class EventsHandler implements Listener {
    FreezeFunc Freeze = new FreezeFunc();
    private final FreezeGUILegacy plugin;
    public EventsHandler(FreezeGUILegacy freezeGUILegacy) {
        this.plugin = freezeGUILegacy;
    }

    @EventHandler
    public void onMenuInteract(InventoryClickEvent e) {
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff")) {
            if(e.getCurrentItem() != null && (e.getCurrentItem()).getType().equals(Material.PAPER)) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onMenuInteract(PlayerMoveEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (FreezeCommand.hm.get(uuid) != Boolean.TRUE) return;
        e.setCancelled(true);
    }
    @EventHandler
    public void inventoryClose(InventoryCloseEvent e) {
        HumanEntity p = e.getPlayer();
        UUID uuid = p.getUniqueId();
        if (FreezeCommand.hm.get(uuid) != Boolean.TRUE) return;
        if(e.getView().getTitle().equalsIgnoreCase("You have been frozen by staff"))  {

            new BukkitRunnable() {
                @Override
                public void run() {
                    p.openInventory(e.getInventory());
                    if(plugin.config.getBoolean("logFreezes")) {
                        System.out.println((p.getName()) + "'s frozen screen has been re-opened (They tried to close it)");
                    }
                }
            }.runTask(this.plugin);
        }
    }
    @EventHandler
    public void onRejoinIfFrozen(PlayerJoinEvent e) {
        UUID uuid = (e.getPlayer()).getUniqueId();
        if ((FreezeCommand.hm.get(uuid)) != Boolean.TRUE) return;
        new BukkitRunnable() {
            @Override
            public void run() {
                Freeze.Freeze((e.getPlayer()), plugin.config.getBoolean("logFreezes"), true);
            }
        }.runTask(this.plugin);
    }

}
