package com.butterfly.plugin.managers.message;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Message {

    GENERAL_NO_CONSOLE("general.messages.no-console"),
    GENERAL_NO_PERMISSION("general.messages.no-permission"),
    GENERAL_NO_PLAYER("general.messages.no-player"),
    GENERAL_INVALID_ARGS("general.messages.invalid-args"),
    GENERAL_ALREADY_IN_GM_SELF("general.messages.already-in-gm.self"),
    GENERAL_ALREADY_IN_GM_OTHER("general.messages.already-in-gm.other"),
    GENERAL_UNABLE_TO_BUILD("general.messages.unable-to-build"),
    GENERAL_DISGUISE_REMOVED("general.messages.disguise-removed"),
    GENERAL_BLACKLISTED("general.messages.blacklisted"),

    CMD_GM_SELF("commands.gm.self"),
    CMD_GM_OTHERS("commands.gm.other"),
    
    CMD_BUILD_TOGGLE_ON("commands.build.toggle-on"),
    CMD_BUILD_TOGGLE_OFF("commands.build.toggle-off"),

    CMD_INV_OPENING("commands.inv.opening"),

    CMD_VANISH_DISGUISED("commands.vanish.disguised"),
    CMD_VANISH_TOGGLE_ON("commands.vanish.toggle-on"),
    CMD_VANISH_TOGGLE_OFF("commands.vanish.toggle-off"),

    CMD_FLY_TOGGLE_ON("commands.fly.toggle-on"),
    CMD_FLY_TOGGLE_OFF("commands.fly.toggle-off"),

    CMD_ITEM_INVALID("commands.item.invalid"),
    CMD_ITEM_GIVEN("commands.item.given"),

    CMD_NICK_TOO_LONG("commands.nick.too-long"),
    CMD_NICK_NICKED("commands.nick.nicked"),

    CMD_UNNICK_NOT_NICKED("commands.unnick.not-nicked"),
    CMD_UNNICK_UNNICKED("commands.unnick.unnicked"),

    CMD_DISGUISE_INVALID("commands.disguise.invalid"),
    CMD_DISGUISE_DISGUISED("commands.disguise.disguised"),

    CMD_UNDIGUISE_NOT_DISGUISED("commands.undisguise.not-disguised"),
    CMD_UNDIGUISE_UNDISGUISED_OTHER("commands.undisguise.undisguised-other"),

    CMD_HEAL_HEALED_SELF("commands.heal.healed-self"),
    CMD_HEAL_HEALED_OTHER("commands.heal.healed-other");

    @Getter @NonNull
    private final String path;
}
