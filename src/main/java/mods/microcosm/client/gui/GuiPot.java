package mods.microcosm.client.gui;

import mods.microcosm.inventory.container.ContainerPot;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class GuiPot extends GuiContainer
{
    public GuiPot(ItemStack pot, EntityPlayer player) {
        super(new ContainerPot(pot, player));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {

    }
}
