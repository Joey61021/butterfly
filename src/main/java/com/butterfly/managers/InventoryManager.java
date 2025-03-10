package com.butterfly.managers;

import com.butterfly.ButterflyCore;
import com.butterfly.util.InventoryMirror;
import com.butterfly.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashSet;
import java.util.Set;

public class InventoryManager {
    public static Set<InventoryMirror> inventories = new HashSet<>();

    public static boolean isBeingTracked(Player target) {
        for (InventoryMirror invs : inventories) {
            if (invs.getOwner().getUniqueId().equals(target.getUniqueId())) {
                return true;
            }
        }
        return false;
    }

    public static InventoryMirror getViewingInventory(Player viewer) {
        for (InventoryMirror invs : inventories) {
            if (invs.getViewers().contains(viewer)) {
                return invs;
            }
        }
        return null;
    }

    public static void closeInventory(Player viewer) {
        InventoryMirror inv = null;
        for (InventoryMirror invs : inventories) {
            if (invs.getViewers().contains(viewer)) {
                inv = invs;
                break;
            }
        }

        if (inv == null) {
            return;
        }

        inv.getViewers().remove(viewer);
        if (inv.getViewers().size() < 1) {
            inventories.remove(inv);
        }
    }

    public static Inventory populateInv(Inventory inv, Player target) {
        inv.clear();

        ItemStack[] items = target.getInventory().getContents();
        for (int i = 0; i < Math.min(items.length, inv.getSize()); i++) {
            if (items[i] != null && items[i].getType() != Material.AIR) {
                if (i < inv.getSize()) {
                    inv.setItem(i, items[i]);
                }
            }
        }

        return inv;
    }

    public static void updateInventory(Player target) {
        Bukkit.getScheduler().runTaskLater(ButterflyCore.instance, () -> {
            for (InventoryMirror invs : inventories) {
                if (invs.getOwner().getUniqueId().equals(target.getUniqueId())) {
                    populateInv(invs.getInventory(), invs.getOwner());
                    for (Player viewer : invs.getViewers()) {
                        viewer.updateInventory();
                    }
                }
            }
        }, 1L);
    }

    public static InventoryMirror establishInv(Player target) {
        for (InventoryMirror invs : inventories) {
            if (invs.getOwner().getUniqueId().equals(target.getUniqueId())) {
                return invs;
            }
        }

        Inventory inv = Bukkit.createInventory(null, 4 * 9, Utils.color("&8" + target.getName() + "'s inventory"));
        InventoryMirror trackedInv = new InventoryMirror(target, populateInv(inv, target), new HashSet<>());
        inventories.add(trackedInv);
        return trackedInv;
    }

    public static void display(Player viewer, Player target) {
        InventoryMirror inv = establishInv(target);
        inv.getViewers().add(viewer);

        viewer.openInventory(inv.getInventory());
    }
}
