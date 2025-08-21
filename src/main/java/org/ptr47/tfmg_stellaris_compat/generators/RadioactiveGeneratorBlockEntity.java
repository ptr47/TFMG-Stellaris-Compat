package org.ptr47.tfmg_stellaris_compat.generators;

import com.drmangotea.tfmg.content.electricity.base.ElectricBlockEntity;
import com.simibubi.create.foundation.item.SmartInventory;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import org.ptr47.tfmg_stellaris_compat.config.TFMGStellarisCompatConfigs;
import org.ptr47.tfmg_stellaris_compat.registry.BlockEntities;

import static org.ptr47.tfmg_stellaris_compat.registry.Tags.*;

public class RadioactiveGeneratorBlockEntity extends ElectricBlockEntity {
    private final SmartInventory inventory;

    private int burnTimeLeft = 0;
    private int burnTimeTotal = 0;
    private int currentPowerLevel = 0;
    private ItemStack currentFuel = null;

    public RadioactiveGeneratorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        inventory = new SmartInventory(1, this) {
            @Override
            public boolean isItemValid(int slot, @NotNull ItemStack stack) {
                return isFuel(stack);
            }
        }.withMaxStackSize(64).allowInsertion().allowExtraction().whenContentsChanged(slot -> tryActivate());

        setLazyTickRate(10);
    }

    @Override
    public void lazyTick() {
        super.lazyTick();
        if (level == null || level.isClientSide) {
            return;
        }
        updateNetwork();
        if (burnTimeLeft > 0) {
            burnTimeLeft--;
            if (burnTimeLeft == 0) {
                currentPowerLevel = 0;
                setLitBlockState(false);
                tryActivate();
            }
        }
    }

    private void tryActivate()
    {
        if (burnTimeLeft > 0) {
            return;
        }

        ItemStack stack = inventory.getItem(0);
        if (!isFuel(stack)) {
            return;
        }

        burnTimeTotal = getBurnDuration(stack);
        burnTimeLeft = burnTimeTotal;
        currentPowerLevel = getPowerLevelForFuel(stack);
        stack.shrink(1);

        setLitBlockState(true);
        updateNextTick();
        setChanged();
        sendData();
    }

    private void setLitBlockState(boolean isLit) {
        BlockState state = getBlockState().setValue(RadioactiveGeneratorBlock.LIT, isLit);
        level.setBlock(getBlockPos(), state, 3);
    }

    private int getPowerLevelForFuel(ItemStack stack) {
        boolean isRefined = stack.is(Tags.Items.INGOTS) || stack.is(Tags.Items.STORAGE_BLOCKS);
        int tier = stack.is(RTG_FUEL_LOW) ? 0 :
                   stack.is(RTG_FUEL_MEDIUM) ? 1 : stack.is(RTG_FUEL_HIGH) ? 2 : stack.is(RTG_FUEL_EXTREME) ? 3 : -1;
        int refinementBonus = 40;
        return isRefined ? refinementBonus + tier * 5 : tier + 1;
    }

    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK,
                BlockEntities.RADIOACTIVE_GENERATOR.get(),
                (be, dir) -> be.getInventory());
    }

    @Override
    protected void write(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
        super.write(compound, registries, clientPacket);
        compound.put("inventory", inventory.serializeNBT(registries));
        compound.putInt("burnLeft", burnTimeLeft);
        compound.putInt("burnTotal", burnTimeTotal);
        compound.putInt("powerLevel", currentPowerLevel);
    }

    @Override
    protected void read(CompoundTag compound, HolderLookup.Provider registries, boolean clientPacket) {
        super.read(compound, registries, clientPacket);
        inventory.deserializeNBT(registries, compound.getCompound("inventory"));
        burnTimeLeft = compound.getInt("burnLeft");
        burnTimeTotal = compound.getInt("burnTotal");
        currentPowerLevel = compound.getInt("powerLevel");
    }


    private int getBurnDuration(ItemStack stack) {
        boolean isBlock = stack.is(Tags.Items.STORAGE_BLOCKS);
        boolean isNugget = stack.is(Tags.Items.NUGGETS);

        int burnTime = 0;
        if (stack.is(RTG_FUEL_LOW)) {
            burnTime = TFMGStellarisCompatConfigs.common().rtgConfig.burnTimeLow.get();
        } else if (stack.is(RTG_FUEL_MEDIUM)) {
            burnTime = TFMGStellarisCompatConfigs.common().rtgConfig.burnTimeMedium.get();
        } else if (stack.is(RTG_FUEL_HIGH)) {
            burnTime = TFMGStellarisCompatConfigs.common().rtgConfig.burnTimeHigh.get();
        } else if (stack.is(RTG_FUEL_EXTREME)) {
            burnTime = TFMGStellarisCompatConfigs.common().rtgConfig.burnTimeExtreme.get();
        }

        return isBlock ? burnTime * 10 : isNugget ? burnTime / 9 : burnTime;
    }

    @Override
    public int voltageGeneration() {
        int base = currentPowerLevel > 0 ? TFMGStellarisCompatConfigs.common().rtgConfig.voltageBase.get() : 0;
        return base + currentPowerLevel * TFMGStellarisCompatConfigs.common().rtgConfig.voltagePerLevel.get();
    }

    @Override
    public int powerGeneration() {
        float lifeFraction = (float) burnTimeLeft / burnTimeTotal;
        double maxDecay = TFMGStellarisCompatConfigs.common().rtgConfig.maxRTGPowerDecay.get();
        float decayFactor = (float) ((1.0f - maxDecay) + (maxDecay * lifeFraction));
        int maxPower = TFMGStellarisCompatConfigs.common().rtgConfig.powerBase.get() +
                currentPowerLevel * TFMGStellarisCompatConfigs.common().rtgConfig.powerPerLevel.get();
        return Math.round(decayFactor * maxPower);
    }

    public SmartInventory getInventory() {
        return inventory;
    }

    public boolean isFuel(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        return stack.is(RTG_FUELS);
    }
}
