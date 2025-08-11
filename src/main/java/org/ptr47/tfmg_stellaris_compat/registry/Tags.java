package org.ptr47.tfmg_stellaris_compat.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.ptr47.tfmg_stellaris_compat.TFMGStellarisCompat;

public class Tags {
    public static final TagKey<Item> RTG_FUELS = tag("fuels");
    public static final TagKey<Item> RTG_FUEL_LOW = tag("fuels/low");
    public static final TagKey<Item> RTG_FUEL_MEDIUM = tag("fuels/medium");
    public static final TagKey<Item> RTG_FUEL_HIGH = tag("fuels/high");
    public static final TagKey<Item> RTG_FUEL_EXTREME = tag("fuels/extreme");


    private static TagKey<Item> tag(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath(TFMGStellarisCompat.MOD_ID, name));
    }

    public static void init() {
    }
}
