package net.wjka.dnm.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.wjka.dnm.Player.PlayerActions;
import net.wjka.dnm.Effects.DiceRoll;

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
            PlayerActions playerActions = new PlayerActions(user);
            playerActions.CallGUI(); //call GUI request in playerActions
        }
        if (!world.isClient && user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
            // store gamemode of player -> interactionManager is from mc
            GameMode gameMode = serverPlayer.interactionManager.getGameMode();
            // do if gamemode not creative
            if(gameMode != GameMode.CREATIVE){
                itemStack.decrement(1); // reduces the stacksize by one -> if 0 then item gets removed
            }
//            DungeonsandMinecraft.LOGGER.info("Player game mode: " + gameMode); //DEBUG LOG
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack); //required return by the use method from minecraft
    }
}
