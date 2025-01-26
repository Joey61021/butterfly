package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("butterfly.inv"))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            MessageManager.sendMessage(player, Message.GENERAL_INVALID_ARGS);
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }

        MessageManager.sendMessage(player, Message.CMD_INV_OPENING, (s) -> s.replace("%player%", target.getDisplayName()));
        Inventory inv = Bukkit.createInventory(null, 6 * 9, Utils.color("&8" + target.getName() + "'s inventory"));

        ItemStack divider = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta meta = divider.getItemMeta();
        meta.setDisplayName(Utils.color("&0#"));
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        divider.setItemMeta(meta);

        for (int i = 0; i < 9; i++) {
            inv.setItem((4 * 9) +  i, divider);
        }

        ItemStack[] items = target.getInventory().getContents();
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].getType() != Material.AIR && i < 36) {
                inv.setItem(i, items[i]);
            }
        }

        ItemStack[] armor = target.getInventory().getArmorContents();
        for (int i = 0; i < armor.length; i++) {
            if (armor[i] != null && armor[i].getType() != Material.AIR && i < 36) {
                inv.setItem((5 * 9) + i, armor[i]);
            }
        }

        player.openInventory(inv);
        return false;
    }
}
