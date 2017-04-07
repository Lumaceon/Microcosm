package mods.microcosm.microcosm.chimericalfurnace;

import mods.microcosm.Microcosm;
import mods.microcosm.block.BlockMod;
import mods.microcosm.lib.GUIs;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

@SuppressWarnings("NullableProblems")
public class BlockChimericalAlloyFurnace extends BlockMod implements ITileEntityProvider
{
    public BlockChimericalAlloyFurnace(Material blockMaterialIn, String name) {
        super(blockMaterialIn, name);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if(playerIn.isSneaking())
            return false;

        playerIn.openGui(Microcosm.instance, GUIs.CHIMERICAL_ALLOY_FURNACE.ordinal(), worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileChimericalAlloyFurnace();
    }
}
