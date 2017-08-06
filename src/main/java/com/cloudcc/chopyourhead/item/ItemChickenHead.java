package com.cloudcc.chopyourhead.item;

import com.cloudcc.chopyourhead.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.SoundEvent;

/**
 * Created by hi112 on 30.11.2016.
 */
public class ItemChickenHead extends Item {

    public ItemChickenHead(){
        setUnlocalizedName(Reference.ModItems.Chicken_Head.getUnlocalizedName());
        setRegistryName(Reference.ModItems.Chicken_Head.getRegistryName());


    }


    

    protected SoundEvent getAmbientSound(){

        return null;
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand) {


        
        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }
}
