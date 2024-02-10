package net.wjka.dnm.item.Dice;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.wjka.dnm.GUI.Gui;
import net.wjka.dnm.GUI.Screen;


public class NegativeDice extends Item {


    DiceRoll dR = new DiceRoll();

    public NegativeDice(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (!world.isClient) { //need to check that this is only called serverside to ensure that it wont be called twice in SP worlds
            dR.RollDice("negative"); //calls the function to roll a dice
        }

        //opens GUI upon right-click:
        MinecraftClient.getInstance().setScreen(new Screen(new Gui()));

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
