package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND = DeferredRegister.create(Registries.SOUND_EVENT, EastMod.MODID);

    public static RegistryObject<SoundEvent> LUTE = SOUND.register( "lute_play", () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(EastMod.MODID, "lute_play")));
}
