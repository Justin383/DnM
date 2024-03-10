package net.wjka.dnm.EventGen;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

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
