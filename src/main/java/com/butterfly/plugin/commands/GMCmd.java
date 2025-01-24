package com.butterfly.plugin.commands;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.managers.GamemodeManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
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
        if (!(player.hasPermission("butterfly.gm") || player.hasPermission("butterfly.*"))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }
        
        if (args.length == 0) {
            GamemodeManager.switchGamemode(player);
            return false;
        }
        
        if (!player.hasPermission("butterfly.others")) {
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
                                            .replace("%gamemode%", ButterflyCore.config.getString("general.gamemodes." + target.getGameMode().toString().toLowerCase())));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return false;
    }
}
