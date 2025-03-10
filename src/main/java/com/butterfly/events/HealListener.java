package com.butterfly.events;

import com.butterfly.managers.PlayerManager;
import com.butterfly.util.Disguise;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;

public class HealListener implements Listener
{

    @EventHandler
    public void onHeal(EntityRegainHealthEvent event)
    {
        if (!(event.getEntity() instanceof Player player))
        {
            return;
        }

        Disguise disguise = PlayerManager.getDisguise(event.getEntity());
        if (disguise == null)
        {
            return;
        }

        disguise.getLivingEntity().setHealth(player.getHealth());
    }
}
