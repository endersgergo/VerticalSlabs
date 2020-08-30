
package com.kriaga.verticalslabs.items;

import com.kriaga.verticalslabs.blocks.VerticalBrickSlab;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;

import static com.kriaga.verticalslabs.blocks.VerticalSlabBase.FULL;
import static com.kriaga.verticalslabs.util.RegistryHandler.VERTICAL_BRICK_SLAB;

public class VerticalBrickSlabItem extends BlockItem {
    public VerticalBrickSlabItem() {
        super(VERTICAL_BRICK_SLAB.get(), new Properties().group(ItemGroup.BUILDING_BLOCKS));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        BlockPos pos =  context.getPos().offset(context.getFace());
        PlayerEntity player = context.getPlayer();

        if (context.getWorld().getBlockState(pos).getBlock() instanceof VerticalBrickSlab &&
                !context.getWorld().getBlockState(pos).get(FULL)) {
            context.getWorld().setBlockState(pos, context.getWorld().getBlockState(pos).with(FULL, true));
            if (!player.isCreative()) player.getHeldItem(context.getHand()).setCount(player.getHeldItem(context.getHand()).getCount()-1);
            return ActionResultType.SUCCESS;
        }
        return super.onItemUse(context);
    }
}

    