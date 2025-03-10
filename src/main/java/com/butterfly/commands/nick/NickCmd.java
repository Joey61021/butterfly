package com.butterfly.commands.nick;

import com.butterfly.ButterflyCore;
import com.butterfly.managers.PlayerManager;
import com.butterfly.util.globals.Messages;
import com.butterfly.util.globals.Permissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NickCmd implements CommandExecutor
{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player player))
        {
            sender.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_CONSOLE));
            return true;
        }

        if (!(player.hasPermission(Permissions.COMMAND_NICK)))
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_NO_PERMISSION));
            return true;
        }

        if (args.length == 0)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.GENERAL_INVALID_ARGS));
            return true;
        }

        if (args[0].length() > 16)
        {
            player.sendMessage(ButterflyCore.getMessages().get(Messages.COMMAND_NICK_TOO_LONG));
            return true;
        }

        PlayerManager.setNickname(player, args[0]);
        return true;
    }
}
