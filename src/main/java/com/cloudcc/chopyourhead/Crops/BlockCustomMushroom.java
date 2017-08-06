package com.cloudcc.chopyourhead.Crops;

import com.cloudcc.chopyourhead.ChopYourHead;
import com.cloudcc.chopyourhead.init.ModBlocks;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Tobias on 08.05.2017.
 */
public class BlockCustomMushroom extends BlockBush implements IGrowable{

    protected static final AxisAlignedBB MUSHROOM_AABB = new AxisAlignedBB(0.2, 0, 0.2,  0.8, 0.6, 0.8);

    public BlockCustomMushroom(){
        setLightLevel(0.934f);
        setTickRandomly(true);
        setRegistryName("chopyourhead", "shroom");
        setUnlocalizedName("shrooomi");
        setCreativeTab(ChopYourHead.CREATIVE_TAB);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return MUSHROOM_AABB;
    }

    @Override
    protected boolean canSustainBush(IBlockState state) {
        return super.canSustainBush(state);
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if(rand.nextInt(25) == 0) {
            int i = 5;
            int j = 4;

            for(BlockPos blockpos : BlockPos.getAllInBoxMutable(pos.add(-4, -1, -4), pos.add(4, 1, 4))) {
                if(worldIn.getBlockState(blockpos).getBlock() == this) {
                    --i;

                    if(i <= 0)
                        return;
                }
            }

            BlockPos blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);

            for(int k = 0; k < 4; ++k) {
                if (worldIn.isAirBlock(blockpos1) && canBlockStay(worldIn, blockpos1, getDefaultState()))
                    pos = blockpos1;

                blockpos1 = pos.add(rand.nextInt(3) - 1, rand.nextInt(2) - rand.nextInt(2), rand.nextInt(3) - 1);
            }

            if(worldIn.isAirBlock(blockpos1) && canBlockStay(worldIn, blockpos1, getDefaultState()))
                worldIn.setBlockState(blockpos1, getDefaultState(), 2);
        }
    }

    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        super.randomDisplayTick(stateIn, worldIn, pos, rand);

        if(rand.nextInt(20) == 0)
            worldIn.spawnParticle(EnumParticleTypes.END_ROD, pos.getX() + 0.2 + rand.nextFloat() * 0.6, pos.getY() + 0.3F, pos.getZ() + 0.2 + rand.nextFloat() * 0.6, 0, 0, 0, new int[0]);    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return false;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {

    }

    @Nullable
    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.grinder);
    }
}
