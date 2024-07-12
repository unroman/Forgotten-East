package com.forgotteneast.world;

import com.forgotteneast.EastMod;
import com.forgotteneast.content.PaperLanternBlock;
import com.forgotteneast.init.ModBlocks;
import com.forgotteneast.init.ModEntities;
import com.forgotteneast.init.ModLootTables;
import com.forgotteneast.init.ModPieces;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePieceAccessor;
import net.minecraft.world.level.levelgen.structure.TemplateStructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockIgnoreProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class PalacePieces {
    private static final ResourceLocation PALACE = new ResourceLocation(EastMod.MODID, "palace");

    public static void addPieces(StructureTemplateManager manager, BlockPos pos, StructurePieceAccessor pieceList, RandomSource random) {
        Rotation rotation = Rotation.NONE;
        pieceList.addPiece(new PalacePieces.Piece(manager, PALACE, pos, rotation));
    }
    public static class Piece extends TemplateStructurePiece {
        public Piece(StructureTemplateManager p_228540_, ResourceLocation p_228541_, BlockPos p_228542_, Rotation p_228543_) {
            super(ModPieces.PALACE_PIECE.get(), 0, p_228540_, p_228541_, p_228541_.toString(), makeSettings(p_228543_), p_228542_);
        }
        public Piece(StructurePieceSerializationContext p_228545_, CompoundTag p_228546_) {
            super(ModPieces.PALACE_PIECE.get(), p_228546_, p_228545_.structureTemplateManager(), (p_228568_) -> makeSettings(Rotation.valueOf(p_228546_.getString("Rot"))));
        }

        private static StructurePlaceSettings makeSettings(Rotation p_228556_) {
            return (new StructurePlaceSettings()).setRotation(p_228556_).setMirror(Mirror.NONE).addProcessor(BlockIgnoreProcessor.STRUCTURE_BLOCK);
        }

        protected void addAdditionalSaveData(StructurePieceSerializationContext p_228558_, CompoundTag p_228559_) {
            super.addAdditionalSaveData(p_228558_, p_228559_);
            p_228559_.putString("Rot", this.placeSettings.getRotation().name());
        }
        public void postProcess(WorldGenLevel worldIn, StructureManager p_229138_, ChunkGenerator p_229139_, RandomSource random, BoundingBox p_229141_, ChunkPos p_229142_, BlockPos pos) {
            super.postProcess(worldIn, p_229138_, p_229139_, random, p_229141_, p_229142_, pos);
            for (int d = 0; d < 8; ++d) {
                BlockPos blockpos = pos.offset(random.nextInt(-9, 7), random.nextInt(10), random.nextInt(-9, 7));
                if (worldIn.getBlockState(blockpos).getBlock() == ModBlocks.LACQUERED_PLANKS.get()) {
                    if (worldIn.isEmptyBlock(blockpos.above())) {
                        worldIn.setBlock(blockpos, Blocks.AIR.defaultBlockState(), 2);

                    } else {
                        worldIn.setBlock(blockpos, Blocks.SANDSTONE.defaultBlockState(), 2);
                    }
                }
            }
        }
        protected void handleDataMarker(String function, BlockPos pos, ServerLevelAccessor worldIn, RandomSource random, BoundingBox sbb) {
            if (function.startsWith("Chest")) {
                worldIn.setBlock(pos, Blocks.CHEST.defaultBlockState(), 2);
                if (sbb.isInside(pos)) {
                    RandomizableContainerBlockEntity.setLootTable(worldIn, random, pos, ModLootTables.PALACE_LOOT);
                }
            }
            if (function.startsWith("Lantern")) {
                if (random.nextInt(6) < 3) {
                    worldIn.setBlock(pos, ModBlocks.PAPER_LANTERN_RED.get().defaultBlockState().setValue(PaperLanternBlock.GILDED, true), 2);
                } else {
                    worldIn.setBlock(pos, ModBlocks.PAPER_LANTERN_RED.get().defaultBlockState(), 2);
                }
            } if (function.startsWith("Spawner")) {
                worldIn.setBlock(pos, Blocks.SPAWNER.defaultBlockState(), 2);
                BlockEntity blockentity = worldIn.getBlockEntity(pos);
                if (blockentity instanceof SpawnerBlockEntity spawnerblockentity) {
                    spawnerblockentity.setEntityId(ModEntities.ADHERENT.get(), random);
                }
            }
        }
    }
}
