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

    @Unique //unique parameter is neccessary to every variable not directly involved in the Inject
    private static int multiplier;
    @Unique
    private static boolean isBrokenByPlayer; //var to check if current block is broken by player

    @Inject(method = "dropStack(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/item/ItemStack;)V", at = @At("HEAD"), cancellable = true)
    private static void onDropStack(World world, BlockPos pos, ItemStack stack, CallbackInfo ci) {
        PlayerActions playerActions = new PlayerActions();
        isBrokenByPlayer = playerActions.getLastBlockBrokeByPlayer();
        if(isBrokenByPlayer){ //used to check if block was broken by player to find out if block broke because of something else -> for example explosion
            boolean isMineable = playerActions.getIfRightTool();
//            DungeonsandMinecraft.LOGGER.info("block drop mineable: " + isMineable);
            if(isMineable){
                int multiplier = PlayerActions.getSpawnMultiplier();
//                DungeonsandMinecraft.LOGGER.info("how many will be dropped: " + multiplier);
                double d = (double)EntityType.ITEM.getHeight() / 2.0;
                double x = (double)pos.getX() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25);
                double y = (double)pos.getY() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25) - d;
                double z = (double)pos.getZ() + 0.5 + MathHelper.nextDouble(world.random, -0.25, 0.25);
                //the 4 above specify the slightly modified spawn coords for th item. this code is read from the mc src code which has the same for the item spawn coords
                // -> dropStack(World world, BlockPos pos, ItemStack stack) method in Block.class
                if(multiplier != 0){
                    for (int i = 0; i < multiplier - 1; i++){
                        ItemEntity itemEntity = new ItemEntity(world, x, y, z, stack);//create new item entity
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

    }
}