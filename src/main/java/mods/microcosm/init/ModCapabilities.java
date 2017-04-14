package mods.microcosm.init;

import mods.microcosm.api.capability.CapabilityEntityAIOverride;
import mods.microcosm.api.capability.CapabilityEntityContainer;
import mods.microcosm.item.ItemExperimentalIngot;

public class ModCapabilities
{
    public static void init()
    {
        CapabilityEntityAIOverride.register();
        CapabilityEntityContainer.register();
        ItemExperimentalIngot.CapabilityExIngot.register();
    }
}
