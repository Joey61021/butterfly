package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.InventoryManager;
import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

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
