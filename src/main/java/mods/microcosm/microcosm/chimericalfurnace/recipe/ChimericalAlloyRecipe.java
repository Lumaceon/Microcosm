package mods.microcosm.microcosm.chimericalfurnace.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.INBTSerializable;

public class ChimericalAlloyRecipe implements INBTSerializable<NBTTagCompound>
{
    public String recipeID; //This should NEVER change.
    public String[] components = new String[6]; //Don't mess with this.
    public ItemStack result;

    public ChimericalAlloyRecipe(String recipeID, ItemStack result) {
        this.recipeID = recipeID;
        this.result = result;
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("recipe_id", recipeID);

        NBTTagList recipeList = new NBTTagList();
        for(String component : components)
        {
            recipeList.appendTag(new NBTTagString(component));
        }
        nbt.setTag("recipe_list", recipeList);

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt)
    {
        recipeID = nbt.getString("recipe_id");

        NBTTagList list = nbt.getTagList("recipe_list", Constants.NBT.TAG_STRING);
        for(int i = 0; i < list.tagCount(); i++)
            components[i] = list.getStringTagAt(i);
    }
}
