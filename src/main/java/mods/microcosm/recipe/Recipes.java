package mods.microcosm.recipe;

import mods.microcosm.init.ModItems;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipe;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipes;
import net.minecraft.item.ItemStack;

public class Recipes
{
    public static void init()
    {
        initChimericalAlloyRecipes();
    }

    public static ChimericalAlloyRecipe animataniumRecipe = new ChimericalAlloyRecipe("animatanium", new ItemStack(ModItems.ingotAnimatanium));
    public static ChimericalAlloyRecipe alchemiumRecipe = new ChimericalAlloyRecipe("alchemium", new ItemStack(ModItems.ingotAlchemium));
    public static ChimericalAlloyRecipe dimentiumRecipe = new ChimericalAlloyRecipe("dimentium", new ItemStack(ModItems.ingotDimentium));
    public static ChimericalAlloyRecipe elementiumRecipe = new ChimericalAlloyRecipe("elementium", new ItemStack(ModItems.ingotElementium));
    private static void initChimericalAlloyRecipes()
    {
        ChimericalAlloyRecipes.register(animataniumRecipe);
        ChimericalAlloyRecipes.register(alchemiumRecipe);
        ChimericalAlloyRecipes.register(dimentiumRecipe);
        ChimericalAlloyRecipes.register(elementiumRecipe);
    }
}
