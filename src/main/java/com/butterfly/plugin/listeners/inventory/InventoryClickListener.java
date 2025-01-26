package com.butterfly.plugin.listeners.inventory;

import com.butterfly.plugin.managers.InventoryManager;
import com.butterfly.plugin.utilities.InventoryMirror;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();

        if (InventoryManager.isBeingTracked(player)) {
            InventoryManager.updateInventory(player);
            return;
        }

        InventoryMirror inv = InventoryManager.getViewingInventory(player);
        if (inv != null && event.getClickedInventory() == inv.getInventory()) {
            event.setCancelled(true);
        }
    }
}
