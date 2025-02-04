package com.butterfly.plugin.commands;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_VANISH))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        PlayerManager.toggleVanish(player);
        return false;
    }
}
