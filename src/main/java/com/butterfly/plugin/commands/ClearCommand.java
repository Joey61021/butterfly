package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Permissions;
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

        int count = countItems(inv);

        if (count == 0)
        {
            MessageManager.sendMessage(player, Message.CMD_CLEAR_NO_ITEMS);
            return true;
        }

        MessageManager.sendMessage(player,
                                    Message.CMD_CLEAR_CLEARED,
                                    (s) -> s.replace("%items%", String.valueOf(countItems(inv)))
                                            .replace("%player%", target.getDisplayName()));
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
