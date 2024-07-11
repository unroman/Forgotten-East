package com.forgotteneast.content;

import com.forgotteneast.EastMod;
import com.forgotteneast.init.ModLayers;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AdherentRenderer extends HumanoidMobRenderer<Adherent, AdherentModel<Adherent>> {
    private static final ResourceLocation LOST_LOCATION = new ResourceLocation(EastMod.MODID, "textures/entity/adherent.png");

    public AdherentRenderer(EntityRendererProvider.Context p_174380_) {
        this(p_174380_, ModLayers.ADHERENT, ModelLayers.SKELETON_INNER_ARMOR, ModelLayers.SKELETON_OUTER_ARMOR);
    }

    public AdherentRenderer(EntityRendererProvider.Context p_174382_, ModelLayerLocation p_174383_, ModelLayerLocation p_174384_, ModelLayerLocation p_174385_) {
        super(p_174382_, new AdherentModel<>(p_174382_.bakeLayer(p_174383_)), 0.5F);

    }

    public ResourceLocation getTextureLocation(Adherent p_115941_) {
        return LOST_LOCATION;
    }
}
