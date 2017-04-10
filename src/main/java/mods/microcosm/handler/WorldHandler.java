package mods.microcosm.handler;

import mods.microcosm.extendeddata.ExtendedMapData;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class WorldHandler
{
    @SubscribeEvent
    public void onWorldLoaded(WorldEvent.Load event)
    {
        if(event.getWorld() != null)
        {
            ExtendedMapData worldData = ExtendedMapData.get(event.getWorld());
            if(!event.getWorld().isRemote && !worldData.areExperimentalAlloysRegistered())
                worldData.registerExperimentalAlloys();
        }
    }
}
