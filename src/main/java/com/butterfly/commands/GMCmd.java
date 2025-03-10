package com.butterfly.commands;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.GamemodeManager;
import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GMCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_GAMEMODE))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }
        
        if (args.length == 0) {
            GamemodeManager.switchGamemode(player);
            return false;
        }
        
        if (!player.hasPermission(Permissions.COMMAND_GAMEMODE)) {
            GamemodeManager.switchGamemode(player);
            return false;
        }
        
        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }
        
        if (target.getUniqueId().equals(player.getUniqueId())) {
            GamemodeManager.switchGamemode(player);
            return false;
        }
        
        GamemodeManager.switchGamemode(target);
        MessageManager.sendMessage(player,
                                    Message.CMD_GM_OTHERS,
                                    (s) -> s.replace("%target%", target.getName())
                                            .replace("%gamemode%", ButterflyCore.messages.getString("general.gamemodes." + target.getGameMode().toString().toLowerCase())));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return false;
    }
}
