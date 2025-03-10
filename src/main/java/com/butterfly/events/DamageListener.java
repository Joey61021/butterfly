package com.butterfly.events;

import com.butterfly.managers.PlayerManager;
import com.butterfly.managers.WorldManager;
import com.butterfly.util.Disguise;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener
{

    @EventHandler
    public void onDamage(EntityDamageEvent event)
    {
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
                    ((Player) entity).playSound(entity.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1.0F, 1.0F);
                    WorldManager.smash(entity.getLocation(), 4, 70);
                }
            }
        }

        if (!(entity instanceof LivingEntity))
        {
            return;
        }

        Disguise disguise = PlayerManager.getDisguise(entity);
        if (disguise == null || disguise.getPlayer() == null)
        {
            return;
        }

        // Prevent disguise suffocation
        if (disguise.getPlayer().getGameMode() == GameMode.CREATIVE || cause.equals(EntityDamageEvent.DamageCause.SUFFOCATION))
        {
            event.setCancelled(true);
        }
    }
}
