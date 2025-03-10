package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class SpawnMobCommand implements CommandExecutor
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
        if (args.length == 0)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        World world = player.getWorld();
        Location location = player.getTargetBlockExact(50).getLocation().add(0, 1, 0);
        EntityType entityType;

        try
        {
            entityType = EntityType.valueOf(args[0]);
        } catch (IllegalArgumentException ignored)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_SPAWNMOB_INVALID_MOB));
            return true;
        }

        int amount = 1;
        if (args.length > 1)
        {
            try
            {
                amount = Integer.parseInt(args[1]);
            } catch (NumberFormatException ignored)
            {
                player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
                return true;
            }
        }

        amount = amount > 50 || amount < 1 ? 1 : amount;
        for (int i = 0; i < amount; i++)
        {
            world.spawnEntity(location, entityType);
        }
        return true;
    }
}
