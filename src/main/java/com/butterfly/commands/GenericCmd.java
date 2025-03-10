package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.GamemodeManager;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenericCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_GAMEMODE)))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        GameMode gamemode = switch (cmd.getName().toLowerCase()) {
            case "gma" -> GameMode.ADVENTURE;
            case "gmc" -> GameMode.CREATIVE;
            case "gms" -> GameMode.SURVIVAL;
            case "gmsp" -> GameMode.SPECTATOR;
            default -> throw new IllegalStateException("Unexpected value: " + cmd.getName().toLowerCase());
        };

        if (args.length == 0)
        {
            GamemodeManager.setGamemode(player, gamemode);
            return true;
        }

        if (!player.hasPermission(Permissions.COMMAND_GAMEMODE))
        {
            GamemodeManager.setGamemode(player, gamemode);
            return true;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null || !target.isOnline())
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PLAYER));
            return true;
        }

        if (target.getUniqueId().equals(player.getUniqueId()))
        {
            GamemodeManager.setGamemode(player, gamemode);
            return true;
        }

        if (target.getGameMode() == gamemode)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_ALREADY_IN_GM_OTHER).replace("%target%", target.getName()));
            return true;
        }

        GamemodeManager.setGamemode(target, gamemode);
        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_GM_OTHERS).replace("%target%", target.getName()).replace("%gamemode%", target.getGameMode().toString().toLowerCase()));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return true;
    }
}
