package com.butterfly.plugin.listeners.inventory;

import com.butterfly.plugin.managers.InventoryManager;
import com.butterfly.plugin.utilities.InventoryMirror;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onDrop(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        InventoryMirror inv = InventoryManager.getViewingInventory(player);
        if (inv != null) {
            InventoryManager.closeInventory(player);
        }
    }
}
