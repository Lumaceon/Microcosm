package mods.microcosm.microcosm.chimericalfurnace.inventory;

import mods.microcosm.inventory.container.ContainerMod;
import mods.microcosm.microcosm.chimericalfurnace.TileChimericalAlloyFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;

public class ContainerChimericalFurnace extends ContainerMod
{
    public TileChimericalAlloyFurnace inventory;
    public EntityPlayer player;

    public ContainerChimericalFurnace(TileChimericalAlloyFurnace tile, EntityPlayer player)
    {
        InventoryPlayer playerInventory = player.inventory;

        this.inventory = tile;
        this.player = player;
        tile.setContainer(this);

        for(int k = 0; k < 3; ++k)
            for(int i1 = 0; i1 < 9; ++i1)
                this.addSlotToContainer(new Slot(playerInventory, i1 + k * 9 + 9, 59 + i1 * 18, 84 + k * 18));
        for(int l = 0; l < 9; ++l)
            this.addSlotToContainer(new Slot(playerInventory, l, 59 + l * 18, 142));

        for(int i = 0; i < 9; i++)
            this.addSlotToContainer(new Slot(inventory, i, i*22 + 2, 20));

        this.onCraftMatrixChanged(inventory);
    }
}
