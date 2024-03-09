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
    String type;
    String DiceType;
    ModifyingTerrain mt;


    public void DecideEvent(int pDiceNum, String pDiceType, ServerWorld serverWorld, PlayerEntity player, String pType) {
        this.DiceNum = pDiceNum;
        RandomNumber = GenerateRandNum();
        this.type = pType;
        //will be used for chosing the event for events c:
        // Now pass serverWorld and player to the event methods
        if (pDiceType == "dice_neutral"){
            NeutralDiceEvent(serverWorld, player);
        }
        else if (pDiceType == "dice_positive") {
            PositiveDiceEvent(serverWorld, player);
        }
        else if (pDiceType == "dice_negative") {
            NegativeDiceEvent(serverWorld, player, RandomNumber, DiceNum);
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

    public void NegativeDiceEvent(ServerWorld serverWorld, PlayerEntity player, int pRandomNum, int pDiceNum) {
        NegativeEffects nE = new NegativeEffects(pDiceNum);
        ModifyingTerrain mt = new ModifyingTerrain();

        if(type != "normal_block"){
            switch(pRandomNum){
                case 0: nE.SpawnEntities(serverWorld, player); break;
                case 1: mt.GatherPlayerPositionData(serverWorld, player); break;
                case 2: mt.GatherPlayerPositionData(serverWorld, player); break;
                case 3: mt.GatherPlayerPositionData(serverWorld, player); break;
                case 4: mt.GatherPlayerPositionData(serverWorld, player); break;
                case 5: nE.SpawnEntities(serverWorld, player); break;
                case 6: nE.SpawnEntities(serverWorld, player); break;
                case 7: nE.SpawnEntities(serverWorld, player); break;
                case 8: nE.ApplyEffectToPlayer(serverWorld, player); break;
                case 9: nE.ApplyEffectToPlayer(serverWorld, player); break;
                case 10: nE.ApplyEffectToPlayer(serverWorld, player); break;
                case 11: nE.ApplyEffectToPlayer(serverWorld, player); break;
                case 12: nE.ApplyEffectToPlayer(serverWorld, player); break;
                case 13: nE.ApplyEffectToPlayer(serverWorld, player); break;
                case 14: nE.SpawnEntities(serverWorld, player); break;
                case 15: nE.SpawnEntities(serverWorld, player); break;
                case 16: nE.SpawnEntities(serverWorld, player); break;
                case 17: nE.SpawnEntities(serverWorld, player); break;
                case 18: nE.SpawnEntities(serverWorld, player); break;
                case 19: nE.SpawnEntities(serverWorld, player); break;
                case 20: nE.SpawnEntities(serverWorld, player); break;
            }
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
