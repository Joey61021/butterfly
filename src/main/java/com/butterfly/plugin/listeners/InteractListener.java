package com.butterfly.plugin.listeners;

import com.butterfly.plugin.enums.DisguiseAbilities;
import com.butterfly.plugin.managers.PlayerManager;
import com.butterfly.plugin.utilities.Disguise;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();

        Disguise disguise = PlayerManager.getDisguise(player);
        if (disguise == null || item == null || item.getType().equals(Material.AIR) || !item.hasItemMeta() || !(event.getAction().equals(Action.LEFT_CLICK_AIR) || event.getAction().equals(Action.LEFT_CLICK_BLOCK))) {
            return;
        }

        for (DisguiseAbilities abilities : DisguiseAbilities.values()) {
            if (item.getType().equals(abilities.getItem().getType())) {
                player.sendMessage(Utils.color(abilities.getDisplay() + " &eused!"));

                if (abilities == DisguiseAbilities.CREEPER_EXPLODE) {
                    ((Creeper) disguise.getLivingEntity()).explode();
                    PlayerManager.removeDisguise(disguise);
                    return;
                }

                if (abilities == DisguiseAbilities.BLAZE_FIREBALL) {
                    SmallFireball smallFireball = (SmallFireball) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.SMALL_FIREBALL);
                    smallFireball.setVelocity(player.getLocation().getDirection().multiply(2));
                    smallFireball.setShooter(disguise.getLivingEntity());
                    return;
                }

                if (abilities == DisguiseAbilities.WITHER_SKULL) {
                    WitherSkull witherSkull = (WitherSkull) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.WITHER_SKULL);

                    witherSkull.setVelocity(player.getLocation().getDirection().multiply(2));

                    witherSkull.setYield(2.0F);
                    witherSkull.setShooter(disguise.getLivingEntity());
                    return;
                }

                if (abilities == DisguiseAbilities.GHAST_FIREBALL) {
                    Fireball fireball = (Fireball) player.getWorld().spawnEntity(player.getEyeLocation().add(0, -3, 0), EntityType.FIREBALL);

                    fireball.setVelocity(player.getLocation().getDirection().multiply(2));
                    fireball.setShooter(disguise.getLivingEntity());
                    return;
                }
                return;
            }
        }
    }
}
