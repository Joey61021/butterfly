package com.butterfly.events;

import com.butterfly.managers.InventoryManager;
import com.butterfly.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PickupListener implements Listener
{

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event)
    {
        Player player = event.getPlayer();

        if (InventoryManager.isBeingTracked(player))
        {
            InventoryManager.updateInventory(player);
        }

        if (PlayerManager.vanish.contains(player.getUniqueId()) || PlayerManager.getDisguise(player) != null)
        {
            event.setCancelled(true);
        }
    }
}
