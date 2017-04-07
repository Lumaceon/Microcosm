package mods.microcosm.client;

import mods.microcosm.Microcosm;
import mods.microcosm.lib.GUIs;
import mods.microcosm.microcosm.chimericalfurnace.TileChimericalAlloyFurnace;
import mods.microcosm.microcosm.chimericalfurnace.client.gui.GuiChimericalFurnace;
import mods.microcosm.microcosm.chimericalfurnace.inventory.ContainerChimericalFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;

public class GuiHandler implements IGuiHandler
{
    public GuiHandler()
    {
        NetworkRegistry.INSTANCE.registerGuiHandler(Microcosm.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(ID == GUIs.CHIMERICAL_ALLOY_FURNACE.ordinal() && te != null && te instanceof TileChimericalAlloyFurnace)
        {
            return new ContainerChimericalFurnace((TileChimericalAlloyFurnace) te, player);
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
        if(ID == GUIs.CHIMERICAL_ALLOY_FURNACE.ordinal() && te != null && te instanceof TileChimericalAlloyFurnace)
        {
            return new GuiChimericalFurnace((TileChimericalAlloyFurnace) te, player);
        }
        return null;
    }
}

