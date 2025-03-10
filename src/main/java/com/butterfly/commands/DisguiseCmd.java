package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.PlayerManager;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DisguiseCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
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

        if (args.length == 0)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        Player target = player;
        if (args.length == 2) {
            target = Bukkit.getPlayer(args[1]);
        }

        if (target == null)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PLAYER));
            return true;
        }
        
        LivingEntity livingEntity = null;
        try
        {
            livingEntity = (LivingEntity) target.getWorld().spawnEntity(target.getLocation(), EntityType.valueOf(args[0].toUpperCase()));
            PlayerManager.setDisguise(target, livingEntity);

            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_DISGUISE_DISGUISED).replace("%target%", target.getDisplayName()).replace("%entity%", args[0].toUpperCase()));
        } catch (IllegalArgumentException error)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_DISGUISE_INVALID).replace("%entity%", args[0]));
            if (livingEntity != null)
            {
                livingEntity.remove();
            }
        }
        return true;
    }
}
