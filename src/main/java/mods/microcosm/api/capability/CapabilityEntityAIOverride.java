package mods.microcosm.api.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import java.util.concurrent.Callable;

/**
 * Capability for entities to override their behavior. An AI override makes use of Schema, which can be thought of as
 * individual pieces of behavior for the mob: each one usually instructs how to add it's AI to the AI list when the
 * entity is constructed.
 */
public class CapabilityEntityAIOverride
{
    @CapabilityInject(IEntityAIOverride.class)
    public static final Capability<IEntityAIOverride> ENTITY_AI_OVERRIDE_CAPABILITY = null;

    public static class DefeaultAIOverrideProvider implements ICapabilitySerializable<NBTTagCompound>
    {
        EntityAIOverrideHandler defaultImplementation = new EntityAIOverrideHandler();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return ENTITY_AI_OVERRIDE_CAPABILITY != null && ENTITY_AI_OVERRIDE_CAPABILITY == capability;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if(hasCapability(capability, facing))
                return ENTITY_AI_OVERRIDE_CAPABILITY.cast(defaultImplementation);
            return null;
        }

        @Override
        public NBTTagCompound serializeNBT() {
            return defaultImplementation.serializeNBT();
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            defaultImplementation.deserializeNBT(nbt);
        }
    }

    public static void register()
    {
        CapabilityManager.INSTANCE.register(IEntityAIOverride.class, new Capability.IStorage<IEntityAIOverride>()
        {
            @Override
            public NBTBase writeNBT(Capability<IEntityAIOverride> capability, IEntityAIOverride instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IEntityAIOverride> capability, IEntityAIOverride instance, EnumFacing side, NBTBase base) {
                instance.deserializeNBT((NBTTagCompound) base);
            }
        }, new Callable<IEntityAIOverride>()
        {
            @Override
            public EntityAIOverrideHandler call() throws Exception {
                return new EntityAIOverrideHandler();
            }
        });
    }
}
