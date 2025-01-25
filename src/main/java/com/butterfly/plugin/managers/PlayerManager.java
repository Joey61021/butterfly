package com.butterfly.plugin.managers;

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
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    public static Set<Player> vanish = new HashSet<>();
    public static Set<Nick> nicknames = new HashSet<>();
    public static Set<Disguise> disguises = new HashSet<>();

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
            if (disguise.getPlayer().getUniqueId().equals(player.getUniqueId())) {
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

    public static void setDisguise(Player player, EntityType entityType) {
        if (getDisguise(player) != null) {
            MessageManager.sendMessage(player, Message.CMD_DISGUISE_ALREADY_DISGUISED);
            return;
        }

        Entity entity = player.getWorld().spawnEntity(player.getLocation(), entityType);

        Disguise disguise = new Disguise(player, entity);
        disguises.add(disguise);
        entity.setGravity(false);
        player.setCollidable(false);

        LivingEntity livingEntity = (LivingEntity) entity;
        livingEntity.setAI(false);
        livingEntity.setCollidable(false);
        livingEntity.setHealth(player.getHealth());
        livingEntity.setMaxHealth(player.getMaxHealth());

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = scoreboard.getTeam("nocollide");
        if (team == null) {
            team = scoreboard.registerNewTeam("nocollide");
            team.setOption(Team.Option.COLLISION_RULE, Team.OptionStatus.NEVER);
        }
        team.addEntry(player.getName());

        MessageManager.sendMessage(player, Message.CMD_DISGUISE_DISGUISED);
    }
}
