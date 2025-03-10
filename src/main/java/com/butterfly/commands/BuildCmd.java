package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class BuildCmd implements CommandExecutor
{

    public static ArrayList<UUID> build = new ArrayList<>();

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!player.hasPermission(Permissions.COMMAND_BUILD))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        if (build.contains(player.getUniqueId()))
        {
            build.remove(player.getUniqueId());
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_BUILD_TOGGLE_OFF));
            return true;
        }
        build.add(player.getUniqueId());
        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_BUILD_TOGGLE_ON));
        return true;
    }
}
