package mods.microcosm.item;

import net.minecraft.item.EnumAction;

public class ItemBerryBland extends ItemMod
{
    public ItemBerryBland(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    public EnumAction getItemUseAction() {
        return EnumAction.NONE;
    }
}