package com.forgotteneast.content;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class StaffItem extends TieredItem {

    public StaffItem(Tier p_43269_, Item.Properties p_43272_) {
        super(p_43269_, p_43272_);
    }

    public boolean canAttackBlock(BlockState p_43291_, Level p_43292_, BlockPos p_43293_, Player p_43294_) {
        return !p_43294_.isCreative();
    }

    public float getDestroySpeed(ItemStack p_43288_, BlockState p_43289_) {
        return p_43289_.is(BlockTags.SWORD_EFFICIENT) ? 1.5F : 1.0F;
    }

    public boolean hurtEnemy(ItemStack itemstack, LivingEntity p_43279_, LivingEntity playerIn) {
        itemstack.hurtAndBreak(1, playerIn, EquipmentSlot.MAINHAND);
        return true;
    }

    public boolean mineBlock(ItemStack itemstack, Level p_43283_, BlockState p_43284_, BlockPos p_43285_, LivingEntity playerIn) {
        if (p_43284_.getDestroySpeed(p_43283_, p_43285_) != 0.0F) {
            itemstack.hurtAndBreak(2, playerIn, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    public InteractionResultHolder<ItemStack> use(Level p_43405_, Player p_43406_, InteractionHand p_43407_) {
        ItemStack itemstack = p_43406_.getItemInHand(p_43407_);
        p_43406_.startUsingItem(p_43407_);
        return InteractionResultHolder.consume(itemstack);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int p_41431_) {
        if (entity instanceof Player player) {
            if (player.hurtTime > 1) {
                player.getCooldowns().addCooldown(stack.getItem(), 40);
                player.sweepAttack();
                player.stopUsingItem();
            }
        }
    }
    @Override
    public boolean canPerformAction(ItemStack stack, net.minecraftforge.common.ToolAction toolAction) {
        return net.minecraftforge.common.ToolActions.DEFAULT_SHIELD_ACTIONS.contains(toolAction);
    }

    public int getUseDuration(ItemStack p_41454_, LivingEntity p_342054_) {return 72000;}


    public UseAnim getUseAnimation(ItemStack p_40678_) {
        return UseAnim.BRUSH;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Item.TooltipContext text, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.forgotteneast.staff").withStyle(ChatFormatting.GRAY));
    }
}

