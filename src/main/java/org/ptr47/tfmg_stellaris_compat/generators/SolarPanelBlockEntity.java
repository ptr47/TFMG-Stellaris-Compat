package org.ptr47.tfmg_stellaris_compat.generators;

import com.drmangotea.tfmg.content.electricity.base.ElectricBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.ptr47.tfmg_stellaris_compat.config.TFMGStellarisCompatConfigs;

public class SolarPanelBlockEntity extends ElectricBlockEntity {

    public SolarPanelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);

        setLazyTickRate(10);
    }

    @Override
    public int voltageGeneration() {
        if (solarEfficiency() <= 0.0f) {
            return 0;
        }

        return TFMGStellarisCompatConfigs.common().solarPanelConfig.voltage.get();
    }

    @Override
    public void lazyTick() {
        super.lazyTick();
        updateNextTick();
    }

    @Override
    public int powerGeneration() {
        int basePower = TFMGStellarisCompatConfigs.common().solarPanelConfig.power.get();

        return Math.round(basePower * solarEfficiency());
    }

    @Override
    public int getMaxVoltage() {
        return 0;
    }

    @Override
    public int getMaxCurrent() {
        return 0;
    }

    private float solarEfficiency() {
        if (level == null || !level.canSeeSky(worldPosition.above()) || level.isThundering()) {
            return 0.0f;
        }

        long time = level.getDayTime() % 24000;
        if (time < 12000) {
            return 1.0f;
        }
        if (time < 13000) {
            return 1.0f - (time - 12000) / 1000.0f;
        }
        if (time < 23000) {
            return 0.0f;
        }
        return (time - 23000) / 1000.0f;
    }
}
