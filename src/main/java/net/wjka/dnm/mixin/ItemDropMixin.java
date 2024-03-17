package net.wjka.dnm.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.PlayerActions;
import net.wjka.dnm.item.Dice.DiceRoll;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class ItemDropMixin {

    @Unique
    private static int multiplier;
    private static boolean isBrokenByPlayer; //var to check if current block is broken by player

    @Inject(method = "dropStack(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private static void onDropStack(World world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        PlayerActions playerActions = new PlayerActions();
        isBrokenByPlayer = playerActions.getLastBlockBrokeByPlayer();
        if(isBrokenByPlayer){
            boolean isMineable = PlayerActions.lastMinedMineable;
//            DungeonsandMinecraft.LOGGER.info("block drop mineable: " + isMineable);
            if(isMineable){
                //implement this tmr
                int multiplier = PlayerActions.getSpawnMultiplier();
//                DungeonsandMinecraft.LOGGER.info("how many will be dropped: " + multiplier);
                double d = (double)EntityType.ITEM.getHeight() / 2.0;
                double e = (double)pos.getX() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25);
                double f = (double)pos.getY() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25) - d;
                double g = (double)pos.getZ() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25);
                if(multiplier != 0){
                    for (int i = 0; i < multiplier - 1; i++){
                        ItemEntity itemEntity = new ItemEntity(world, e, f, g, stack);//create new item entity
                        world.spawnEntity(itemEntity); //spawns it
                        ci.cancel(); //need to cancel or minecraft will drop a item by itself
                    }
                } else {
                    ci.cancel(); //if item amount 0 we NEED to cancel our event
                }

            } else {
                ci.cancel(); //cancel if no or wrong tool in hand
            }
        }
        playerActions.setLastBlockBrokeByPlayer(false); //sets the var in playerActions to false

        // Check if the block at the given position should not drop items


        // This could be based on the block type, metadata, or custom logic
    }
}