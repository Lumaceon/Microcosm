package mods.microcosm.block.itemblock;

import mods.microcosm.Microcosm;
import mods.microcosm.util.ISimpleNamed;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

@SuppressWarnings("NullableProblems")
public class ItemBlockMod extends ItemBlock implements ISimpleNamed
{
    protected String simpleName;

    public ItemBlockMod(Block block, int maxStack, int maxDamage, String name)
    {
        super(block);
        this.setMaxStackSize(maxStack);
        this.setMaxDamage(maxDamage);
        this.setNoRepair();
        this.setCreativeTab(Microcosm.instance.CREATIVE_TAB);
        this.simpleName = name;
        this.setRegistryName(name);
        this.setUnlocalizedName(name);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        return I18n.translateToLocal(this.getUnlocalizedName(stack));
    }

    @Override
    public String getSimpleName() {
        return this.simpleName;
    }
}
