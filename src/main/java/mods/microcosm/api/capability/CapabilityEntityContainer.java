package mods.microcosm.api.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import java.util.concurrent.Callable;

public class CapabilityEntityContainer
{
    @CapabilityInject(IEntityContainer.class)
    public static final Capability<IEntityContainer> ENTITY_CONTAINER_CAPABILITY = null;

    public static class DefeaultEntityContainerProvider implements ICapabilitySerializable<NBTTagCompound>
    {
        EntityContainerHandler defaultImplementation = new EntityContainerHandler();

        @Override
        public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
            return ENTITY_CONTAINER_CAPABILITY != null && ENTITY_CONTAINER_CAPABILITY == capability;
        }

        @Override
        public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
            if(hasCapability(capability, facing))
                return ENTITY_CONTAINER_CAPABILITY.cast(defaultImplementation);
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
        CapabilityManager.INSTANCE.register(IEntityContainer.class, new Capability.IStorage<IEntityContainer>()
        {
            @Override
            public NBTBase writeNBT(Capability<IEntityContainer> capability, IEntityContainer instance, EnumFacing side) {
                return instance.serializeNBT();
            }

            @Override
            public void readNBT(Capability<IEntityContainer> capability, IEntityContainer instance, EnumFacing side, NBTBase base) {
                instance.deserializeNBT((NBTTagCompound) base);
            }
        }, new Callable<IEntityContainer>()
        {
            @Override
            public EntityContainerHandler call() throws Exception {
                return new EntityContainerHandler();
            }
        });
    }
}
