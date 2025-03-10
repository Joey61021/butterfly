package com.butterfly.commands.nick;

import com.butterfly.managers.PlayerManager;
import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnnickCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_NICK))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        PlayerManager.removeNickname(player);
        return false;
    }
}
