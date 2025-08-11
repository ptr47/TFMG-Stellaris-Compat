package org.ptr47.tfmg_stellaris_compat;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import org.ptr47.tfmg_stellaris_compat.config.TFMGStellarisCompatConfigs;
import org.ptr47.tfmg_stellaris_compat.loot.LootModifiers;
import org.ptr47.tfmg_stellaris_compat.registry.BlockEntities;
import org.ptr47.tfmg_stellaris_compat.registry.Blocks;
import org.ptr47.tfmg_stellaris_compat.registry.Fluids;
import org.ptr47.tfmg_stellaris_compat.registry.Tags;
import org.slf4j.Logger;

@Mod(TFMGStellarisCompat.MOD_ID)
public class TFMGStellarisCompat {
    public static final String MOD_ID = "tfmg_stellaris_compat";
    public static final Logger LOGGER = LogUtils.getLogger();
    public static final Registrate REGISTRATE = Registrate.create(MOD_ID);

    public TFMGStellarisCompat(IEventBus modEventBus, ModContainer modContainer)
    {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        REGISTRATE.registerEventListeners(modEventBus);
        LootModifiers.register(modEventBus);
        Fluids.init();
        Blocks.init();
        BlockEntities.init();
        Tags.init();

        TFMGStellarisCompatConfigs.register(modLoadingContext, modContainer);
    }
}
