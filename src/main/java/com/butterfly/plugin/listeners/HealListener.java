package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class HealListener implements Listener {

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event) {
        Disguise disguise = PlayerManager.getDisguise(event.getEntity());
        if (disguise == null) {
            return;
        }

        if (event.getEntity() instanceof Player) {
            event.setCancelled(true);
            return;
        }

        disguise.getPlayer().setHealth(((LivingEntity) event.getEntity()).getHealth());
    }
}
