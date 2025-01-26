package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodListener implements Listener {

    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
        Entity entity = event.getEntity();
        Disguise disguise = PlayerManager.getDisguise(entity);

        if (entity instanceof Player && disguise != null) {
            ((Player) entity).setFoodLevel(20);
            event.setCancelled(true);
        }
    }
}
