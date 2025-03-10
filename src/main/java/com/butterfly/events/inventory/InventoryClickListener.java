package com.butterfly.events.inventory;

import com.butterfly.managers.InventoryManager;
import com.butterfly.util.InventoryMirror;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener
{

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player))
        {
            return;
        }

        if (InventoryManager.isBeingTracked(player))
        {
            InventoryManager.updateInventory(player);
            return;
        }

        InventoryMirror inv = InventoryManager.getViewingInventory(player);
        if (inv != null && event.getClickedInventory() == inv.getInventory())
        {
            event.setCancelled(true);
        }
    }
}
