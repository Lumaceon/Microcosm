package mods.microcosm.extendeddata;

import mods.microcosm.lib.Reference;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipe;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.util.Constants;

@SuppressWarnings({"ConstantConditions", "NullableProblems"})
public class ExtendedMapData extends WorldSavedData
{
    private static final String ID = Reference.MOD_ID + "_map_save_data";
    private boolean alloysRegistered;
    /**
     * Every time new recipes generate, usually as a result of a broken recipe, it becomes a new "generation". This can
     * be used to warn things like the experimental ingot that their data may now be incorrect.
     */
    private int experimentalAlloyGeneration = -1;

    public ExtendedMapData() {
        super(ID);
    }

    public ExtendedMapData(String p_i2141_1_) {
        super(p_i2141_1_);
    }

    public static ExtendedMapData get(World world)
    {
        ExtendedMapData dataHandler = (ExtendedMapData) world.getMapStorage().getOrLoadData(ExtendedMapData.class, ID);
        if(dataHandler == null)
        {
            dataHandler = new ExtendedMapData();
            world.getMapStorage().setData(ID, dataHandler);
        }
        return dataHandler;
    }

    public boolean areExperimentalAlloysRegistered() {
        return alloysRegistered;
    }

    public void registerExperimentalAlloys()
    {
        System.out.println("[Microcosm] Registered new experimental alloy recipes.");
        ChimericalAlloyRecipes.generateNewRecipes();
        alloysRegistered = true;
        markDirty();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt)
    {
        nbt.setBoolean("alloys_registered", alloysRegistered);
        nbt.setInteger("alloy_generation", experimentalAlloyGeneration);

        NBTTagList list = new NBTTagList();
        for(ChimericalAlloyRecipe recipe : ChimericalAlloyRecipes.getRecipes())
        {
            list.appendTag(recipe.serializeNBT());
        }
        nbt.setTag("recipe_list", list);

        return nbt;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbt)
    {
        if(nbt.hasKey("alloys_registered"))
            this.alloysRegistered = nbt.getBoolean("alloys_registered");

        if(nbt.hasKey("alloy_generation"))
            this.experimentalAlloyGeneration = nbt.getInteger("alloy_generation");

        if(alloysRegistered)
        {
            NBTTagList list = nbt.getTagList("recipe_list", Constants.NBT.TAG_COMPOUND);
            for(int i = 0; i < list.tagCount(); i++)
            {
                NBTTagCompound recipeTag = list.getCompoundTagAt(i);
                for(ChimericalAlloyRecipe recipe : ChimericalAlloyRecipes.getRecipes())
                {
                    if(recipe.recipeID.equals(recipeTag.getString("recipe_id")))
                    {
                        recipe.deserializeNBT(recipeTag);
                        break;
                    }
                }
            }
            if(!ChimericalAlloyRecipes.areRecipesProperlyLoaded())
            {
                ChimericalAlloyRecipes.generateNewRecipes();
                experimentalAlloyGeneration++;
                markDirty();
            }
        }
    }
}
