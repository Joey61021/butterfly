package com.butterfly.managers.message;

import com.butterfly.ButterflyCore;
import com.butterfly.util.Utils;
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
        if (ButterflyCore.messages.isString(path))
            receiver.sendMessage(Utils.color(replacementFunction.apply(ButterflyCore.messages.getString(path))));
        else if (ButterflyCore.messages.isList(path)) {
            String joinedMessage = String.join("\n", ButterflyCore.messages.getStringList(path));
            receiver.sendMessage(Utils.color(replacementFunction.apply(joinedMessage)));
        }
        else
            throw new IllegalArgumentException("Path \"" + path + "\" is not a string or list of strings in the messages.yml");
    }
}
