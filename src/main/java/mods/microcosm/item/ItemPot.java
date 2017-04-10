package mods.microcosm.item;

import mods.microcosm.Microcosm;
import mods.microcosm.lib.GUIs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nullable;

public class ItemPot extends ItemMod
{
    public ItemPot(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        if(playerIn.isSneaking())
            return new ActionResult(EnumActionResult.PASS, itemStackIn);

        playerIn.openGui(Microcosm.instance, GUIs.POT.ordinal(), worldIn, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new PotCapabilityProvider();
    }

    public class PotCapabilityProvider implements ICapabilitySerializable<NBTTagCompound>
    {
        ItemStackHandler implementation = new ItemStackHandler(10);

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY != null && CapabilityItemHandler.ITEM_HANDLER_CAPABILITY == capability;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if(hasCapability(capability, facing))
                return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(implementation);
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return implementation.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            implementation.deserializeNBT(nbt);
        }
    }
}