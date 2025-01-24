package com.butterfly.plugin.managers;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.managers.message.Message;
import com.butterfly.plugin.managers.message.MessageManager;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GamemodeManager {

    public static void setGamemode(Player player, GameMode gamemode) {
        if (player.getGameMode() == gamemode) {
            MessageManager.sendMessage(player, Message.GENERAL_ALREADY_IN_GM_SELF);
            return;
        }
        player.setGameMode(gamemode);
        MessageManager.sendMessage(player,
                                    Message.CMD_GM_SELF,
                                    (s) -> s.replace("%gamemode%", ButterflyCore.config.getString("general.gamemodes." + player.getGameMode().toString().toLowerCase())));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    }

    public static void switchGamemode(Player player) {
        switch (player.getGameMode()) {
            case SURVIVAL:
            case ADVENTURE:
                player.setGameMode(GameMode.CREATIVE);
                break;
            default:
                player.setGameMode(GameMode.SURVIVAL);
                break;
        }
        MessageManager.sendMessage(player,
                                    Message.CMD_GM_SELF,
                                    (s) -> s.replace("%gamemode%", ButterflyCore.config.getString("general.gamemodes." + player.getGameMode().toString().toLowerCase())));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    }
}
