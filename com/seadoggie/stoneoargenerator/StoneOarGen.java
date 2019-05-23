package com.seadoggie.stoneoargenerator;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class StoneOarGen extends JavaPlugin{

    public static FileConfiguration config;

    @Override
    public void onEnable(){
        // Register the event manager with the server (so that my events get called)
        getServer().getPluginManager().registerEvents(new StoneOarGenListeners(this), this);
        createConfig();
    }

    @Override
    public void onDisable(){
        // Don't need to do anything here... that I know of
    }


    //Credit for createConfig goes to someone on SpigotMC, but many people have used it and finding the original author seems pointless
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
