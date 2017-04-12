package mods.microcosm.api.entity;

import java.util.HashMap;

public class SchemaRegistry
{
    public static final HashMap<String, SchemaType> SCHEMA_TYPES = new HashMap<String, SchemaType>();

    public static void registerSchemaType(SchemaType schemaType) {
        SCHEMA_TYPES.put(schemaType.uniqueID, schemaType);
    }

    public static SchemaType getSchemaTypeFromID(String ID) {
        return SCHEMA_TYPES.get(ID);
    }
}
