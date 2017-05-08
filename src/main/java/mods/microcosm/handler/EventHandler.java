package mods.microcosm.handler;

import mods.microcosm.block.crops.BlockCropBerry;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Iterator;
import java.util.List;

/**
 * Created at 11:58 AM on 5/8/2017.
 * Part of the Microcosm project.
 */
public class EventHandler {

    // I copied much of MatrexsVigil's Harvestcraft "Right Click" addon for this code...
    // There's really only so many ways to do it. >_>

    @SubscribeEvent
    public void onPlayerHarvestBerry(PlayerInteractEvent e){
        final IBlockState blockState = e.getWorld().getBlockState(e.getPos());

        if(blockState.getBlock() instanceof BlockCropBerry) {
            harvestBerryBush(blockState, e.getEntityPlayer(), e.getWorld(), e.getPos());
        }
    }

    public void harvestBerryBush(IBlockState blockState, EntityPlayer player, World world, BlockPos blockPos){
        final BlockCrops crops = (BlockCrops) blockState.getBlock();
        if(crops.isMaxAge(blockState)) {
            final ItemStack item = player.getHeldItemMainhand();
            final int fortune = EnchantmentHelper.getEnchantmentLevel(Enchantments.FORTUNE, item);

            final List<ItemStack> drops = crops.getDrops(world, blockPos, blockState, fortune);

            world.setBlockState(blockPos, crops.withAge(0));

            for(ItemStack drop : drops) {
                dropItem(drop, world, blockPos);
            }
        }
    }
    private static void dropItem(ItemStack itemStack, World world, BlockPos pos) {
        if(world.restoringBlockSnapshots || world.isRemote)
            return;

        float f = 0.5F;
        double d0 = (world.rand.nextFloat() * f) + 0.25D;
        double d1 = (world.rand.nextFloat() * f) + 0.25D;
        double d2 = (world.rand.nextFloat() * f) + 0.25D;

        final EntityItem entityItem =
                new EntityItem(world, pos.getX() + d0, pos.getY() + d1, pos.getZ() + d2, itemStack);
        entityItem.setDefaultPickupDelay();
        world.spawnEntityInWorld(entityItem);
    }
}
