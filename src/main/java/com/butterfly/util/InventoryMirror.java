package com.butterfly.util;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;

public class InventoryMirror {

    @Getter @Setter private Player owner;
    @Getter @Setter private Inventory inventory;
    @Getter @Setter private HashSet<Player> viewers;

    public InventoryMirror(Player owner, Inventory inventory, HashSet<Player> viewers) {
        this.owner = owner;
        this.inventory = inventory;
        this.viewers = viewers;
    }
}
