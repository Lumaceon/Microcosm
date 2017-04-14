package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.item.*;
import mods.microcosm.item.weapon.ItemCaptureGun;
import mods.microcosm.util.ISimpleNamed;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class ModItems
{
    // we can't register these in preInit, so we store every registered Item in this and register them later.
    public static ArrayList<Item> itemsForModel = new ArrayList<Item>();

	// let's try to keep these alphabetical for sanity's sake.
    public static Item berryBoost;
    public static Item berryFire;
	public static Item berryHasty;
	public static Item berryNight;
    public static Item berryRegen;
    public static Item berrySpeed;
    public static Item berryStrength;
    public static Item berryWater;

    public static Item bugSwatter;

    public static Item ingotAlchemium;
    public static Item ingotAnimatanium;
    public static Item ingotBrass;
    public static Item ingotCopper;
    public static Item ingotCosmic;
    public static Item ingotDimentium;
    public static Item ingotElementium;
    public static Item ingotExperimental;
    public static Item ingotSteel;
    public static Item ingotZinc;

    public static Item pot;
    public static Item mobCapsule;
    public static Item captureGun;

    public static void init()
    {
		bugSwatter = new ItemBugSwatter(1, 100, "bugSwatter");
        pot = new ItemPot(1,200,"pot");

        berryBoost = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryBoost").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 300, 0), 1F);
        berryFire = new ItemModSeedFood(2, 0.5F, ModBlocks.cropBerry, Blocks.FARMLAND,"berryFire").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0), 1F);
        berryHasty = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryHasty").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.HASTE, 300, 0), 1F);
        berryNight = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryNight").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0), 1F);
        berryRegen = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryRegen").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 0), 1F);
        berrySpeed = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berrySpeed").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.SPEED, 300, 0), 1F);
        berryStrength = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryStrength").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 0), 1F);
        berryWater = new ItemModSeedFood(2, 0.5F, Blocks.BEETROOTS, Blocks.FARMLAND,"berryWater").setAlwaysEdible()
                .setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0), 1F);

        ingotCosmic = new ItemCosmicIngot(64, 100, "ingotCosmic");
        ingotCopper = new ItemMod(64, 100, "ingotCopper");
        ingotZinc = new ItemMod(64, 100, "ingotZinc");
        ingotBrass = new ItemMod(64, 100, "ingotBrass");
        ingotSteel = new ItemMod(64, 100, "ingotSteel");
        ingotExperimental = new ItemExperimentalIngot(1, 100, "ingotExperimental");
        ingotAnimatanium = new ItemMod(64, 100, "ingotAnimatanium");
        ingotAlchemium = new ItemMod(64, 100, "ingotAlchemium");
        ingotDimentium = new ItemMod(64, 100, "ingotDimentium");
        ingotElementium = new ItemMod(64, 100, "ingotElementium");

        mobCapsule = new ItemMobCapsule(1, 100, "mobCapsule");
        captureGun = new ItemCaptureGun("captureGun");

        register(bugSwatter);
        register(pot);

		register(berryBoost);
        register(berryFire);
        register(berryHasty);
        register(berryNight);
        register(berryRegen);
        register(berrySpeed);
        register(berryStrength);
        register(berryWater);

        register(ingotCosmic); //Expensive ingot, about as rare as a diamond. Use for mid-to-late-game recipes.
        register(ingotCopper);
        register(ingotZinc);
        register(ingotBrass);
        register(ingotSteel);

        //The following are all chimerical alloys, which are (usually) expensive and hard to get. For powerful recipes.
        register(ingotExperimental);
        register(ingotAnimatanium); //For recipes involving life, intelligence, behavior, and the soul.
        register(ingotAlchemium); //For recipes involving alchemy, liquid, potions, and magical (or general) change.
        register(ingotDimentium); //For recipes involving mass storage (bigger on the inside). Also anything else to do with dimensional weirdness.
        register(ingotElementium); //For recipes involving elements and elemental magic. Alternatively, just magic in general.

        register(mobCapsule);
        register(captureGun);
        OreDictionary.registerOre("ingotCosmic", ingotCosmic);
        OreDictionary.registerOre("ingotCopper", ingotCopper);
        OreDictionary.registerOre("ingotZinc", ingotZinc);
        OreDictionary.registerOre("ingotBrass", ingotBrass);
        OreDictionary.registerOre("ingotSteel", ingotSteel);
        OreDictionary.registerOre("ingotAnimatanium", ingotAnimatanium);
        OreDictionary.registerOre("ingotAlchemium", ingotAlchemium);
        OreDictionary.registerOre("ingotDimentium", ingotDimentium);
        OreDictionary.registerOre("ingotElementium", ingotElementium);
    }

    private static void register(Item item)
    {
        GameRegistry.register(item);
        itemsForModel.add(item);
        Microcosm.instance.CREATIVE_TAB.itemsToDisplay.add(item);
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
