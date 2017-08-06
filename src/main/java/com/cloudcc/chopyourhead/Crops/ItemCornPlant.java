package com.cloudcc.chopyourhead.Crops;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSeeds;

/**
 * Created by Tobias on 28.03.2017.
 */
public class ItemCornPlant extends ItemSeeds {


    private static Block crops;
    private static Block soil;

    public ItemCornPlant(ItemSeeds itemSeeds) {

        super(soil,crops);



    }
}
