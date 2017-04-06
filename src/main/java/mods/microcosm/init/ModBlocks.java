package mods.microcosm.init;

import mods.microcosm.Microcosm;
import mods.microcosm.block.BlockModOre;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

public class ModBlocks
{
    public static Block oreTest;

    public static void init()
    {
        oreTest = new BlockModOre(Material.ROCK, 3, "ore_test");
        register(oreTest);
        OreDictionary.registerOre("oreTest", oreTest);
    }

    public static void initTE()
    {

    }

    private static void register(Block block)
    {
        GameRegistry.register(block);
        GameRegistry.register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        Microcosm.proxy.registerBlockModel(block, block.getUnlocalizedName());
    }

    private static void registerWithoutItemBlock(Block block)
    {
        GameRegistry.register(block);
        Microcosm.proxy.registerBlockModel(block, block.getUnlocalizedName());
    }
}
