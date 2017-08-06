package com.cloudcc.chopyourhead.recipes;

import com.cloudcc.chopyourhead.init.ModBlocks;
import com.cloudcc.chopyourhead.init.ModItems;
import com.google.common.collect.Maps;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by Tobias on 18.08.16.
 */
public class ModGrinderRecipes {


    private static final ModGrinderRecipes grindingBase = new ModGrinderRecipes();
    /** The list of grinding results. */
    private final Map grindingList = Maps.newHashMap();
    private final Map experienceList = Maps.newHashMap();

    public static ModGrinderRecipes instance()
    {
        return grindingBase;
    }


    public ModGrinderRecipes()
    {

        this.addGrindingRecipe(
                new ItemStack(Item.getItemFromBlock(ModBlocks.mithril_ore)),
                new ItemStack((ModItems.mithril_dust)), 0.7F);

    }

    public void addGrindingRecipe(ItemStack parItemStackIn,
                                  ItemStack parItemStackOut, float parExperience)
    {

        grindingList.put(parItemStackIn, parItemStackOut);
        experienceList.put(parItemStackOut, Float.valueOf(parExperience));

    }


    public ItemStack getGrindingResult(ItemStack parItemStack)
    {
        Iterator iterator = grindingList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack, (ItemStack)entry
                .getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean areItemStacksEqual(ItemStack parItemStack1,
                                       ItemStack parItemStack2)
    {
        return parItemStack2.getItem() == parItemStack1.getItem()
                && (parItemStack2.getMetadata() == 32767
                || parItemStack2.getMetadata() == parItemStack1
                .getMetadata());
    }

    public Map getGrindingList()
    {
        return grindingList;
    }

    public float getGrindingExperience(ItemStack parItemStack)
    {
        Iterator iterator = experienceList.entrySet().iterator();
        Map.Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Map.Entry)iterator.next();
        }
        while (!areItemStacksEqual(parItemStack,
                (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}
