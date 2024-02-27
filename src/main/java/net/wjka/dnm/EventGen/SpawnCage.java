package net.wjka.dnm.EventGen;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class SpawnCage {
    PlayerEntity player = MinecraftClient.getInstance().player;
    private int radius;

    public  SpawnCage(){
        radius = 1; //creates a 3x3 bedrock layer
        //nothing yet
    }

    public void GatherPlayerPositionData(ServerWorld serverWorld, PlayerEntity player) {

        BlockPos playerPos = player.getBlockPos();
        // Now pass the ServerWorld to the RemoveBlocks method
//        RemoveBlocks(serverWorld, playerPos);
        SpawnCageAroundPlayer(serverWorld, playerPos);
    }


    private void SpawnCageAroundPlayer(ServerWorld serverWorld, BlockPos playerPos){
    //bottom bedrock layer

        for (int rX = -radius; rX <= radius; rX++) {
            for (int rY = -1; rY <= -1 + 5; rY++) {
                for (int rZ = -radius; rZ <= radius; rZ++) {
                    BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, playerPos.getY() -1, playerPos.getZ() + rZ);
                    // Use the ServerWorld instance to change the block state
                    serverWorld.setBlockState(targetBlock, Blocks.BEDROCK.getDefaultState(), 3); // Flag 3 for client update //needed or nothing changes for player THANK GOD THIS IS HANDLED BY MINECRAFT
                    }
                }
            }

        //cage around player
        for (int height = 0; height <= 2; height++){
            for (int rX = -radius; rX <= radius; rX++) {
                for (int rY = -1; rY <= -1 + 5; rY++) {
                    for (int rZ = -radius; rZ <= radius; rZ++) {
                        BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, playerPos.getY() + height, playerPos.getZ() + rZ);
                        // Use the ServerWorld instance to change the block state
                        serverWorld.setBlockState(targetBlock, Blocks.IRON_BARS.getDefaultState(), 3); // Flag 3 for client update //needed or nothing changes for player THANK GOD THIS IS HANDLED BY MINECRAFT
                    }
                }
            }
        }
        //clear the middle for cage
        for (int height = 0; height <= 2; height++){
            BlockPos targetBlock = new BlockPos(playerPos.getX() , playerPos.getY() + height, playerPos.getZ());
            // Use the ServerWorld instance to change the block state
            serverWorld.setBlockState(targetBlock, Blocks.AIR.getDefaultState(), 3);
        }


        //spawn anvil
        BlockPos targetBlock = new BlockPos(playerPos.getX() , playerPos.getY() + 40, playerPos.getZ());
        // Use the ServerWorld instance to change the block state
        serverWorld.setBlockState(targetBlock, Blocks.ANVIL.getDefaultState(), 3);

    }


}
