package net.wjka.dnm.Listener;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback; //fabric api import, which gives us the ability to listen for specific minecraft events...
//better than creating a linetracebychannel func. >:c
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.client.realms.dto.PlayerActivities;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.VerifyMineable;

public class MiningListener {


    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, entity) -> {
            if (!world.isClient) {
                ItemStack heldItem = player.getMainHandStack(); //detect the item player is holding
                Block blockBeingMined = world.getBlockState(pos).getBlock(); //detect the block being mined
                //pos outputs the position of the block being mined
                DungeonsandMinecraft.LOGGER.info("Block mined: " + blockBeingMined + "at: " + pos + "using: " + heldItem);
                //call method to check if block is being mineable with hand or current tool
                VerifyMineable vm = new VerifyMineable(blockBeingMined, heldItem); //pass values for checking...
                vm.CheckIfMineable();

            }
            // Return PASS to allow other events to process
        });
    }
}