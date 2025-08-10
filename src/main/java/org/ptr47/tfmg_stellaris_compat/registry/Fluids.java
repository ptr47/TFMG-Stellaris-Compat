package org.ptr47.tfmg_stellaris_compat.registry;

import com.drmangotea.tfmg.registry.TFMGTags;
import com.simibubi.create.AllTags;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.tterrag.registrate.util.entry.FluidEntry;

import static org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat.REGISTRATE;

public class Fluids {
    public static final FluidEntry<VirtualFluid> OXYGEN =
            REGISTRATE.gasFluid("oxygen", 0xffb3e6ff)
                      .lang("Oxygen")
                      .tag(TFMGTags.TFMGFluidTags.GAS.tag)
                      .bucket()
                      .lang("Oxygen Tank")
                      .tag(AllTags.commonItemTag("buckets/oxygen"))
                      .model((ctx, prov) -> {})
                      .build()
                      .register();

    public static void init() {

    }
}
