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

    public static Config messages;

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();
        registerCommands();
        registerListeners();
    }

    void loadConfig() {
        messages = new Config(this, getDataFolder(), "messages", "messages.yml");
    }

    void registerCommands() {
        getCommand("gma").setExecutor(new GenericCmd());
        getCommand("gmc").setExecutor(new GenericCmd());
        getCommand("gm").setExecutor(new GMCmd());
        getCommand("gms").setExecutor(new GenericCmd());
        getCommand("gmsp").setExecutor(new GenericCmd());
        getCommand("build").setExecutor(new BuildCmd());
        getCommand("vanish").setExecutor(new VanishCmd());
        getCommand("inv").setExecutor(new InvCmd());
        getCommand("fly").setExecutor(new FlyCmd());
        getCommand("item").setExecutor(new ItemCmd());
        getCommand("item").setExecutor(new ItemCmd());
        getCommand("nick").setExecutor(new NickCmd());
        getCommand("unnick").setExecutor(new UnnickCmd());
        getCommand("disguise").setExecutor(new DisguiseCmd());
        getCommand("undisguise").setExecutor(new UndisguiseCmd());
        getCommand("heal").setExecutor(new HealCmd());
    }

    void registerListeners() {
        getServer().getPluginManager().registerEvents(new BreakListener(), this);
        getServer().getPluginManager().registerEvents(new PlaceListener(), this);
        getServer().getPluginManager().registerEvents(new AttackListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new HealListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
    }
}
