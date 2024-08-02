package com.forgotteneast.content;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class PaperLanternBlock extends LanternBlock {

    public static final BooleanProperty GILDED = BooleanProperty.create("gilded");
    protected static final VoxelShape AABB = Shapes.or(Block.box(3, 0, 3, 13, 10, 13), Block.box(6, 10, 6, 10, 11, 10));
    protected static final VoxelShape HANGING_AABB = Shapes.or(Block.box(3, 5, 3, 13, 15, 13), Block.box(6, 4, 6, 10, 16, 10));

    public PaperLanternBlock(Properties p_153465_) {
        super(p_153465_);
        this.registerDefaultState(this.getStateDefinition().any().setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(GILDED, Boolean.valueOf(false)));
    }

    public VoxelShape getShape(BlockState p_153474_, BlockGetter p_153475_, BlockPos p_153476_, CollisionContext p_153477_) {
        return p_153474_.getValue(HANGING) ? HANGING_AABB : AABB;
    }
    protected ItemInteractionResult useItemOn(ItemStack itemstack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_332723_) {
        if (state.getValue(GILDED)) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        } else {
            if (itemstack.is(Items.GOLD_INGOT)) {
                level.playSound((Player)null, pos, SoundEvents.ARMOR_EQUIP_GOLD.get(), SoundSource.BLOCKS, 0.3F, 0.5F);
                this.gild(state, level, pos);
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                }
                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
    public void gild(BlockState state, Level level, BlockPos pos) {
        state = state.cycle(GILDED);
        level.setBlock(pos, state, 3);
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_153490_) {
        super.createBlockStateDefinition(p_153490_.add(GILDED));
    }
}
