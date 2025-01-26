package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import com.butterfly.plugin.utilities.Nick;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        if (PlayerManager.vanish.contains(player)) {
            PlayerManager.vanish.remove(player);
        }

        Nick nick = PlayerManager.getNick(player);
        if (nick != null) {
            PlayerManager.nicknames.remove(nick);
        }

        if (PlayerManager.activeDisguises.contains(player)) {
            PlayerManager.activeDisguises.remove(player);

            Disguise disguise = PlayerManager.getDisguise(player);
            if (disguise != null) {
                disguise.getLivingEntity().remove();
                PlayerManager.disguises.remove(disguise);
            }
        }
    }
}
