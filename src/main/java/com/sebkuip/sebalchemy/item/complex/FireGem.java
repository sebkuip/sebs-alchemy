package com.sebkuip.sebalchemy.item.complex;

import com.sebkuip.sebalchemy.util.ModSoundEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FireGem extends Item {
    public FireGem(Properties properties) {
        super(properties);
        MinecraftForge.EVENT_BUS.addListener(FireGem::serverTick);
    }

    private static final List<ConvertingBlock> converting = new ArrayList<>();

    private boolean addConverting(ConvertingBlock block) {
        for (ConvertingBlock other : converting) {
            if (block.equals(other)) {
                return false;
            }
        }
        converting.add(block);
        return true;
    }

    private static void serverTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        List<ConvertingBlock> toRemove = new ArrayList<>();
        for (ConvertingBlock block: converting) {
            if (block.frame == 30) {
                toRemove.add(block);
                block.lastFrame();
            } else {
                block.playFrame();
                block.frame++;
            }
        }
        for (ConvertingBlock block: toRemove) {
            converting.remove(block);
        }
    }


    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        double chance = 0.35;
        World world = Objects.requireNonNull(context.getPlayer()).getCommandSenderWorld();

        if(!world.isClientSide()) {
            PlayerEntity playerEntity = context.getPlayer();
            BlockState clickedBlock = world.getBlockState(context.getClickedPos());
            ServerWorld serverWorld = (ServerWorld) world;
            if(stack.getDamageValue() == stack.getMaxDamage()-1) {
                return ActionResultType.FAIL;
            }

            if (clickedBlock.getBlock() == Blocks.STONE) {
                ConvertingBlock block = new ConvertingBlock(chance > random.nextDouble(), serverWorld, context.getClickedPos());
                if (addConverting(block)) {
                    stack.hurtAndBreak(1, playerEntity, player -> player.broadcastBreakEvent(EquipmentSlotType.MAINHAND));
                    serverWorld.playSound(null, context.getClickedPos(), ModSoundEvents.STONE_TO_GOLD.get(), SoundCategory.BLOCKS, 1, 1);
                } else {
                    return ActionResultType.FAIL;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }
}

class ConvertingBlock {
    public int frame;
    boolean success;
    ServerWorld world;
    BlockPos pos;
    public ConvertingBlock(boolean success, ServerWorld world, BlockPos pos) {
        frame = 0;
        this.success = success;
        this.world = world;
        this.pos = pos;
    }

    public void lastFrame() {
        if (success) {
            world.sendParticles(new BlockParticleData(ParticleTypes.BLOCK, Blocks.GOLD_BLOCK.defaultBlockState()),
                    pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d,
                    20, 0.1d, 0.1d, 0.1d, 2d);
            world.setBlock(pos, Blocks.GOLD_BLOCK.defaultBlockState(), 3);
        } else {
            world.sendParticles(new BlockParticleData(ParticleTypes.BLOCK, Blocks.REDSTONE_BLOCK.defaultBlockState()),
                    pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d,
                    20, 0.1d, 0.1d, 0.1d, 2d);
        }
    }

    public void playFrame() {
            world.sendParticles(new BlockParticleData(ParticleTypes.BLOCK, Blocks.STONE.defaultBlockState()),
                    pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5,
                    5, 0.1d, 0.1d, 0.1d, 2d);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof ConvertingBlock)) {
            return false;
        }
        ConvertingBlock o = (ConvertingBlock) other;
        return this.pos.equals(o.pos);
    }
}
