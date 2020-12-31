package io.github.ultrusbot.merryminecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class GiftBlock extends Block {
    private static final VoxelShape VOXEL_SHAPE = Block.createCuboidShape(3.5D, 0.0D, 3.5D, 12.5D, 8.0D, 12.5D);

    public GiftBlock(Settings settings) {
        super(settings);
    }
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VOXEL_SHAPE;
    }
}
