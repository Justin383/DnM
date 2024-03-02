package net.wjka.dnm.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.item.Dice.NegativeDice;
import net.wjka.dnm.item.Dice.NeutralDice;
import net.wjka.dnm.item.Dice.PositiveDice;
//lots of imports :0

public class ModdedItems {
        //declarations of Items :3
    //this type (public static final item ...) is neccesary for fabric(minecraft) to know we create a new item
    public static final Item NEUTRALDICE = registerItems("neutral_dice", new NeutralDice(new FabricItemSettings()));

    public static final Item NEGATIVEDICE = registerItems("negative_dice", new NegativeDice(new FabricItemSettings()));
    public static final Item POSITIVEDICE = registerItems("positive_dice", new PositiveDice(new FabricItemSettings()));


    private static Item registerItems(String name, Item item){ //handles registering our items, so we don't need to write this manually for every item
        return Registry.register(Registries.ITEM, new Identifier(DungeonsandMinecraft.MOD_ID, name), item);
    }

    private static void addItemsToCreativeInv(FabricItemGroupEntries entries){ //is used to add them to the player (creative) inventory
       //make items available for the creative player inventory
        entries.add(NEUTRALDICE); entries.add(NEGATIVEDICE); entries.add(POSITIVEDICE);
    }

    public static void InitRegisterItems(){
        DungeonsandMinecraft.LOGGER.info("Registring of Items Started for: " + DungeonsandMinecraft.MOD_ID); //Logging for the console...

        //Add Items which are being registered here to the specified ItemGroup
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ModdedItems::addItemsToCreativeInv);//should be last piece of code to be run in order to add all items

    }
    //add item to itemgroup ingredients

}
