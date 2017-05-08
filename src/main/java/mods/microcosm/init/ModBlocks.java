package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.block.BlockModOre;
import mods.microcosm.block.crops.*;
import mods.microcosm.block.itemblock.ItemBlockCosmicOre;
import mods.microcosm.microcosm.chimericalfurnace.BlockChimericalAlloyFurnace;
import mods.microcosm.microcosm.chimericalfurnace.TileChimericalAlloyFurnace;
import mods.microcosm.util.ISimpleNamed;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class ModBlocks
{
    // We can't register these in preInit, so we store every registered Item in this and register them later.
    public static ArrayList<Block> blocksForModel = new ArrayList<Block>(200);

    public static Block
            chimericalAlloyFurnace,
            oreCosmic,
            oreCopper,
            oreZinc;

    public static BlockCropBerry
            blockCropBerryBoost,
            blockCropBerryFire,
            blockCropBerryHasty,
            blockCropBerryNight,
            blockCropBerryRegen,
            blockCropBerrySpeed,
            blockCropBerryStrength,
            blockCropBerryWater;

    public static void init()
    {
        // alphabetical inits
        oreCopper = new BlockModOre(Material.ROCK, 1, "oreCopper");
        oreCosmic = new BlockModOre(Material.ROCK, 3, "oreCosmic");
        oreZinc   = new BlockModOre(Material.ROCK, 1, "oreZinc");

        // alphabetical normal registrations
        register(oreCopper);
        register(oreZinc);

        // alphabetical special registrations
        registerWithoutItemBlock(oreCosmic);
        GameRegistry.register(new ItemBlockCosmicOre(oreCosmic, 64, 1, ((ISimpleNamed)oreCosmic).getSimpleName()));

        // alphabetical oredict registrations
        OreDictionary.registerOre("oreCopper", oreCopper);
        OreDictionary.registerOre("oreCosmic", oreCosmic);
        OreDictionary.registerOre("oreZinc", oreZinc);

        // TE's
        chimericalAlloyFurnace = new BlockChimericalAlloyFurnace(Material.IRON, "chimericalAlloyFurnace");
        register(chimericalAlloyFurnace);

        // berry inits & registrations
        blockCropBerryBoost    = new BlockCropBerry("blockCropBerryBoost");
        blockCropBerryFire     = new BlockCropBerry("blockCropBerryFire");
        blockCropBerryHasty    = new BlockCropBerry("blockCropBerryHasty");
        blockCropBerryNight    = new BlockCropBerry("blockCropBerryNight");
        blockCropBerryRegen    = new BlockCropBerry("blockCropBerryRegen");
        blockCropBerrySpeed    = new BlockCropBerry("blockCropBerrySpeed");
        blockCropBerryStrength = new BlockCropBerry("blockCropBerryStrength");
        blockCropBerryWater    = new BlockCropBerry("blockCropBerryWater");
        registerTechnical(blockCropBerryBoost);/*
        registerTechnical(blockCropBerryFire);
        registerTechnical(blockCropBerryHasty);
        registerTechnical(blockCropBerryNight);
        registerTechnical(blockCropBerryRegen);
        registerTechnical(blockCropBerrySpeed);
        registerTechnical(blockCropBerryStrength);
        registerTechnical(blockCropBerryWater);*/
    }

    public static void initTE()
    {
        GameRegistry.registerTileEntity(TileChimericalAlloyFurnace.class, ((ISimpleNamed) chimericalAlloyFurnace).getSimpleName());
    }

    private static void register(Block block)
    {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        blocksForModel.add(block);
        Microcosm.instance.CREATIVE_TAB.blocksToDisplay.add(block);
    }

    /**
     * By default, register also includes an ItemBlock (block are held in the inventory as ItemBlocks, which extend
     * Item). This will skip the ItemBlock and only register the Block, making it unable to be held in the inventory.
     *
     * Generally you would want to use this when you want to register your own custom ItemBlock for the block.
     */
    private static void registerWithoutItemBlock(Block block)
    {
        GameRegistry.register(block);
        blocksForModel.add(block);
        Microcosm.instance.CREATIVE_TAB.blocksToDisplay.add(block);
    }

    /**
     * Registers a block without adding it to the creative menu.
     * Used for technical block or things to keep hidden.
     * An even more skimmed-down version of the above method.
     * @param block
     */
    private static void registerTechnical(Block block)
    {
        GameRegistry.register(block);
        blocksForModel.add(block);
    }

    public static void initModels()
    {
        if(blocksForModel != null)
        {
            for(Block block : blocksForModel)
                if(block != null && block instanceof ISimpleNamed)
                    Microcosm.proxy.registerBlockModel(block, ((ISimpleNamed) block).getSimpleName());
            blocksForModel.clear();
        }
        blocksForModel = null;
    }
}
