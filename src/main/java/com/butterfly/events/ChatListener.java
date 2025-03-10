package com.butterfly.events;

import com.butterfly.ButterflyCore;
import com.butterfly.commands.StaffChatCmd;
import com.butterfly.util.Utils;
import com.butterfly.util.globals.Messages;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener
{

    @EventHandler
    public void onChat(PlayerChatEvent event) {
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (StaffChatCmd.staffChat.contains(player.getUniqueId()))
        {
            event.setCancelled(true);
            for (Player players : Bukkit.getOnlinePlayers())
            {
                if (players.hasPermission("butterfly.admin"))
                {
                    players.sendMessage(Utils.color("&c&l[STAFF] &f" + player.getDisplayName() + ": " + message));
                }
            }
            return;
        }

        String[] text = { ChatColor.stripColor(message) };

        for (String blacklist : ButterflyCore.getBlacklisted())
        {
            if (!text[0].toLowerCase().contains(blacklist.toLowerCase()))
            {
                continue;
            }
            for (String args : text[0].split(" "))
            {
                if (args.equalsIgnoreCase(blacklist))
                {
                    event.setCancelled(true);
                    player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_BLACKLISTED));
                    return;
                }
            }
        }
    }
}
