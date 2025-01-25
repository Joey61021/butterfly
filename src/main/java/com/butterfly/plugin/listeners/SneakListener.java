package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

public class SneakListener implements Listener {

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();

        Disguise disguise = PlayerManager.getDisguise(player);
        if (disguise == null) {
            return;
        }

        if (disguise.getEntity().getType().equals(EntityType.CREEPER)) {
            disguise.getEntity().remove();
            PlayerManager.disguises.remove(disguise);
            player.getWorld().createExplosion(player.getLocation(), 3);
        }
    }
}
