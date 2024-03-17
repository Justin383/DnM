package net.wjka.dnm;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DungeonsandMinecraftDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		//we dont need and use this

		/*
		[MOD_NAME]DataGenerator, in this case DungeonsandMinecraftDataGenerator is a method used to generate neccesary .json files
		on the fly once the mod loads, it is considered a good practice to use them for more complex mods, using more models, more crafting recipes,
		loot tables and other stuff because this can be automated to generate stuff for each item instead of generating the .json files manually
		however, given the few items we created and the small ammount of json files needed, this was the lowest priority to implement and for good reason.
		The time was used for more important and better things.
		 */
	}
}
