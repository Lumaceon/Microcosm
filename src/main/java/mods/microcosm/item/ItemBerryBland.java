package mods.microcosm.item;

public class ItemBerryBland extends ItemMod
{
    public ItemBerryBland(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);

        this.setMaxStackSize(16);
        this.setMaxDamage(0);
        this.setUnlocalizedName("Bland Berry");
    }
}