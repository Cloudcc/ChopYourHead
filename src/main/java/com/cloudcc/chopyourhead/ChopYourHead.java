package com.cloudcc.chopyourhead;

import com.cloudcc.chopyourhead.proxy.commonProxy;
import com.cloudcc.chopyourhead.util.CreativeTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * Created by Tobias on 09.11.2016.
 */
@Mod(modid = Reference.MODID, version = Reference.VERSION)
public class ChopYourHead {


    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static commonProxy proxy;

    public static final CreativeTabs CREATIVE_TAB = new CreativeTab();
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        System.out.println("Time to Chop some Heads!");
        proxy.preInit();




    }


    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init();

    }
    
    @Mod.EventHandler
        public void postInit(FMLPostInitializationEvent event)
        {
            proxy.postInit();

        }
    }


