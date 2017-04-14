package mods.microcosm.api.capability;

import mods.microcosm.api.entity.Schema;
import net.minecraft.nbt.NBTTagCompound;

public interface IEntityAIOverride
{
    public Schema[] getSchema();
    public Schema[] getTargets();

    public NBTTagCompound serializeNBT();
    public void deserializeNBT(NBTTagCompound base);
}
