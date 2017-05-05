package mods.microcosm.creativetab;

import mods.microcosm.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import java.util.ArrayList;
import java.util.List;

public class CreativeTabMicrocosm extends CreativeTabs
{
    List<ItemStack> list;
    public ArrayList<Item> itemsToDisplay = new ArrayList<Item>();
    public ArrayList<Block> blocksToDisplay = new ArrayList<Block>();

    public CreativeTabMicrocosm(String label) {
        super(label);
        setBackgroundImageName("item_search.png");
    }

    @Override
    public Item getTabIconItem() {
        return ModItems.pot;
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
    public void displayAllRelevantItems(List<ItemStack> list)
    {
        this.list = list;

        for(Item i : itemsToDisplay)
            if(i != null)
                addItem(i);

        for(Block i : blocksToDisplay)
            if(i != null)
                addBlock(i);
    }

}
