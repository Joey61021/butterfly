package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.projectiles.ProjectileSource;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        Disguise disguise = PlayerManager.getDisguise(player);
        if (disguise == null || !event.getAction().equals(Action.LEFT_CLICK_AIR)) {
            return;
        }

        if (disguise.getEntity().getType().equals(EntityType.WITHER)) {
            WitherSkull witherSkull = (WitherSkull) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.WITHER_SKULL);

            witherSkull.setVelocity(player.getLocation().getDirection().multiply(2));

            witherSkull.setYield(2.0F);
            witherSkull.setShooter((ProjectileSource) disguise.getEntity());
            return;
        }

        if (disguise.getEntity().getType().equals(EntityType.BLAZE)) {
            SmallFireball smallFireball = (SmallFireball) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SMALL_FIREBALL);

            smallFireball.setVelocity(player.getLocation().getDirection().multiply(2));
            smallFireball.setShooter((ProjectileSource) disguise.getEntity());
            return;
        }

        if (disguise.getEntity().getType().equals(EntityType.GHAST)) {
            Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getEyeLocation().add(0, -3, 0), EntityType.FIREBALL);

            fireball.setVelocity(player.getLocation().getDirection().multiply(2));
            fireball.setShooter((ProjectileSource) disguise.getEntity());
        }
    }
}
