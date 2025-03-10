package com.butterfly.util;

import org.bukkit.entity.Player;

public class Nick
{

    private Player player;
    private String nickname;
    private String pooledName;

    public Nick(Player player, String nickname, String pooledName)
    {
        this.player = player;
        this.nickname = nickname;
        this.pooledName = pooledName;
    }

    public Player getPlayer()
    {
        return player;
    }

    public String getNickname()
    {
        return nickname;
    }

    public String getPooledName()
    {
        return pooledName;
    }
}
