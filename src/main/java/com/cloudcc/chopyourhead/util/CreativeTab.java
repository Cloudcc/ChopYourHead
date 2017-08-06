package com.cloudcc.chopyourhead.util;

import com.cloudcc.chopyourhead.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

/**
 * Created by hi112 on 24.12.2016.
 */
public class CreativeTab extends CreativeTabs {

    public CreativeTab(){
        super("tabChopYourHead");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.chicken_head;
    }
}
