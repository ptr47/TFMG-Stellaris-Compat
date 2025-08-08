package org.ptr47.tfmg_stellaris_compat;

import com.drmangotea.tfmg.base.fluid.GasFluidType;
import com.simibubi.create.content.fluids.VirtualFluid;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.VirtualFluidBuilder;
import com.tterrag.registrate.builders.FluidBuilder;

import static com.drmangotea.tfmg.registry.TFMGFluids.getGasTexture;
import static org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat.MOD_ID;

public class Registrate extends CreateRegistrate {
    protected Registrate(String modid) {
        super(modid);
    }
    public static final Registrate REGISTRATE = Registrate.create(MOD_ID);
    public static Registrate create(String modid) {
        return new Registrate(modid);
    }

    public FluidBuilder<VirtualFluid, CreateRegistrate> gasFluid(String name, int color) {
        return entry(name, c -> new VirtualFluidBuilder<>(self(),self(), name, c, getGasTexture(), getGasTexture(),
                GasFluidType.create(color),VirtualFluid::createSource,VirtualFluid::createFlowing));
    }
}
