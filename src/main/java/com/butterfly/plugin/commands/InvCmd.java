package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.InventoryManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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

        InventoryManager.display(player, target);
        MessageManager.sendMessage(player, Message.CMD_INV_OPENING, (s) -> s.replace("%player%", target.getDisplayName()));
        return false;
    }
}
