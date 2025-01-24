package com.butterfly.plugin.listeners.world;

import com.butterfly.plugin.commands.BuildCmd;
import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class PlaceListener implements Listener {

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();

        if (!BuildCmd.build.contains(player) && (player.getGameMode() == GameMode.CREATIVE || PlayerManager.vanish.contains(player))) {
            MessageManager.sendMessage(player, Message.GENERAL_UNABLE_TO_BUILD);
            event.setCancelled(true);
        }
    }
}
