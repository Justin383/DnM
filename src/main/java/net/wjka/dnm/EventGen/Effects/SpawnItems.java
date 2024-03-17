package net.wjka.dnm.EventGen.Effects;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.wjka.dnm.DungeonsandMinecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SpawnItems {

    private BlockPos pos;
    private World world;
    private double h,x,y,z;
    private ItemStack stack;

    private List<Item> items = Arrays.asList(
            Items.DIAMOND,
            Items.IRON_INGOT,
            Items.GOLD_INGOT,
            Items.EMERALD,
            Items.ENDER_PEARL,
            Items.ENCHANTED_GOLDEN_APPLE,
            Items.NETHERITE_INGOT
            // created a list with items which could get spawned
    );

    List<Item> blocks = Arrays.asList(
            Items.STONE,
            Items.GRASS_BLOCK,
            Items.DIRT,
            Items.COBBLESTONE,
            Items.OAK_PLANKS,
            Items.DEEPSLATE
            // created a list with blocks which could get spawned.
    );

    public SpawnItems(BlockPos pPos, World pWorld){
        this.pos = pPos;
        this.world = pWorld;
        h = (double) EntityType.ITEM.getHeight() / 2.0;
        x = (double)pos.getX() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25);
        y = (double)pos.getY() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25) - h;
        z = (double)pos.getZ() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25);
    }

    public void GetInfo(int num){
        switch(num){
            case 0,1,2,3,4,5: SpawnItems(); break;
            case 6,7,8,9,10 : SpawnTools(); break;
            case 11,12,13,14,15,16,17,18,19,20: SpawnBlocks(); break;
        }
//        DungeonsandMinecraft.LOGGER.info("DuceNum from spawnItems: " + num);

    }


    private void SpawnItems(){
        // Select a random item from list, with its size (dynamic)
        Item randomItem = items.get(ThreadLocalRandom.current().nextInt(items.size() - 1));
        //saves the chosen item in a itemstack with the count of 16
        ItemStack stack = new ItemStack(randomItem, 16);
        //creates a new itemEntity with this, uses the playerCoords and the item we chose
        ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);
        //spawns :3
        world.spawnEntity(itemEntity);
    }

    private void SpawnTools(){
        int r = ThreadLocalRandom.current().nextInt(0, 4 + 1);
        switch(r){
            case 0:
                stack = new ItemStack(Items.NETHERITE_AXE, 1);
                break;
            case 1:
                stack = new ItemStack(Items.NETHERITE_PICKAXE, 1);
                break;
            case 2:
                stack = new ItemStack(Items.NETHERITE_SHOVEL, 1);
                break;
            case 3:
                stack = new ItemStack(Items.NETHERITE_SWORD, 1);
                break;
            case 4:
                stack = new ItemStack(Items.NETHERITE_HOE, 1);

                break;
        }
        ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);//create new item entity
        world.spawnEntity(itemEntity);

    }

    private void SpawnBlocks(){
        /*
          Block[] block = new Block[5];
          block[0] = Blocks.STONE;
          block[1] = Blocks.GRASS_BLOCK;
          ...
          we can create as many as we want with the array too
          -> List and array fullfill the same purpose
          --> but list just looks better
         */



        // Chose what Block from the list will be used
        Item randomBlock = blocks.get(ThreadLocalRandom.current().nextInt(items.size() - 1));

        // create itemstack from block from list
        ItemStack stack = new ItemStack(randomBlock, 64);

        // spawn the block
        ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);
        world.spawnEntity(itemEntity);
    }
}
