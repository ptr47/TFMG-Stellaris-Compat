package org.ptr47.tfmg_stellaris_compat.generators;

import com.drmangotea.tfmg.content.electricity.base.ElectricBlockEntity;
import com.simibubi.create.foundation.item.SmartInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import org.jetbrains.annotations.NotNull;
import org.ptr47.tfmg_stellaris_compat.registry.BlockEntities;

public class RadioactiveGeneratorBlockEntity extends ElectricBlockEntity {
    private final SmartInventory inventory;

    public RadioactiveGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = new SmartInventory(1, this) {
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return isFuel(stack);
            }
        }.withMaxStackSize(64).allowInsertion().allowExtraction().whenContentsChanged(slot -> setChanged());
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(
                Capabilities.ItemHandler.BLOCK,
                BlockEntities.RADIOACTIVE_GENERATOR.get(),
                (be, dir) -> be.getInventory()
        );
    }

    public SmartInventory getInventory() {
        return inventory;
    }

    public boolean isFuel(ItemStack stack) {
        return true;
    }
}
