package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.item.ItemBerryBland;
import mods.microcosm.item.ItemBugSwatter;
import mods.microcosm.util.ISimpleNamed;
import mods.microcosm.item.ItemPot;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModItems
{
    //We can't register these in preInit, so we store every registered Item in this and register them later.
    public static ArrayList<Item> itemsForModel = new ArrayList<Item>(200);

    public static Item bugSwatter;
    public static Item berryBland;
    public static Item pot;

    public static void init()
    {
        bugSwatter = new ItemBugSwatter(1, 100, "bug_swatter");
        berryBland = new ItemBerryBland(64,0,"bland_berry");
        pot = new ItemPot(1,200,"pot");

        register(bugSwatter);

        berryBland = new ItemBerryBland(64, 0, "bland_berry");
        register(berryBland);
        register(pot);

    }

    private static void register(Item item)
    {
        GameRegistry.register(item);
        itemsForModel.add(item);
    }

    public static void initModels()
    {
        if(itemsForModel != null)
        {
            for(Item item : itemsForModel)
                if(item != null && item instanceof ISimpleNamed)
                    Microcosm.proxy.registerItemModel(item, ((ISimpleNamed) item).getSimpleName());
            itemsForModel.clear();
        }
        itemsForModel = null;
    }
}
