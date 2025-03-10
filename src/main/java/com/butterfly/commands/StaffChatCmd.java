package com.butterfly.commands;

import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class StaffChatCmd implements CommandExecutor {

    public static ArrayList<UUID> staffChat = new ArrayList<>();

    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission(Permissions.COMMAND_STAFF_CHAT)) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        if (staffChat.contains(player.getUniqueId())) {
            staffChat.remove(player.getUniqueId());
            MessageManager.sendMessage(player, Message.CMD_STAFF_CHAT_TOGGLE_OFF);
            return false;
        }

        staffChat.add(player.getUniqueId());
        MessageManager.sendMessage(player, Message.CMD_STAFF_CHAT_TOGGLE_ON);
        return false;
    }
}
