package net.wjka.dnm.EventGen;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.chunk.Chunk;
import net.wjka.dnm.DungeonsandMinecraft;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class ModifyingTerrain {
    private int playerX, playerZ, playerY; //is Y but Minecraft uses X and Z for its horizontal pos. Y is vertical!
    private int radius;
    private int rX, rY, rZ;
    PlayerEntity player = MinecraftClient.getInstance().player;


    public ModifyingTerrain(){
        radius = 5;
    }

    public void GatherPlayerPositionData(){
        playerX = player.getBlockX();
        playerZ = player.getBlockZ();
        playerY = player.getBlockY();

        GatherAreaData();

    }

    private void GatherAreaData(){
        World world = player.getWorld();
        BlockPos playerPos = player.getBlockPos();
        ChunkPos chunkPos = player.getChunkPos();



        RemoveBlocks(world, playerPos);
    }

    private BlockBox GenerateBlockArea(int playerX, int playerY, int playerZ, int radius){
        int rMin = radius * (-1);
        int rMax = radius;
        int X = playerX;
        int Y = playerY;
        int Z = playerZ;
        int height = 0;

        ArrayList<BlockPos> list = Lists.newArrayList();
        BlockBox bp = new BlockBox(playerX + rMax, height, playerZ + rMin, playerX + rMin, height + 1, playerZ + rMax);

        return bp;
    }

    private void RemoveBlocks(World world, BlockPos playerPos){
        DungeonsandMinecraft.LOGGER.info("server");
        MinecraftClient.getInstance().execute(() -> {
            for (int height = -64; height <= 320; height = height + 5){
                for (rX = -radius; rX <= radius; rX++){
                    for (rY = height; rY <= height + 5; rY++) {
                        for (rZ = -radius; rZ <= radius; rZ++){
                            ArrayList<BlockPos> list = Lists.newArrayList();
                            BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, rY, playerPos.getZ() + rZ);
                            world.setBlockState(targetBlock, Blocks.AIR.getDefaultState()); //replace block with AIR on current area
                            world.updateNeighbors(targetBlock, Blocks.AIR);

                            }
                        }
                    }
                }
            });
    }










}
