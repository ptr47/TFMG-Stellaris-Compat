package org.ptr47.tfmg_stellaris_compat;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.ptr47.tfmg_stellaris_compat.registry.FluidRegistry;
import org.slf4j.Logger;

import static org.ptr47.tfmg_stellaris_compat.Registrate.REGISTRATE;

@Mod(TFMGStellarisCompat.MOD_ID)
public class TFMGStellarisCompat
{
    public static final String MOD_ID = "tfmg_stellaris_compat";
    private static final Logger LOGGER = LogUtils.getLogger();
    public TFMGStellarisCompat(IEventBus modEventBus) {
        REGISTRATE.registerEventListeners(modEventBus);

        FluidRegistry.init();
    }
}
