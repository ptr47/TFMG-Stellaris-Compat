package org.ptr47.tfmg_stellaris_compat.config;

import net.createmod.catnip.config.ConfigBase;

public class SolarPanelConfig extends ConfigBase {

    public final ConfigInt voltage = i(120, 0, "voltageBase", SolarPanelConfig.Comments.voltage);
    public final ConfigInt power = i(1000, 0, "powerBase", SolarPanelConfig.Comments.power);

    @Override
    public String getName() {
        return "Solar Panel";
    }
    private static class Comments {
        static final String voltage = "The voltage output of the solar panel.";
        static final String power = "The power output of the solar panel.";
    }
}
