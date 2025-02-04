package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

                if (isLargeMob(disguise.getLivingEntity().getType()) && cause.equals(EntityDamageEvent.DamageCause.FALL))
                {
                    event.setCancelled(true);
                } else
                {
                    return;
                }

                // Smash blocks on hard impact
                if (event.getEntity().getFallDistance() > 10) {
                    smash(entity.getLocation());
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

    public static void smash(Location location)
    {
        World world = location.getWorld();

        // Do not proceed if the world is null
        if (world == null)
        {
            return;
        }

        int radius = 4;
        int breakChance = 20;

        Set<Block> blocks = new HashSet<>();
        getNearbyBlocks(radius, location.add(0, -1, 0), blocks); // Get nearby blocks under player

        Random random = new Random();

        for (Block b : blocks)
        {
            // todo fixme
//                        if ((random.nextDouble() * 100) > breakChance)
//                        {
//                            continue;
//                        }

            Material material = b.getType();
            if (!canBeBroken(material)) continue;

            FallingBlock fallingBlock = world.spawnFallingBlock(b.getLocation().add(0.5, 0, 0.5), material.createBlockData());

            // Create velocity
            double x = (random.nextDouble() - 0.5) * 1.5;
            double y = random.nextDouble() * 1.5 + 0.5;
            double z = (random.nextDouble() - 0.5) * 1.5;

            fallingBlock.setVelocity(new Vector(x, y, z));

            fallingBlock.setDropItem(false);

            b.setType(Material.AIR);
        }
    }

    public static boolean isLargeMob(EntityType entityType) {
        return switch (entityType) {
            case IRON_GOLEM, RAVAGER, WARDEN, WITHER, HOGLIN, ZOGLIN, ENDER_DRAGON -> true;
            default -> false;
        };
    }

    public static boolean canBeBroken(Material material) {
        return switch (material) {
            case BEDROCK, OBSIDIAN -> false;
            default -> true;
        };
    }

    public static void getNearbyBlocks(int radius, Location location, Set<Block> blocks) {
        int bx = (int) location.getX();
        int by = (int) location.getY();
        int bz = (int) location.getZ();

        World world = location.getWorld();

        // Cannot proceed if world is null
        if (world == null)
        {
            return;
        }

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    Block relative = world.getBlockAt(bx + x, by + y, bz + z);

                    // Check if within spherical radius
                    if (relative.getLocation().distance(location) <= radius) {
                        blocks.add(relative);
                    }
                }
            }
        }
    }
}
