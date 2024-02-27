package net.wjka.dnm;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback; //fabric api import, which gives us the ability to listen for specific minecraft events...
//better than creating a linetracebychannel func. >:c
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.wjka.dnm.EventGen.VerifyMineable;

public class MiningListener {


    public static void register() {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (!world.isClient) {
                ItemStack heldItem = player.getMainHandStack(); //detect the item player is holding
                Block blockBeingMined = world.getBlockState(pos).getBlock(); //detect the block being mined
                // to handle which block is being mined
                //pos outputs the position of the block being mined
                DungeonsandMinecraft.LOGGER.info("Block mined: " + blockBeingMined + "at: " + pos + "using: " + heldItem);





                //call method to check if block is being mineable with hand or current tool
                VerifyMineable vm = new VerifyMineable();
                vm.CheckIfRightToolIsInHand(blockBeingMined, heldItem);

            }
            return ActionResult.PASS; // Return PASS to allow other events to process
        });
    }
}
