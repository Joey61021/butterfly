package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.GamemodeManager;
import com.butterfly.util.Utils;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCmd implements CommandExecutor
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
        
        if (args.length == 0)
        {
            GamemodeManager.switchGamemode(player);
            return true;
        }
        
        if (!player.hasPermission(Permissions.COMMAND_GAMEMODE))
        {
            GamemodeManager.switchGamemode(player);
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
            GamemodeManager.switchGamemode(player);
            return true;
        }
        
        GamemodeManager.switchGamemode(target);
        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_GM_OTHERS).replace("%target%", target.getName()).replace("%gamemode%", Utils.color(GamemodeManager.getGamemode(target.getGameMode()))));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return true;
    }
}
