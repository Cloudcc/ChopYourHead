/*
package com.cloudcc.chopyourhead.handler.inventory;

import com.cloudcc.chopyourhead.item.ItemChickenHead;
import com.cloudcc.chopyourhead.util.NBTUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

import java.util.UUID;

import static net.minecraftforge.common.util.BlockSnapshot.readFromNBT;

*/
/**
 * Created by hi112 on 11.12.2016.
 *//*

public class inventoryHead implements IInventory {

    private ItemStack headStack; //itemstack for heads
    private EntityPlayer player; //player
    private ItemStack[] inventory; //Stored item
    private String sortType; // sort option

    public inventoryHead(EntityPlayer player, ItemStack headStack){
        this.headStack = headStack;
        this.player = player;
        this.inventory = new ItemStack[this.getSizeInventory()];
        this.sortType = "id";
        readFromNBT(headStack.getTagCompound());
    }

    public ItemStack getHeadStack() {
        return headStack;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public String getSortType() {
        return sortType;
    }

    public void toggelSortType(){
        if(sortType.equals("id"))
            sortType = "alphabetical";
        else
            sortType = "id";
        save();
    }

    @Override
    public int getSizeInventory() {
        return ((ItemChickenHead)headStack.getItem()).getSize(headStack);
    }


    @Nullable
    @Override
    public ItemStack decrStackSize(int slotIndex, int amount) {
        if (inventory[slotIndex] != null) {
            if (inventory[slotIndex].stackSize <= amount) {
                ItemStack itemStack = inventory[slotIndex];
                inventory[slotIndex] = null;
                return itemStack;
            }
            ItemStack itemStack1 = inventory[slotIndex].splitStack(amount);
            if (inventory[slotIndex].stackSize == 0) {
                inventory[slotIndex] = null;
            }
            return itemStack1;
        } else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int slotIndex, @Nullable ItemStack itemStack) {
        inventory[slotIndex] = itemStack;
        if(itemStack != null && itemStack.stackSize > getInventoryStackLimit()){
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getName() {
        return ((ItemChickenHead)headStack.getItem().getName(headStack));
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TextComponentString(getName());
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {

        ItemStack stack = null;
        if(inventory[index] != null ){
            stack = inventory[index];
            inventory[index] = null;
        }
        return stack;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {

    }

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear() {
        for(int i = 0; i < inventory.length; i++){
            inventory[i] = null;
        }
    }

    public int hasStackInInv(Block blockToCheck, int meta){
        int total = 0;
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] != null && inventory[i].stackSize > 0){
                Block headpackItemAsBlock = Block.getBlockFromItem(inventory[i].getItem());
                if(headpackItemAsBlock.equals(blockToCheck) && inventory[i].getItemDamage() == meta){
                    total += inventory[i].stackSize;
                }
            }
        }
        return total;
    }

    public boolean removeOneItem(Block blockToCheck, int meta){
        for(int i = 0; i < inventory.length; i++){
            if(inventory[i] != null && inventory[i].stackSize > 0){
                Block headpackItemAsBlock = Block.getBlockFromItem(inventory[i].getItem());
                if(headpackItemAsBlock.equals(blockToCheck) && inventory[i].getItemDamage() == meta){
                    inventory[i].stackSize--;
                    if(inventory[i].stackSize == 0) inventory[i] = null;
                    save();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isEmpty(){
        for(ItemStack stack : inventory){
            if(stack != null && stack.stackSize > 0){
                return false;
            }
        }
        return true;
    }

    public void onGuiSaved(EntityPlayer entityPlayer){
        if(headStack != null){
            save();
        }
    }

    public void saveWithSideCheck(EntityPlayer player){
        if(!player.worldObj.isRemote){
            onGuiSaved(player);
        }
    }

    public void save(){

        NBTTagCompound nbtTagCompound = headStack.getTagCompound();

        if(nbtTagCompound == null){
            nbtTagCompound = new NBTTagCompound();
        }

        writeToNBT(nbtTagCompound);
        headStack.setTagCompound(nbtTagCompound);
    }

    public void writeToNBT(NBTTagCompound nbtTagCompound){
        if(!player.worldObj.isRemote) {
            ItemStack tempStack = findParentItemStack(player);
            ItemStack stackToUse = (tempStack == null) ? headStack : tempStack;
            if (headStack != null) {


                nbtTagCompound = stackToUse.getTagCompound();

                NBTTagList tagList = new NBTTagList();
                for (int i = 0; i < inventory.length; i++) {
                    if (inventory[i] != null) {
                        NBTTagCompound tagCompound = new NBTTagCompound();
                        tagCompound.setByte(IronBackpacksConstants.NBTKeys.ITEMS, Constants.NBT.TAG_COMPOUND);
                        this.inventory = new ItemStack[this.getSizeInventory()];

                        for (int i = 0; i < tagList.tagCount(); i++) {
                            NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
                            int j = stackTag.getByte(IronBackPacksConstants.NBTKeys.SLOT);
                            if (i >= 0 && i <= inventory.length) {
                                this.inventory[j] = ItemStack.loadItemStackFromNBT(stackTag);
                            }
                        }
                    }
                }
            }
        } else {
            if(nbtTagCompound.hasKey(IronBackPacksConstants.NBTKeys.SORT_TYPE)){
                this.sortType = nbtTagCompound.getString(IronBackpacksConstants.NBTKeys.SORT_TYPE);
            }
        }
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound, boolean noPlayer){
        if(noPlayer && (headStack != null)){
            nbtTagCompound = headStack.getTagCompound();


            if(nbtTagCompound.hasKey(IronBackpacksConstants.NBTKeys.SORT_TYPE)){
                this.sortType = nbtTagCompound.getString(IronBackpacksConstants.NTBKeys.SORT_TYPE);

            if(nbtTagCompound != null){
                if(nbtTagCompound.hasKey(IronBackpackConstants.NBTKeys.Items));

                NBTTagList tagList = nbtTagCompound.getTagList(IronBackpackConstants.NBTKeys.Items, Constants.NBT.TAG_COMPOUND);
                this.inventory = new ItemStack[this.getSizeInventory()];

                for(int i = 0; i < tagList.getCompoundTagAt(i); i++){
                    NBTTagCompound stackTag = tagList.getCompoundTagAt(i);
                    int j = stackTag.getByte(IronBackpacksConstants.NBTKeys.SLOT);
                    if(i >= 0 && i <= inventory.length){
                        this.inventory[j] = ItemStack.loadItemStackFromNBT(stackTag)
                    }
                }

                }

            }
        }
    }

    private ItemStack findParentItemStack(EntityPlayer entityPlayer){
        if(NBTUtils.hasUUID(headStack)){
            UUID parentUUID = new UUID(backpackStack.getTagCompound().getLong(IronBackpacksConstants.Miscellaneous.MOST_SIG_UUID), backpackStack.getTagCompound().getLong(IronBackpacksConstants.Miscellaneous.LEAST_SIG_UUID));
            for (int i = 0; i < entityPlayer.inventory.getSizeInventory(); i++){
                ItemStack itemStack = entityPlayer.inventory.getStackInSlot(i);

                if (itemStack != null && itemStack.getItem() instanceof IBackpack && NBTUtils.hasUUID(itemStack)){
                    if (itemStack.getTagCompound().getLong(IronBackpacksConstants.Miscellaneous.MOST_SIG_UUID) == parentUUID.getMostSignificantBits() && itemStack.getTagCompound().getLong(IronBackpacksConstants.Miscellaneous.LEAST_SIG_UUID) == parentUUID.getLeastSignificantBits()){
                        return itemStack;
                    }
                }
            }

            ItemStack equipped = PlayerWearingBackpackCapabilities.getEquippedBackpack(entityPlayer);
            if (equipped != null && equipped.getItem() instanceof IBackpack && NBTUtils.hasUUID(equipped)) {
                if (equipped.getTagCompound().getLong(IronBackpacksConstants.Miscellaneous.MOST_SIG_UUID) == parentUUID.getMostSignificantBits() && equipped.getTagCompound().getLong(IronBackpacksConstants.Miscellaneous.LEAST_SIG_UUID) == parentUUID.getLeastSignificantBits()) {
                    return equipped;
                }
            }
        }
        return null;
    }
}
*/
