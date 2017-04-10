package mods.microcosm.block.itemblock;

import mods.microcosm.util.Colors;
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
        String name = super.getItemStackDisplayName(stack);
        String ret = "";
        String temp;
        byte color = (byte) ((System.currentTimeMillis() / 100) % (name.length() + 6));
        for(int i = 0; i < name.length(); i++)
        {
            switch (color)
            {
                case 0:
                case 1:
                    temp = Colors.WHITE;
                    break;
                case 2:
                case 3:
                    temp = Colors.GREY;
                    break;
                case 4:
                case 5:
                    temp = Colors.DARK_GREY;
                    break;
                default:
                    temp = Colors.BLACK;
                    break;
            }
            color = (byte) ((color - 1) % (name.length() + 6));
            ret = ret + temp + name.substring(i, i+1);
        }

        return ret;
    }
}
