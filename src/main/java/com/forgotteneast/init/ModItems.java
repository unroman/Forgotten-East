package com.forgotteneast.init;

import com.forgotteneast.EastMod;
import com.forgotteneast.content.LuteItem;
import com.forgotteneast.content.StaffItem;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.SwordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(Registries.ITEM, EastMod.MODID);
    public static RegistryObject<Item> STAFF = register(() -> new StaffItem(ModTiers.STAFF, new Item.Properties().attributes(SwordItem.createAttributes(ModTiers.STAFF, 1, -2f))), "staff");
    public static RegistryObject<Item> LUTE = register(() -> new LuteItem(new Item.Properties().rarity(Rarity.RARE)), "lute");
    public static RegistryObject<Item> LACQUER = register(() -> new Item(new Item.Properties()), "lacquer");
    public static RegistryObject<Item> ADHERENT_EGG = register(() -> new ForgeSpawnEggItem(ModEntities.ADHERENT, 5451574, 12623485, new Item.Properties()), "adherent_spawn_egg");
    public static RegistryObject<Item> register(Supplier<Item> builder, String name) {
        return ITEM.register(name, builder);
    }
}
