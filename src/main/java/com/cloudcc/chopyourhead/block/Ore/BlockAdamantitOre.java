package com.cloudcc.chopyourhead.block.Ore;

import com.cloudcc.chopyourhead.Reference;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by hi112 on 02.12.2016.
 */
public class BlockAdamantitOre extends BlockOre{

    public BlockAdamantitOre(){
        super();
        setUnlocalizedName(Reference.ModOres.ADAMANTIT_ORE.getUnlocalizedName());
        setRegistryName(Reference.ModOres.ADAMANTIT_ORE.getRegistryName());
        setHardness(1.0F); //TODO
        setResistance(2f); //TODO
        setHarvestLevel("pickaxe", 3);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        return super.getExpDrop(state, world, pos, fortune);
    }

}
