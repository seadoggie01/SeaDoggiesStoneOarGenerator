package com.seadoggie.stoneoregen;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.Plugin;

import java.util.Set;

public class StoneOreGenListeners implements Listener {

    private Plugin myPlugin;

    public StoneOreGenListeners(Plugin iNeedThis){
        myPlugin = iNeedThis;
    }

    @EventHandler
    public void CobbleGeneration(BlockFromToEvent event){

        //If the To_block is water
        if(event.getToBlock().getLocation().getBlock().getType() == Material.WATER){
            // If the above block is lava
            if(event.getToBlock().getLocation().add(0, 1, 0).getBlock().getType() == Material.LAVA){
                //Should generate stone...
                GenerateSomething(event.getToBlock());
                // Cancel the event so other plugins don't generate stuff / Minecraft generates stone
                event.setCancelled(true);
            }
        }
    }

    // Generate Something besides stone
    //TODO: Load the config values into a class and implement that to speed things up... reading a file repeatedly is stupid
    private void GenerateSomething(Block myBlock){
        String path = "OreGenThousandths";
        int randomNumber = (int) (Math.random() * 1000);
        int total = 0;
        int count;
        boolean found = false;
        String tempCount = "";

        // Get the config file of the plugin
        ConfigurationSection config = myPlugin.getConfig();
        // Get the list of keys under 'OreGenThousandths'
        Set<String> oreArray = config.getConfigurationSection(path).getKeys(false);

        // For each item in the config
        for(String something: oreArray) {
            // If we didn't find what we want to generate yet
            if(!found){
                // Get the count
                tempCount = config.getString(path + "." + something);
                if(tempCount == null){
                    System.out.println("Error fetching: " + path + "." + something);
                }

                // get the value
                count = Integer.parseInt(tempCount.replace(" ", ""));

                // add it to the total
                total += count;

                // if the random number is less than the total
                if (randomNumber <= total) {
                    // Generate the material listed in the config
                    myBlock.setType(Material.getMaterial(something));
                    // We found it, stop checking
                    found = true;
                }
            }
        }
    }
}
