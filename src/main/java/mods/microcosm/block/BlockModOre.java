package mods.microcosm.block;

import net.minecraft.block.material.Material;

public class BlockModOre extends BlockMod
{
    public BlockModOre(Material blockMaterialIn, int harvestLevel, String unlocalizedName) {
        super(blockMaterialIn, unlocalizedName);
        this.setHarvestLevel("pickaxe", harvestLevel);
    }
}
