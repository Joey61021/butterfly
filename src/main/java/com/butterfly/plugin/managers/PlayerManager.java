package com.butterfly.plugin.managers;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.enums.DisguiseAbilities;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Disguise;
import com.butterfly.plugin.utilities.Nick;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class PlayerManager {

    public static Set<Player> vanish = new HashSet<>();
    public static Set<Nick> nicknames = new HashSet<>();
    public static Set<Disguise> disguises = new HashSet<>();
    public static Set<Player> activeDisguises = new HashSet<>();

    public static void toggleVanish(Player player) {
        if (vanish.contains(player)) {
            Disguise disguise = getDisguise(player);
            if (disguise != null) {
                MessageManager.sendMessage(player, Message.CMD_VANISH_DISGUISED);
                return;
            }

            vanish.remove(player);
            MessageManager.sendMessage(player, Message.CMD_VANISH_TOGGLE_OFF);

            Nick nick = getNick(player);
            player.setPlayerListName(Utils.color(nick == null ? player.getDisplayName() : nick.getNickname()));

            for (Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
            return;
        }
        vanish.add(player);
        MessageManager.sendMessage(player, Message.CMD_VANISH_TOGGLE_ON);

        player.setPlayerListName(Utils.color("&7[V] " + player.getName()));

        for (Player players : Bukkit.getOnlinePlayers()) {
            if (!players.hasPermission("butter.vanish")) {
                players.hidePlayer(player);
            }
        }
    }

    public static Nick getNick(Player player) {
        for (Nick nicks : nicknames) {
            if (nicks.getPlayer().getUniqueId() == player.getUniqueId()) {
                return nicks;
            }
        }
        return null;
    }

    public static void setNickname(Player player, String nickname) {
        Nick fetched = getNick(player);
        if (fetched != null) {
            nicknames.remove(fetched);
        }

        Nick nick = new Nick(player, nickname, player.getDisplayName());
        nicknames.add(nick);

        player.setDisplayName(Utils.color(nickname));

        if (!vanish.contains(player)) {
            player.setPlayerListName(Utils.color(nickname));
        }

        MessageManager.sendMessage(player,
                                    Message.CMD_NICK_NICKED,
                                    (s) -> s.replace("%nick%", nickname));
    }

    public static void removeNickname(Player player) {
        Nick nick = getNick(player);
        if (nick == null) {
            MessageManager.sendMessage(player, Message.CMD_UNNICK_NOT_NICKED);
            return;
        }

        player.setDisplayName(Utils.color(nick.getPooledName()));
        player.setPlayerListName(Utils.color((vanish.contains(player) ? "&7[V] " + player.getName() : nick.getPooledName())));
        nicknames.remove(nick);

        MessageManager.sendMessage(player, Message.CMD_UNNICK_UNNICKED);
    }

    public static Disguise getDisguise(Player player) {
        for (Disguise disguise : disguises) {
            if (disguise.getPlayer() == player) {
                return disguise;
            }
        }
        return null;
    }

    public static Disguise getDisguise(Entity entity) {
        for (Disguise disguise : disguises) {
            if (disguise.getLivingEntity() == entity) {
                return disguise;
            }
        }
        return null;
    }

    public static void removeDisguise(Disguise disguise) {
        Player player = disguise.getPlayer();
        disguise.getLivingEntity().remove();

        disguises.remove(disguise);
        activeDisguises.remove(player);

        player.setMaxHealth(20);
        player.setHealth(player.getMaxHealth());
        player.getInventory().clear();

        MessageManager.sendMessage(player, Message.GENERAL_DISGUISE_REMOVED);

        if (!vanish.contains(player)) {
            for (Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
        }
    }

    public static void setDisguise(Player player, LivingEntity livingEntity) {
        Disguise fetchedDisguise = getDisguise(player);
        if (fetchedDisguise != null) {
            disguises.remove(fetchedDisguise);
            fetchedDisguise.getLivingEntity().remove();
        }

        Disguise disguise = new Disguise(player, livingEntity);
        activeDisguises.add(player);
        disguises.add(disguise);

        livingEntity.setGravity(false);
        livingEntity.setAI(false);
        livingEntity.setCollidable(false);

        player.setMaxHealth(livingEntity.getMaxHealth());
        player.setHealth(livingEntity.getHealth());
        player.hideEntity(ButterflyCore.instance, livingEntity);
        player.setFoodLevel(20);
        player.getInventory().clear();

        for (DisguiseAbilities abilities : DisguiseAbilities.values()) {
            if (abilities.getEntityType().equals(livingEntity.getType())) {
                ItemStack item = abilities.getItem();
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(Utils.color(abilities.getDisplay()));
                item.setItemMeta(meta);

                player.getInventory().addItem(item);
            }
        }

        for (Player players : Bukkit.getOnlinePlayers()) {
            players.hidePlayer(player);
        }
    }
}
