package com.cloudcc.chopyourhead.item;

import com.cloudcc.chopyourhead.ChopYourHead;
import com.cloudcc.chopyourhead.Reference;
import net.minecraft.item.Item;

/**
 * Created by hi112 on 26.12.2016.
 */
public class ItemMithrilGear extends Item {

    public ItemMithrilGear(){
        setCreativeTab(ChopYourHead.CREATIVE_TAB);
        setRegistryName(Reference.ModItems.MITHRIL_GEAR.getRegistryName());
        setUnlocalizedName(Reference.ModItems.MITHRIL_GEAR.getUnlocalizedName());
    }
}
