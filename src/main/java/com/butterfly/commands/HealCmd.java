package com.butterfly.commands;

import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import com.butterfly.util.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_HEAL))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            player.setFireTicks(0);
            Utils.removePotionEffects(player);
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
        target.setFireTicks(0);
        Utils.removePotionEffects(target);
        MessageManager.sendMessage(player,
                                    Message.CMD_HEAL_HEALED_OTHER,
                                    (s) -> s.replace("%target%", target.getDisplayName()));
        return false;
    }
}
