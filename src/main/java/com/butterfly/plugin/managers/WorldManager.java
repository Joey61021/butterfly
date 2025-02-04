package com.butterfly.plugin.managers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class WorldManager {

    public static void smash(Location location, int radius, int breakChance)
    {
        World world = location.getWorld();

        // Do not proceed if the world is null
        if (world == null)
        {
            return;
        }

        Set<Block> blocks = new HashSet<>();
        getNearbyBlocks(radius, location.add(0, -1, 0), blocks); // Get nearby blocks under player

        Random random = new Random();

        for (Block b : blocks)
        {
            if ((random.nextDouble() * 100) > breakChance)
            {
                continue;
            }

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
        if (world == null) {
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
