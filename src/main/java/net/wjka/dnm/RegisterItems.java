package net.wjka.dnm;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry; //use this registry not java.rmi.registry!

public class RegisterItems {
    //declarations of Items :3
    public static final Item TESTITEM = registerItems("testitem", new Item(new FabricItemSettings())); //init item

    private static void addItemsToInv(FabricItemGroupEntries entries){ //is used to add them to the player (creative) inventory
        entries.add(TESTITEM);
    } //as said... add items to creative inventory :3

    private static Item registerItems(String name, Item item){ //handles registering our items, so we dont need to write this manually for every item
        return Registry.register(Registries.ITEM, new Identifier(DungeonsandMinecraft.MOD_ID, name), item);
    }
    public static void InitRegisterItems(){
        DungeonsandMinecraft.LOGGER.info("Registring of Items Started" + DungeonsandMinecraft.MOD_ID); //Logging for the console...


        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(RegisterItems::addItemsToInv);//should be last piece of code to be run in order to add all items
        //were initialised before!
        }
        //add item to itemgroup ingredients
    }

