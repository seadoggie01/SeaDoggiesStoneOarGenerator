package com.seadoggie.stoneoregen;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StoneOreGen extends JavaPlugin{

    public static FileConfiguration config;

    @Override
    public void onEnable(){
        System.out.println("I was enabled!");
        // Register the event manager with the server (so that my events get called)
        getServer().getPluginManager().registerEvents(new StoneOreGenListeners(this), this);
        createConfig();
    }

    @Override
    public void onDisable(){
        System.out.println("I was disabled!");
    }


    //TODO: Credit for createConfig goes to... Someone on SpigotMC, I can't find their name! RIP
    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
