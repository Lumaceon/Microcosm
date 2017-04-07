package mods.microcosm.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.text.ITextComponent;

import javax.annotation.Nullable;

public class TileModInventory extends TileMod implements IInventory
{
    Container container;
    ItemStack[] inventory;
    int stackLimit;

    public TileModInventory(int size, int stackLimit)
    {
        this.inventory = new ItemStack[size];
        this.stackLimit = stackLimit;
    }

    @Override
    public int getSizeInventory() {
        return inventory == null ? 0 : this.inventory.length; //0 if no inventory, length if it exists.
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        return index >= this.getSizeInventory() ? null : this.inventory[index]; //If the index is in bounds, get stack.
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count)
    {
        if(this.inventory[index] != null)
        {
            ItemStack itemstack;
            if(this.inventory[index].stackSize <= count)
            {
                itemstack = this.inventory[index];
                this.inventory[index] = null;

                onChange();

                return itemstack;
            }
            else
            {
                itemstack = this.inventory[index].splitStack(count);

                if(this.inventory[index].stackSize == 0)
                    this.inventory[index] = null;

                onChange();

                return itemstack;
            }
        }
        return null;
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index)
    {
        ItemStack item = inventory[index];
        inventory[index] = null;

        onChange();

        return item;
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack)
    {
        this.inventory[index] = stack;

        onChange();
    }

    @Override
    public int getInventoryStackLimit() {
        return stackLimit;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return container.canInteractWith(player);
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return false;
    }

    @Override
    public int getField(int id) {
        return 0;
    }

    @Override
    public void setField(int id, int value) {}

    @Override
    public int getFieldCount() {
        return 0;
    }

    @Override
    public void clear()
    {
        for(int n = 0; n < inventory.length; n++)
            inventory[n] = null;

        onChange();
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasCustomName() {
        return false;
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    public static final String INVENTORY_TAG = "microcosm_inventory";
    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);

        NBTTagList nbtList = new NBTTagList();
        for(int index = 0; index < inventory.length; index++)
        {
            if(inventory[index] != null)
            {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("slot_index", (byte)index);
                inventory[index].writeToNBT(tag);
                nbtList.appendTag(tag);
            }
        }
        nbt.setTag(INVENTORY_TAG, nbtList);
        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        super.readFromNBT(nbt);

        NBTTagList tagList = nbt.getTagList(INVENTORY_TAG, 10);
        inventory = new ItemStack[getSizeInventory()];
        for(int i = 0; i < tagList.tagCount(); ++i)
        {
            NBTTagCompound tagCompound = tagList.getCompoundTagAt(i);
            byte slotIndex = tagCompound.getByte("slot_index");
            if(slotIndex >= 0 && slotIndex < inventory.length)
                inventory[slotIndex] = ItemStack.loadItemStackFromNBT(tagCompound);
        }
    }

    protected void onChange()
    {
        if(this.container != null)
            this.container.onCraftMatrixChanged(this);
        markDirty();
    }

    public void setContainer(Container container) {
        this.container = container;
    }
}
