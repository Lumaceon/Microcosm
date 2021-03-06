package mods.microcosm.inventory;

import mods.microcosm.inventory.container.ContainerPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class InventoryPot implements IInventory
{
    public ItemStack pot;
    public ContainerPot containerPot;
    public int size;
    public int stackLimit;

    public InventoryPot(ContainerPot container, int size, int stackLimit, ItemStack pot) {
        this.pot = pot;
        this.containerPot = container;
        this.size = size;
        this.stackLimit = stackLimit;
    }

    private IItemHandler getItemHandler() {
        return pot.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
    }

    @Override
    public int getSizeInventory() {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler == null ? 0 : itemHandler.getSlots();
    }

    @Nullable
    @Override
    public ItemStack getStackInSlot(int index) {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler == null ? null : itemHandler.getStackInSlot(index);
    }

    @Nullable
    @Override
    public ItemStack decrStackSize(int index, int count) {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler.extractItem(index, count, false);
    }

    @Nullable
    @Override
    public ItemStack removeStackFromSlot(int index) {
        IItemHandler itemHandler = getItemHandler();
        return itemHandler.extractItem(index, 64, false);
    }

    @Override
    public void setInventorySlotContents(int index, @Nullable ItemStack stack) {
        IItemHandler itemHandler = getItemHandler();
        itemHandler.extractItem(index, 64, false);
        itemHandler.insertItem(index, stack, false);
    }

    @Override
    public int getInventoryStackLimit() {
        return stackLimit;
    }

    @Override
    public void markDirty() {}

    @Override
    public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
    }

    @Override
    public void openInventory(EntityPlayer player) {}

    @Override
    public void closeInventory(EntityPlayer player) {}

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        return true;
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
    public void clear() {
        IItemHandler itemHandler = getItemHandler();
        for(int i = 0; i < itemHandler.getSlots(); i++)
            itemHandler.extractItem(i, 64, false);
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
}
