package mods.microcosm.item;

import mods.microcosm.api.capability.CapabilityEntityContainer;
import mods.microcosm.api.capability.IEntityContainer;
import mods.microcosm.api.entity.EntityStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemMobCapsule extends ItemMod
{
    public ItemMobCapsule(int maxStack, int maxDamage, String name) {
        super(maxStack, maxDamage, name);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced)
    {
        IEntityContainer cap = stack.getCapability(CapabilityEntityContainer.ENTITY_CONTAINER_CAPABILITY, EnumFacing.DOWN);
        if(cap != null)
        {
            tooltip.add("Size: " + cap.getMaxCapacity());
            EntityStack[] ents = cap.getEntities();
            for(EntityStack e : ents)
                tooltip.add(e.getEntityTypeDisplayName());
        }
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        if(!worldIn.isRemote)
        {
            IEntityContainer cap = stack.getCapability(CapabilityEntityContainer.ENTITY_CONTAINER_CAPABILITY, EnumFacing.DOWN);
            if(cap != null && cap.getEntityCount() > 0)
            {
                EntityStack entityStack = cap.extractEntity(0);
                if(entityStack != null)
                {
                    Entity e = entityStack.createEntityForWorld(worldIn);

                    e.setPosition(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
                    worldIn.spawnEntityInWorld(e);
                    return EnumActionResult.SUCCESS;
                }
            }
        }
        return EnumActionResult.PASS;
    }

    @Override
    public net.minecraftforge.common.capabilities.ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
        return new CapabilityEntityContainer.DefeaultEntityContainerProvider();
    }
}
