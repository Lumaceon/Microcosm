package mods.microcosm.microcosm.chimericalfurnace.recipe;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChimericalAlloyRecipes
{
    private static Random random = new Random();
    private static ArrayList<String> defaults = new ArrayList<String>(); //The potentials that 'may' be possible.
    private static ArrayList<String> chimericalAlloyMetals = new ArrayList<String>(); //The metals confirmed to be possible.

    private static ArrayList<ChimericalAlloyRecipe> recipes = new ArrayList<ChimericalAlloyRecipe>();

    /**
     * Used to determine how many of the items passed in via the second parameter match the recipe of the first
     * parameter. Note that duplicates ARE considered invalid the second time they appear in inventory. Thus, this only
     * counts UNIQUE items.
     * @return The number of unique, valid items found in the inventory for the recipe.
     */
    public static int getNumberOfValidItems(ChimericalAlloyRecipe recipe, ItemStack[] inventory)
    {
        if(inventory.length <= 0)
            return 0;

        int foundItems = 0;
        for(String s : recipe.components)
            for(int n = 0; n < 6; n++)
                if(n < inventory.length)
                {
                    List<ItemStack> itemList = OreDictionary.getOres(s);
                    if(itemList != null && itemList.size() > 0 && OreDictionary.itemMatches(inventory[n], itemList.get(0), false))
                    {
                        ++foundItems;
                        if(foundItems >= inventory.length)
                            return foundItems;
                        break;
                    }
                }

        return foundItems;
    }

    public static boolean isChimericalAlloyMetal(ItemStack stack)
    {
        if(stack == null)
            return false;

        for(String s : chimericalAlloyMetals)
        {
            List<ItemStack> itemList = OreDictionary.getOres(s);
            if(itemList != null && itemList.size() > 0 && OreDictionary.itemMatches(stack, itemList.get(0), false))
                return true;
        }
        return false;
    }

    public static void register(ChimericalAlloyRecipe recipe) {
        recipes.add(recipe);
    }

    public static ArrayList<ChimericalAlloyRecipe> getRecipes() {
        return recipes;
    }

    /**
     * Lists the metals that we want to use if they are available in this MC instance.
     */
    public static void preInit()
    {
        defaults.add("ingotIron");
        defaults.add("ingotGold");
        defaults.add("ingotSilver");
        defaults.add("ingotAluminum");
        defaults.add("ingotAluminumBrass");
        defaults.add("ingotInvar");
        defaults.add("ingotDarkSteel");
        defaults.add("ingotElectrum");
        defaults.add("ingotLead");
        defaults.add("ingotPlatinum");
        defaults.add("ingotSteel");
        defaults.add("ingotThaumium");
        defaults.add("ingotTin");
        defaults.add("ingotCopper");
        defaults.add("ingotBronze");
        defaults.add("ingotZinc");
        defaults.add("ingotBrass");
        defaults.add("ingotCosmic");
    }

    /**
     * Checks to see what metals are actually in this MC instance and adds them to an ArrayList. These will be the final
     * potential metals which can be researched and may be a part of a chimerical alloy.
     */
    public static void postInit() {
        for (String s : defaults)
            if (s != null && OreDictionary.doesOreNameExist(s))
                chimericalAlloyMetals.add(s);
    }

    /**
     * Generates new, random alloy recipes.
     */
    public static void generateNewRecipes()
    {
        if(chimericalAlloyMetals.size() < 6)
        {
            System.out.println("[Microcosm] ERROR: Number of chimerical alloy metals lower than 6. If this error occurs, someone really screwed up.");
            return;
        }

        ArrayList<String> metals;
        boolean conflict;
        int emergencyConflictExit = 0; //If we try to resolve conflicts over 9000 times, exit a potentially infinite loop.

        for(int i = 0; i < recipes.size(); i++)
        {
            ChimericalAlloyRecipe recipe = recipes.get(i);
            metals = (ArrayList<String>) chimericalAlloyMetals.clone();
            conflict = i != 0;
            int offset = 0;

            if(recipe == null)
                continue;
            if(recipe.components == null || recipe.components.length != 6)
                recipe.components = new String[6];

            //Generate recipe.
            for(int n = 0; n < 6; n++)
            {
                int metalIndex = random.nextInt(metals.size());
                recipe.components[n] = metals.get(metalIndex);
                metals.remove(metalIndex);
            }

            //Check for duplicate recipes, which won't run the first time through for obvious reasons.
            while(conflict && emergencyConflictExit <= 9000)
            {
                emergencyConflictExit++;
                for(int ii = 0; ii < i; ii++)
                {
                    ChimericalAlloyRecipe conflictRecipe = recipes.get(ii);
                    for(String s : recipe.components) //Loop through current recipe.
                    {
                        boolean found = false;
                        for(String s2 : conflictRecipe.components)
                            if(s.equals(s2))
                                found = true;
                        if(!found) //We found a metal that isn't in eternium; the recipe doesn't conflict.
                            conflict = false;
                    }
                }

                //This recipe conflicts with another recipe, so we need to change it.
                if(conflict)
                {
                    metals = (ArrayList<String>) chimericalAlloyMetals.clone();
                    for(int n = 0; n < 6; n++)
                    {
                        //Essentially makes a recipe from 0 to 5 and shifts the range up by 1 each time it conflicts.
                        int metalIndex = (n + offset) % chimericalAlloyMetals.size();
                        recipe.components[n] = metals.get(metalIndex);
                        metals.remove(metalIndex);
                    }
                    ++offset; //If this ends up returning to conflict, we offset the default recipe.
                }
            }

            if(emergencyConflictExit >= 1000)
                System.out.println("[Microcosm] ERROR: Chimerical alloy recipe tried to resolve conflicts over 9000 times. This likely occurred due to a lack of ingots in your MC instance.");
        }
    }

    /**
     * Tries to load experimental alloy recipes from the given String arrays.
     * @return True if these were successfully loaded, false if something went wrong; usually a missing metal.
     */
    public static boolean areRecipesProperlyLoaded()
    {
        for(ChimericalAlloyRecipe recipe : recipes)
        {
            if(recipe.components.length < 6)
                return false;
            for(String s : recipe.components)
                if(s == null || !OreDictionary.doesOreNameExist(s))
                    return false;
        }
        return true;
    }
}
