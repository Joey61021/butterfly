package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.PlayerManager;
import com.butterfly.util.Disguise;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UndisguiseCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_DISGUISE)))
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

        Disguise disguise = PlayerManager.getDisguise(target);
        if (disguise == null)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_UNDISGUISE_NOT_DISGUISED));
            return true;
        }

        PlayerManager.removeDisguise(disguise);

        if (!player.getUniqueId().equals(target.getUniqueId()))
        {
            Player finalTarget = target;
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_UNDISGUISE_UNDISGUISED_OTHER).replace("%target%", finalTarget.getDisplayName()));
        }
        return true;
    }
}
