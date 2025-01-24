package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("butterfly.item"))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            MessageManager.sendMessage(player, Message.GENERAL_INVALID_ARGS);
            return false;
        }

        int amount = args.length > 1 ? Integer.parseInt(args[1]) : 1;

        try {
            Material item = Material.valueOf(args[0].toUpperCase());
            player.getInventory().addItem(new ItemStack(item, amount));
            MessageManager.sendMessage(player,
                                        Message.CMD_ITEM_GIVEN,
                                        (s) -> s.replace("%amount%", String.valueOf(amount))
                                                .replace("%item%", item.toString()));
        } catch (IllegalArgumentException error) {
            MessageManager.sendMessage(player,
                                        Message.CMD_ITEM_INVALID,
                                        (s) -> s.replace("%item%", args[0]));
        }
        return false;
    }
}
