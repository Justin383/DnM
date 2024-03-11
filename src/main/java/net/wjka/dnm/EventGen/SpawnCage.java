package net.wjka.dnm.EventGen;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;

public class SpawnCage {
    private PlayerEntity player;
    private ServerWorld world;
    private int radius;

    public  SpawnCage(ServerWorld pWorld, PlayerEntity pPlayer){
        this.player = pPlayer;
        this.world = pWorld;
        radius = 1; //creates a 3x3 bedrock layer
    }

    public void GatherPlayerPositionData() {
        BlockPos playerPos = player.getBlockPos();
        ChangeGameMode(world);
        SpawnCageAroundPlayer(world, playerPos);
    }

    private void ChangeGameMode(ServerWorld world){
        if (player instanceof ServerPlayerEntity) { //check if player exists on server
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player; //get serverplayerentity from player
            serverPlayer.changeGameMode(GameMode.ADVENTURE); //read from src code //changes gamemode
        }
    }


    private void SpawnCageAroundPlayer(ServerWorld serverWorld, BlockPos playerPos){

        //bottom bedrock layer
        for (int rX = -radius; rX <= radius; rX++) {
            for (int rY = -1; rY <= -1 + 5; rY++) {
                for (int rZ = -radius; rZ <= radius; rZ++) {
                    BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, playerPos.getY() -1, playerPos.getZ() + rZ);
                    serverWorld.setBlockState(targetBlock, Blocks.BEDROCK.getDefaultState(), 3); // Flag 3 for client update //needed or nothing changes for player THANK GOD THIS IS HANDLED BY MINECRAFT
                    }
                }
            }

        //cage
        for (int height = 0; height <= 2; height++){
            for (int rX = -radius; rX <= radius; rX++) {
                for (int rY = -1; rY <= -1 + 5; rY++) {
                    for (int rZ = -radius; rZ <= radius; rZ++) {
                        BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, playerPos.getY() + height, playerPos.getZ() + rZ);
                        serverWorld.setBlockState(targetBlock, Blocks.IRON_BARS.getDefaultState(), 3); // Flag 3 for client update //needed or nothing changes for player THANK GOD THIS IS HANDLED BY MINECRAFT
                    }
                }
            }
        }
        //clear the middle for cage
        for (int height = 0; height <= 2; height++){
            BlockPos targetBlock = new BlockPos(playerPos.getX() , playerPos.getY() + height, playerPos.getZ());
            serverWorld.setBlockState(targetBlock, Blocks.AIR.getDefaultState(), 3);
        }


        //spawn anvil
        BlockPos targetBlock = new BlockPos(playerPos.getX() , playerPos.getY() + 40, playerPos.getZ());
        serverWorld.setBlockState(targetBlock, Blocks.ANVIL.getDefaultState(), 3);

    }


}
