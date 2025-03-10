package com.butterfly;

import com.butterfly.commands.*;
import com.butterfly.commands.nick.NickCmd;
import com.butterfly.commands.nick.UnnickCmd;
import com.butterfly.config.Config;
import com.butterfly.events.*;
import com.butterfly.events.inventory.InventoryClickListener;
import com.butterfly.events.inventory.InventoryCloseListener;
import com.butterfly.util.globals.Messages;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;

public class ButterflyCore extends JavaPlugin
{

    private static Config messages;
    private static Config blacklist;

    private static HashSet<String> blacklisted = new HashSet<>();

    @Override
    public void onEnable()
    {
        messages = new Config(this, Messages.class, "messages.yml");
        blacklist = new Config(this, Object.class, "kits.yml");

        registerCommands();
        registerListeners();

        blacklisted.addAll(blacklist.getStringList("words"));

        System.out.println("[BUTTERFLY] World UID : " + Bukkit.getWorld("world").getUID());
    }

    void registerCommands()
    {
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
        getCommand("broadcast").setExecutor(new BroadcastCmd());
        getCommand("clear").setExecutor(new ClearCommand());
        getCommand("spawnmob").setExecutor(new SpawnMobCommand());
    }

    void registerListeners()
    {
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
        getServer().getPluginManager().registerEvents(new QuitListener(), this);
        getServer().getPluginManager().registerEvents(new WorldListener(), this);
    }

    public static Config getMessages()
    {
        return messages;
    }

    public static HashSet<String> getBlacklisted()
    {
        return blacklisted;
    }
}
