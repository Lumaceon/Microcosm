package mods.microcosm.init;

import mods.microcosm.item.ItemExperimentalIngot;

public class ModCapabilities
{
    public static void init()
    {
        ItemExperimentalIngot.CapabilityExIngotHandler.register();
    }
}
