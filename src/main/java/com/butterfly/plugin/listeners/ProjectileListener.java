package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.WorldManager;
import org.bukkit.entity.Arrow;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileListener implements Listener {

    @EventHandler
    public void onProjectile(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow arrow))
        {
            return;
        }

        if (arrow.hasMetadata("boomStick"))
        {
            WorldManager.smash(arrow.getLocation(), 5, 100);
            arrow.remove();
        }
    }
}
