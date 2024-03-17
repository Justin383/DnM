package net.wjka.dnm.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.PlayerActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class BlockBreakMixin {


    @Mixin(ServerPlayerInteractionManager.class)
    public interface ServerPlayerInteractionManagerAccessor {
        @Accessor("player")
        ServerPlayerEntity getPlayer(); //getplayer accessor because its private in the ServerPlayerInteractionManager class
    }


    @Inject(at = @At("HEAD"), method = "tryBreakBlock")
    public void onExecute(BlockPos pos, CallbackInfoReturnable<Boolean> cir){
        ServerPlayerInteractionManager manager = (ServerPlayerInteractionManager) (Object) this;

        ServerPlayerEntity player = ((ServerPlayerInteractionManagerAccessor)this).getPlayer(); //get player
        World world = player.getWorld(); //get world
        ServerWorld sWorld = player.getServerWorld(); //get serverworld
        BlockState blockState = world.getBlockState(pos); //get blockstate of block in coords pos
        Block minedBlock = blockState.getBlock(); //get block from the blockstate
        ItemStack stack = player.getMainHandStack(); //get stack -> current item held
//        DungeonsandMinecraft.LOGGER.info("player broke a blocc" + minedBlock);
        //call playerActions class
        PlayerActions pA = new PlayerActions(player, sWorld); //create obj
        //call PlayerActions GetBlock Method
        pA.BlockMined(minedBlock, stack); //call the method, that a block was mined
        pA.setLastBlockBrokeByPlayer(true); //give back feedback that the last block mined was mined by a player so we can decide if the block should drop items later
        //now prevent the Block from dropping
    }
}
