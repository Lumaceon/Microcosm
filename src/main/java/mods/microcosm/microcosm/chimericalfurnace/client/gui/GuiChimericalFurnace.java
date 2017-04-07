package mods.microcosm.microcosm.chimericalfurnace.client.gui;

import mods.microcosm.microcosm.chimericalfurnace.TileChimericalAlloyFurnace;
import mods.microcosm.microcosm.chimericalfurnace.inventory.ContainerChimericalFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;

public class GuiChimericalFurnace extends GuiContainer
{
    public GuiChimericalFurnace(TileChimericalAlloyFurnace tile, EntityPlayer player) {
        super(new ContainerChimericalFurnace(tile, player));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        //TODO Draw background later.
    }
}
