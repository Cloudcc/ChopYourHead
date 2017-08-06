package com.cloudcc.chopyourhead.tile;

import com.cloudcc.chopyourhead.block.BlockGrinder;
import com.cloudcc.chopyourhead.recipes.ModGrinderRecipes;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;

/**
 * Created by hi112 on 26.12.2016.
 */
public class TEGrinder extends TileEntityLockable implements ISidedInventory, ITickable {

    private final int[] SLOTS_TOP = new int[]{0};
    private final int[] SLOTS_BOTTOM = new int[]{2, 1};
    private final int[] SLOTS_SIDED = new int[]{1};

    private ItemStack[] grinderItemStacks = new ItemStack[3];

    private int furnaceBurnTime;

    private int currentItemBurnTime;
    private int cookTime;
    private int totalCookTime;
    private String grinderCustomName;

    public int getSizeInventory(){
        return  this.grinderItemStacks.length;
    }


    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.grinderItemStacks[index];
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.getAndSplit(this.grinderItemStacks, index, count);
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        return ItemStackHelper.getAndRemove(this.grinderItemStacks, index);
    }


    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        boolean flag = stack != null && stack.isItemEqual(this.grinderItemStacks[index]) && ItemStack.areItemStacksEqual(stack, this.grinderItemStacks[index]);
        this.grinderItemStacks[index] = stack;

        if(stack != null && stack.stackSize > this.getInventoryStackLimit()){
            stack.stackSize = this.getInventoryStackLimit();
        }

        if(index == 0 && !flag)
        {
            this.totalCookTime = this.getCookTime(stack);
            this.cookTime = 0;
            this.markDirty();
        }
    }


    public String getName() { return this.hasCustomName() ? this.grinderCustomName : "container.grinder"; }

    @Override
    public boolean hasCustomName() {
        return this.grinderCustomName != null && !this.grinderCustomName.isEmpty();
    }

    public void setCustomInventoryName(String p_145951_1_)
    {
        this.grinderCustomName = p_145951_1_;
    }

    public static void func_189676_a(DataFixer p_189676_0_)
    {
        p_189676_0_.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists("Grinder", new String[] { "Items"}));
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList nbtTagList = compound.getTagList("Items", 10);
        this.grinderItemStacks = new ItemStack[this.getSizeInventory()];

        for(int i = 0; i < nbtTagList.tagCount(); ++i){
            NBTTagCompound nbtTagCompound = nbtTagList.getCompoundTagAt(i);
            int j  = nbtTagCompound.getByte("Slot");

            if( j >= 0 && j < this.grinderItemStacks.length){
                this.grinderItemStacks[j] = ItemStack.loadItemStackFromNBT(nbtTagCompound);
            }
        }

        this.furnaceBurnTime = compound.getInteger("BurnTime");
        this.cookTime = compound.getInteger("CookTime");
        this.totalCookTime = compound.getInteger("CookTimeTotal");
        this.currentItemBurnTime = getItemBurnTime(this.grinderItemStacks[1]);

        if(compound.hasKey("CustomName", 8)){
            this.grinderCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("BurnTime", this.furnaceBurnTime);
        compound.setInteger("CookTime", this.cookTime);
        compound.setInteger("CookTimeTotal", this.totalCookTime);
        NBTTagList nbtTagList = new NBTTagList();

        for(int i = 0; i < this.grinderItemStacks.length; ++i){
            if(this.grinderItemStacks[i] != null){
                NBTTagCompound nbtTagCompound = new NBTTagCompound();
                nbtTagCompound.setByte("Slot", (byte)i);
                this.grinderItemStacks[i].writeToNBT(nbtTagCompound);
                nbtTagList.appendTag(nbtTagCompound);
            }
        }

        compound.setTag("Items", nbtTagList);

        if( this.hasCustomName()){
            compound.setString("CustomName", this.grinderCustomName);
        }

        return compound;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning(){
        return this.furnaceBurnTime > 0;
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inventory){
        return inventory.getField(0) > 0;
    }

    @Override
    public void update() {
        boolean flag = this.isBurning();
        boolean flag1 = false;

        if(this.isBurning()){
            --this.furnaceBurnTime;
        }

        if(!this.worldObj.isRemote)
        {
            if(this.isBurning() || this.grinderItemStacks[1] != null && this.grinderItemStacks[0] != null) //Todo
            {
                if(!this.isBurning() && this.canSmelt()){
                    this.furnaceBurnTime = getItemBurnTime(this.grinderItemStacks[1]);
                    this.currentItemBurnTime = this.furnaceBurnTime;

                    if(this.isBurning())
                    {
                        flag1 = true;

                        if(this.grinderItemStacks[1] != null){
                            --this.grinderItemStacks[1].stackSize;

                            if(this.grinderItemStacks[1].stackSize == 0)
                            {
                                this.grinderItemStacks[1] = grinderItemStacks[1].getItem().getContainerItem(grinderItemStacks[1]);
                            }
                        }
                    }
                }

                if(this.isBurning() && this.canSmelt()){ //TODO
                    ++this.cookTime;

                    if(this.cookTime == this.totalCookTime)
                    {
                        this.cookTime = 0;
                        this.totalCookTime = this.getCookTime(this.grinderItemStacks[0]);
                        this.smeltItem();
                        flag1 = true;
                    }
                }

                else {
                    this.cookTime = 0;
                }
            }
            else if(!this.isBurning() && this.cookTime > 0){
                this.cookTime = MathHelper.clamp_int(this.cookTime - 2, 0, this.totalCookTime);
            }

            if(flag != this.isBurning())
            {
                flag1 = true;
                BlockGrinder.setState(this.isBurning(), this.worldObj, this.pos);
            }
        }

        if(flag1){
            this.markDirty();
        }
    }
    //Time to Cook Something Lower = Faster
    public int getCookTime(@Nullable ItemStack stack){
        return 200;
    }

    private boolean canSmelt(){
        if(this.grinderItemStacks[0] == null){
            return false;
        }
        else
        {
            ItemStack itemStack = ModGrinderRecipes.instance().getGrindingResult(this.grinderItemStacks[0]);
            if(itemStack == null) return false;
            if(this.grinderItemStacks[2] == null) return true;
            if(!this.grinderItemStacks[2].isItemEqual(itemStack)) return false;
            int result = grinderItemStacks[2].stackSize + itemStack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.grinderItemStacks[2].getMaxStackSize(); //TODO
        }
    }

    public void smeltItem(){
        if(this.canSmelt())
        {
            ItemStack itemStack = ModGrinderRecipes.instance().getGrindingResult(this.grinderItemStacks[0]);

            if(this.grinderItemStacks[2] == null){
                this.grinderItemStacks[2] = itemStack.copy();
            }

            else if(this.grinderItemStacks[2].getItem() == itemStack.getItem())
            {
                this.grinderItemStacks[2].stackSize += itemStack.stackSize;
            }

            if(this.grinderItemStacks[0].getItem() == Item.getItemFromBlock(Blocks.SPONGE) && this.grinderItemStacks[0].getMetadata() == 1 && this.grinderItemStacks[1] != null && this.grinderItemStacks[1].getItem() == Items.BUCKET){
                this.grinderItemStacks[1] = new ItemStack(Items.WATER_BUCKET);
            }

            --this.grinderItemStacks[0].stackSize;

            if(this.grinderItemStacks[0].stackSize <= 0){
                this.grinderItemStacks[0] = null;
            }
        }
    }


    public static int getItemBurnTime(ItemStack stack){
        if(stack == null){
            return 0;
        }
        else{
            Item item = stack.getItem();

            if(item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR){
                Block block = Block.getBlockFromItem(item);

                if(block == Blocks.WOODEN_SLAB){
                    return 150;
                }

                if(block.getDefaultState().getMaterial() == Material.WOOD){
                    return 300;
                }

                if(block == Blocks.COAL_BLOCK){
                    return 16000;
                }
            }
            if(item instanceof ItemTool && "Wood".equals(((ItemTool)item).getToolMaterialName())) return 200;
            if(item instanceof ItemSword && "Wood".equals(((ItemSword)item).getToolMaterialName())) return 200;
            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;
            if (item == Items.STICK) return 100;
            if (item == Items.COAL) return 1600;
            if (item == Items.LAVA_BUCKET) return 20000;
            if (item == Item.getItemFromBlock(Blocks.SAPLING)) return 100;
            if (item == Items.BLAZE_ROD) return 2400;
            return net.minecraftforge.fml.common.registry.GameRegistry.getFuelValue(stack);
        }
    }

    public static boolean isItemFuel(ItemStack stack){
        return getItemBurnTime(stack) > 0;
    }

    public boolean isUseableByPlayer(EntityPlayer player){
        return this.worldObj.getTileEntity(this.pos) != this ? false : player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory(EntityPlayer player) {

    }

    @Override
    public void closeInventory(EntityPlayer player) {

    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if(index == 2){
            return false;
        }
        else if(index != 1){
            return true;
        }
        else
        {
            ItemStack itemstack = this.grinderItemStacks[1];
            return isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack) && (itemstack == null || itemstack.getItem() != Items.BUCKET);
        }
    }

    @Override
    public int[] getSlotsForFace(EnumFacing side) {
        return side == EnumFacing.DOWN ? SLOTS_BOTTOM : (side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDED);
    }

    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if(direction == EnumFacing.DOWN && index == 1){
            Item item = stack.getItem();

            if(item != Items.WATER_BUCKET && item != Items.BUCKET){
                return false;
            }
        }
        return true;
    }

    @Override
    public String getGuiID() {
        return "minecraft:furnace";
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerFurnace(playerInventory, this);
    }

    @Override
    public int getField(int id) {
        switch (id)
        {
            case 0:
                return this.furnaceBurnTime;
            case 1:
                return this.currentItemBurnTime;
            case 2:
                return this.cookTime;
            case 3:
                return this.totalCookTime;
            default:
                return 0;
        }
    }

    @Override
    public void setField(int id, int value) {
        switch (id)
        {
            case 0:
                this.furnaceBurnTime = value;
                break;
            case 1:
                this.currentItemBurnTime = value;
                break;
            case 2:
                this.cookTime = value;
                break;
            case 3:
                this.totalCookTime = value;
        }
    }

    @Override
    public int getFieldCount() {
        return 4;
    }

    @Override
    public void clear() {
        for(int i = 0; i < this.grinderItemStacks.length; ++i){
            this.grinderItemStacks[i] = null;
        }
    }

    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, EnumFacing.WEST);

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, EnumFacing facing)
    {
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }

}