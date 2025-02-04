package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.managers.WorldManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        EntityDamageEvent.DamageCause cause = event.getCause();

        if (entity instanceof Player)
        {
            Disguise disguise = PlayerManager.getDisguise((Player) entity);
            if (disguise != null)
            {
                disguise.getPlayer().setHealth(((LivingEntity) entity).getHealth());

                if (WorldManager.isLargeMob(disguise.getLivingEntity().getType()) && cause.equals(EntityDamageEvent.DamageCause.FALL))
                {
                    event.setCancelled(true);
                } else
                {
                    return;
                }

                // Smash blocks on hard impact
                if (event.getEntity().getFallDistance() > 10) {
                    WorldManager.smash(entity.getLocation(), 4, 70);
                }
            }
        }

        if (!(entity instanceof LivingEntity)) {
            return;
        }

        Disguise disguise = PlayerManager.getDisguise(entity);
        if (disguise == null || disguise.getPlayer() == null) {
            return;
        }

        // Prevent disguise suffocation
        if (disguise.getPlayer().getGameMode() == GameMode.CREATIVE || cause.equals(EntityDamageEvent.DamageCause.SUFFOCATION)) {
            event.setCancelled(true);
        }
    }
}
