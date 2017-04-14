package mods.microcosm.handler;

import mods.microcosm.api.capability.CapabilityEntityContainer;
import mods.microcosm.api.capability.IEntityContainer;
import mods.microcosm.api.entity.EntityStack;
import mods.microcosm.item.ItemMobCapsule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PlayerHandler
{
    @SubscribeEvent
    public void onRightclickEntity(PlayerInteractEvent.EntityInteract event)
    {
        EntityPlayer player = event.getEntityPlayer();
        ItemStack heldItem = player.inventory.getCurrentItem();
        if(!event.getEntity().worldObj.isRemote && heldItem != null && heldItem.getItem() instanceof ItemMobCapsule)
        {
            if(heldItem.hasCapability(CapabilityEntityContainer.ENTITY_CONTAINER_CAPABILITY, EnumFacing.DOWN))
            {
                Entity e = event.getTarget();
                if(e != null && !e.isDead)
                {
                    IEntityContainer cap = heldItem.getCapability(CapabilityEntityContainer.ENTITY_CONTAINER_CAPABILITY, EnumFacing.DOWN);
                    if(cap.addEntity(new EntityStack(event.getTarget().serializeNBT())))
                        event.getTarget().setDead();
                }
            }
        }
    }
}
