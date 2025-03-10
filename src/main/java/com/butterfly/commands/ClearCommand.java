package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ClearCommand implements CommandExecutor
{

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!player.hasPermission(Permissions.COMMAND_CLEAR))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        Player fetch = player;
        if (args.length > 0)
        {
            fetch = Bukkit.getPlayer(args[0]);
        }

        if (fetch == null)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PLAYER));
            return true;
        }

        final Player target = fetch;
        Inventory inv = target.getInventory();

        int count = countItems(inv);

        if (count == 0)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_CLEAR_NO_ITEMS));
            return true;
        }

        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_CLEAR_CLEARED).replace("%items%", String.valueOf(countItems(inv))).replace("%player%", target.getDisplayName()));
        inv.clear();
        return true;
    }

    public static int countItems(Inventory inv)
    {
        int i = 0;

        for (ItemStack item : inv.getContents())
        {
            if (item == null || item.getType() == Material.AIR)
            {
                continue;
            }

            i += item.getAmount();
        }

        return i;
    }
}
