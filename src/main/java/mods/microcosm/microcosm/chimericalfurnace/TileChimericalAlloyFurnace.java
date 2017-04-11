package mods.microcosm.microcosm.chimericalfurnace;

import mods.microcosm.init.ModItems;
import mods.microcosm.item.ItemExperimentalIngot;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipe;
import mods.microcosm.microcosm.chimericalfurnace.recipe.ChimericalAlloyRecipes;
import mods.microcosm.tile.TileModInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;

public class TileChimericalAlloyFurnace extends TileModInventory implements ITickable
{
    public int timer = 0;

    public TileChimericalAlloyFurnace() {
        super(7, 64);
    }

    @Override
    public void update()
    {
        if(timer % 100 == 0)
        {
            ItemStack[] inventory = new ItemStack[6];
            for(int i = 0; i < 6; i++)
                inventory[i] = this.getStackInSlot(i);

            ArrayList<String> experimentalIngotDisplay = new ArrayList<String>();
            ArrayList<ChimericalAlloyRecipe> recipes = ChimericalAlloyRecipes.getRecipes();
            ItemStack resultSlot = this.getStackInSlot(6);
            for(ChimericalAlloyRecipe recipe : recipes)
            {
                int metals = ChimericalAlloyRecipes.getNumberOfValidItems(recipe, inventory);
                if(metals == 6 && (resultSlot == null || OreDictionary.itemMatches(recipe.result, resultSlot, false)))
                {
                    boolean newItemCreated = false;

                    if(resultSlot == null)
                    {
                        this.setInventorySlotContents(6, recipe.result.copy());
                        newItemCreated = true;
                    }
                    else if(resultSlot.getMaxStackSize() - resultSlot.stackSize >= recipe.result.stackSize)
                    {
                        resultSlot.stackSize += recipe.result.stackSize;
                        newItemCreated = true;
                    }

                    if(newItemCreated)
                        for(int i = 0; i < 6; i++)
                            this.decrStackSize(i, 1);
                    return;
                }
                else if(resultSlot == null)
                {
                    experimentalIngotDisplay.add(recipe.result.getDisplayName() + ": " + (int) ((metals / 6.0F) * 100.0F) + "%");
                }
            }
            if(resultSlot == null)
            {
                String[] componentNames = new String[6];
                for(int i = 0; i < 6; i++)
                {
                    if(inventory[i] == null)
                        return; //All slots must be filled by something
                    else
                        componentNames[i] = inventory[i].getDisplayName(); //Save the display names.
                }

                //Consume the ingots.
                for(int i = 0; i < 6; i++)
                    this.decrStackSize(i, 1);

                //Create an experimental ingot. Give it the data so it remembers stuff.
                ItemStack is = new ItemStack(ModItems.ingotExperimental);
                ItemExperimentalIngot.IExIngotInfoHandler cap = is.getCapability(ItemExperimentalIngot.CapabilityExIngotHandler.CAPABILITY, EnumFacing.DOWN);
                if(cap != null)
                {
                    String[] lines = new String[experimentalIngotDisplay.size()];
                    experimentalIngotDisplay.toArray(lines);
                    cap.setLines(lines);
                    for(int i = 0; i < 6; i++)
                        cap.setComponentName(i, componentNames[i]);
                }

                this.setInventorySlotContents(6, is);
            }
            timer = 1;
        }
        else
            timer++;
    }
}
