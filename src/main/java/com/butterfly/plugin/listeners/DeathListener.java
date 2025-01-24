package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();

        Disguise disguise = PlayerManager.getDisguise(entity);
        if (disguise == null) {
            return;
        }

        PlayerManager.disguises.remove(disguise);

        if (event.getEntity() == disguise.getEntity()) {
            event.getDrops().clear();
            return;
        }
        disguise.getEntity().remove();
    }
}
