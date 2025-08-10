package org.ptr47.tfmg_stellaris_compat.registry;

import com.tterrag.registrate.util.entry.BlockEntityEntry;
import org.ptr47.tfmg_stellaris_compat.generators.RadioactiveGeneratorBlockEntity;

import static org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat.REGISTRATE;

public class BlockEntities {
    public static final BlockEntityEntry<RadioactiveGeneratorBlockEntity> RADIOACTIVE_GENERATOR =
            REGISTRATE.blockEntity("radioactive_generator", RadioactiveGeneratorBlockEntity::new)
                    .validBlocks(Blocks.RADIOACTIVE_GENERATOR)
                    .register();

    public static void init() {

    }
}
