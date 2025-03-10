package com.butterfly.events;

import com.butterfly.managers.PlayerManager;
import com.butterfly.util.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event)
    {
        Entity attacker = event.getDamager();
        Disguise disguise = PlayerManager.getDisguise(attacker);

        if (attacker instanceof Player && disguise != null && event.getEntity() == disguise.getLivingEntity())
        {
            event.setCancelled(true);
        }
    }
}
