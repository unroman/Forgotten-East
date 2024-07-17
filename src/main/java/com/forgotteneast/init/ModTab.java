package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EastMod.MODID);
    public static RegistryObject<CreativeModeTab> TAB = CREATIVE_TAB.register("eastgroup", () -> CreativeModeTab.builder()
            .icon(() -> ModItems.LACQUER.get().asItem().getDefaultInstance())
            .title(Component.translatable("itemGroup.eastgroup"))
            .displayItems((parameters, output) -> {
                output.accept(ModBlocks.PAPER_LANTERN_WHITE.get());
                output.accept(ModBlocks.PAPER_LANTERN_BLACK.get());
                output.accept(ModBlocks.PAPER_LANTERN_RED.get());
                output.accept(ModBlocks.PAPER_LANTERN_BLUE.get());
                output.accept(ModItems.STAFF.get());
                output.accept(ModItems.LUTE.get());
                output.accept(ModItems.LACQUER.get());
                output.accept(ModItems.ADHERENT_EGG.get());
                output.accept(ModBlocks.DRAGON_BLOCK.get());
                output.accept(ModBlocks.LACQUERED_PLANKS.get());
                output.accept(ModBlocks.LACQUERED_STAIRS.get());
                output.accept(ModBlocks.LACQUERED_SLAB.get());
                output.accept(ModBlocks.LACQUERED_PLATE.get());
                output.accept(ModBlocks.LACQUERED_FENCE.get());
                output.accept(ModBlocks.LACQUERED_TRAPDOOR.get());
                output.accept(ModBlocks.LACQUERED_DOOR.get());
                output.accept(ModBlocks.LACQUERED_BUTTON.get());
                output.accept(ModBlocks.ROOF_TILES.get());
                output.accept(ModBlocks.ROOF_TILES_STAIRS.get());
                output.accept(ModBlocks.ROOF_TILES_SLAB.get());
    }).build());
}
