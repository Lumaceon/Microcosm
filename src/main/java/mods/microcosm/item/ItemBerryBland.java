package mods.microcosm.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBerryBland extends ItemModFood
{
    public ItemBerryBland(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood, name);
    }
}