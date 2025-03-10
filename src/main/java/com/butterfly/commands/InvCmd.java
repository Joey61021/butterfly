package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.InventoryManager;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_INVENTORY)))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        if (args.length == 0)
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

        InventoryManager.display(player, target);
        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_INV_OPENING).replace("%player%", target.getDisplayName()));
        return true;
    }
}
