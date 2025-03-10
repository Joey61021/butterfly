package com.butterfly.managers;

import com.butterfly.ButterflyCore;
import com.butterfly.util.Utils;
import com.butterfly.util.globals.Messages;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GamemodeManager
{

    public static void setGamemode(Player player, GameMode gamemode)
    {
        if (player.getGameMode() == gamemode)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_ALREADY_IN_GM_SELF));
            return;
        }

        player.setGameMode(gamemode);
        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_GM_SELF).replace("%gamemode%", Utils.color(getGamemode(player.getGameMode()))));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    }

    public static void switchGamemode(Player player)
    {
        switch (player.getGameMode())
        {
            case SURVIVAL:
            case ADVENTURE:
                player.setGameMode(GameMode.CREATIVE);
                break;
            default:
                player.setGameMode(GameMode.SURVIVAL);
                break;
        }

        player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_GM_SELF).replace("%gamemode%", Utils.color(getGamemode(player.getGameMode()))));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
    }

    public static String getGamemode(GameMode gamemode)
    {
        return switch (gamemode)
        {
            case SURVIVAL -> "&aSurvival";
            case CREATIVE -> "&cCreative";
            case ADVENTURE -> "&eAdventure";
            case SPECTATOR -> "&dSpectator";
        };
    }
}
