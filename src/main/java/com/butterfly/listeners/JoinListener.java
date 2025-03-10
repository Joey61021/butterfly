package com.butterfly.listeners;

import com.butterfly.managers.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setMaxHealth(20);

        for (Player players : Bukkit.getOnlinePlayers()) {
            if (PlayerManager.vanish.contains(players.getUniqueId()) || PlayerManager.activeDisguises.contains(players.getUniqueId())) {
                player.hidePlayer(players);
            }
        }
    }
}
