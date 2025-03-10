package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class PushCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_PUSH)))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        if (args.length < 2)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PLAYER));
            return true;
        }

        int vel;
        try
        {
            vel = Integer.parseInt(args[1]);
        } catch (Exception error)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        Vector direction = target.getLocation().getDirection().multiply(-vel).setY(0.5);
        target.setVelocity(direction);
        return true;
    }
}
