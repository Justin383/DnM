package net.wjka.dnm;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.wjka.dnm.Items.ModdedItems;
import net.wjka.dnm.Player.NetworkingManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DungeonsandMinecraft implements ModInitializer {
	public static final String MOD_ID = "assets/dungeons_and_minecraft"; //MOD ID String. DON'T EDIT

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID); //setup of the Logger, similar to System.out.println but its considered to be used for mc mod developement
	// -> System.out.println wont show any output in the MC console after the project has been built and exported into a .jar MOD file


	@Override
	public void onInitialize() { //similiar to main(); -> entry point for fabric
		ModdedItems.InitRegisterItems(); //Registring of all Items
		NetworkingManager.registerTypePacketHandlers(); //register the handlers for dice type packets
		NetworkingManager.registerNumPacketHandlers(); //register the handlers for dice number packets
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, registryAccess) -> { //custom command registration
			CommandClass.registerCheatSheet(dispatcher); //register cheatsheet command
			CommandClass.registerGUIToggle(dispatcher); //register dicegui command
		});
		LOGGER.info("hi mum, i made a fabric mod c:"); //shows when the mod loads c:
	}
}