package com.butterfly.commands;

import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BuildCmd implements CommandExecutor {

    public static ArrayList<UUID> build = new ArrayList<>();

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(Permissions.COMMAND_BUILD)) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (build.contains(player.getUniqueId())) {
            build.remove(player.getUniqueId());
            MessageManager.sendMessage(player, Message.CMD_BUILD_TOGGLE_OFF);
            return false;
        }
        build.add(player.getUniqueId());
        MessageManager.sendMessage(player, Message.CMD_BUILD_TOGGLE_ON);
        return false;
    }
}
