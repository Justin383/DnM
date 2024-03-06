package net.wjka.dnm.EventGen;

import net.minecraft.entity.passive.PandaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.wjka.dnm.DungeonsandMinecraft;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class DiceEventGen {

    int DiceNum;
    int RandomNumber;
    String DiceType;
    ModifyingTerrain mt;


    public void DecideEvent(int pDiceNum, String pDiceType, ServerWorld serverWorld, PlayerEntity player) {
        this.DiceNum = pDiceNum;
        RandomNumber = GenerateRandNum(); //will be used for chosing the event for events c:
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
            DungeonsandMinecraft.LOGGER.info("ERROR, WRONG TYPE OF DICE!");
        }
    }


    private int GenerateRandNum(){
         int temprandnum = ThreadLocalRandom.current().nextInt( 0, 20 + 1);
         int calcrandnum = (DiceNum + temprandnum) / 2;
         return calcrandnum;

    }



    //all events that might happen will be listed as methods in here. methods will be implemented in many other classes

    private void NegativeDiceEvent(ServerWorld serverWorld, PlayerEntity player) {
        NegativeEffects nE = new NegativeEffects();
        ModifyingTerrain mt = new ModifyingTerrain();

        switch(RandomNumber){
            case 0: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 1: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 2: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 3: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 4: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 5: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 6: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 7: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 8: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 9: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 10: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 11: mt.GatherPlayerPositionData(serverWorld, player); break;
            case 12: mt.GatherPlayerPositionData(serverWorld, player); break;
            /*case 13: code break;
            case 14: code break;
            case 15: code break;
            case 16: code break;
            case 17: code break;
            case 18: code break;
            case 19: code break;
            case 20: code break;*/


        }


//        ModifyingTerrain mt = new ModifyingTerrain();
//        mt.GatherPlayerPositionData(serverWorld, player);
//        SpawnCage sc = new SpawnCage();
//        sc.GatherPlayerPositionData(serverWorld, player);

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
