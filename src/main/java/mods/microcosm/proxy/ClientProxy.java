package mods.microcosm.proxy;

import mods.microcosm.client.GuiHandler;
import mods.microcosm.client.render.ModelRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{
    @Override
    public void preInit(FMLPreInitializationEvent event)
    {
        //OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
    }

    @Override
    public void registerBlockModel(Block block, String registryName) {
        ModelRegistry.registerItemBlockModel(block, registryName);
    }

    @Override
    public void registerItemModel(Item item, String registryName) {
        ModelRegistry.registerItemModel(item, registryName);
    }
}
