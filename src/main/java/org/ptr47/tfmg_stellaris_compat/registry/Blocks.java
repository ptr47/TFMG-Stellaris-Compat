package org.ptr47.tfmg_stellaris_compat.registry;

import com.simibubi.create.foundation.data.SharedProperties;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.SoundType;
import org.ptr47.tfmg_stellaris_compat.generators.RadioactiveGeneratorBlock;
import org.ptr47.tfmg_stellaris_compat.generators.SolarPanelBlock;

import static org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat.REGISTRATE;

public class Blocks {
    public static final BlockEntry<RadioactiveGeneratorBlock> RADIOACTIVE_GENERATOR =
            REGISTRATE.block("rtg", RadioactiveGeneratorBlock::new)
                      .lang("RTG")
                      .initialProperties(SharedProperties::softMetal)
                      .properties(p -> p.sound(SoundType.NETHERITE_BLOCK).strength(10F).requiresCorrectToolForDrops())
                      .blockstate((ctx, prov) -> {})
                      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                      .tag(BlockTags.NEEDS_IRON_TOOL)
                      .item()
                      .model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
                              ResourceLocation.fromNamespaceAndPath("stellaris", "block/radioactive_generator")))
                      .build()
                      .register();

    public static final BlockEntry<SolarPanelBlock> SOLAR_PANEL =
            REGISTRATE.block("solar_panel", SolarPanelBlock::new)
                      .lang("Solar Panel")
                      .initialProperties(SharedProperties::softMetal)
                      .properties(p -> p.sound(SoundType.METAL).strength(9F).requiresCorrectToolForDrops())
                      .blockstate((ctx, prov) -> {})
                      .tag(BlockTags.MINEABLE_WITH_PICKAXE)
                      .tag(BlockTags.NEEDS_IRON_TOOL)
                      .item()
                      .model((ctx, prov) -> prov.withExistingParent(ctx.getName(),
                              ResourceLocation.fromNamespaceAndPath("stellaris", "block/solar_panel")))
                      .build()
                      .register();

    public static void init() {

    }
}
