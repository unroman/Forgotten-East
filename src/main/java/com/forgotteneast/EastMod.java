package com.forgotteneast;

import com.forgotteneast.init.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("forgotteneast")
public class EastMod {
    public static final String MODID = "forgotteneast";

    public EastMod() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        bus.addListener(ModEntities::attributes);
        bus.addListener(ModEntities::renderers);
        bus.addListener(ModEntities::layers);
        ModTypes.TYPE.register(bus);
        ModPieces.PIECE.register(bus);
        ModItems.ITEM.register(bus);
        ModBlocks.BLOCK.register(bus);
        ModEntities.ENTITY.register(bus);
        ModSounds.SOUND.register(bus);
        ModTab.CREATIVE_TAB.register(bus);
    }
}
