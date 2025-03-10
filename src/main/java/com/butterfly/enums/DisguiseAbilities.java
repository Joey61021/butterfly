package com.butterfly.enums;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public enum DisguiseAbilities
{

    CREEPER_EXPLODE(EntityType.CREEPER, "&cExplode", new ItemStack(Material.TNT)),
    BLAZE_FIREBALL(EntityType.BLAZE, "&6Fireball", new ItemStack(Material.BLAZE_ROD)),
    WITHER_SKULL(EntityType.WITHER, "&bWither Skull", new ItemStack(Material.WITHER_SKELETON_SKULL)),
    GHAST_FIREBALL(EntityType.GHAST, "&cFireball", new ItemStack(Material.FIRE_CHARGE)),
    ENDERMAN_TELEPORT(EntityType.ENDERMAN, "&9Teleport", new ItemStack(Material.STICK)),
    ENDERMAN_PICKUP_BLOCK(EntityType.ENDERMAN, "&aPickup Block", new ItemStack(Material.STONE_SHOVEL));

    private final EntityType entityType;
    private final String display;
    private final ItemStack item;

    DisguiseAbilities(EntityType entityType, String display, ItemStack item)
    {
        this.entityType = entityType;
        this.display = display;
        this.item = item;
    }

    public EntityType getEntityType()
    {
        return entityType;
    }

    public String getDisplay()
    {
        return display;
    }

    public ItemStack getItem()
    {
        return item;
    }
}
