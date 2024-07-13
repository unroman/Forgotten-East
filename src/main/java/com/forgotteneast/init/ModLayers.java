package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModLayers {

    private static ModelLayerLocation register(String name) {
        return register(name, "main");
    }
    public static final ModelLayerLocation ADHERENT = register("adherent");

    private static ModelLayerLocation register(String name, String type) {
        return register(ResourceLocation.fromNamespaceAndPath(EastMod.MODID, name), type);
    }

    private static ModelLayerLocation register(ResourceLocation location, String type) {
        return new ModelLayerLocation(location, type);
    }
}
