package com.butterfly.plugin.listeners;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.commands.StaffChatCmd;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Utils;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (StaffChatCmd.staffChat.contains(player.getUniqueId())) {
            event.setCancelled(true);
            for (Player players : Bukkit.getOnlinePlayers()) {
                if (players.hasPermission("butterfly.admin")) {
                    players.sendMessage(Utils.color("&c&l[STAFF] &f" + player.getDisplayName() + ": " + message));
                }
            }
            return;
        }

        String[] text = { ChatColor.stripColor(message) };

        for (String blacklist : ButterflyCore.blacklisted) {
            if (!text[0].toLowerCase().contains(blacklist.toLowerCase())) {
                continue;
            }
            for (String args : text[0].split(" ")) {
                if (args.equalsIgnoreCase(blacklist)) {
                    event.setCancelled(true);
                    MessageManager.sendMessage(player, Message.GENERAL_BLACKLISTED);
                    return;
                }
            }
        }
    }
}
