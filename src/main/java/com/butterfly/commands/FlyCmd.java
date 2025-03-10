package com.butterfly.commands;

import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_FLY))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        Player target = player;
        if (args.length == 1) {
            target = Bukkit.getPlayer(args[0]);
        }

        if (target == null) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }

        Player finalTarget = target;
        if (target.getAllowFlight()) {
            target.setFlying(false);
            target.setAllowFlight(false);
            MessageManager.sendMessage(player,
                                        Message.CMD_FLY_TOGGLE_OFF,
                                        (s) -> s.replace("%target%", finalTarget.getDisplayName()));
            player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
            return false;
        }

        target.setAllowFlight(true);

        MessageManager.sendMessage(player,
                                    Message.CMD_FLY_TOGGLE_ON,
                                    (s) -> s.replace("%target%", finalTarget.getDisplayName()));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return false;
    }
}
