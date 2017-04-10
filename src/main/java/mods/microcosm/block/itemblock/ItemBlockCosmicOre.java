package mods.microcosm.block.itemblock;

import mods.microcosm.util.StringAnimations;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class ItemBlockCosmicOre extends ItemBlockMod
{
    public ItemBlockCosmicOre(Block block, int maxStack, int maxDamage, String name) {
        super(block, maxStack, maxDamage, name);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        return StringAnimations.cosmicLightScroll(super.getItemStackDisplayName(stack));
    }
}
