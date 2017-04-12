package mods.microcosm.item;

import mods.microcosm.util.StringAnimations;
import net.minecraft.item.ItemStack;

public class ItemCosmicIngot extends ItemMod
{
    public ItemCosmicIngot(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return StringAnimations.cosmicLightScroll(super.getItemStackDisplayName(stack));
    }
}
