package com.cloudcc.chopyourhead.init;

import com.cloudcc.chopyourhead.Crops.BlockCustomMushroom;
import com.cloudcc.chopyourhead.block.BlockGrinder;
import com.cloudcc.chopyourhead.block.BlockMachineCasing;
import com.cloudcc.chopyourhead.block.Ore.BlockAdamantitOre;
import com.cloudcc.chopyourhead.block.Ore.BlockMithrilOre;
import com.cloudcc.chopyourhead.tile.TEGrinder;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 09.11.2016.
 */
public class ModBlocks {

    public static Block grinder;

    public static Block machine_casing;

    public static Block basecrop;

    public static Block customMushroom;

    //ore
    public static Block adamantit_ore;
    public static Block mithril_ore;

    public static void init(){

        customMushroom = new BlockCustomMushroom();


        machine_casing = new BlockMachineCasing();
        grinder = new BlockGrinder(true, "grinder");
        GameRegistry.registerTileEntity(TEGrinder.class, "tile_entity_grinder");

        //ore
        adamantit_ore = new BlockAdamantitOre();
        mithril_ore = new BlockMithrilOre();
    }

    public static void register(){

        registerBlock(customMushroom);
       // registerBlock(basecrop);
        registerBlock(machine_casing);
        registerBlock(grinder);


        //ore
        registerBlock(mithril_ore);
        registerBlock(adamantit_ore);
    }

    private static void registerBlock(Block block){
        GameRegistry.register(block);
        ItemBlock item = new ItemBlock(block);
        item.setRegistryName(block.getRegistryName());
        GameRegistry.register(item);
    }


    public static void registerRenders(){
        registerRender(grinder);
        registerRender(machine_casing);

      //  registerRender(basecrop);

        //ore
        registerRender(mithril_ore);
        registerRender(adamantit_ore);
    }

    private static void registerRender(Block block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),0,new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }
}


