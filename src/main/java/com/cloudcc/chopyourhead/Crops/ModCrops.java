package com.cloudcc.chopyourhead.Crops;


import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 28.03.2017.
 */
public class ModCrops {

    public static Block Corn_plant;

    public static void init(){

        Corn_plant = new BlockCornPlant();




    }

    public static void registerRenders(){

        registerRender(Corn_plant);

    }


    private static void registerRender(Block block){
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block),0,new ModelResourceLocation(block.getRegistryName(), "inventory"));
    }


}
