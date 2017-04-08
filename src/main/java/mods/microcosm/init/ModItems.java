package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.item.ItemModSeedFood;
import mods.microcosm.util.ISimpleNamed;
import mods.microcosm.item.ItemBugSwatter;
import mods.microcosm.item.ItemPot;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;

public class ModItems
{
    // we can't register these in preInit, so we store every registered Item in this and register them later.
    public static ArrayList<Item> itemsForModel = new ArrayList<Item>(200);

	// let's try to keep these alphabetical for sanity's sake.
    public static Item berryBoost;
	public static Item berryHasty;
    public static Item berryIcy;
    public static Item berryRegen;

    public static Item bugSwatter;
    public static Item pot;

    public static void init()
    {
		bugSwatter = new ItemBugSwatter(1, 100, "bugSwatter");
        pot = new ItemPot(1,200,"pot");

        berryBoost = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryBoost")
                .setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 300, 0), 1F);
        berryHasty = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryHasty")
                .setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.HASTE, 300, 0), 1F);
        berryIcy = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.SNOW,"berryIcy")
                .setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0), 1F);
        berryRegen = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryRegen")
                .setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 0), 1F);

        // register
        register(bugSwatter);
        register(pot);

		register(berryBoost);
        register(berryHasty);
        register(berryIcy);
        register(berryRegen);

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
