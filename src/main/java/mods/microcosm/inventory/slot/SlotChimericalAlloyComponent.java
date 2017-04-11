package mods.microcosm.inventory.slot;

import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class SlotChimericalAlloyComponent extends Slot
{
    public SlotChimericalAlloyComponent(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(@Nullable ItemStack stack) {
        return stack != null && ChimericalAlloyRecipes.isChimericalAlloyMetal(stack);
    }
}
