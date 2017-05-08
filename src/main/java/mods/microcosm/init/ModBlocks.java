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

    public static Block oreCosmic;
    public static Block oreCopper;
    public static Block oreZinc;
    public static Block chimericalAlloyFurnace;

    public static BlockCropBerry blockCropBerryBoost;
    public static BlockCropBerry blockCropBerryFire;
    public static BlockCropBerry blockCropBerryHasty;
    public static BlockCropBerry blockCropBerryNight;
    public static BlockCropBerry blockCropBerryRegen;
    public static BlockCropBerry blockCropBerrySpeed;
    public static BlockCropBerry blockCropBerryStrength;
    public static BlockCropBerry blockCropBerryWater;

    public static void init()
    {
        oreCosmic = new BlockModOre(Material.ROCK, 3, "oreCosmic");
        registerWithoutItemBlock(oreCosmic);
        GameRegistry.register(new ItemBlockCosmicOre(oreCosmic, 64, 1, ((ISimpleNamed)oreCosmic).getSimpleName()));
        OreDictionary.registerOre("oreCosmic", oreCosmic);

        oreCopper = new BlockModOre(Material.ROCK, 1, "oreCopper");
        register(oreCopper);
        OreDictionary.registerOre("oreCopper", oreCopper);

        oreZinc = new BlockModOre(Material.ROCK, 1, "oreZinc");
        register(oreZinc);
        OreDictionary.registerOre("oreZinc", oreZinc);

        chimericalAlloyFurnace = new BlockChimericalAlloyFurnace(Material.IRON, "chimericalAlloyFurnace");
        register(chimericalAlloyFurnace);

        // these don't need a name, nor should they be registered.
        blockCropBerryBoost    = new BlockCropBerry("");
        blockCropBerryFire     = new BlockCropBerry("");
        blockCropBerryHasty    = new BlockCropBerry("");
        blockCropBerryNight    = new BlockCropBerry("");
        blockCropBerryRegen    = new BlockCropBerry("");
        blockCropBerrySpeed    = new BlockCropBerry("");
        blockCropBerryStrength = new BlockCropBerry("");
        blockCropBerryWater    = new BlockCropBerry("");
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
     * By default, register also includes an ItemBlock (blocks are held in the inventory as ItemBlocks, which extend
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
