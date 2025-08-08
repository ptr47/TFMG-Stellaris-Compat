package org.ptr47.tfmg_stellaris_compat.mixin.stellaris;

import com.drmangotea.tfmg.datagen.recipes.TFMGRecipeProvider;
import com.st0x0ef.stellaris.common.entities.vehicles.RoverEntity;
import com.st0x0ef.stellaris.common.registry.ItemsRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(RoverEntity.class)
public class RoverEntityMixin {
    @Redirect(
            method = "tryFillUpRover",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/item/ItemStack;is(Lnet/minecraft/world/item/Item;)Z"
            )
    )
    private boolean redirectFuelItemIs(ItemStack instance, Item item) {
        return instance.is(ItemsRegistry.FUEL_BUCKET.get()) ||
                instance.is(TFMGRecipeProvider.F.hydrogenTank().asItem());
    }
}
