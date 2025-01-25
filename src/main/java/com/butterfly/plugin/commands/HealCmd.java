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
import org.bukkit.inventory.ItemStack;

public class HealCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission("butterfly.heal"))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            MessageManager.sendMessage(player, Message.CMD_HEAL_HEALED_SELF);
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }

        target.setHealth(target.getMaxHealth());
        target.setFoodLevel(20);
        MessageManager.sendMessage(player,
                                    Message.CMD_HEAL_HEALED_OTHER,
                                    (s) -> s.replace("%target%", target.getDisplayName()));
        return false;
    }
}
