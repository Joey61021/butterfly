package com.butterfly.commands;

import com.butterfly.managers.PlayerManager;
import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DisguiseCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_DISGUISE))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            MessageManager.sendMessage(player, Message.GENERAL_INVALID_ARGS);
            return false;
        }

        Player target = player;
        if (args.length == 2) {
            target = Bukkit.getPlayer(args[1]);
        }

        if (target == null) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }
        
        LivingEntity livingEntity = null;
        try {
            livingEntity = (LivingEntity) target.getWorld().spawnEntity(target.getLocation(), EntityType.valueOf(args[0].toUpperCase()));
            PlayerManager.setDisguise(target, livingEntity);

            Player finalTarget = target;
            MessageManager.sendMessage(player,
                                        Message.CMD_DISGUISE_DISGUISED,
                                        (s) -> s.replace("%target%", finalTarget.getDisplayName())
                                                .replace("%entity%", args[0].toUpperCase()));
        } catch (IllegalArgumentException error) {
            MessageManager.sendMessage(player,
                                        Message.CMD_DISGUISE_INVALID,
                                        (s) -> s.replace("%entity%", args[0]));
            if (livingEntity != null) {
                livingEntity.remove();
            }
        }
        return false;
    }
}
