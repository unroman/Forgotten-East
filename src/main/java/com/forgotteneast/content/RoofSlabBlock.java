package com.forgotteneast.content;

import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.*;

import javax.annotation.Nullable;

public class RoofSlabBlock extends SlabBlock {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public RoofSlabBlock(Properties p_56359_) {
        super(p_56359_);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext p_56361_) {
        return super.getStateForPlacement(p_56361_).setValue(FACING, p_56361_.getHorizontalDirection());
    }

    public BlockState rotate(BlockState p_56922_, Rotation p_56923_) {
        return p_56922_.setValue(FACING, p_56923_.rotate(p_56922_.getValue(FACING)));
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56388_) {
        p_56388_.add(TYPE, WATERLOGGED, FACING);
    }
}
