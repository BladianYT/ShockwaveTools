package me.bladian.shockwavetools;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

/**
 * Created by Bladian. Before using the code, kindly ask permission to him via the following methods.
 * <p>
 * Twitter: BladianMC
 * Discord: Bladian#6411
 * <p>
 * Thank you for reading!
 */


public class ShockwaveTool
{

    private String ID;
    private Material material;
    private String name;
    private List<String> lore;
    private int radiusLR;
    private int radiusIO;
    private int radiusUD;

    public ShockwaveTool(String ID, Material material, String name, List<String> lore, int radiusLR, int radiusIO, int radiusUD)
    {
        this.ID = ID;
        this.material = material;
        this.name = name;
        this.lore = lore;
        this.radiusLR = radiusLR;
        this.radiusIO = radiusIO;
        this.radiusUD = radiusUD;
    }

    public String getID()
    {
        return ID;
    }

    public String getName()
    {
        return name;
    }

    public int getRadiusLR()
    {
        return radiusLR;
    }

    public int getRadiusIO()
    {
        return radiusIO;
    }

    public int getRadiusUD()
    {
        return radiusUD;
    }

    public ItemStack getItemStack()
    {
        ItemStack itemStack = new ItemStack(material);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
