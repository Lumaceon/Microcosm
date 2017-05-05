package mods.microcosm.block.crops;

import net.minecraft.item.Item;

/**
 * Created at 4:50 PM on 5/5/2017.
 * Part of the Microcosm project.
 */
public class BlockCropBerryNight extends BlockCropBerry{
    private static Item berry;

    public BlockCropBerryNight(String name, Item berry) {
        super(name, berry);
    }

    public static void setBerry(Item berry){
        BlockCropBerryNight.berry = berry;
    }

    @Override
    protected Item getCrop()
    {
        return berry;
    }
    @Override
    protected Item getSeed()
    {
        return berry;
    }
}