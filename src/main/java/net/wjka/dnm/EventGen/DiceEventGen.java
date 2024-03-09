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
    ServerWorld world;
    PlayerEntity player;
    String type;
    String DiceType;


    public DiceEventGen(ServerWorld pWorld, PlayerEntity pPlayer, String pType, int pDiceNum){
        this.world = pWorld;
        this.player = pPlayer;
        this.type = pType;
        this.DiceNum = pDiceNum;
    }


    public void DecideEvent() {
        RandomNumber = GenerateRandNum();
        if (type == "dice_neutral"){
            NeutralDiceEvent(RandomNumber);
        }
        else if (type == "dice_positive") {
            PositiveDiceEvent(RandomNumber);
        }
        else if (type == "dice_negative") {
            NegativeDiceEvent(RandomNumber);
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

    public void NegativeDiceEvent(int pRandomNum) {
        NegativeEffects nE = new NegativeEffects(DiceNum, world, player);
        ModifyingTerrain mt = new ModifyingTerrain();

        if(type != "normal_block"){
            switch(pRandomNum){
                case 0: nE.SpawnEntities(world, player); break;
                case 1: mt.GatherPlayerPositionData(world, player); break;
                case 2: mt.GatherPlayerPositionData(world, player); break;
                case 3: mt.GatherPlayerPositionData(world, player); break;
                case 4: mt.GatherPlayerPositionData(world, player); break;
                case 5: nE.SpawnEntities(world, player); break;
                case 6: nE.SpawnEntities(world, player); break;
                case 7: nE.SpawnEntities(world, player); break;
                case 8: nE.ApplyEffectToPlayer(); break;
                case 9: nE.ApplyEffectToPlayer(); break;
                case 10: nE.ApplyEffectToPlayer(); break;
                case 11: nE.ApplyEffectToPlayer(); break;
                case 12: nE.ApplyEffectToPlayer(); break;
                case 13: nE.ApplyEffectToPlayer(); break;
                case 14: nE.SpawnEntities(world, player); break;
                case 15: nE.SpawnEntities(world, player); break;
                case 16: nE.SpawnEntities(world, player); break;
                case 17: nE.SpawnEntities(world, player); break;
                case 18: nE.SpawnEntities(world, player); break;
                case 19: nE.SpawnEntities(world, player); break;
                case 20: nE.SpawnEntities(world, player); break;
            }
        }
    }

    public void NeutralDiceEvent(int pRandomNum) {
        NegativeEffects nE = new NegativeEffects(DiceNum, world, player);
        ModifyingTerrain mt = new ModifyingTerrain();
        if(type != "normal_block"){
            switch(pRandomNum){
                case 0: nE.SpawnEntities(world, player); break;
                case 1: mt.GatherPlayerPositionData(world, player); break;
                case 2: mt.GatherPlayerPositionData(world, player); break;
                case 3: mt.GatherPlayerPositionData(world, player); break;
                case 4: mt.GatherPlayerPositionData(world, player); break;
                case 5: nE.SpawnEntities(world, player); break;
                case 6: nE.SpawnEntities(world, player); break;
                case 7: nE.SpawnEntities(world, player); break;
                case 8: nE.ApplyEffectToPlayer(); break;
                case 9: nE.ApplyEffectToPlayer(); break;
                case 10: nE.ApplyEffectToPlayer(); break;
                case 11: nE.ApplyEffectToPlayer(); break;
                case 12: nE.ApplyEffectToPlayer(); break;
                case 13: nE.ApplyEffectToPlayer(); break;
                case 14: nE.SpawnEntities(world, player); break;
                case 15: nE.SpawnEntities(world, player); break;
                case 16: nE.SpawnEntities(world, player); break;
                case 17: nE.SpawnEntities(world, player); break;
                case 18: nE.SpawnEntities(world, player); break;
                case 19: nE.SpawnEntities(world, player); break;
                case 20: nE.SpawnEntities(world, player); break;
            }
        }
    }

    public void PositiveDiceEvent(int pRandomNum) {
        NegativeEffects nE = new NegativeEffects(DiceNum, world, player);
        ModifyingTerrain mt = new ModifyingTerrain();
        if(type != "normal_block"){
            switch(pRandomNum){
                case 0: nE.SpawnEntities(world, player); break;
                case 1: mt.GatherPlayerPositionData(world, player); break;
                case 2: mt.GatherPlayerPositionData(world, player); break;
                case 3: mt.GatherPlayerPositionData(world, player); break;
                case 4: mt.GatherPlayerPositionData(world, player); break;
                case 5: nE.SpawnEntities(world, player); break;
                case 6: nE.SpawnEntities(world, player); break;
                case 7: nE.SpawnEntities(world, player); break;
                case 8: nE.ApplyEffectToPlayer(); break;
                case 9: nE.ApplyEffectToPlayer(); break;
                case 10: nE.ApplyEffectToPlayer(); break;
                case 11: nE.ApplyEffectToPlayer(); break;
                case 12: nE.ApplyEffectToPlayer(); break;
                case 13: nE.ApplyEffectToPlayer(); break;
                case 14: nE.SpawnEntities(world, player); break;
                case 15: nE.SpawnEntities(world, player); break;
                case 16: nE.SpawnEntities(world, player); break;
                case 17: nE.SpawnEntities(world, player); break;
                case 18: nE.SpawnEntities(world, player); break;
                case 19: nE.SpawnEntities(world, player); break;
                case 20: nE.SpawnEntities(world, player); break;
            }
        }
    }

}
