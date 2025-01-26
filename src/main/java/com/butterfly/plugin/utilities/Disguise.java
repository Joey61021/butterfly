package com.butterfly.plugin.utilities;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Disguise {

    @Getter @Setter private Player player;
    @Getter @Setter private LivingEntity livingEntity;

    public Disguise(Player player, LivingEntity livingEntity) {
        this.player = player;
        this.livingEntity = livingEntity;
    }
}
