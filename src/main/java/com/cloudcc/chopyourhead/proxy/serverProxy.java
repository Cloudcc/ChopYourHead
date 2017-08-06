package com.cloudcc.chopyourhead.proxy;

import com.cloudcc.chopyourhead.world.WorldGen;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Tobias on 09.11.2016.
 */
public class serverProxy implements commonProxy {

    @Override
    public void preInit() {


    }

    @Override
    public void init() {
        GameRegistry.registerWorldGenerator(new WorldGen(), 0);

    }

    @Override
    public void postInit() {

    }
}
