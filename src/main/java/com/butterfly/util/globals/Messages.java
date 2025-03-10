package com.butterfly.util.globals;

import com.butterfly.config.keys.TextConfigKey;

import static com.butterfly.config.DataSupplier.textKey;

public final class Messages
{
    private Messages() {
    }
    public static final TextConfigKey GENERAL_NO_CONSOLE = textKey("general.messages.no_console");
    public static final TextConfigKey GENERAL_NO_PERMISSION = textKey("general.messages.no_permission");
    public static final TextConfigKey GENERAL_NO_PLAYER = textKey("general.messages.no_player");
    public static final TextConfigKey GENERAL_INVALID_ARGS = textKey("general.messages.invalid_args");
    public static final TextConfigKey GENERAL_ALREADY_IN_GM_SELF = textKey("general.messages.already_in_gm.self");
    public static final TextConfigKey GENERAL_ALREADY_IN_GM_OTHER = textKey("general.messages.already_in_gm.other");
    public static final TextConfigKey GENERAL_UNABLE_TO_BUILD = textKey("general.messages.unable_to_build");
    public static final TextConfigKey GENERAL_DISGUISE_REMOVED = textKey("general.messages.disguise_removed");
    public static final TextConfigKey GENERAL_BLACKLISTED = textKey("general.messages.blacklisted");

    public static final TextConfigKey COMMAND_GM_SELF = textKey("commands.gm.self");
    public static final TextConfigKey COMMAND_GM_OTHERS = textKey("commands.gm.other");

    public static final TextConfigKey COMMAND_BUILD_TOGGLE_ON = textKey("commands.build.toggle_on");
    public static final TextConfigKey COMMAND_BUILD_TOGGLE_OFF = textKey("commands.build.toggle_off");

    public static final TextConfigKey COMMAND_INV_OPENING = textKey("commands.inv.opening");

    public static final TextConfigKey COMMAND_VANISH_DISGUISED = textKey("commands.vanish.disguised");
    public static final TextConfigKey COMMAND_VANISH_TOGGLE_ON = textKey("commands.vanish.toggle_on");
    public static final TextConfigKey COMMAND_VANISH_TOGGLE_OFF = textKey("commands.vanish.toggle_off");

    public static final TextConfigKey COMMAND_STAFF_CHAT_TOGGLE_ON = textKey("commands.staff_chat.toggle_on");
    public static final TextConfigKey COMMAND_STAFF_CHAT_TOGGLE_OFF = textKey("commands.staff_chat.toggle_off");

    public static final TextConfigKey COMMAND_FLY_TOGGLE_ON = textKey("commands.fly.toggle_on");
    public static final TextConfigKey COMMAND_FLY_TOGGLE_OFF = textKey("commands.fly.toggle_off");

    public static final TextConfigKey COMMAND_ITEM_INVALID = textKey("commands.item.invalid");
    public static final TextConfigKey COMMAND_ITEM_GIVEN = textKey("commands.item.given");

    public static final TextConfigKey COMMAND_NICK_TOO_LONG = textKey("commands.nick.too_long");
    public static final TextConfigKey COMMAND_NICK_NICKED = textKey("commands.nick.nicked");

    public static final TextConfigKey COMMAND_UNNICK_NOT_NICKED = textKey("commands.unnick.not_nicked");
    public static final TextConfigKey COMMAND_UNNICK_UNNICKED = textKey("commands.unnick.unnicked");

    public static final TextConfigKey COMMAND_DISGUISE_INVALID = textKey("commands.disguise.invalid");
    public static final TextConfigKey COMMAND_DISGUISE_DISGUISED = textKey("commands.disguise.disguised");

    public static final TextConfigKey COMMAND_UNDISGUISE_NOT_DISGUISED = textKey("commands.undisguise.not_disguised");
    public static final TextConfigKey COMMAND_UNDISGUISE_UNDISGUISED_OTHER = textKey("commands.undisguise.undisguised_other");

    public static final TextConfigKey COMMAND_HEAL_HEALED_SELF = textKey("commands.heal.healed_self");
    public static final TextConfigKey COMMAND_HEAL_HEALED_OTHER = textKey("commands.heal.healed_other");

    public static final TextConfigKey COMMAND_CLEAR_NO_ITEMS = textKey("commands.clear.no_items");
    public static final TextConfigKey COMMAND_CLEAR_CLEARED = textKey("commands.clear.cleared");

    public static final TextConfigKey COMMAND_SPAWNMOB_INVALID_MOB = textKey("commands.spawnmob.invalid_mob");
}
