package com.butterfly.plugin.utilities;

import org.bukkit.ChatColor;

public class Utils {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
