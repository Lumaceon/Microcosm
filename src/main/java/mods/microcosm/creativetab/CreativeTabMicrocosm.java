package mods.microcosm.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CreativeTabMicrocosm extends CreativeTabs
{
    public CreativeTabMicrocosm(String label) {
        super(label);
    }

    @Override
    public Item getTabIconItem() {
        return Items.NETHER_STAR;
    }
}
