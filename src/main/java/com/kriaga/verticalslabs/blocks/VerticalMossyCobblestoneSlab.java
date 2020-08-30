
    package com.kriaga.verticalslabs.blocks;

import com.kriaga.verticalslabs.items.VerticalMossyCobblestoneSlabItem;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class VerticalMossyCobblestoneSlab extends VerticalSlabBase {

    public VerticalMossyCobblestoneSlab() {
        super(
                Properties.create(Material.ROCK)
                        .sound(SoundType.STONE)
                        .hardnessAndResistance(2.0f, 6.0f)
                        .harvestTool(ToolType.PICKAXE)
                        .harvestLevel(0)
                        .func_235861_h_()
        );
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (hit.getFace().getOpposite() == state.get(FACING) &&
                !state.get(FULL) &&
                player.getHeldItem(handIn).getItem() instanceof VerticalMossyCobblestoneSlabItem) {
            worldIn.setBlockState(pos, state.with(FULL, true));
            if (!player.isCreative()) player.getHeldItem(handIn).setCount(player.getHeldItem(handIn).getCount()-1);
            return ActionResultType.SUCCESS;
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
}

    