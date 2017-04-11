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
    public static ChimericalAlloyRecipe luminiumRecipe = new ChimericalAlloyRecipe("luminium", new ItemStack(ModItems.ingotLuminium));
    private static void initChimericalAlloyRecipes()
    {
        ChimericalAlloyRecipes.register(animataniumRecipe);
        ChimericalAlloyRecipes.register(luminiumRecipe);
    }
}
