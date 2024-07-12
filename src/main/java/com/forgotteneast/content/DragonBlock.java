package com.forgotteneast.content;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.Nullable;

public class DragonBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty CHARGED = BlockStateProperties.ENABLED;
    public DragonBlock(Properties p_49795_) {
        super(p_49795_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(CHARGED, true));
    }

    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block p_55669_, BlockPos p_55670_, boolean p_55671_) {
        if (state.getValue(CHARGED)) {
            if (level.hasNeighborSignal(pos)) {
                for (int b=1;b<5;b+=1) {
                    BlockPos firepos = getFront(state, pos, b);
                    if (level.isEmptyBlock(firepos)) {
                        level.setBlock(firepos, Blocks.FIRE.defaultBlockState(), 2);
                    } else break;
                }
                level.playSound((Player)null, pos, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.3F, 0.5F);
                this.discharge(state, level, pos);
            }
        }
    }
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (state.getValue(CHARGED)) {
            return InteractionResult.PASS;
        } else {
            if (itemstack.is(Items.LAVA_BUCKET)) {
                level.playSound((Player)null, pos, SoundEvents.LAVA_POP, SoundSource.BLOCKS, 0.3F, 0.5F);
                this.discharge(state, level, pos);
                if (!player.isCreative()) {
                    itemstack.shrink(1);
                    player.setItemInHand(hand, Items.BUCKET.getDefaultInstance());
                }
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    public void discharge(BlockState state, Level level, BlockPos pos) {
        state = state.cycle(CHARGED);
        level.setBlock(pos, state, 3);
    }
    public BlockPos getFront(BlockState state, BlockPos pos, int far) {
        Direction face = state.getValue(FACING);
        switch (face) {
            case SOUTH:
                return pos.south(far);
            case WEST:
                return pos.west(far);
            case EAST:
                return pos.east(far);
            default:
                return pos.north(far);
        }
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_56361_) {
        return super.getStateForPlacement(p_56361_).setValue(FACING, p_56361_.getHorizontalDirection().getOpposite());
    }

    public BlockState rotate(BlockState p_56922_, Rotation p_56923_) {
        return p_56922_.setValue(FACING, p_56923_.rotate(p_56922_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56388_) {
        p_56388_.add(FACING, CHARGED);
    }
}
