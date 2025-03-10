package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_ITEM)))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        if (args.length == 0)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        int amount = args.length > 1 ? Integer.parseInt(args[1]) : 1;

        try
        {
            Material item = Material.valueOf(args[0].toUpperCase());
            player.getInventory().addItem(new ItemStack(item, amount));
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_ITEM_GIVEN).replace("%amount%", String.valueOf(amount)).replace("%item%", item.toString()));
        } catch (IllegalArgumentException error)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_ITEM_INVALID).replace("%item%", args[0]));
        }
        return true;
    }
}
