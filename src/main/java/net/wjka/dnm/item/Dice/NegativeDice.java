package net.wjka.dnm.item.Dice;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.wjka.dnm.DungeonsandMinecraft;

public class NegativeDice extends Item {




    public NegativeDice(Settings settings) {
        super(settings);
    }



    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient && world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)world;
            // Pass serverWorld to the RollDice method
            DiceRoll dR = new DiceRoll("dice_negative",serverWorld, user);
            dR.RollDice();
        }
        if(world.isClient){
            DungeonsandMinecraft.LOGGER.info("hi mum");
            //execute gwui code hwere
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
