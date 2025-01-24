package com.butterfly.plugin.utilities;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

public class Nick {

    @Getter @Setter private Player player;
    @Getter @Setter private String nickname;
    @Getter @Setter private String pooledName;

    public Nick(Player player, String nickname, String pooledName) {
        this.player = player;
        this.nickname = nickname;
        this.pooledName = pooledName;
    }
}
