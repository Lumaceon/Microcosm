package mods.microcosm;

import mods.microcosm.client.GuiHandler;
import mods.microcosm.creativetab.CreativeTabMicrocosm;
import mods.microcosm.handler.PlayerHandler;
import mods.microcosm.handler.WorldHandler;
import mods.microcosm.init.ModBlocks;
import mods.microcosm.init.ModCapabilities;
import mods.microcosm.init.ModItems;
import mods.microcosm.lib.Reference;
import mods.microcosm.proxy.CommonProxy;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipes;
import mods.microcosm.recipe.Recipes;
import net.minecraftforge.common.MinecraftForge;
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

    public final CreativeTabMicrocosm CREATIVE_TAB = new CreativeTabMicrocosm("Microcosm");

    @Mod.EventHandler
    public void preInitialize(FMLPreInitializationEvent event)
    {
        ModCapabilities.init();
        ModItems.init();
        ModBlocks.init();
        ModBlocks.initTE();

        ChimericalAlloyRecipes.preInit();

        proxy.preInit(event);
    }

    @Mod.EventHandler
    public void initialize(FMLInitializationEvent event)
    {
        ModItems.initModels();
        ModBlocks.initModels();

        Recipes.init();
        MinecraftForge.EVENT_BUS.register(new WorldHandler());
        MinecraftForge.EVENT_BUS.register(new PlayerHandler());

        new GuiHandler();

        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInitialize(FMLPostInitializationEvent event)
    {
        ChimericalAlloyRecipes.postInit();
        proxy.postInit(event);
    }
}
