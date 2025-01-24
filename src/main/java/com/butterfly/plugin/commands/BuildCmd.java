package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class BuildCmd implements CommandExecutor {

    public static ArrayList<Player> build = new ArrayList<>();

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("fractured.admin")) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (build.contains(player)) {
            build.remove(player);
            MessageManager.sendMessage(player, Message.CMD_BUILD_TOGGLE_OFF);
            return false;
        }
        build.add(player);
        MessageManager.sendMessage(player, Message.CMD_BUILD_TOGGLE_ON);
        return false;
    }
}
