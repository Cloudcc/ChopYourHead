package com.cloudcc.chopyourhead.proxy;

import com.cloudcc.chopyourhead.Crops.ModCrops;
import com.cloudcc.chopyourhead.init.ModBlocks;
import com.cloudcc.chopyourhead.init.ModItems;
import com.cloudcc.chopyourhead.init.ModRecipes;
import com.cloudcc.chopyourhead.world.WorldGen;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 09.11.2016.
 */
public class clientProxy implements commonProxy {


    @Override
    public void preInit() {
        ModCrops.init();

        ModItems.init();
        ModItems.register();
        ModBlocks.init();
        ModBlocks.register();
    }

    @Override
    public void init() {

        ModCrops.registerRenders();

        ModBlocks.registerRenders();
        ModItems.registerRenders();
        GameRegistry.registerWorldGenerator(new WorldGen(), 0);


    }

    @Override
    public void postInit(){
        ModRecipes.register();
    }
}
