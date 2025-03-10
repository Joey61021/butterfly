package com.butterfly.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static void removePotionEffects(Player player) {
        player.getActivePotionEffects().forEach((effect) -> player.removePotionEffect(effect.getType()));
    }
}
