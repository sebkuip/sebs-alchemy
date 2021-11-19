package com.sebkuip.sebalchemy.item.complex;

import com.sebkuip.sebalchemy.util.ModSoundEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

import java.util.Objects;

public class FireGem extends Item {
    public FireGem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = Objects.requireNonNull(context.getPlayer()).getCommandSenderWorld();

        if(!world.isClientSide()) {
            PlayerEntity playerEntity = context.getPlayer();
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());

            if(stack.getDamageValue() == stack.getMaxDamage()-1) {
                return ActionResultType.FAIL;
            }

            if (clickedBlock.getBlock() == Blocks.STONE) {
                world.setBlock(context.getClickedPos(), Blocks.GOLD_BLOCK.defaultBlockState(), 3);
                world.playSound(null, context.getClickedPos(), ModSoundEvents.STONE_TO_GOLD.get(), SoundCategory.BLOCKS, 1, 1);
                stack.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
            }
        }

        return ActionResultType.SUCCESS;
    }

}
