package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Permissions;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PushCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_PUSH))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }
        if (args.length < 2) {
            MessageManager.sendMessage(player, Message.GENERAL_INVALID_ARGS);
            return false;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }

        int vel;
        try {
            vel = Integer.parseInt(args[1]);
        } catch (Exception error) {
            MessageManager.sendMessage(player, Message.GENERAL_INVALID_ARGS);
            return false;
        }

        Vector direction = target.getLocation().getDirection().multiply(-vel).setY(0.5);
        target.setVelocity(direction);
        return false;
    }
}
