package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.item.*;
import mods.microcosm.item.armor.*;
import mods.microcosm.item.weapon.*;
import mods.microcosm.util.ISimpleNamed;

import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

import static mods.microcosm.init.ModBlocks.*;

public class ModItems
{
    // We can't register these in preInit, so we store every registered Item in this and register them later.
    public static ArrayList<Item> itemsForModel = new ArrayList<Item>();

    public static Item
            // special items
            bugSwatter,
            captureGun,
            pot,
            mobCapsule,

            // berries
            berryBoost,
            berryFire,
            berryHasty,
            berryNight,
            berryRegen,
            berrySpeed,
            berryStrength,
            berryWater,

            // ingots
            ingotAlchemium,
            ingotAnimatanium,
            ingotBrass,
            ingotCopper,
            ingotCosmic,
            ingotDimentium,
            ingotElementium,
            ingotExperimental,
            ingotSteel,
            ingotZinc,

            // armor
            armorGundamHead,
            armorGundamChest,
            armorGundamLegs,
            armorGundamFeet;

    public static void init()
    {
        // special items
		bugSwatter = new ItemBugSwatter(1, 100, "bugSwatter");
        pot        = new ItemPot(1,200,"pot");
        register(bugSwatter);
        register(pot);

        // berries
        berryBoost =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryBoost,
                        Blocks.FARMLAND,
                        "berryBoost")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.JUMP_BOOST, 300, 0), 1F);
        berryFire =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryFire,
                        Blocks.FARMLAND,
                        "berryFire")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.FIRE_RESISTANCE, 300, 0), 1F);
        berryHasty =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryHasty,
                        Blocks.FARMLAND,
                        "berryHasty")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.HASTE, 300, 0), 1F);
        berryNight =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryNight,
                        Blocks.FARMLAND,
                        "berryNight")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 300, 0), 1F);

        berryRegen =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryRegen,
                        Blocks.FARMLAND,
                        "berryRegen")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.REGENERATION, 300, 0), 1F);
        berrySpeed =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerrySpeed,
                        Blocks.FARMLAND,
                        "berrySpeed")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.SPEED, 300, 0), 1F);
        berryStrength =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryStrength,
                        Blocks.FARMLAND,
                        "berryStrength")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.STRENGTH, 300, 0), 1F);
        berryWater =
                new ItemModSeedFood(2, 0.5F,
                        blockCropBerryWater,
                        Blocks.FARMLAND,
                        "berryWater")
                        .setAlwaysEdible()
                        .setPotionEffect(new PotionEffect(MobEffects.WATER_BREATHING, 300, 0), 1F);
        register(berryBoost);
        register(berryFire);
        register(berryHasty);
        register(berryNight);
        register(berryRegen);
        register(berrySpeed);
        register(berryStrength);
        register(berryWater);
        // now that the berries are init'd, we can update the bushes' yields.
        blockCropBerryBoost.setBerry(berryBoost);
        blockCropBerryFire.setBerry(berryFire);
        blockCropBerryHasty.setBerry(berryHasty);
        blockCropBerryNight.setBerry(berryNight);
        blockCropBerryRegen.setBerry(berryRegen);
        blockCropBerrySpeed.setBerry(berrySpeed);
        blockCropBerryStrength.setBerry(berryStrength);
        blockCropBerryWater.setBerry(berryWater);

        // ingots
        ingotBrass  = new ItemMod(64, 100, "ingotBrass");
        ingotCopper = new ItemMod(64, 100, "ingotCopper");
        ingotCosmic = new ItemCosmicIngot(64, 100, "ingotCosmic");
        ingotSteel  = new ItemMod(64, 100, "ingotSteel");
        ingotZinc   = new ItemMod(64, 100, "ingotZinc");
        register(ingotBrass);
        register(ingotCopper);
        register(ingotCosmic); // Expensive ingot, about as rare as a diamond. Use for mid-to-late-game recipes.
        register(ingotSteel);
        register(ingotZinc);
        OreDictionary.registerOre("ingotBrass", ingotBrass);
        OreDictionary.registerOre("ingotCopper", ingotCopper);
        OreDictionary.registerOre("ingotCosmic", ingotCosmic);
        OreDictionary.registerOre("ingotSteel", ingotSteel);
        OreDictionary.registerOre("ingotZinc", ingotZinc);
        // The following are all chimerical alloys, which are (usually) expensive and hard to get. For powerful recipes.
        ingotAlchemium    = new ItemMod(64, 100, "ingotAlchemium");
        ingotAnimatanium  = new ItemMod(64, 100, "ingotAnimatanium");
        ingotDimentium    = new ItemMod(64, 100, "ingotDimentium");
        ingotElementium   = new ItemMod(64, 100, "ingotElementium");
        ingotExperimental = new ItemExperimentalIngot(1, 100, "ingotExperimental");
        register(ingotExperimental);
        register(ingotAlchemium);   // For recipes involving alchemy, liquid, potions, and magical (or general) change.
        register(ingotAnimatanium); // For recipes involving life, intelligence, behavior, and the soul.
        register(ingotDimentium);   // For recipes involving mass storage (bigger on the inside). Also anything else to do with dimensional weirdness.
        register(ingotElementium);  // For recipes involving elements and elemental magic. Alternatively, just magic in general.
        OreDictionary.registerOre("ingotAlchemium", ingotAlchemium);
        OreDictionary.registerOre("ingotAnimatanium", ingotAnimatanium);
        OreDictionary.registerOre("ingotDimentium", ingotDimentium);
        OreDictionary.registerOre("ingotElementium", ingotElementium);

        // armor
        armorGundamHead  = new ItemModArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.HEAD, "armorGundamHead");
        armorGundamChest = new ItemModArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.CHEST, "armorGundamChest");
        armorGundamLegs  = new ItemModArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.LEGS, "armorGundamLegs");
        armorGundamFeet  = new ItemModArmor(ItemArmor.ArmorMaterial.DIAMOND, 3, EntityEquipmentSlot.FEET, "armorGundamFeet");
        register(armorGundamHead);
        register(armorGundamChest);
        register(armorGundamLegs);
        register(armorGundamFeet);

        // advanced items
        captureGun = new ItemCaptureGun("captureGun");
        mobCapsule = new ItemMobCapsule(1, 100, "mobCapsule");
        register(captureGun);
        register(mobCapsule);
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
