package com.forgotteneast.init;

import com.google.common.base.Suppliers;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
    STAFF(BlockTags.INCORRECT_FOR_STONE_TOOL, 198, 2.0F, 2.0F, 6, () -> {
        return Ingredient.of(ItemTags.PLANKS);
    });
    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    private ModTiers(
            final TagKey<Block> p_334032_, final int p_43332_, final float p_43334_, final float p_43335_, final int p_43333_, final Supplier<Ingredient> p_43337_
    ) {
        this.incorrectBlocksForDrops = p_334032_;
        this.uses = p_43332_;
        this.speed = p_43334_;
        this.damage = p_43335_;
        this.enchantmentValue = p_43333_;
        this.repairIngredient = Suppliers.memoize(p_43337_::get);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}