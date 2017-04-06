package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.item.ItemBerryBland;
import mods.microcosm.item.ItemBugSwatter;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
    public static Item bugSwatter;
    public static Item berryBland;

    public static void init()
    {
        bugSwatter = new ItemBugSwatter(1, 100, "bug_swatter");
        berryBland = new ItemBerryBland(64,0,"bland_berry");

        register(bugSwatter);
        register(berryBland);
    }

    private static void register(Item item)
    {
        GameRegistry.register(item);
        Microcosm.proxy.registerItemModel(item, item.getUnlocalizedName());
    }
}
