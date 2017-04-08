package mods.microcosm.item.berries;

import mods.microcosm.item.ItemModSeedFood;
import net.minecraft.block.Block;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class ItemBerry extends ItemModSeedFood
{

    public ItemBerry(int healAmount, float saturation, Block crops, Block soil, String name,
                     int duration, int amplifier, float probability) {

        super(healAmount, saturation, crops, soil, name);

        PotionEffect potionEffect = new PotionEffect(MobEffects.JUMP_BOOST, duration, amplifier);

        this.setAlwaysEdible();
        this.setPotionEffect(new potionEffect, probability);
    }
}