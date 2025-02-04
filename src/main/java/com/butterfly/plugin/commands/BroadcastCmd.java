package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Permissions;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class BroadcastCmd implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!sender.hasPermission(Permissions.COMMAND_BROADCAST))
        {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_PERMISSION);
            return true;
        }

        if (args.length == 0)
        {
            MessageManager.sendMessage(sender, Message.GENERAL_INVALID_ARGS);
            return true;
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).toList().forEach(s -> sb.append(s).append(" "));
        String message = sb.toString();
        Bukkit.broadcastMessage(Utils.color("&4&l!!! &c&l[BROADCAST] &f" + message + " &4&l!!!"));
        return true;
    }
}
