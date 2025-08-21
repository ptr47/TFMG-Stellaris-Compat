package org.ptr47.tfmg_stellaris_compat.config;

import net.createmod.catnip.config.ConfigBase;

public class CommonConfig extends ConfigBase {

    public final RTGConfig rtgConfig = nested(0, RTGConfig::new, "Config for RTG Generator");
    public final SolarPanelConfig solarPanelConfig = nested(1, SolarPanelConfig::new, "Config for Solar Panel");

    @Override
    public String getName() {
        return "common";
    }
}
