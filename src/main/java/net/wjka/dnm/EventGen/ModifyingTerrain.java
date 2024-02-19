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
    private int radius;
//    PlayerEntity player = MinecraftClient.getInstance().player;


    public ModifyingTerrain(){
        radius = 8;
    }


    public void GatherPlayerPositionData(ServerWorld serverWorld, PlayerEntity player) {
        BlockPos playerPos = player.getBlockPos();
        // Now pass the ServerWorld to the RemoveBlocks method
        RemoveBlocks(serverWorld, playerPos);
    }

    //not needed... maybe ill delete it or optimise this code!

//    private BlockBox GenerateBlockArea(int playerX, int playerY, int playerZ, int radius){
//        int rMin = radius * (-1);
//        int rMax = radius;
//        int X = playerX;
//        int Y = playerY;
//        int Z = playerZ;
//        int height = 0;
//
//        ArrayList<BlockPos> list = Lists.newArrayList();
//        BlockBox bp = new BlockBox(playerX + rMax, height, playerZ + rMin, playerX + rMin, height + 1, playerZ + rMax);
//
//        return bp;
//    }

    private void RemoveBlocks(ServerWorld serverWorld, BlockPos playerPos) {
        for (int height = -64; height <= 320; height += 5) {
            for (int rX = -radius; rX <= radius; rX++) {
                for (int rY = height; rY <= height + 5; rY++) {
                    for (int rZ = -radius; rZ <= radius; rZ++) {
                        BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, rY, playerPos.getZ() + rZ);
                        // Use the ServerWorld instance to change the block state
                        serverWorld.setBlockState(targetBlock, Blocks.AIR.getDefaultState(), 3); // Flag 3 for client update
                    }
                }
            }
        }
    }

}
