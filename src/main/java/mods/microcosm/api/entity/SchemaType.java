package mods.microcosm.api.entity;

/**
 * Class that determines an AI class to be added to an entity when it spawns. Custom Schema subclasses may be specified
 * so variables can be saved/loaded.
 */
public abstract class SchemaType
{
    protected String uniqueID;

    public SchemaType() {}

    public SchemaType(String uniqueID) {
        this.uniqueID = uniqueID;
    }

    /**
     * @return The Schema class for this type. Can just be Schema. You'll want this to be a subclass for custom data.
     */
    public abstract Class<? extends Schema> getSchemaClass();
}
