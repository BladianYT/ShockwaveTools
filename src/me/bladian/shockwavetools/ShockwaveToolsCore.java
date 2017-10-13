package me.bladian.shockwavetools;

import org.bukkit.Material;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class ShockwaveToolsCore extends JavaPlugin
{

    //Eventually make a manager for this class?
    private List<ShockwaveTool> shockwaveTools = new ArrayList<>();

    @Override
    public void onEnable()
    {

        saveDefaultConfig();

        Configuration config = getConfig();

        for(String string : config.getStringList("tools"))
        {
            String[] split = string.split(";");
            List<String> lore = new ArrayList<>(Arrays.asList(split[3].split(":")));
            ShockwaveTool shockwaveTool = new ShockwaveTool(
                    split[0],
                    Material.getMaterial(split[1]),
                    split[2],
                    lore,
                    Integer.parseInt(split[4]),
                    Integer.parseInt(split[5]),
                    Integer.parseInt(split[6]));
            shockwaveTools.add(shockwaveTool);
        }

        getCommand("giveTool").setExecutor(new ComGiveTool(this));
        getServer().getPluginManager().registerEvents(new Event(this), this);
    }

    @Override
    public void onDisable()
    {

    }

    public List<ShockwaveTool> getShockwaveTools()
    {
        return shockwaveTools;
    }
}
