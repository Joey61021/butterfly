package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.Utils;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class BroadcastCmd implements CommandExecutor {

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!sender.hasPermission(Permissions.COMMAND_BROADCAST))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        if (args.length == 0)
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        StringBuilder sb = new StringBuilder();
        Arrays.stream(args).toList().forEach(s -> sb.append(s).append(" "));
        String message = sb.toString();
        Bukkit.broadcastMessage(Utils.color("&4&l!!! &c&l[BROADCAST] &f" + message + " &4&l!!!"));
        return true;
    }
}
