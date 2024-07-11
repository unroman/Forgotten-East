package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.google.common.collect.Sets;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class ModLootTables {
    private static final Set<ResourceLocation> LOCATIONS = Sets.newHashSet();
    public static final ResourceLocation PALACE_LOOT = register("chest/palace");

    private static ResourceLocation register(String p_78768_) {
        return register(new ResourceLocation(EastMod.MODID, p_78768_));
    }

    private static ResourceLocation register(ResourceLocation p_78770_) {
        if (LOCATIONS.add(p_78770_)) {
            return p_78770_;
        } else {
            throw new IllegalArgumentException(p_78770_ + " is already a registered built-in loot table");
        }
    }
}
