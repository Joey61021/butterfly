package com.butterfly.plugin;

import com.butterfly.plugin.commands.*;
import com.butterfly.plugin.commands.nick.NickCmd;
import com.butterfly.plugin.commands.nick.UnnickCmd;
import com.butterfly.plugin.listeners.*;
import com.butterfly.plugin.listeners.inventory.InventoryClickListener;
import com.butterfly.plugin.listeners.inventory.InventoryCloseListener;
import com.butterfly.plugin.utilities.Config;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class ButterflyCore extends JavaPlugin {

    public static ButterflyCore instance;

    public static Config messages;
    public static Config blacklist;

    public static HashSet<String> blacklisted = new HashSet<>();

    @Override
    public void onEnable() {
        instance = this;

        loadConfig();
        registerCommands();
        registerListeners();

        blacklisted.addAll(blacklist.getStringList("words"));
    }

    void loadConfig() {
        messages = new Config(this, getDataFolder(), "messages", "messages.yml");
        blacklist = new Config(this, getDataFolder(), "blacklist", "blacklist.yml");
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
        getCommand("staffchat").setExecutor(new StaffChatCmd());
        getCommand("push").setExecutor(new PushCmd());
        getCommand("boomstick").setExecutor(new BoomStickCommand());
        getCommand("broadcast").setExecutor(new BroadcastCmd());
        getCommand("clear").setExecutor(new ClearCommand());
    }

    void registerListeners() {
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
        getServer().getPluginManager().registerEvents(new AttackListener(), this);
        getServer().getPluginManager().registerEvents(new ChatListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new DropListener(), this);
        getServer().getPluginManager().registerEvents(new FoodListener(), this);
        getServer().getPluginManager().registerEvents(new HealListener(), this);
        getServer().getPluginManager().registerEvents(new InteractListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new MoveListener(), this);
        getServer().getPluginManager().registerEvents(new PickupListener(), this);
        getServer().getPluginManager().registerEvents(new ProjectileListener(), this);
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
    }
}
