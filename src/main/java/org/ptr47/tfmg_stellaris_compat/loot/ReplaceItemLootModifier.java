package org.ptr47.tfmg_stellaris_compat.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ReplaceItemLootModifier extends LootModifier {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReplaceItemLootModifier.class);

    public record Replacement(Item from, Item to) {}

    public static final Codec<Replacement> REPLACEMENT_CODEC =
            Codec.list(BuiltInRegistries.ITEM.byNameCodec()).comapFlatMap(list -> {
                if (list.size() != 2) {
                    return DataResult.error(() -> "Replacement must have exactly 2 items");
                }
                return DataResult.success(new Replacement(list.get(0), list.get(1)));
            }, r -> List.of(r.from(), r.to()));

    public static final MapCodec<ReplaceItemLootModifier> CODEC =
            RecordCodecBuilder.mapCodec(inst -> LootModifier.codecStart(inst)
                    .and(Codec.list(REPLACEMENT_CODEC).fieldOf("replacements").forGetter(m -> m.replacements)).and(
                            Codec.STRING
                                    .optionalFieldOf("namespace", "stellaris")
                                    .forGetter(m -> m.namespace)
                    )
                    .apply(inst, ReplaceItemLootModifier::new));

    private final List<Replacement> replacements;
    private final String namespace;

    protected ReplaceItemLootModifier(LootItemCondition[] conditions, List<Replacement> replacements, String namespace)
    {
        super(conditions);
        this.replacements = replacements;
        this.namespace = namespace;

        LOGGER.info("[ReplaceItemLootModifier] Loaded with {} replacements", replacements.size());
        for (Replacement r : replacements) {
            LOGGER.info(" - {} -> {}", BuiltInRegistries.ITEM.getKey(r.from()), BuiltInRegistries.ITEM.getKey(r.to()));
        }
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(@NotNull ObjectArrayList<ItemStack> generatedLoot, @NotNull LootContext context)
    {
        var tableId = context.getQueriedLootTableId();
        if (!namespace.equals(tableId.getNamespace())) {
            return generatedLoot;
        }
        for (int i = 0; i < generatedLoot.size(); i++) {
            ItemStack stack = generatedLoot.get(i);
            for (Replacement r : replacements) {
                if (stack.getItem() == r.from()) {
                    LOGGER.info("[ReplaceItemLootModifier] Replacing {} with {} (count: {})",
                            BuiltInRegistries.ITEM.getKey(stack.getItem()),
                            BuiltInRegistries.ITEM.getKey(r.to()),
                            stack.getCount());
                    generatedLoot.set(i, new ItemStack(r.to(), stack.getCount()));
                }
            }
        }
        return generatedLoot;
    }

    @Override
    public @NotNull MapCodec<? extends IGlobalLootModifier> codec()
    {
        return CODEC;
    }
}