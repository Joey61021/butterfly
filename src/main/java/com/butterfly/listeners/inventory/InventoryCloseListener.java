package com.butterfly.listeners.inventory;

import com.butterfly.managers.InventoryManager;
import com.butterfly.util.InventoryMirror;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        InventoryMirror inv = InventoryManager.getViewingInventory(player);
        if (inv != null) {
            InventoryManager.closeInventory(player);
        }
    }
}
