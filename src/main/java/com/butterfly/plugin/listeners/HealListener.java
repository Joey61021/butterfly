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
        if (!(event.getEntity() instanceof Player) || !PlayerManager.activeDisguises.contains((Player) event.getEntity())) {
            return;
        }

        Player player = (Player) event.getEntity();
        Disguise disguise = PlayerManager.getDisguise(event.getEntity());
        disguise.getLivingEntity().setHealth(player.getHealth());
    }
}
