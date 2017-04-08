package mods.microcosm.creativetab;

import mods.microcosm.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class CreativeTabMicrocosm extends CreativeTabs
{
    public CreativeTabMicrocosm(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.bugSwatter;
    }

    @Override
    public boolean hasSearchBar(){
        return true;
    }

    // trying to get sorting to work >_>
    /*
    @Override
    public void displayAllRelevantItems(List<ItemStack> list){
        for (Item item : ModItems.init();){

        }
    }
    */
}
