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
    public static final Item TESTITEM = registerItems("testitem", new Item(new FabricItemSettings())); //init item

    private static void addItemsToInv(FabricItemGroupEntries entries){ //is used to add them to the player (creative) inventory
        entries.add(TESTITEM);
    }

    private static Item registerItems(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(DungeonsandMinecraft.MOD_ID, name), item);
    }
    public static void InitRegister(){
        DungeonsandMinecraft.LOGGER.info("Registring Started" + DungeonsandMinecraft.MOD_ID); //Logging for the console...

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(RegisterItems::addItemsToInv);//should be last piece of code to be run in order to add all items
        //were initialised before!
        }
    }

