package com.kriaga.verticalslabs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.IWaterLoggable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.*;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public class VerticalSlabBase extends Block implements IWaterLoggable {

    //straight 0
    //inner left 1
    //inner right 2
    //outer left 3
    //outer right 4

    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final BooleanProperty FULL = BooleanProperty.create("full");
    public  static final IntegerProperty SHAPE = IntegerProperty.create("shape", 0, 4);

    // north facing
    private static final VoxelShape SHAPE_N = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_NIL = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 16, 16, 8),
            Block.makeCuboidShape(0, 0, 8, 8, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_NIR = Stream.of(
            Block.makeCuboidShape(0, 16, 0, 16, 0, 8),
            Block.makeCuboidShape(8, 16, 0, 16, 8, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_NOL = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 8, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_NOR = Stream.of(
            Block.makeCuboidShape(8, 0, 0, 16, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    // south facing
    private static final VoxelShape SHAPE_S = Stream.of(
            Block.makeCuboidShape(0, 0, 8, 16, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_SIL = Stream.of(
            Block.makeCuboidShape(0, 0, 8, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 0, 8, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_SIR = Stream.of(
            Block.makeCuboidShape(0, 0, 8, 16, 16, 16),
            Block.makeCuboidShape(8, 0, 0, 16, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_SOL = Stream.of(
            Block.makeCuboidShape(0, 0, 8, 8, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_SOR = Stream.of(
            Block.makeCuboidShape(8, 0, 8, 16, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    //west facing
    private static final VoxelShape SHAPE_W = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 8, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_WIL = Stream.of(
            Block.makeCuboidShape(0, 0, 8, 8, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_WIR = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 8, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_WOL = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 8, 16, 16),
            Block.makeCuboidShape(8, 0, 8, 16, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_WOR = Stream.of(
            Block.makeCuboidShape(0, 0, 0, 8, 16, 16),
            Block.makeCuboidShape(8, 0, 0, 16, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    // east facing
    private static final VoxelShape SHAPE_E = Stream.of(
            Block.makeCuboidShape(8, 0, 0, 16, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_EIL = Stream.of(
            Block.makeCuboidShape(8, 0, 8, 16, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_EIR = Stream.of(
            Block.makeCuboidShape(8, 0, 0, 16, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_EOL = Stream.of(
            Block.makeCuboidShape(8, 0, 0, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 8, 8, 16, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_EOR = Stream.of(
            Block.makeCuboidShape(8, 0, 0, 16, 16, 16),
            Block.makeCuboidShape(0, 0, 0, 8, 16, 8))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    private static final VoxelShape SHAPE_FULL = Stream.of(
            Block.makeCuboidShape(0, 16, 0, 16, 0, 16))
            .reduce((v1, v2) -> {
                return VoxelShapes.combineAndSimplify(v1, v2, IBooleanFunction.OR);}).get();

    public VerticalSlabBase(Properties properties) {
        super(properties.notSolid());
        this.setDefaultState(this.stateContainer.getBaseState().with(FULL, false).with(SHAPE, 0));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockState checkedState = checkAdjacentBlocks(context.getWorld(), context.getPos(),
                this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing()), 0);

        return checkedState;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        if (state.get(FULL)) return SHAPE_FULL;
        switch (state.get(FACING)) {
            case NORTH:
                if (state.get(SHAPE) == 1) return SHAPE_NIL;
                else if (state.get(SHAPE) == 2) return SHAPE_NIR;
                else if (state.get(SHAPE) == 3) return SHAPE_NOL;
                else if (state.get(SHAPE) == 4) return SHAPE_NOR;
                return SHAPE_N;
            case SOUTH:
                if (state.get(SHAPE) == 1) return SHAPE_SIL;
                else if (state.get(SHAPE) == 2) return SHAPE_SIR;
                else if (state.get(SHAPE) == 3) return SHAPE_SOL;
                else if (state.get(SHAPE) == 4) return SHAPE_SOR;
                return SHAPE_S;
            case EAST:
                if (state.get(SHAPE) == 1) return SHAPE_EOR;
                else if (state.get(SHAPE) == 2) return SHAPE_EOL;
                else if (state.get(SHAPE) == 3) return SHAPE_EIR;
                else if (state.get(SHAPE) == 4) return SHAPE_EIL;
                return SHAPE_E;
            case WEST:
                if (state.get(SHAPE) == 1) return SHAPE_WOR;
                else if (state.get(SHAPE) == 2) return SHAPE_WOL;
                else if (state.get(SHAPE) == 3) return SHAPE_WIR;
                else if (state.get(SHAPE) == 4) return SHAPE_WIL;
                return SHAPE_W;
            default:
                return SHAPE_N;
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.toRotation(state.get(FACING)));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, FULL, SHAPE, BlockStateProperties.WATERLOGGED);
    }

    private void updateNeighbours(World world, BlockPos pos, boolean blockBeingDestroyed) {
        BlockState north = world.getBlockState(pos.offset(Direction.NORTH));
        BlockState south = world.getBlockState(pos.offset(Direction.SOUTH));
        BlockState west = world.getBlockState(pos.offset(Direction.WEST));
        BlockState east = world.getBlockState(pos.offset(Direction.EAST));

        if (north.getBlock() instanceof VerticalSlabBase) {
            if (!blockBeingDestroyed)world.setBlockState(pos.offset(Direction.NORTH),
                    checkAdjacentBlocks(world, pos.offset(Direction.NORTH), north, 0));
            else world.setBlockState(pos.offset(Direction.NORTH),
                    checkAdjacentBlocks(world, pos.offset(Direction.NORTH), north, 2));
        }
        if (south.getBlock() instanceof VerticalSlabBase) {
            if (!blockBeingDestroyed) world.setBlockState(pos.offset(Direction.SOUTH),
                    checkAdjacentBlocks(world, pos.offset(Direction.SOUTH), south, 0));
            else world.setBlockState(pos.offset(Direction.SOUTH),
                    checkAdjacentBlocks(world, pos.offset(Direction.SOUTH), south, 1));
        }
        if (west.getBlock() instanceof VerticalSlabBase) {
            if (!blockBeingDestroyed) world.setBlockState(pos.offset(Direction.WEST),
                    checkAdjacentBlocks(world, pos.offset(Direction.WEST), west, 0));
            else world.setBlockState(pos.offset(Direction.WEST),
                    checkAdjacentBlocks(world, pos.offset(Direction.WEST), west, 4));
        }
        if (east.getBlock() instanceof VerticalSlabBase) {
            if (!blockBeingDestroyed) world.setBlockState(pos.offset(Direction.EAST),
                    checkAdjacentBlocks(world, pos.offset(Direction.EAST), east, 0));
            else world.setBlockState(pos.offset(Direction.EAST),
                    checkAdjacentBlocks(world, pos.offset(Direction.EAST), east, 3));
        }
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity, ItemStack stack) {
        updateNeighbours(world, pos, false);
        super.onBlockPlacedBy(world, pos, state, entity, stack);
    }

    @Override
    public void onBlockHarvested(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        updateNeighbours(world, pos, true);
        super.onBlockHarvested(world, pos, state, player);
    }

    public boolean canContainFluid(IBlockReader worldIn, BlockPos pos, BlockState state, Fluid fluidIn) {
        return !state.get(FULL);
    }

    private BlockState checkAdjacentBlocks(World world, BlockPos pos, BlockState center, int beingDestroyed) {
        /*
        beingDestroyed
        0 none
        1 north
        2 south
        3 west
        4 east
         */

        if (center.get(FULL)) return center;

        BlockState north = world.getBlockState(pos.offset(Direction.NORTH));
        if (beingDestroyed == 1) north = north.with(FULL, true);
        BlockState south = world.getBlockState(pos.offset(Direction.SOUTH));
        if (beingDestroyed == 2) south = south.with(FULL, true);
        BlockState west = world.getBlockState(pos.offset(Direction.WEST));
        if (beingDestroyed == 3) west = west.with(FULL, true);
        BlockState east = world.getBlockState(pos.offset(Direction.EAST));
        if (beingDestroyed == 4) east = east.with(FULL, true);

        //placing northwards
        if (center.get(FACING) == Direction.NORTH &&
                !((west.getBlock() instanceof VerticalSlabBase && !west.get(FULL) &&(west.get(FACING) == Direction.NORTH &&
                        (west.get(SHAPE) == 0 || west.get(SHAPE) == 1 || west.get(SHAPE) == 4) ||
                        west.get(FACING) == Direction.WEST && (west.get(SHAPE) == 0 || west.get(SHAPE) == 1))) &&
                (east.getBlock() instanceof VerticalSlabBase && !east.get(FULL) && (east.get(FACING) == Direction.NORTH &&
                        (east.get(SHAPE) == 0 || east.get(SHAPE) == 2 || east.get(SHAPE) == 3)||
                        east.get(FACING) == Direction.EAST && (east.get(SHAPE) == 0 || east.get(SHAPE) == 1))))
        ) {
            if (north.getBlock() instanceof VerticalSlabBase && !north.get(FULL)) {
                if (north.get(FACING) == Direction.WEST) {
                    if (east.getBlock() instanceof VerticalSlabBase && (
                        (east.get(FACING) == Direction.NORTH && (east.get(SHAPE) == 0 || east.get(SHAPE) == 3 || east.get(SHAPE) == 2 || east.get(SHAPE) == 4)) ||
                        (east.get(FACING) == Direction.EAST && (east.get(SHAPE) == 0 || east.get(SHAPE) == 1))
                    )) {
                        if (south.getBlock() instanceof VerticalSlabBase && !south.get(FULL)) {
                            if (south.get(FACING) == Direction.WEST) return center.with(SHAPE, 1);
                            else if (south.get(FACING) == Direction.EAST) return center.with(SHAPE, 2);
                        }
                        return  center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 3);
                }
                if (north.get(FACING) == Direction.EAST) {
                    if (west.getBlock() instanceof VerticalSlabBase && (
                        (west.get(FACING) == Direction.NORTH && (west.get(SHAPE) == 0 || west.get(SHAPE) == 4 || west.get(SHAPE) == 1 || west.get(SHAPE) == 3)) ||
                        (west.get(FACING) == Direction.WEST && (west.get(SHAPE) == 0 || west.get(SHAPE) == 1))
                    )) {
                        if (south.getBlock() instanceof VerticalSlabBase && !south.get(FULL)) {
                            if (south.get(FACING) == Direction.WEST) return center.with(SHAPE, 1);
                            else if (south.get(FACING) == Direction.EAST) return center.with(SHAPE, 2);
                        }
                        return  center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 4);
                }
            }
            if (south.getBlock() instanceof VerticalSlabBase && !south.get(FULL)) {
                if (south.get(FACING) == Direction.WEST) return center.with(SHAPE, 1);
                else if (south.get(FACING) == Direction.EAST) return center.with(SHAPE, 2);
            }
        }
        //placing southwards
        else if (center.get(FACING) == Direction.SOUTH &&
                !((west.getBlock() instanceof VerticalSlabBase && !west.get(FULL) &&(west.get(FACING) == Direction.SOUTH &&
                        (west.get(SHAPE) == 0 || west.get(SHAPE) == 2 || west.get(SHAPE) == 3) ||
                        west.get(FACING) == Direction.WEST && (west.get(SHAPE) == 0 || west.get(SHAPE) == 2))) &&
                (east.getBlock() instanceof VerticalSlabBase && !east.get(FULL) && (east.get(FACING) == Direction.SOUTH &&
                        (east.get(SHAPE) == 0 || east.get(SHAPE) == 1 || east.get(SHAPE) == 4)||
                        east.get(FACING) == Direction.EAST && (east.get(SHAPE) == 0 || east.get(SHAPE) == 2))))
        ) {
            if (south.getBlock() instanceof VerticalSlabBase && !south.get(FULL)) {
                if (south.get(FACING) == Direction.WEST){
                    if (east.getBlock() instanceof VerticalSlabBase && (
                        (east.get(FACING) == Direction.SOUTH && (east.get(SHAPE) == 0 || east.get(SHAPE) == 2 || east.get(SHAPE) == 3 || east.get(SHAPE) == 4)) ||
                        (east.get(FACING) == Direction.EAST && (east.get(SHAPE) == 0 || east.get(SHAPE) == 2))
                    )) {
                        if (north.getBlock() instanceof VerticalSlabBase && !north.get(FULL)) {
                            if (north.get(FACING) == Direction.WEST) return center.with(SHAPE, 1);
                            else if (north.get(FACING) == Direction.EAST) return center.with(SHAPE, 2);
                        }
                        return center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 3);
                }
                if (south.get(FACING) == Direction.EAST) {
                    if (west.getBlock() instanceof VerticalSlabBase && (
                        (west.get(FACING) == Direction.SOUTH && (west.get(SHAPE) == 0 || west.get(SHAPE) == 1 || west.get(SHAPE) == 4 || west.get(SHAPE) == 3)) ||
                        (west.get(FACING) == Direction.WEST && (west.get(SHAPE) == 0 || west.get(SHAPE) == 2))
                    )) {
                        if (north.getBlock() instanceof VerticalSlabBase && !north.get(FULL)) {
                            if (north.get(FACING) == Direction.WEST) return center.with(SHAPE, 1);
                            else if (north.get(FACING) == Direction.EAST) return center.with(SHAPE, 2);
                        }
                        return center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 4);
                }
            }
            if (north.getBlock() instanceof VerticalSlabBase && !north.get(FULL)) {
                if (north.get(FACING) == Direction.WEST) return center.with(SHAPE, 1);
                else if (north.get(FACING) == Direction.EAST) return center.with(SHAPE, 2);
            }
        }
        //placing westwards
        else if (center.get(FACING) == Direction.WEST &&
                !((south.getBlock() instanceof VerticalSlabBase && !south.get(FULL) &&(south.get(FACING) == Direction.WEST &&
                        (south.get(SHAPE) == 0 || south.get(SHAPE) == 1 || south.get(SHAPE) == 4) ||
                        south.get(FACING) == Direction.SOUTH && (south.get(SHAPE) == 0 || south.get(SHAPE) == 1))) &&
                (north.getBlock() instanceof VerticalSlabBase && !north.get(FULL) && (north.get(FACING) == Direction.WEST &&
                        (north.get(SHAPE) == 0 || north.get(SHAPE) == 2 || north.get(SHAPE) == 3)||
                        north.get(FACING) == Direction.NORTH && (north.get(SHAPE) == 0 || north.get(SHAPE) == 1))))
        ) {
            if (west.getBlock() instanceof VerticalSlabBase && !west.get(FULL)) {
                if (west.get(FACING) == Direction.NORTH) {
                    if (south.getBlock() instanceof VerticalSlabBase && (
                        (south.get(FACING) == Direction.WEST && (south.get(SHAPE) == 0 || south.get(SHAPE) == 2 || south.get(SHAPE) == 3 || south.get(SHAPE) == 4)) ||
                        (south.get(FACING) == Direction.SOUTH && (south.get(SHAPE) == 0 || south.get(SHAPE) == 1))
                    )){
                        if (east.getBlock() instanceof VerticalSlabBase && !east.get(FULL)) {
                            if (east.get(FACING) == Direction.NORTH) return center.with(SHAPE, 1);
                            else if (east.get(FACING) == Direction.SOUTH) return center.with(SHAPE, 2);
                        }
                        return center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 3);
                }
                if (west.get(FACING) == Direction.SOUTH) {
                    if (north.getBlock() instanceof VerticalSlabBase && (
                        (north.get(FACING) == Direction.WEST && (north.get(SHAPE) == 0 || north.get(SHAPE) == 1 || north.get(SHAPE) == 4 || north.get(SHAPE) == 3)) ||
                        (north.get(FACING) == Direction.NORTH && (north.get(SHAPE) == 0 || north.get(SHAPE) == 1))
                    )){
                        if (east.getBlock() instanceof VerticalSlabBase && !east.get(FULL)) {
                            if (east.get(FACING) == Direction.NORTH) return center.with(SHAPE, 1);
                            else if (east.get(FACING) == Direction.SOUTH) return center.with(SHAPE, 2);
                        }
                        return center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 4);
                }
            }
            if (east.getBlock() instanceof VerticalSlabBase && !east.get(FULL)) {
                if (east.get(FACING) == Direction.NORTH) return center.with(SHAPE, 1);
                else if (east.get(FACING) == Direction.SOUTH) return center.with(SHAPE, 2);
            }
        }
        //placing eastwards
        else if (center.get(FACING) == Direction.EAST &&
                !((south.getBlock() instanceof VerticalSlabBase && !south.get(FULL) &&(south.get(FACING) == Direction.EAST &&
                        (south.get(SHAPE) == 0 || south.get(SHAPE) == 3 || south.get(SHAPE) == 4) ||
                        south.get(FACING) == Direction.SOUTH && (south.get(SHAPE) == 0 || south.get(SHAPE) == 2))) &&
                (north.getBlock() instanceof VerticalSlabBase && !north.get(FULL) && (north.get(FACING) == Direction.EAST &&
                        (north.get(SHAPE) == 0 || north.get(SHAPE) == 2 || north.get(SHAPE) == 3)||
                        north.get(FACING) == Direction.NORTH && (north.get(SHAPE) == 0 || north.get(SHAPE) == 2))))
        ) {
            if (east.getBlock() instanceof VerticalSlabBase && !east.get(FULL)) {
                if (east.get(FACING) == Direction.NORTH) {
                    if (south.getBlock() instanceof VerticalSlabBase && (
                        (south.get(FACING) == Direction.EAST && (south.get(SHAPE) == 0 || south.get(SHAPE) == 2 || south.get(SHAPE) == 3 || south.get(SHAPE) == 4)) ||
                        (south.get(FACING) == Direction.SOUTH && (south.get(SHAPE) == 0 || south.get(SHAPE) == 2))
                    )){
                        if (west.getBlock() instanceof VerticalSlabBase && !west.get(FULL)) {
                            if (west.get(FACING) == Direction.NORTH) return center.with(SHAPE, 1);
                            else if (west.get(FACING) == Direction.SOUTH) return center.with(SHAPE, 2);
                        }
                        return center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 3);
                }
                if (east.get(FACING) == Direction.SOUTH) {
                    if (north.getBlock() instanceof VerticalSlabBase && (
                        (north.get(FACING) == Direction.EAST && (north.get(SHAPE) == 0 || north.get(SHAPE) == 1 || north.get(SHAPE) == 4 || north.get(SHAPE) == 3)) ||
                        (north.get(FACING) == Direction.NORTH && (north.get(SHAPE) == 0 || north.get(SHAPE) == 2))
                    )){
                        if (west.getBlock() instanceof VerticalSlabBase && !west.get(FULL)) {
                            if (west.get(FACING) == Direction.NORTH) return center.with(SHAPE, 1);
                            else if (west.get(FACING) == Direction.SOUTH) return center.with(SHAPE, 2);
                        }
                        return center.with(SHAPE, 0);
                    }
                    return center.with(SHAPE, 4);
                }
            }
            if (west.getBlock() instanceof VerticalSlabBase && !west.get(FULL)) {
                if (west.get(FACING) == Direction.NORTH) return center.with(SHAPE, 1);
                else if (west.get(FACING) == Direction.SOUTH) return center.with(SHAPE, 2);
            }
        }
        return center.with(SHAPE, 0);
    }
}
