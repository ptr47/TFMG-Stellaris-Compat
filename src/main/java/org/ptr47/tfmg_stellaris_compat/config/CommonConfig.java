package org.ptr47.tfmg_stellaris_compat.config;

import net.createmod.catnip.config.ConfigBase;

public class CommonConfig extends ConfigBase {

    public final RTGConfig rtgConfig = nested(0, RTGConfig::new, "Config for RTG Generator");

    @Override
    public String getName() {
        return "common";
    }
}
