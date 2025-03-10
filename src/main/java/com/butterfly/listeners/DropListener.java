package com.butterfly.listeners;

import com.butterfly.managers.InventoryManager;
import com.butterfly.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class DropListener implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();

        if (InventoryManager.isBeingTracked(player)) {
            InventoryManager.updateInventory(player);
        }

        if (PlayerManager.activeDisguises.contains(player.getUniqueId())) {
            event.setCancelled(true);
        }
    }
}
