package me.bladian.shockwavetools;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class Event implements Listener
{

    private ShockwaveToolsCore core;

    public Event(ShockwaveToolsCore core)
    {
        this.core = core;
    }

    private Map<UUID, BlockFace> blockFaceMap = new HashMap<>();


    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        //Random one
        blockFaceMap.put(e.getPlayer().getUniqueId(), BlockFace.DOWN);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e)
    {
        blockFaceMap.remove(e.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e)
    {
        if(!e.isCancelled())
        {
            blockFaceMap.put(e.getPlayer().getUniqueId(), e.getBlockFace());
        }
    }

    //Hopefully this works with all of the factions versions...
    @EventHandler(priority = EventPriority.LOWEST)
    public void onBreak(BlockBreakEvent e)
    {
        Block block = e.getBlock();
        Player p = e.getPlayer();
        if(!e.isCancelled())
        {
            if(p.getItemInHand() != null)
            {
                if (p.getItemInHand().getType() != Material.AIR)
                {
                    ItemStack itemStack = p.getItemInHand();
                    if (itemStack.getItemMeta().getDisplayName() != null)
                    {
                        for (ShockwaveTool shockwaveTool : core.getShockwaveTools())
                        {
                            if(itemStack.getItemMeta().getDisplayName().equals(shockwaveTool.getName()))
                            {
                                Location loc = block.getLocation();
                                int radiusLR = shockwaveTool.getRadiusLR();
                                int radiusIO = shockwaveTool.getRadiusIO();
                                int radiusUD = shockwaveTool.getRadiusUD();
                                //This should stop the plugin from having any other dependencies
                                ItemStack pick = new ItemStack(Material.DIAMOND_PICKAXE);
                                pick.addEnchantments(p.getItemInHand().getEnchantments());
                                p.setItemInHand(pick);
                                //This is going to be ugly as hell, hopefully I find a better solution to find the block face
                                switch (blockFaceMap.get(p.getUniqueId()))
                                {
                                    case UP:
                                        for (int x = loc.getBlockX() - radiusLR; x <= loc.getBlockX() + radiusLR; x++)
                                        {
                                            for (int y = loc.getBlockY() - radiusIO; y <= loc.getBlockY(); y++)
                                            {
                                                for (int z = loc.getBlockZ() - radiusUD; z <= loc.getBlockZ() + radiusUD; z++)
                                                {
                                                    //If you want to use anything above 1.7 use the following code
                                                    //((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(x, y, z));
                                                    ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(x, y, z);
                                                }
                                            }
                                        }
                                        break;

                                    case DOWN:
                                        for (int x = loc.getBlockX() - radiusLR; x <= loc.getBlockX() + radiusLR; x++)
                                        {
                                            for (int y = loc.getBlockY() + radiusIO; y >= loc.getBlockY(); y--)
                                            {
                                                for (int z = loc.getBlockZ() - radiusUD; z <= loc.getBlockZ() + radiusUD; z++)
                                                {
                                                    //If you want to use anything above 1.7 use the following code
                                                    //((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(x, y, z));
                                                    ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(x, y, z);
                                                }
                                            }
                                        }
                                        break;
                                    case SOUTH:
                                        for (int x = loc.getBlockX() - radiusLR; x <= loc.getBlockX() + radiusLR; x++)
                                        {
                                            for (int y = loc.getBlockY() - radiusUD; y <= loc.getBlockY() + radiusUD; y++)
                                            {
                                                for (int z = loc.getBlockZ() - radiusIO; z <= loc.getBlockZ(); z++)
                                                {
                                                    //If you want to use anything above 1.7 use the following code
                                                    //((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(x, y, z));
                                                    ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(x, y, z);
                                                }
                                            }
                                        }
                                        break;

                                    case NORTH:
                                        for (int x = loc.getBlockX() - radiusLR; x <= loc.getBlockX() + radiusLR; x++)
                                        {
                                            for (int y = loc.getBlockY() - radiusUD; y <= loc.getBlockY() + radiusUD; y++)
                                            {
                                                for (int z = loc.getBlockZ() + radiusIO; z >= loc.getBlockZ(); z--)
                                                {
                                                    //If you want to use anything above 1.7 use the following code
                                                    //((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(x, y, z));
                                                    ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(x, y, z);
                                                }
                                            }
                                        }
                                        break;

                                    case EAST:
                                        for (int x = loc.getBlockX() - radiusIO; x <= loc.getBlockX(); x++)
                                        {
                                            for (int y = loc.getBlockY() - radiusUD; y <= loc.getBlockY() + radiusUD; y++)
                                            {
                                                for (int z = loc.getBlockZ() - radiusLR; z <= loc.getBlockZ() + radiusLR; z++)
                                                {
                                                    //If you want to use anything above 1.7 use the following code
                                                    //((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(x, y, z));
                                                    ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(x, y, z);
                                                }
                                            }
                                        }
                                        break;

                                    case WEST:
                                        for (int x = loc.getBlockX() + radiusIO; x >= loc.getBlockX(); x--)
                                        {
                                            for (int y = loc.getBlockY() - radiusUD; y <= loc.getBlockY() + radiusUD; y++)
                                            {
                                                for (int z = loc.getBlockZ() - radiusLR; z <= loc.getBlockZ() + radiusLR; z++)
                                                {
                                                    //If you want to use anything above 1.7 use the following code
                                                    //((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(new BlockPosition(x, y, z));
                                                    ((CraftPlayer) e.getPlayer()).getHandle().playerInteractManager.breakBlock(x, y, z);
                                                }
                                            }
                                        }
                                        break;

                                }
                                p.setItemInHand(itemStack);
                            }
                        }
                    }
                }
            }
        }
    }
}
