package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.item.ItemBugSwatter;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModItems
{
    public static Item bugSwatter;

    public static void init()
    {
        bugSwatter = new ItemBugSwatter(1, 100, "bug_swatter");
        register(bugSwatter);
    }

    private static void register(Item item)
    {
        GameRegistry.register(item);
        Microcosm.proxy.registerItemModel(item, item.getUnlocalizedName());
    }
}
