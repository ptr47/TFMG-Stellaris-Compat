package org.ptr47.tfmg_stellaris_compat.loot;

import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat;

import java.util.function.Supplier;

public class LootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS,
                    TFMGStellarisCompat.MOD_ID);

    @SuppressWarnings("unused")
    public static final Supplier<MapCodec<ReplaceItemLootModifier>> REPLACE_ITEM = LOOT_MODIFIERS.register(
            "replace_item",
            () -> ReplaceItemLootModifier.CODEC);

    public static void register(IEventBus bus)
    {
        LOOT_MODIFIERS.register(bus);
    }
}
