package org.ptr47.tfmg_stellaris_compat.registry;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import org.ptr47.tfmg_stellaris_compat.generators.RadioactiveGeneratorBlockEntity;
import org.ptr47.tfmg_stellaris_compat.generators.SolarPanelBlockEntity;

import static org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat.REGISTRATE;

public class BlockEntities {
    public static final BlockEntityEntry<RadioactiveGeneratorBlockEntity> RADIOACTIVE_GENERATOR =
            REGISTRATE.blockEntity("radioactive_generator", RadioactiveGeneratorBlockEntity::new)
                      .validBlocks(Blocks.RADIOACTIVE_GENERATOR)
                      .register();

    public static final BlockEntityEntry<SolarPanelBlockEntity> SOLAR_PANEL =
            REGISTRATE.blockEntity("solar_panel", SolarPanelBlockEntity::new)
                      .validBlocks(Blocks.SOLAR_PANEL)
                      .register();

    public static void init() {

    }
}
