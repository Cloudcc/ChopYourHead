package com.cloudcc.chopyourhead.block.Ore;

import com.cloudcc.chopyourhead.ChopYourHead;
import com.cloudcc.chopyourhead.init.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by hi112 on 02.12.2016.
 */
public class BlockOre extends Block {

    public BlockOre() {
        super(Material.ROCK);
        setCreativeTab(ChopYourHead.CREATIVE_TAB);
    }

    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState) this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int i = random.nextInt(fortune + 2) - 1;

            if (i < 0) {
                i = 0;
            }
            return this.quantityDropped(random) * (i + 1);
        } else {
            return this.quantityDropped(random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    public int getExpDrop(IBlockState state, IBlockAccess world, BlockPos pos, int fortune) {
        Random random = world instanceof World ? ((World) world).rand : new Random();
        if (this.getItemDropped(state, random, fortune) != Item.getItemFromBlock(this)) {
            int i = 0;

            if (this == ModBlocks.mithril_ore) {
                i = MathHelper.getRandomIntegerInRange(random, 3, 6);
            } else if (this == ModBlocks.adamantit_ore) {
                i = MathHelper.getRandomIntegerInRange(random, 4, 10);
            }
            return i;
        }
        return 0;
    }

    @Nullable
    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(this);
    }


}



