package mods.microcosm.creativetab;

import mods.microcosm.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.List;

import static mods.microcosm.init.ModBlocks.*;
import static mods.microcosm.init.ModItems.*;

public class CreativeTabMicrocosm extends CreativeTabs
{
    List<ItemStack> list;

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

    private void addItem(Item item) {
        item.getSubItems(item, this, list);
    }

    private void addBlock(Block block) {
        ItemStack stack = new ItemStack(block);
        block.getSubBlocks(stack.getItem(), this, list);
    }

    @Override
    public void displayAllRelevantItems(List<ItemStack> list){
        this.list = list;

        addItem(bugSwatter);
        addItem(pot);
        addItem(berryBoost);
        addItem(berryFire);
        addItem(berryHasty);
        addItem(berryNight);
        addItem(berryRegen);
        addItem(berrySpeed);
        addItem(berryStrength);
        addItem(berryWater);

        addBlock(oreTest);
        addBlock(chimericalAlloyFurnace);
    }

}
