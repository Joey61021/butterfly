package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UndisguiseCmd implements CommandExecutor {

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

        Disguise disguise = PlayerManager.getDisguise(player);
        if (disguise == null) {
            MessageManager.sendMessage(player, Message.CMD_UNDIGUISE_NOT_DISGUISED);
            return false;
        }

        PlayerManager.removeDisguise(disguise);
        return false;
    }
}
