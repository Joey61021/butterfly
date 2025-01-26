package com.butterfly.plugin.enums;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public enum DisguiseAbilities {
    CREEPER_EXPLODE(EntityType.CREEPER, "&cExplode", new ItemStack(Material.TNT)),
    BLAZE_FIREBALL(EntityType.BLAZE, "&6Fireball", new ItemStack(Material.BLAZE_ROD)),
    WITHER_SKULL(EntityType.WITHER, "&bWither Skull", new ItemStack(Material.WITHER_SKELETON_SKULL)),
    GHAST_FIREBALL(EntityType.GHAST, "&cFireball", new ItemStack(Material.FIRE_CHARGE));

    @Getter private final EntityType entityType;
    @Getter private final String display;
    @Getter private final ItemStack item;

    DisguiseAbilities(EntityType entityType, String display, ItemStack item) {
        this.entityType = entityType;
        this.display = display;
        this.item = item;
    }
}