package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class DamageListener implements Listener {

    @EventHandler
    public void onMove(EntityDamageEvent event) {
        Entity entity = event.getEntity();

        if (!(entity instanceof LivingEntity)) {
            return;
        }

        Disguise disguise = PlayerManager.getDisguise(entity);
        if (disguise == null || disguise.getPlayer() == null) {
            return;
        }

        if (disguise.getPlayer().getGameMode() == GameMode.CREATIVE) {
            event.setCancelled(true);
            return;
        }

        disguise.getPlayer().setHealth(((LivingEntity) entity).getHealth());
    }
}
