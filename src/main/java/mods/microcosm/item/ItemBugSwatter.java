package mods.microcosm.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class ItemBugSwatter extends ItemMod
{
    public ItemBugSwatter(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    public ActionResult<ItemStack> onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ,
                SoundEvents.ENTITY_EXPERIENCE_ORB_TOUCH, SoundCategory.PLAYERS,
                0.8F, worldIn.rand.nextFloat() * 0.1F + 0.9F);
        //System.out.println("Hellur.");

        return new ActionResult(EnumActionResult.PASS, itemStackIn);
    }
}