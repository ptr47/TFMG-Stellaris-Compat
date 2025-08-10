package org.ptr47.tfmg_stellaris_compat.events;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.ptr47.tfmg_stellaris_compat.generators.RadioactiveGeneratorBlockEntity;

@EventBusSubscriber
public class Capabilities {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        RadioactiveGeneratorBlockEntity.registerCapabilities(event);
    }
}
