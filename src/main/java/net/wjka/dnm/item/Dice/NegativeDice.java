package net.wjka.dnm.item.Dice;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.GUI.DiceToast;
import net.wjka.dnm.GUI.PopUpScreen;
import net.wjka.dnm.PlayerActions;

public class NegativeDice extends Item {


    public NegativeDice(Settings settings) {
        super(settings);
    }


    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        ServerWorld serverWorld = (ServerWorld)world;
        if (!world.isClient && world instanceof ServerWorld) {
            // Pass serverWorld to the RollDice method
            DiceRoll dR = new DiceRoll("dice_negative",serverWorld, user);
            dR.RollDice();
        }
        if(world.isClient){
            PlayerActions playerActions = new PlayerActions(user, serverWorld);
            playerActions.CallGUI();
        }
        if (!world.isClient && user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayer = (ServerPlayerEntity) user;
            // store gamemode of player -> interactionManager is from mc
            GameMode gameMode = serverPlayer.interactionManager.getGameMode();
            // do if gamemode not creative
            if(gameMode != GameMode.CREATIVE){
                itemStack.decrement(1); // reduces the stacksize by one -> if 0 then item gets removed
            }
            DungeonsandMinecraft.LOGGER.info("Player game mode: " + gameMode); //DEBUG LOG
        }

        return new TypedActionResult<>(ActionResult.SUCCESS, itemStack);
    }
}
