package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.forgotteneast.content.Adherent;
import com.forgotteneast.content.AdherentModel;
import com.forgotteneast.content.AdherentRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY = DeferredRegister.create(Registries.ENTITY_TYPE, EastMod.MODID);

    public static RegistryObject<EntityType<Adherent>> ADHERENT = ENTITY.register("adherent", () -> EntityType.Builder.of(Adherent::new, MobCategory.MONSTER).sized(0.6F, 1.99F).clientTrackingRange(8).build("adherent"));

    public static void attributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.ADHERENT.get(), Monster.createMonsterAttributes().add(Attributes.MOVEMENT_SPEED, 0.28).add(Attributes.ATTACK_SPEED, -3).add(Attributes.MAX_HEALTH, 25).build());
    }

    public static void renderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.ADHERENT.get(), AdherentRenderer::new);
    }

    public static void layers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModLayers.ADHERENT, AdherentModel::createBodyLayer);
    }
}
