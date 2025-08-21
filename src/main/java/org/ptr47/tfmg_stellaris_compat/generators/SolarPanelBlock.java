package org.ptr47.tfmg_stellaris_compat.generators;

import com.drmangotea.tfmg.content.electricity.base.IElectric;
import com.simibubi.create.foundation.block.IBE;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.ptr47.tfmg_stellaris_compat.registry.BlockEntities;

public class SolarPanelBlock extends Block implements IBE<SolarPanelBlockEntity> {
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    public SolarPanelBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void createBlockStateDefinition(@NotNull StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public void onPlace(BlockState pState, Level level, BlockPos pos, BlockState pOldState, boolean pIsMoving) {
        withBlockEntityDo(level, pos, IElectric::onPlaced);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        IBE.onRemove(state, level, pos, newState);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                   .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public Class<SolarPanelBlockEntity> getBlockEntityClass() {
        return SolarPanelBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends SolarPanelBlockEntity> getBlockEntityType() {
        return BlockEntities.SOLAR_PANEL.get();
    }
}
