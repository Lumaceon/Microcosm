package mods.microcosm.microcosm.chimericalfurnace.client.gui;

import mods.microcosm.lib.Textures;
import mods.microcosm.microcosm.chimericalfurnace.TileChimericalAlloyFurnace;
import mods.microcosm.microcosm.chimericalfurnace.inventory.ContainerChimericalFurnace;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipes;
import mods.microcosm.recipe.Recipes;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiChimericalFurnace extends GuiContainer
{
    public TileChimericalAlloyFurnace tile;

    public GuiChimericalFurnace(TileChimericalAlloyFurnace tile, EntityPlayer player) {
        super(new ContainerChimericalFurnace(tile, player));
        this.tile = tile;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(Textures.TEST_GUI);
        this.drawWholeTexturedRect(this.guiLeft + 1, this.guiTop + 19, 128, 18);
    }

    /**
     * Similar to drawTexturedModalRect, but always draws the entire bound texture. Inefficient, but who cares. It's
     * simpler to code and FAR easier to understand. I have yet to notice cripplingly significant lag using this.
     */
    public void drawWholeTexturedRect(int x, int y, int width, int height)
    {
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer renderer = tessellator.getBuffer();
        renderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        renderer.pos((double)(x + 0), (double)(y + height), (double)this.zLevel).tex(0, 1).endVertex();
        renderer.pos((double)(x + width), (double)(y + height), (double)this.zLevel).tex(1, 1).endVertex();
        renderer.pos((double)(x + width), (double)(y + 0), (double)this.zLevel).tex(1, 0).endVertex();
        renderer.pos((double)(x + 0), (double)(y + 0), (double)this.zLevel).tex(0, 0).endVertex();
        tessellator.draw();
    }
}
