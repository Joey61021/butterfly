package com.butterfly.util;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Disguise {

    private Player player;
    private LivingEntity livingEntity;

    public Disguise(Player player, LivingEntity livingEntity) {
        this.player = player;
        this.livingEntity = livingEntity;
    }

    public Player getPlayer()
    {
        return player;
    }

    public LivingEntity getLivingEntity()
    {
        return livingEntity;
    }
}
