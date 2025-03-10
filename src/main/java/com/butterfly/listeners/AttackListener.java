package com.butterfly.listeners;

import com.butterfly.managers.PlayerManager;
import com.butterfly.util.Disguise;
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
