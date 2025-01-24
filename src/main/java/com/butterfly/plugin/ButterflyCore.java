package com.butterfly.plugin;

import com.butterfly.plugin.commands.*;
import com.butterfly.plugin.commands.nick.NickCmd;
import com.butterfly.plugin.commands.nick.UnnickCmd;
import com.butterfly.plugin.listeners.*;
import com.butterfly.plugin.listeners.world.BreakListener;
import com.butterfly.plugin.listeners.world.PlaceListener;
import com.butterfly.plugin.utilities.Config;
import org.bukkit.plugin.java.JavaPlugin;

public class ButterflyCore extends JavaPlugin {

    public static ButterflyCore instance;

    public static Config config;

    @Override
    public void onEnable() {
        loadConfig();
        registerCommands();
        registerListeners();
    }

    void loadConfig() {
        config = new Config(this, getDataFolder(), "config", "config.yml");
    }

    void registerCommands() {
        getCommand("gma").setExecutor(new GenericCmd());
        getCommand("gmc").setExecutor(new GenericCmd());
        getCommand("gm").setExecutor(new GMCmd());
        getCommand("gms").setExecutor(new GenericCmd());
        getCommand("gmsp").setExecutor(new GenericCmd());
        getCommand("vanish").setExecutor(new VanishCmd());
        getCommand("inv").setExecutor(new InvCmd());
        getCommand("fly").setExecutor(new FlyCmd());
        getCommand("item").setExecutor(new ItemCmd());
        getCommand("item").setExecutor(new ItemCmd());
        getCommand("nick").setExecutor(new NickCmd());
        getCommand("unnick").setExecutor(new UnnickCmd());
        getCommand("disguise").setExecutor(new DisguiseCmd());
        getCommand("undisguise").setExecutor(new UndisguiseCmd());
    }

    void registerListeners() {
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlaceListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new HealListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
    }
}
