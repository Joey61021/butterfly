package com.butterfly.commands;

import com.butterfly.managers.PlayerManager;
import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.Disguise;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UndisguiseCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        if (!(player.hasPermission(Permissions.COMMAND_DISGUISE))) {
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

        Disguise disguise = PlayerManager.getDisguise(target);
        if (disguise == null) {
            MessageManager.sendMessage(player, Message.CMD_UNDIGUISE_NOT_DISGUISED);
            return false;
        }

        PlayerManager.removeDisguise(disguise);

        if (!player.getUniqueId().equals(target.getUniqueId())) {
            Player finalTarget = target;
            MessageManager.sendMessage(player,
                                        Message.CMD_UNDIGUISE_UNDISGUISED_OTHER,
                                        (s) -> s.replace("%target%", finalTarget.getDisplayName()));
        }
        return false;
    }
}
