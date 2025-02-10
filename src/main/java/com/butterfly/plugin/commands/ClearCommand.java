package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class ClearCommand implements CommandExecutor
{

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
    {
        if (!(sender instanceof Player))
        {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return true;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(Permissions.COMMAND_CLEAR))
        {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return true;
        }

        Player fetch = player;
        if (args.length > 0)
        {
            fetch = Bukkit.getPlayer(args[0]);
        }

        if (fetch == null)
        {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return true;
        }

        final Player target = fetch;
        Inventory inv = target.getInventory();

        MessageManager.sendMessage(player, Message.CMD_CLEARED, (s) -> s.replace("%items%", String.valueOf(inv.getContents().length)).replace("%player%", target.getDisplayName()));
        inv.clear();
        return true;
    }
}
