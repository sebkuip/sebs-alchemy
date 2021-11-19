package com.sebkuip.sebalchemy;

import com.sebkuip.sebalchemy.block.ModBlocks;
import com.sebkuip.sebalchemy.item.ModItems;
import com.sebkuip.sebalchemy.util.ModSoundEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod("sebalchemy")
public class SebAlchemy {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "sebalchemy";

    public SebAlchemy() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModSoundEvents.register(modEventBus);

        modEventBus.addListener(this::setup);
        modEventBus.addListener(this::doClientStuff);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event) {

    }

    private void doClientStuff(final FMLClientSetupEvent event) {

    }

}