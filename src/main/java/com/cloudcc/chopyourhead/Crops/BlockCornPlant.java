package com.cloudcc.chopyourhead.Crops;

import com.cloudcc.chopyourhead.Reference;
import net.minecraft.block.BlockCrops;
import net.minecraft.item.Item;

/**
 * Created by Tobias on 28.03.2017.
 */
public class BlockCornPlant extends BlockCrops {

    public BlockCornPlant(){

        setRegistryName(Reference.ModCrops.CORN_PLANT.getRegistryName());
        setUnlocalizedName(Reference.ModCrops.CORN_PLANT.getUnlocalizedName());

    }

    @Override
    protected Item getSeed() {
        return super.getSeed();
    }

    @Override
    protected Item getCrop() {
        return super.getCrop();
    }
}
