package com.butterfly.plugin.managers;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Disguise;
import com.butterfly.plugin.utilities.Nick;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    public static Set<Player> vanish = new HashSet<>();
    public static Set<Nick> nicknames = new HashSet<>();
    public static Set<Disguise> disguises = new HashSet<>();

    public static void toggleVanish(Player player) {
        if (vanish.contains(player)) {
            vanish.remove(player);
            player.sendMessage(Utils.color("&fVanish has been toggled &c&lOFF&f!"));

            Nick nick = getNick(player);
            player.setPlayerListName(Utils.color(nick == null ? player.getDisplayName() : nick.getNickname()));

            for (Player players : Bukkit.getOnlinePlayers()) {
                players.showPlayer(player);
            }
            return;
        }
        vanish.add(player);
        player.sendMessage(Utils.color("&fVanish has been toggled &a&lON&f!"));

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
        Nick currentNick = getNick(player);
        if (currentNick != null) {
            MessageManager.sendMessage(player,
                                        Message.CMD_NICK_ALREADY_NICKED,
                                        (s) -> s.replace("%nick%", currentNick.getNickname()));
            return;
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
            if (disguise.getPlayer().getUniqueId() == player.getUniqueId()) {
                return disguise;
            }
        }
        return null;
    }

    public static Disguise getDisguise(Entity entity) {
        for (Disguise disguise : disguises) {
            if (disguise.getEntity() == entity) {
                return disguise;
            }
        }
        return null;
    }

    public static void setDisguise(Player player, Entity entity) {
        if (getDisguise(player) != null) {
            MessageManager.sendMessage(player, Message.CMD_DISGUISE_ALREADY_DISGUISED);
            return;
        }

        Disguise disguise = new Disguise(player, entity);
        disguises.add(disguise);

        entity.setGravity(false);
        ((LivingEntity) entity).setAI(false);
        ((LivingEntity) entity).setCollidable(false);
        player.hideEntity(ButterflyCore.instance, entity);

        MessageManager.sendMessage(player, Message.CMD_DISGUISE_DISGUISED);
    }
}
