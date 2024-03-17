package net.wjka.dnm.EventGen;

import net.minecraft.block.Blocks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import net.wjka.dnm.DungeonsandMinecraft;

public class SpawnCage {
    private PlayerEntity player;
    private ServerWorld world;
    private int radius;
    private BlockPos playerPos;
    private static boolean hasSummonedCage; //bool to check if gamemode should be changed
    private static GameMode prevGameMode; //static var to store gamemode before the cage event

    public  SpawnCage(ServerWorld pWorld, PlayerEntity pPlayer){
        this.player = pPlayer;
        this.world = pWorld;
        radius = 1; //creates a 3x3 bedrock layer
    }

    public void GatherPlayerPositionData() {
        this.playerPos = player.getBlockPos();
        ChangeGameMode();
        SpawnCageAroundPlayer(world, playerPos);
    }

    private void ChangeGameMode(){
        if (player instanceof ServerPlayerEntity) { //check if player exists on server
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player; //get serverplayerentity from player
            prevGameMode = serverPlayer.interactionManager.getGameMode(); //stores last gamemode to change it back upon death
//            DungeonsandMinecraft.LOGGER.info("Gamemode: " + prevGameMode);
            if(prevGameMode == GameMode.ADVENTURE){
                prevGameMode = GameMode.DEFAULT; //sets player into the serverworld default gamemode if this has been called twice before he died
            }

            serverPlayer.changeGameMode(GameMode.ADVENTURE); //read from src code //changes gamemode -> implement gamemodechanger on death
            hasSummonedCage = true; //set val to check if gamemode should be changed upon death
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
        for (int height = 0; height <= 42; height++){
            BlockPos targetBlock = new BlockPos(playerPos.getX() , playerPos.getY() + height, playerPos.getZ());
            serverWorld.setBlockState(targetBlock, Blocks.AIR.getDefaultState(), 3);
        }

        //spawn anvil
        BlockPos targetBlock = new BlockPos(playerPos.getX() , playerPos.getY() + 40, playerPos.getZ());
        serverWorld.setBlockState(targetBlock, Blocks.ANVIL.getDefaultState(), 3);

    }

    public void ChangeToPrevGamemode(ServerPlayerEntity serverPlayer){
        if(hasSummonedCage){
//            DungeonsandMinecraft.LOGGER.info("callChangeGamemode");
            serverPlayer.changeGameMode(prevGameMode); //changes it to previous gamemode
            hasSummonedCage = false;
        }
    }


}
