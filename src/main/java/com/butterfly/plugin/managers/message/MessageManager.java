package com.butterfly.plugin.managers.message;

import com.butterfly.plugin.ButterflyCore;
import com.butterfly.plugin.utilities.Utils;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;

import java.util.function.Function;

@RequiredArgsConstructor
public class MessageManager {

    public static void sendMessage(CommandSender receiver, Message message) {
        sendMessage(receiver, message, Function.identity());
    }

    public static void sendMessage(CommandSender receiver, Message message, Function<String, String> replacementFunction) {
        String path = message.getPath();
        if (ButterflyCore.config.isString(path))
            receiver.sendMessage(Utils.color(replacementFunction.apply(ButterflyCore.config.getString(path))));
        else if (ButterflyCore.config.isList(path)) {
            String joinedMessage = String.join("\n", ButterflyCore.config.getStringList(path));
            receiver.sendMessage(Utils.color(replacementFunction.apply(joinedMessage)));
        }
        else
            throw new IllegalArgumentException("Path \"" + path + "\" is not a string or list of strings in the config.yml");
    }
}