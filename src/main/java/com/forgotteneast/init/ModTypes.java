package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.forgotteneast.world.PalaceStructure;
import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTypes {
    public static final DeferredRegister<StructureType<?>> TYPE = DeferredRegister.create(Registries.STRUCTURE_TYPE, EastMod.MODID);
    public static final RegistryObject<StructureType<PalaceStructure>> PALACE_TYPE = TYPE.register("palace", () -> stuff(PalaceStructure.CODEC));

    private static <T extends Structure> StructureType<T> stuff(Codec<T> codec) {
        return () -> codec;
    }
}
