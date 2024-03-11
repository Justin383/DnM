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
    private ServerWorld world;
    private BlockPos playerPos;
    private PlayerEntity player;

    public ModifyingTerrain(ServerWorld pWorld, PlayerEntity pPlayer){
        radius = 8;
        this.player = pPlayer;
        this.world = pWorld;
    }


    public void GatherPlayerPositionData() {
        playerPos = player.getBlockPos();
        // Now pass the ServerWorld to the RemoveBlocks method
        RemoveBlocks();
    }


    private void RemoveBlocks() {
        for (int height = -64; height <= 320; height += 5) {
            for (int rX = -radius; rX <= radius; rX++) {
                for (int rY = height; rY <= height + 5; rY++) {
                    for (int rZ = -radius; rZ <= radius; rZ++) {
                        BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, rY, playerPos.getZ() + rZ);
                        world.setBlockState(targetBlock, Blocks.AIR.getDefaultState(), 3); // Flag 3 needed for client update
                    }
                }
            }
        }
    }
}
