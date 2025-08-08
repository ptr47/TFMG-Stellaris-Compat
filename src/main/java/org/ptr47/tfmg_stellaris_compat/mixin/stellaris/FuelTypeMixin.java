package org.ptr47.tfmg_stellaris_compat.mixin.stellaris;

import com.drmangotea.tfmg.datagen.recipes.TFMGRecipeProvider;
import com.st0x0ef.stellaris.common.vehicle_upgrade.FuelType;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FuelType.Type.class)
public class FuelTypeMixin {
    @Inject(
            method = "getTypeBasedOnItem",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void injectGetTypeBasedOnItem(Item item, CallbackInfoReturnable<FuelType.Type> cir) {
        if (item == null) {
            return;
        }
        else if (item.getDefaultInstance().is(TFMGRecipeProvider.F.dieselBucket().asItem())) {
            cir.setReturnValue(FuelType.Type.DIESEL);
        }
        else if (item.getDefaultInstance().is(TFMGRecipeProvider.F.hydrogenTank().asItem())) {
            cir.setReturnValue(FuelType.Type.HYDROGEN);
        }
    }
}
