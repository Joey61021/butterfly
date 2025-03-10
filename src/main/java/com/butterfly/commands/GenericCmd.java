package com.butterfly.commands;

import com.butterfly.managers.GamemodeManager;
import com.butterfly.managers.message.Message;
import com.butterfly.managers.message.MessageManager;
import com.butterfly.util.globals.Permissions;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GenericCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            MessageManager.sendMessage(sender, Message.GENERAL_NO_CONSOLE);
            return false;
        }

        Player player = (Player) sender;
        if (!(player.hasPermission(Permissions.COMMAND_GAMEMODE))) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PERMISSION);
            return false;
        }

        GameMode gamemode;
        switch (cmd.getName().toLowerCase()) {
            case "gma": gamemode = GameMode.ADVENTURE; break;
            case "gmc": gamemode = GameMode.CREATIVE; break;
            case "gms": gamemode = GameMode.SURVIVAL; break;
            case "gmsp": gamemode = GameMode.SPECTATOR; break;
            default:
                throw new IllegalStateException("Unexpected value: " + cmd.getName().toLowerCase());
        }

        if (args.length == 0) {
            GamemodeManager.setGamemode(player, gamemode);
            return false;
        }

        if (!player.hasPermission(Permissions.COMMAND_GAMEMODE)) {
            GamemodeManager.setGamemode(player, gamemode);
            return false;
        }

        Player target = Bukkit.getServer().getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            MessageManager.sendMessage(player, Message.GENERAL_NO_PLAYER);
            return false;
        }

        if (target.getUniqueId().equals(player.getUniqueId())) {
            GamemodeManager.setGamemode(player, gamemode);
            return false;
        }

        if (target.getGameMode() == gamemode) {
            MessageManager.sendMessage(player,
                                        Message.GENERAL_ALREADY_IN_GM_OTHER,
                                        (s) -> s.replace("%target%", target.getName()));
            return false;
        }

        GamemodeManager.setGamemode(target, gamemode);
        MessageManager.sendMessage(player,
                                    Message.CMD_GM_OTHERS,
                                    (s) -> s.replace("%target%", target.getName())
                                            .replace("%gamemode%", target.getGameMode().toString().toLowerCase()));
        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0F, 1.0F);
        return false;
    }
}
