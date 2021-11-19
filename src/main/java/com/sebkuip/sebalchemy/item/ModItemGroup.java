package com.sebkuip.sebalchemy.item;

import com.sebkuip.sebalchemy.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {

    public static final ItemGroup SEBALCHEMY_ORE_GROUP = new ItemGroup("sebalchemyOreTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.AMETHYST_ORE.get());
        }
    };

    public static final ItemGroup SEBALCHEMY_BLOCK_GROUP = new ItemGroup("sebalchemyBlockTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.AMETHYST_BLOCK.get());
        }
    };

    public static final ItemGroup SEBALCHEMY_ITEM_GROUP = new ItemGroup("sebalchemyItemTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };

    public static final ItemGroup SEBALCHEMY_TOOL_GROUP = new ItemGroup("sebalchemyToolTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.AMETHYST.get());
        }
    };
}
