package net.wjka.dnm.EventGen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceEventGen {

    int DiceNum;
    String DiceType;

    public void DecideEvent(int pDiceNum, String pDiceType, ServerWorld serverWorld, PlayerEntity player) {
        this.DiceNum = pDiceNum;
        // Now pass serverWorld and player to the event methods
        if (pDiceType == "neutral"){
            NeutralDiceEvent(serverWorld, player);
        }
        else if (pDiceType == "positive") {
            PositiveDiceEvent(serverWorld, player);
        }
        else if (pDiceType == "negative") {
            NegativeDiceEvent(serverWorld, player);
        }
        else{
            //do nothing
        }
    }


    private void GenerateRandomNumberAlgorythm(){
         int random = ThreadLocalRandom.current().nextInt( 0, 20 + 1);

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
