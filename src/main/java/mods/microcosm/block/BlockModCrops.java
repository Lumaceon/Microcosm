package mods.microcosm.block;

import mods.microcosm.Microcosm;
import mods.microcosm.util.ISimpleNamed;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraftforge.common.IPlantable;

public class BlockModCrops extends BlockCrops implements ISimpleNamed, IGrowable, IPlantable
{
    public String simpleName;

    public BlockModCrops(String name) {
        super();

        this.setCreativeTab(Microcosm.instance.CREATIVE_TAB);
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
