package mods.microcosm.item.berries;

import mods.microcosm.item.ItemModFood;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemBerryBoost extends ItemModFood
{
    public ItemBerryBoost(int amount, float saturation, boolean isWolfFood, String name) {
        super(amount, saturation, isWolfFood, name);
    }

    @Override
    protected void onFoodEaten(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(8), 5400, 0));
        }
    }
}