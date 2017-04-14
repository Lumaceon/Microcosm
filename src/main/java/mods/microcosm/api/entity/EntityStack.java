package mods.microcosm.api.entity;

import mods.microcosm.api.capability.CapabilityEntityAIOverride;
import mods.microcosm.lib.Reference;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.CapabilityDispatcher;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * This class is meant to contain the 'inventory' representation of an entity.
 *
 * An EntityStack is created via the NBT data of the entity, so pass [the entity].serializeNBT() into the constructor.
 * The primary benefit of EntityStack is to store specific data loaded from NBT, such as the entity's name, so NBT need
 * not be consulted every time you need to access it.
 *
 * For example: if, every tick, you need to get the entity's name, constructing an EntityStack will load/store that name
 * and save/load it only as necessary. If you instead needed to know its health, you'd make a subclass of EntityStack,
 * register it with EntityStack.registerCustomEntityStackClass, and have its constructor load the health from the NBT
 * tag passed in.
 */
@SuppressWarnings("deprecation")
public class EntityStack
{
    private static final HashMap<String, Class<? extends EntityStack>> ENTITY_STACK_CLASSES = new HashMap<String, Class<? extends EntityStack>>();

    public NBTTagCompound nbt = null;
    protected net.minecraftforge.common.capabilities.CapabilityDispatcher capabilities;
    protected Class<? extends Entity> entityClass = null;
    protected String entityID = "";
    protected String customName = "";

    /**
     * Used to load the appropriate data from nbt. Classes will always be instantiated via this constructor (one with
     * NBTTagCompound as the sole parameter).
     * @param nbt The tag representing this entity.
     */
    public EntityStack(NBTTagCompound nbt)
    {
        this.nbt = nbt;
        this.entityID = nbt.getString("id");
        if(nbt.hasKey("CustomName"))
            this.customName = nbt.getString("CustomName");
        this.entityClass = EntityList.NAME_TO_CLASS.get(getEntityTypeID());
        if(nbt.hasKey("ForgeCaps"))
        {
            if(this.capabilities == null)
            {
                Map<ResourceLocation, ICapabilityProvider> list = new HashMap<ResourceLocation, ICapabilityProvider>();
                list.put(new ResourceLocation(Reference.MOD_ID + ":AI_override"), new CapabilityEntityAIOverride.DefeaultAIOverrideProvider());
                capabilities = new CapabilityDispatcher(list);
            }
            this.capabilities.deserializeNBT(nbt.getCompoundTag("ForgeCaps"));
        }
    }

    public boolean hasCapability(net.minecraftforge.common.capabilities.Capability<?> capability, net.minecraft.util.EnumFacing facing) {
        return getCapability(capability, facing) != null || capabilities != null && capabilities.hasCapability(capability, facing);
    }

    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, net.minecraft.util.EnumFacing facing) {
        return capabilities == null ? null : capabilities.getCapability(capability, facing);
    }

    /**
     * ### OVERRIDE THIS IN YOUR CHILD CLASS ###
     * This should always return a unique string ID to be used for this class.
     * @return The unique string ID for this class, must be the same as registered in registerCustomEntityStackClass.
     */
    public String getEntityStackClassID() {
        return "";
    }

    public Class<? extends Entity> getEntityClass() {
        return entityClass;
    }

    /**
     * @return The string ID for this type of entity. This can be used to get the entity's class via the map
     * EntityList#NAME_TO_CLASS by using this as the key.
     */
    public String getEntityTypeID() {
        return entityID;
    }

    /**
     * @return The localized name for this type of entity.
     */
    public String getEntityTypeDisplayName() {
        return I18n.translateToLocal(entityID);
    }

    /**
     * @return Ths custom name for this entity, or null if the entity has none.
     */
    public String getEntityCustomName() {
        return customName;
    }

    /**
     * Creates an Entity class for this stack, with the passed in world as the parameter for it's constructor. This does
     * not spawn said entity, merely creates the class.
     * @return An entity loaded from this stack, but not spawned in-world yet.
     */
    public Entity createEntityForWorld(World world) {
        return EntityList.createEntityFromNBT(writeToNBT(), world);
    }

    /**
     * Saves the custom data back to nbt for both spawning the entity and saving the changes made via EntityStack.
     * @return The NBTTagCompound with the EntityStack data saved to it.
     */
    public NBTTagCompound writeToNBT()
    {
        nbt.setString("id", entityID);
        if(!customName.equals(""))
            nbt.setString("CustomName", customName);
        if (this.capabilities != null) nbt.setTag("ForgeCaps", this.capabilities.serializeNBT());
        return nbt;
    }

    /**
     * @param id The string ID for the desired EntityStack class.
     * @return The custom class extending EntityStack, or the EntityStack class if there is no key registered to the id.
     */
    public static Class<? extends EntityStack> getEntityStackClass(String id)
    {
        Class<? extends EntityStack> c = ENTITY_STACK_CLASSES.get(id);
        if(c != null)
            return c;
        return EntityStack.class;
    }

    public static void registerCustomEntityStackClass(Class<? extends EntityStack> c, String ID) {
        ENTITY_STACK_CLASSES.put(ID, c);
    }
}
