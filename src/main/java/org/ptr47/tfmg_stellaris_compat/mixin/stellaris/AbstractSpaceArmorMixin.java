package org.ptr47.tfmg_stellaris_compat.mixin.stellaris;

import com.drmangotea.tfmg.registry.TFMGFluids;
import com.fej1fun.potentials.fluid.ItemFluidStorage;
import com.fej1fun.potentials.fluid.UniversalFluidItemStorage;
import com.st0x0ef.stellaris.common.items.armors.AbstractSpaceArmor;
import dev.architectury.fluid.FluidStack;
import net.minecraft.world.item.ItemStack;
import org.ptr47.tfmg_stellaris_compat.registry.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractSpaceArmor.AbstractSpaceChestplate.class)
public class AbstractSpaceArmorMixin {
    @Inject(method = "getFluidTank", at = @At("HEAD"), cancellable = true)
    private void replaceOxygenFluidTank(ItemStack stack, CallbackInfoReturnable<UniversalFluidItemStorage> cir)
    {
        cir.setReturnValue(new ItemFluidStorage(com.st0x0ef.stellaris.common.registry.DataComponentsRegistry.FLUID_LIST.get(),
                stack,
                2,
                3000)
        {
            @Override
            public boolean isFluidValid(int tank, FluidStack stack)
            {
                if (tank == 0) {
                    return stack.getFluid().isSame(Fluids.OXYGEN.get());
                } else if (tank == 1) {
                    return stack.getFluid().isSame(TFMGFluids.DIESEL.get());
                } else {
                    return false;
                }
            }
        });
    }
}