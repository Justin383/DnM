package net.wjka.dnm.item.Dice;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class NeutralDice extends Item {


    DiceRoll dR = new DiceRoll();

    public NeutralDice(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (world.isClient) { //need to check that this is only called serverside to ensure that it wont be called twice in SP worlds
            //grab serverworld from here
            dR.RollDice("neutral"); //calls the function to roll a dice
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
