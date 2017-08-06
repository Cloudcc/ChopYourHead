package com.cloudcc.chopyourhead.item.armor;

import com.cloudcc.chopyourhead.ChopYourHead;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

/**
 * Created by hi112 on 23.12.2016.
 */
public class ItemMithrilArmor extends ItemArmor {

    public ItemMithrilArmor(String unlocalizedName, ArmorMaterial material, int renderIndex, EntityEquipmentSlot entityEquipmentSlot){

        super(material,renderIndex,entityEquipmentSlot);
        this.setUnlocalizedName(unlocalizedName);
        setCreativeTab(ChopYourHead.CREATIVE_TAB);

        this.setMaxStackSize(1);
    }
}
