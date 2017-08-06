package com.cloudcc.chopyourhead.block;

import com.cloudcc.chopyourhead.ChopYourHead;
import com.cloudcc.chopyourhead.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

/**
 * Created by hi112 on 26.12.2016.
 */
public class BlockMachineCasing extends Block {

    public BlockMachineCasing(){

        super(Material.ROCK);
        setCreativeTab(ChopYourHead.CREATIVE_TAB);
        setUnlocalizedName(Reference.ModBlocks.MACHINECASING.getUnlocalizedName());
        setRegistryName(Reference.ModBlocks.MACHINECASING.getRegistryName());
    }
}
