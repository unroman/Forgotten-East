package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.google.common.collect.Sets;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

public class ModLootTables {
    private static final Set<ResourceKey<LootTable>> LOCATIONS = Sets.newHashSet();
    public static final ResourceKey<LootTable> PALACE_LOOT = register("chest/palace");

    private static ResourceKey<LootTable> register(String p_78768_) {
        return register(ResourceKey.create(Registries.LOOT_TABLE, ResourceLocation.fromNamespaceAndPath(EastMod.MODID, p_78768_)));
    }

    private static ResourceKey<LootTable> register(ResourceKey<LootTable> p_330139_) {
        if (LOCATIONS.add(p_330139_)) {
            return p_330139_;
        } else {
            throw new IllegalArgumentException(p_330139_.location() + " is already a registered built-in loot table");
        }
    }
}
