package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.InventoryManager;
import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        if (entity instanceof Player && InventoryManager.isBeingTracked((Player) entity)) {
            InventoryManager.updateInventory(((Player) entity));
        }

        Disguise disguise = PlayerManager.getDisguise(entity);
        if (disguise == null) {
            return;
        }

        PlayerManager.removeDisguise(disguise);
        if (event.getEntity() == disguise.getLivingEntity()) {
            event.getDrops().clear();
            return;
        }

        disguise.getLivingEntity().remove();
    }
}
