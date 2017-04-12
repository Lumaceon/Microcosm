package mods.microcosm.init;

import mods.microcosm.api.entity.CapabilityEntityAIOverride;
import mods.microcosm.item.ItemExperimentalIngot;

public class ModCapabilities
{
    public static void init()
    {
        ItemExperimentalIngot.CapabilityExIngot.register();
        CapabilityEntityAIOverride.register();
    }
}
