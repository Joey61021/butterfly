package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_FLY)))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        Player target = player;
        if (args.length == 1)
        {
            target = Bukkit.getPlayer(args[0]);
        }

        if (target == null)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PLAYER));
            return true;
        }

        Player finalTarget = target;
        if (target.getAllowFlight()) {
            target.setFlying(false);
            target.setAllowFlight(false);
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_FLY_TOGGLE_OFF).replace("%target%", finalTarget.getDisplayName()));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
            return true;
        }

        target.setAllowFlight(true);

        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_FLY_TOGGLE_ON).replace("%target%", finalTarget.getDisplayName()));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return true;
    }
}
