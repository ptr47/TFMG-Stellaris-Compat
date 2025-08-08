package org.ptr47.tfmg_stellaris_compat.mixin.stellaris;

import com.drmangotea.tfmg.datagen.recipes.TFMGRecipeProvider;
import com.st0x0ef.stellaris.common.menus.slot.VehicleFuelSlot;
import com.st0x0ef.stellaris.common.registry.DataComponentsRegistry;
import com.st0x0ef.stellaris.common.registry.ItemsRegistry;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VehicleFuelSlot.class)
public class VehicleFuelSlotMixin {
    @Inject(method = "mayPlace", at = @At("HEAD"), cancellable = true)
    private void replaceMayPlace(ItemStack stack, CallbackInfoReturnable<Boolean> cir)
    {
        boolean isProperFuel = stack.is(ItemsRegistry.FUEL_BUCKET.get()) ||
                stack.has(DataComponentsRegistry.RADIOACTIVE.get()) ||
                stack.is(TFMGRecipeProvider.F.hydrogenTank().asItem());
        cir.setReturnValue(isProperFuel);
    }
}
