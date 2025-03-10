package com.butterfly.events;

import com.butterfly.managers.PlayerManager;
import com.butterfly.util.Disguise;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class MoveListener implements Listener
{

    @EventHandler
    public void onMove(PlayerMoveEvent event)
    {
        Player player = event.getPlayer();

        Disguise disguise = PlayerManager.getDisguise(player);
        if (disguise == null || disguise.getLivingEntity() == null)
        {
            return;
        }

        disguise.getLivingEntity().teleport(player);
    }
}
