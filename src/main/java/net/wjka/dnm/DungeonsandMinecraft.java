package net.wjka.dnm;

import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsandMinecraft implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "dungeons_and_minecraft"; //MOD ID String. DON'T EDIT

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() { //similiar to main();
		/* This code runs as soon as Minecraft is in a mod-load-ready state.
		   However, some things (like resources) may still be uninitialized.
		   Proceed with mild caution.*/

		RegisterItems.InitRegisterItems(); //Registring of all Items will be done via this method!
		RegisterBlocks.InitRegisterBlocks(); //Registring of all Blocks




		LOGGER.info("Hello Fabric world!");
	}
}