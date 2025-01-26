package com.butterfly.plugin.listeners;

import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class AttackListener implements Listener {

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        Entity victim = event.getEntity();
        Entity attacker = event.getDamager();
        Disguise disguise = PlayerManager.getDisguise(attacker);

        if (attacker instanceof Player && disguise != null && victim == disguise.getLivingEntity()) {
            event.setCancelled(true);
        }
    }
}
