package mods.microcosm;

import mods.microcosm.creativetab.CreativeTabMicrocosm;
import mods.microcosm.init.ModBlocks;
import mods.microcosm.init.ModItems;
import mods.microcosm.lib.Reference;
import mods.microcosm.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.Random;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class Microcosm
{
    public static Random random = new Random();

    @Mod.Instance(Reference.MOD_ID)
    public static Microcosm instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static CommonProxy proxy;

    public final CreativeTabs CREATIVE_TAB = new CreativeTabMicrocosm("Microcosm");

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ModItems.init();
        ModBlocks.init();
        ModBlocks.initTE();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        ModItems.initModels();
        ModBlocks.initModels();

        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}
