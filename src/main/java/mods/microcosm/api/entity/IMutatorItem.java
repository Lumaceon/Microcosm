package mods.microcosm.api.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.item.ItemStack;

public interface IMutatorItem
{
    /**
     * Gets the type of mutator. First 4 types correspond to attributes, where UPDATEABLE instead calls update each tick
     * and ignores getAttributeValue.
     */
    public Type getType(ItemStack stack);

    /**
     * Called for MAX_HEALTH, KNOCKBACK_RESISTANCE, MOVEMENT_SPEED and ATTACK_DAMAGE to set the corresponding attributes
     * when the entity is spawned (or loaded) in world.
     * @return The value to set the corresponding attribute to.
     */
    public double getAttributeValue(ItemStack stack);

    /**
     * Called each tick if this mutator is of the UPDATEABLE type.
     */
    public void update(EntityCreature entity, ItemStack stack);

    public enum Type
    {
        MAX_HEALTH, KNOCKBACK_RESISTANCE, MOVEMENT_SPEED, ATTACK_DAMAGE, UPDATEABLE
    }
}
