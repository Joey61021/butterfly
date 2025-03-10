package com.butterfly.util;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashSet;

public class InventoryMirror
{

    private Player owner;
    private Inventory inventory;
    private HashSet<Player> viewers;

    public InventoryMirror(Player owner, Inventory inventory, HashSet<Player> viewers)
    {
        this.owner = owner;
        this.inventory = inventory;
        this.viewers = viewers;
    }

    public Player getOwner()
    {
        return owner;
    }

    public Inventory getInventory()
    {
        return inventory;
    }

    public HashSet<Player> getViewers()
    {
        return viewers;
    }
}
