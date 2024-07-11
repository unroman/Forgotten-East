package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.forgotteneast.world.PalacePieces;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModPieces {
    public static final DeferredRegister<StructurePieceType> PIECE = DeferredRegister.create(Registries.STRUCTURE_PIECE, EastMod.MODID);

    public static final RegistryObject<StructurePieceType> PALACE_PIECE = PIECE.register("palace", ()-> PalacePieces.Piece::new);
}
