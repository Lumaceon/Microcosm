package mods.microcosm.api.entity;

import net.minecraft.nbt.NBTTagCompound;

public class Schema
{
    public SchemaType schemaType;

    protected Schema() {}

    public Schema(SchemaType type) {
        this.schemaType = type;
    }

    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();
        if(schemaType != null)
            nbt.setString("ID", schemaType.uniqueID);
        return nbt;
    }

    public void deserializeNBT(NBTTagCompound nbt) {
        schemaType = SchemaRegistry.getSchemaTypeFromID(nbt.getString("ID"));
    }

    public static Schema getSchemaFromNBT(NBTTagCompound nbt)
    {
        Schema ret = null;
        SchemaType type = SchemaRegistry.getSchemaTypeFromID(nbt.getString("ID"));
        if(type == null)
            return null;

        try {
            ret = type.getSchemaClass().newInstance();
            ret.deserializeNBT(nbt);
        } catch (InstantiationException e) {
            System.err.println("[Microcosm] Failed to instantiate Schema class for SchemaType with ID: " + type.uniqueID);
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return ret;
    }
}
