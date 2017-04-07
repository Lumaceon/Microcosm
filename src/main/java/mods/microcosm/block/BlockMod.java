package mods.microcosm.block;

import mods.microcosm.Microcosm;
import mods.microcosm.util.ISimpleNamed;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockMod extends Block implements ISimpleNamed
{
    public String simpleName;

    public BlockMod(Material blockMaterialIn, String name) {
        super(blockMaterialIn);
        this.setCreativeTab(Microcosm.instance.CREATIVE_TAB);
        this.setHardness(3.0F);
        this.simpleName = name;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getUnlocalizedName() {
        return this.getRegistryName().toString();
    }

    @Override
    public String getSimpleName() {
        return this.simpleName;
    }
}
