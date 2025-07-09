package dev.yooproject.yoolib.object.guis;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public final class YooGuiManager {

    private static final AtomicBoolean REGISTERED = new AtomicBoolean(false);

    private YooGuiManager() {
        throw new UnsupportedOperationException();
    }

    public static void register(Plugin plugin) {
        Objects.requireNonNull(plugin, "plugin");
        if (REGISTERED.getAndSet(true)) {
            throw new IllegalStateException("YooGui is already registered");
        }
        Bukkit.getPluginManager().registerEvents(new InventoryListener(plugin), plugin);
    }

    public static final class InventoryListener implements Listener {
        private final Plugin plugin;

        public InventoryListener(Plugin plugin) {
            this.plugin = plugin;
        }

        @EventHandler
        public void onInventoryClick(InventoryClickEvent e) {
            if (e.getInventory().getHolder() instanceof YooGui && e.getClickedInventory() != null) {
                YooGui inv = (YooGui) e.getInventory().getHolder();
                boolean wasCancelled = e.isCancelled();
                e.setCancelled(true);
                inv.handleClick(e);
                if (!wasCancelled && !e.isCancelled()) {
                    e.setCancelled(false);
                }
            }
        }

        @EventHandler
        public void onInventoryDrag(InventoryDragEvent e) {
            if (e.getInventory().getHolder() instanceof YooGui) {
                YooGui inv = (YooGui) e.getInventory().getHolder();
                boolean wasCancelled = e.isCancelled();
                e.setCancelled(true);
                inv.handleDrag(e);
                if (!wasCancelled && !e.isCancelled()) {
                    e.setCancelled(false);
                }
            }
        }

        @EventHandler
        public void onInventoryOpen(InventoryOpenEvent e) {
            if (e.getInventory().getHolder() instanceof YooGui) {
                YooGui inv = (YooGui) e.getInventory().getHolder();
                inv.handleOpen(e);
            }
        }

        @EventHandler
        public void onInventoryClose(InventoryCloseEvent e) {
            if (e.getInventory().getHolder() instanceof YooGui) {
                YooGui inv = (YooGui) e.getInventory().getHolder();
                if (inv.handleClose(e)) {
                    Bukkit.getScheduler().runTask(this.plugin, () -> inv.open((Player) e.getPlayer()));
                }
            }
        }

        @EventHandler
        public void onPluginDisable(PluginDisableEvent e) {
            if (e.getPlugin() == this.plugin) {
                REGISTERED.set(false);
            }
        }
    }
}
