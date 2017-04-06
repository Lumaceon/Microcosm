package mods.microcosm.block;

import mods.microcosm.Microcosm;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block
{
    public BlockMod(Material blockMaterialIn, String unlocalizedName) {
        super(blockMaterialIn);
        this.setCreativeTab(Microcosm.instance.CREATIVE_TAB);
        this.setHardness(3.0F);
        this.setRegistryName(unlocalizedName);
        this.setUnlocalizedName(unlocalizedName);
    }

    @Override
    public String getUnlocalizedName() {
        return this.getRegistryName().toString();
    }
}
