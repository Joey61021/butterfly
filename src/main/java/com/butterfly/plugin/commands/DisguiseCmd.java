package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Disguise;
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
        if (!(player.hasPermission("butterfly.item"))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (args.length == 0) {
            MessageManager.sendMessage(player, Message.GENERAL_INVALID_ARGS);
            return false;
        }

        try {
            LivingEntity livingEntity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.valueOf(args[0].toUpperCase()));
            PlayerManager.setDisguise(player, livingEntity);
        } catch (IllegalArgumentException error) {
            MessageManager.sendMessage(player,
                                        Message.CMD_DISGUISE_INVALID,
                                        (s) -> s.replace("%entity%", args[0]));
        }
        return false;
    }
}
