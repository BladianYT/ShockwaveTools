package me.bladian.shockwavetools;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class ComGiveTool implements CommandExecutor
{

    private ShockwaveToolsCore core;

    public ComGiveTool(ShockwaveToolsCore core)
    {
        this.core = core;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
    {
        if(command.getName().equalsIgnoreCase("giveTool"))
        {
            if(!commandSender.hasPermission("permission.shockwave"))
            {
                commandSender.sendMessage("§cYou don't have permission");
                return true;
            }
            if(strings.length < 2)
            {
                commandSender.sendMessage("§c/giveTool <player> <ID>");
                return true;
            }
            Player t = Bukkit.getPlayer(strings[0]);
            if(t == null)
            {
                commandSender.sendMessage("§cPlayer isn't online");
                return true;
            }
            if(getShockwaveTool(strings[1]) == null)
            {
                commandSender.sendMessage("§cTool ID doesn't exist");
                return true;
            }
            t.getInventory().addItem(getShockwaveTool(strings[1]).getItemStack());
        }
        return false;
    }

    //This should be moved to a manager
    private ShockwaveTool getShockwaveTool(String string)
    {
        for(ShockwaveTool shockwaveTool : core.getShockwaveTools())
        {
            if(shockwaveTool.getID().equalsIgnoreCase(string))
            {
                return shockwaveTool;
            }
        }
        return null;
    }
}
