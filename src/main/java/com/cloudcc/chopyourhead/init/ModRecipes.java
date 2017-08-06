package com.cloudcc.chopyourhead.init;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by hi112 on 22.12.2016.
 */
public class ModRecipes {

    public static void register() {


        //ingot
        GameRegistry.addSmelting(ModBlocks.adamantit_ore, new ItemStack(ModItems.adamantit_ingot), 10);
        GameRegistry.addSmelting(ModItems.adamantit_dust, new ItemStack(ModItems.adamantit_ingot), 20);
        GameRegistry.addSmelting(ModBlocks.mithril_ore, new ItemStack(ModItems.mithril_ingot), 2);
        GameRegistry.addSmelting(ModItems.mithril_dust, new ItemStack(ModItems.mithril_ingot), 4);

        //Quartz
        GameRegistry.addSmelting(new ItemStack(ModItems.mithril_ingot,2), new ItemStack(Items.QUARTZ),  0);

        //armor
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_boots), "ANA", "ADA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_boots, 'N', Items.NETHER_STAR);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_chestplate), "ADA", "ANA", "AAA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_chestplate, 'N', Items.NETHER_STAR);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_helmet), "NNN", "AAA", "ADA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_helmet, 'N', Items.NETHER_STAR);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.adamantium_leggings), "AAA", "ADA", "ANA", 'A', ModItems.adamantit_ingot, 'D', ModItems.mithril_leggings, 'N', Items.NETHER_STAR);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_boots), "A A", "ADA", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_BOOTS);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_chestplate), "ADA", "AAA", "AAA", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_CHESTPLATE);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_helmet), "AAA", "ADA", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_HELMET);
        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_leggings), "AAA", "ADA", "A A", 'A', ModItems.mithril_ingot, 'D', Items.DIAMOND_LEGGINGS);

        GameRegistry.addShapedRecipe(new ItemStack(ModItems.mithril_gear), " M ", "MSM", " M ", 'M', ModItems.mithril_ingot, 'S', Items.STICK);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.grinder), "MFM", "GCG", "MMM", 'M', ModItems.mithril_ingot, 'F', Items.FLINT, 'G', ModItems.mithril_gear, 'C', ModBlocks.machine_casing);
        GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.machine_casing), "MMM", "M M", "MMM", 'M', ModItems.mithril_ingot);

    }
}
