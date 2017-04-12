package mods.microcosm.api.entity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class EntityAIOverrideHandler implements IEntityAIOverride
{
    protected Schema[] schema;
    protected Schema[] targets;

    @Override
    public Schema[] getSchema() {
        return new Schema[0];
    }

    @Override
    public Schema[] getTargets() {
        return new Schema[0];
    }

    @Override
    public NBTTagCompound serializeNBT()
    {
        NBTTagCompound nbt = new NBTTagCompound();

        NBTTagList list = new NBTTagList();

        for(Schema s : schema)
            if (s != null)
                list.appendTag(s.serializeNBT());
        nbt.setTag("schema", list);

        list = new NBTTagList();
        for(Schema s : targets)
            if (s != null)
                list.appendTag(s.serializeNBT());
        nbt.setTag("targets", list);

        return nbt;
    }

    @Override
    public void deserializeNBT(NBTTagCompound base)
    {
        if(base.hasKey("schema"))
        {
            NBTTagList list = base.getTagList("schema", Constants.NBT.TAG_COMPOUND);
            if(list != null)
            {
                schema = new Schema[list.tagCount()];
                for(int i = 0; i < list.tagCount(); i++)
                {
                    NBTTagCompound tag = list.getCompoundTagAt(i);
                    Schema s = Schema.getSchemaFromNBT(tag);
                    if(s != null && s.schemaType != null)
                        schema[i] = s;
                }
            }
        }

        if(base.hasKey("targets"))
        {
            NBTTagList list = base.getTagList("targets", Constants.NBT.TAG_COMPOUND);
            if(list != null)
            {
                targets = new Schema[list.tagCount()];
                for(int i = 0; i < list.tagCount(); i++)
                {
                    NBTTagCompound tag = list.getCompoundTagAt(i);
                    Schema s = Schema.getSchemaFromNBT(tag);
                    if(s != null && s.schemaType != null)
                        targets[i] = s;
                }
            }
        }
    }
}
