package com.cloudcc.chopyourhead.item.armor;

import com.cloudcc.chopyourhead.ChopYourHead;
import com.cloudcc.chopyourhead.init.ModItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

import java.util.List;

/**
 * Created by hi112 on 23.12.2016.
 */
public class ItemAdamantiumArmor extends ItemArmor {




    public ItemAdamantiumArmor(String unlocalizedName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot entityEquipmentSlot){

        super(material,renderIndex,entityEquipmentSlot);
        this.setUnlocalizedName(unlocalizedName);
        setCreativeTab(ChopYourHead.CREATIVE_TAB);

        this.setMaxStackSize(1);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return super.getItemStackDisplayName(stack);
    }


    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {


            if (playerIn.inventory.armorItemInSlot(3) != null && playerIn.inventory.armorItemInSlot(3).getItem() == ModItems.adamantium_helmet
                    && playerIn.inventory.armorItemInSlot(2) != null && playerIn.inventory.armorItemInSlot(2).getItem() == ModItems.adamantium_chestplate
                    && playerIn.inventory.armorItemInSlot(1) != null && playerIn.inventory.armorItemInSlot(1).getItem() == ModItems.adamantium_leggings
                    && playerIn.inventory.armorItemInSlot(0) != null && playerIn.inventory.armorItemInSlot(0).getItem() == ModItems.adamantium_boots) {
                tooltip.remove("Set 0/4");
                tooltip.add(TextFormatting.WHITE + "Set 4/4 (Allows Flying)");


            }
        else {
                tooltip.add("Set 0/4 (Allows Flying)");
            }

    }


    @Override
    public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack) {

        if(world.isRemote) {


       //     player.stepHeight = 0;


            if (player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ModItems.adamantium_boots) {
                player.stepHeight = 1; //stepassist
            }
          if(player.inventory.armorItemInSlot(3) != null && player.inventory.armorItemInSlot(3).getItem() == ModItems.adamantium_helmet
                  && player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ModItems.adamantium_chestplate
                  && player.inventory.armorItemInSlot(1) != null && player.inventory.armorItemInSlot(1).getItem() == ModItems.adamantium_leggings
                  && player.inventory.armorItemInSlot(0) != null && player.inventory.armorItemInSlot(0).getItem() == ModItems.adamantium_boots){
              player.capabilities.isFlying = true;
          }
            else player.capabilities.isFlying = false;

        }


    }



    private void effectPlayer(EntityPlayer player,int duration , Potion potion, int amplifier){

        duration = duration*20;

        if(player.getActivePotionEffect(potion) == null || player.getActivePotionEffect(potion).getDuration() <= 1)
            player.addPotionEffect(new PotionEffect(potion, duration, amplifier, true, true));


    }


}



