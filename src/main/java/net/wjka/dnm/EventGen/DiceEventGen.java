package net.wjka.dnm.EventGen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.concurrent.ThreadLocalRandom;

public class DiceEventGen {

    int DiceNum;
    String DiceType;


    public void DecideEvent(int pDiceNum, String pDiceType, ServerWorld serverWorld, PlayerEntity player) {
        this.DiceNum = pDiceNum;
        //GenerateRandomNumberAlgorythm(1);
        // Now pass serverWorld and player to the event methods
        if (pDiceType == "dice_neutral"){
            NeutralDiceEvent(serverWorld, player);
        }
        else if (pDiceType == "dice_positive") {
            PositiveDiceEvent(serverWorld, player);
        }
        else if (pDiceType == "dice_negative") {
            NegativeDiceEvent(serverWorld, player);
        }
        else{
            //do nothing c:
        }
    }


    private void GenerateRandomNumberAlgorythm(int pDiceNum){
        //int diceNum exists too, which is being calculated somewhere else
         int randomNumber = ThreadLocalRandom.current().nextInt( 0, 20 + 1);



         //int lastStoredNumber = randomOutNumber;
    }



    //all events that might happen will be listed as methods in here. methods will be implemented in many other classes

    private void NegativeDiceEvent(ServerWorld serverWorld, PlayerEntity player) {
//        ModifyingTerrain mt = new ModifyingTerrain();
//        mt.GatherPlayerPositionData(serverWorld, player);
//        SpawnCage sc = new SpawnCage();
//        sc.GatherPlayerPositionData(serverWorld, player);
        PositiveEffects pE = new PositiveEffects();
        pE.ApplyEffectToPlayer(serverWorld, player, DiceNum);

    }

    private void NeutralDiceEvent(ServerWorld serverWorld, PlayerEntity player) {

//        ModifyingTerrain mt = new ModifyingTerrain();
//        mt.GatherPlayerPositionData(serverWorld, player);
        SpawnCage sc = new SpawnCage();
        sc.GatherPlayerPositionData(serverWorld, player);
//        PositiveEffects pE = new PositiveEffects();
//        pE.ApplyEffectToPlayer(serverWorld, player, DiceNum);
    }

    private void PositiveDiceEvent(ServerWorld serverWorld, PlayerEntity player) {
        ModifyingTerrain mt = new ModifyingTerrain();
        mt.GatherPlayerPositionData(serverWorld, player);
//        PositiveEffects pE = new PositiveEffects();
//        pE.ApplyEffectToPlayer(serverWorld, player, DiceNum);
    }

}
