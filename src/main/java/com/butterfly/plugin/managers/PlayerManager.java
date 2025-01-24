package com.butterfly.plugin.managers;

import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import com.butterfly.plugin.utilities.Nick;
import com.butterfly.plugin.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class PlayerManager {

    public static Set<Player> vanish = new HashSet<>();
    public static Set<Nick> nicknames = new HashSet<>();

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
        player.setPlayerListName(Utils.color((vanish.contains(player) ? "&7[V]" + player.getName() : nick.getPooledName())));
        nicknames.remove(nick);

        MessageManager.sendMessage(player, Message.CMD_UNNICK_UNNICKED);
    }
}
