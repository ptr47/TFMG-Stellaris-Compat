package org.ptr47.tfmg_stellaris_compat.config;

import net.createmod.catnip.config.ConfigBase;

public class RTGConfig extends ConfigBase {

    public final ConfigGroup burn_time = group(1, "burn_time", "Burn times");
    public final ConfigInt burnTimeLow = i(12000, 20, "burnTimeLow", Comments.burnTimeLow);
    public final ConfigInt burnTimeMedium = i(18000, 20, "burnTimeMedium", Comments.burnTimeMedium);
    public final ConfigInt burnTimeHigh = i(24000, 20, "burnTimeHigh", Comments.burnTimeHigh);
    public final ConfigInt burnTimeExtreme = i(30000, 20, "burnTimeExtreme", Comments.burnTimeExtreme);

    public final ConfigGroup voltage = group(1, "voltage", "Voltage");
    public final ConfigInt voltageBase = i(1000, 0, "voltageBase", Comments.voltageBase);

    public final ConfigGroup power = group(1, "power", "Power");
    public final ConfigInt powerBase = i(1000, 0, "powerBase", Comments.powerBase);
    public final ConfigInt powerPerLevel = i(5000, 0, "powerPerLevel", Comments.powerPerLevel);
    public final ConfigInt fuelRefinementBonus = i(40, 0,"fuelRefinementBonus", Comments.fuelRefinementBonus);
    public final ConfigFloat maxRTGPowerDecay = f(0.3f, 0.0f, 1.0f, "maxRTGPowerDecay", Comments.maxRTGPowerDecay);

    @Override
    public String getName() {
        return "Compact Nuclear Reactor";
    }

    private static class Comments {
        static final String burnTimeLow = "The burn time of the low-power Compact Nuclear Reactor fuel.";
        static final String burnTimeMedium = "The burn time of the medium-power Compact Nuclear Reactor fuel.";
        static final String burnTimeHigh = "The burn time of the high-power Compact Nuclear Reactor fuel.";
        static final String burnTimeExtreme = "The burn time of the extreme-power Compact Nuclear Reactor fuel.";
        static final String voltageBase = "The voltage output of the Compact Nuclear Reactor.";
        static final String powerBase = "The base power output of the Compact Nuclear Reactor.";
        static final String powerPerLevel = "The power increase per fuels' power level. Power level is calculated based on the fuel's tier and refinement.";
        static final String maxRTGPowerDecay =
                "The percentage of Compact Nuclear Reactor's power output that will be lost at the end of a fuels burn time.\n" +
                        "0.0 = no decay, 1.0 = full decay.";
        static final String fuelRefinementBonus = "The bonus added to the power level of a fuel that is refined (ingot or block).";
    }
}
