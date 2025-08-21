package org.ptr47.tfmg_stellaris_compat.generators;

import com.drmangotea.tfmg.content.electricity.base.ElectricBlockEntity;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.ptr47.tfmg_stellaris_compat.config.TFMGStellarisCompatConfigs;

public class SolarPanelBlockEntity extends ElectricBlockEntity {

    public SolarPanelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public int voltageGeneration() {
        return TFMGStellarisCompatConfigs.common().solarPanelConfig.voltage.get();
    }

    @Override
    public int powerGeneration() {
        return TFMGStellarisCompatConfigs.common().solarPanelConfig.power.get();
    }
}
