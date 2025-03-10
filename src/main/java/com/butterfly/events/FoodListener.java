package com.butterfly.events;

import com.butterfly.managers.PlayerManager;
import com.butterfly.util.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodListener implements Listener
{

    @EventHandler
    public void onFood(FoodLevelChangeEvent event)
    {
        Entity entity = event.getEntity();

        if (entity instanceof Player && PlayerManager.getDisguise(entity) != null)
        {
            ((Player) entity).setFoodLevel(20);
            event.setCancelled(true);
        }
    }
}
