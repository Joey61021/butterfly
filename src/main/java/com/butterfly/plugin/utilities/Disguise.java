package com.butterfly.plugin.utilities;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Disguise {

    @Getter @Setter private Player player;
    @Getter @Setter private Entity entity;

    public Disguise(Player player, Entity entity) {
        this.player = player;
        this.entity = entity;
    }
}
