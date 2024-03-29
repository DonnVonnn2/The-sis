package mersif.cooler;

import mersif.cooler.block.ModBlocks;
import mersif.cooler.event.ArmorChargingHandler;
import mersif.cooler.event.RedstoneChargingCallback;
import mersif.cooler.item.ModItemGroups;
import mersif.cooler.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mersif.cooler.item.ModItems;

public class SampleItem implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "cooler";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        LOGGER.info("Hello Fabric world!");
        ModItems.registerItems();
        ModRecipes.registerRecipes();
        ModItemGroups.registerItemGroups();
        ModBlocks.registerModBLocks();
        RedstoneChargingCallback.EVENT.register(new ArmorChargingHandler());
    }
}