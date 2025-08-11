package org.ptr47.tfmg_stellaris_compat.config;

import net.createmod.catnip.config.ConfigBase;

public class RTGConfig extends ConfigBase {

    public final ConfigGroup burn_time = group(1, "burn_time", "Burn times");
    public final ConfigInt burnTimeLow = i(12000, 20, "burnTimeLow", Comments.burnTimeLow);
    public final ConfigInt burnTimeMedium = i(18000, 20, "burnTimeMedium", Comments.burnTimeMedium);
    public final ConfigInt burnTimeHigh = i(24000, 20, "burnTimeHigh", Comments.burnTimeHigh);
    public final ConfigInt burnTimeExtreme = i(30000, 20, "burnTimeExtreme", Comments.burnTimeExtreme);

    public final ConfigGroup voltage = group(1, "voltage", "Voltage");
    public final ConfigInt voltageBase = i(100, 0, "voltageBase", Comments.voltageBase);
    public final ConfigInt voltagePerLevel = i(40, 0, "voltagePerLevel", Comments.voltagePerLevel);

    public final ConfigGroup power = group(1, "power", "Power");
    public final ConfigInt powerBase = i(2000, 0, "powerBase", Comments.powerBase);
    public final ConfigInt powerPerLevel = i(6000, 0, "powerPerLevel", Comments.powerPerLevel);
    public final ConfigBool enableFuelDecay = b(true, "enableFuelDecay", Comments.enableFuelDecay);
    @Override
    public String getName() {
        return "RTG";
    }

    private static class Comments {
        static final String burnTimeLow = "The burn time of the low-power RTG fuel.";
        static final String burnTimeMedium = "The burn time of the medium-power RTG fuel.";
        static final String burnTimeHigh = "The burn time of the high-power RTG fuel.";
        static final String burnTimeExtreme = "The burn time of the extreme-power RTG fuel.";
        static final String voltageBase = "The base voltage of the RTG.";
        static final String voltagePerLevel = "The voltage increase per power level.";
        static final String powerBase = "The base power output of the RTG.";
        static final String powerPerLevel = "The power increase per power level.";
        static final String enableFuelDecay = "Whether fuel decay is enabled.";
    }
}
