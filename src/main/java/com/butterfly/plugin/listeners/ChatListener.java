package com.butterfly.plugin.listeners;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String[] text = {ChatColor.stripColor(event.getMessage()) };

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
