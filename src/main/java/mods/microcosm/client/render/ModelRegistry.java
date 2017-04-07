package mods.microcosm.client.render;

import mods.microcosm.lib.Reference;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

public class ModelRegistry
{
    public static void registerItemBlockModel(Block block, String registryName) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID + ":" + registryName, "inventory"));
    }

    public static void registerItemBlockCustomModel(Block block, String registryName) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Reference.MOD_ID.toLowerCase() + ":" + registryName, "inventory"));
    }

    public static void registerItemModel(Item item, String registryName) {
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(Reference.MOD_ID + ":" + registryName, "inventory"));
    }
}
