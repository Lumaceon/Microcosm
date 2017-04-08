package mods.microcosm.item;

import mods.microcosm.Microcosm;
import mods.microcosm.util.ISimpleNamed;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;

public class ItemModSeedFood extends ItemSeedFood implements ISimpleNamed
{

    protected String simpleName;

    public ItemModSeedFood(int healAmount, float saturation, Block crops, Block soil, String name) {
        super(healAmount, saturation, crops, soil);

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
    public String getUnlocalizedName() {
        return this.getRegistryName().toString();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        return this.getUnlocalizedName();
    }

    @Override
    public String getSimpleName() {
        return this.simpleName;
    }
}
