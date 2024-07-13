package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.forgotteneast.content.DragonBlock;
import com.forgotteneast.content.PaperLanternBlock;
import com.forgotteneast.content.RoofSlabBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(Registries.BLOCK, EastMod.MODID);
    public static RegistryObject<Block> ROOF_TILES = register(() -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)), "roof_tiles");
    public static RegistryObject<Block> LACQUERED_PLANKS = register(() -> new Block(BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).instrument(NoteBlockInstrument.BASS).strength(2.0F, 3.0F).sound(SoundType.WOOD)), "lacquered_planks");
    public static RegistryObject<Block> LACQUERED_STAIRS = register(() -> new StairBlock(LACQUERED_PLANKS.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get())), "lacquered_stairs");
    public static RegistryObject<Block> LACQUERED_SLAB = register(() -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get())), "lacquered_slab");
    public static RegistryObject<Block> LACQUERED_FENCE = register(() -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get())), "lacquered_fence");
    public static RegistryObject<Block> LACQUERED_DOOR = register(() -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get())), "lacquered_door");
    public static RegistryObject<Block> LACQUERED_TRAPDOOR = register(() -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get())), "lacquered_trapdoor");
    public static RegistryObject<Block> LACQUERED_PLATE = register(() -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get()).noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)), "lacquered_pressure_plate");
    public static RegistryObject<Block> LACQUERED_BUTTON = register(() -> new ButtonBlock(BlockSetType.OAK, 30, BlockBehaviour.Properties.ofFullCopy(LACQUERED_PLANKS.get()).noCollission().strength(0.5F).pushReaction(PushReaction.DESTROY)), "lacquered_button");

    public static RegistryObject<Block> ROOF_TILES_STAIRS = register(() -> new StairBlock(ROOF_TILES.get().defaultBlockState(), BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_STAIRS)), "roof_tiles_stairs");
    public static RegistryObject<Block> ROOF_TILES_SLAB = register(() -> new RoofSlabBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICK_SLAB)), "roof_tiles_slab");
    public static RegistryObject<Block> PAPER_LANTERN_RED = register(() -> new PaperLanternBlock(BlockBehaviour.Properties.of().mapColor(MapColor.WOOL).forceSolidOn().strength(1.0F).sound(SoundType.WOOL).lightLevel((p_152677_) -> {return 15;}).noOcclusion().pushReaction(PushReaction.DESTROY)), "paper_lantern_red");
    public static RegistryObject<Block> PAPER_LANTERN_WHITE = register(() -> new PaperLanternBlock(BlockBehaviour.Properties.ofFullCopy(PAPER_LANTERN_RED.get())), "paper_lantern_white");
    public static RegistryObject<Block> PAPER_LANTERN_BLACK = register(() -> new PaperLanternBlock(BlockBehaviour.Properties.ofFullCopy(PAPER_LANTERN_RED.get())), "paper_lantern_black");
    public static RegistryObject<Block> DRAGON_BLOCK = register(() -> new DragonBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE_BRICKS)), "dragon_trap");
    public static RegistryObject<Block> register(Supplier<Block> builder, String name) {
        RegistryObject<Block> blockRegistryObject = BLOCK.register(name, builder);
        ModItems.ITEM.register(name, () -> new BlockItem(blockRegistryObject.get(), new Item.Properties()));
        return blockRegistryObject;
    }
}
