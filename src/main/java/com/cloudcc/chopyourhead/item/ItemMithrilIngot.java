package com.cloudcc.chopyourhead.item;

import com.cloudcc.chopyourhead.ChopYourHead;
import com.cloudcc.chopyourhead.Reference;
import net.minecraft.item.Item;

/**
 * Created by hi112 on 22.12.2016.
 */
public class ItemMithrilIngot extends Item {

    public ItemMithrilIngot(){
        setRegistryName(Reference.ModItems.MITHRIL_INGOT.getRegistryName());
        setUnlocalizedName(Reference.ModItems.MITHRIL_INGOT.getUnlocalizedName());
        setCreativeTab(ChopYourHead.CREATIVE_TAB);
    }
}
