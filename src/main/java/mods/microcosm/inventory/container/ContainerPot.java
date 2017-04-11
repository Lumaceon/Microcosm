package mods.microcosm.inventory.container;

import mods.microcosm.inventory.InventoryPot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class ContainerPot extends ContainerMod
{
    public ItemStack pot;
    public EntityPlayer player;
    public InventoryPot mainInventory;

    public ContainerPot(ItemStack pot, EntityPlayer player)
    {
        InventoryPlayer playerInventory = player.inventory;

        this.pot = pot;
        this.player = player;
        IItemHandler itemHandler = pot.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.DOWN);
        if(itemHandler == null)
            return;
        mainInventory = new InventoryPot(this, itemHandler.getSlots(), 64, pot);

        int offsetX = 0;
        for(int k = 0; k < 3; ++k)
            for(int i1 = 0; i1 < 9; ++i1)
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, offsetX + i1 * 18, 84 + k * 18));
        for(int l = 0; l < 9; ++l)
            this.addSlotToContainer(new Slot(playerInventory, l, offsetX + l * 18, 142));

        for(int i = 0; i < itemHandler.getSlots(); i++)
            this.addSlotToContainer(new Slot(mainInventory, i, i*22 + 2, 20));
    }
}
