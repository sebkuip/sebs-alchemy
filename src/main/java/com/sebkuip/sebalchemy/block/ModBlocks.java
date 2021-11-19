package com.sebkuip.sebalchemy.block;

import com.sebkuip.sebalchemy.SebAlchemy;
import com.sebkuip.sebalchemy.item.ModItemGroup;
import com.sebkuip.sebalchemy.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SebAlchemy.MOD_ID);

    public static final RegistryObject<Block> AMETHYST_ORE = registerOre("amethyst_ore",
            () -> new Block(AbstractBlock.Properties.of(Material.STONE).harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(5f)));

    public static final RegistryObject<Block> AMETHYST_BLOCK = registerBlock("amethyst_block",
            () -> new Block(AbstractBlock.Properties.of(Material.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE).strength(5f)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.SEBALCHEMY_BLOCK_GROUP)));
    }

    private static <T extends Block>RegistryObject<T> registerOre(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerOreItem(name, toReturn);

        return toReturn;
    }

    private static <T extends Block> void registerOreItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(ModItemGroup.SEBALCHEMY_ORE_GROUP)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }

}
