package com.sebkuip.sebalchemy.item;

import com.sebkuip.sebalchemy.SebAlchemy;
import com.sebkuip.sebalchemy.item.complex.FireGem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SebAlchemy.MOD_ID);

    public static final RegistryObject<Item> AMETHYST = ITEMS.register("amethyst", () -> new Item(new Item.Properties().tab(ModItemGroup.SEBALCHEMY_ITEM_GROUP)));
    public static final RegistryObject<Item> AMETHYST_STICK = ITEMS.register("amethyst_stick", () -> new Item(new Item.Properties().tab(ModItemGroup.SEBALCHEMY_ITEM_GROUP)));

    public static final RegistryObject<Item> FIREGEM = ITEMS.register("firegem", () -> new FireGem(new Item.Properties().tab(ModItemGroup.SEBALCHEMY_ITEM_GROUP).durability(10)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
