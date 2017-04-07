package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.block.BlockMod;
import mods.microcosm.block.BlockModOre;
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
    //We can't register these in preInit, so we store every registered Item in this and register them later.
    public static ArrayList<Block> blocksForModel = new ArrayList<Block>(200);

    public static Block oreTest;
    public static Block chimericalAlloyFurnace;

    public static void init()
    {
        oreTest = new BlockModOre(Material.ROCK, 3, "ore_test");
        register(oreTest);
        OreDictionary.registerOre("oreTest", oreTest);

        chimericalAlloyFurnace = new BlockChimericalAlloyFurnace(Material.IRON, "chimerical_alloy_furnace");
        register(chimericalAlloyFurnace);
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
