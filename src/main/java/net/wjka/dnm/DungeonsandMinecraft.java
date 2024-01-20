package net.wjka.dnm;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsandMinecraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "dungeons_and_minecraft"; //important String. DON'T EDIT
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() { //similiar to main();
		/* This code runs as soon as Minecraft is in a mod-load-ready state.
		   However, some things (like resources) may still be uninitialized.
		   Proceed with mild caution.*/

		RegisterItems.InitRegister(); //Registring of all Items will be done via this method!




		LOGGER.info("Hello Fabric world!");
	}
}