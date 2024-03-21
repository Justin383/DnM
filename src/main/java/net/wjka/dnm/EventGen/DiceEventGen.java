package net.wjka.dnm.EventGen;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.GameMode;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.Effects.NegativeEffects;
import net.wjka.dnm.EventGen.Effects.NeutralEffects;
import net.wjka.dnm.EventGen.Effects.PositiveEffects;
import net.wjka.dnm.PlayerActions;

import java.util.concurrent.ThreadLocalRandom;

public class DiceEventGen {

    int DiceNum;
    int RandomNumber;
    private ServerWorld world;
    private PlayerEntity player;
    private String type;

    public DiceEventGen(ServerWorld pWorld, PlayerEntity pPlayer, String pType, int pDiceNum){
        this.world = pWorld;
        this.player = pPlayer;
        this.type = pType;
        this.DiceNum = pDiceNum;
    }

    public void DecideEvent() {
        RandomNumber = GenerateRandNum();
//        DungeonsandMinecraft.LOGGER.info("Random Num: " + RandomNumber);
        switch(type){
            case "dice_neutral": NeutralDiceEvent(RandomNumber); break;
            case "dice_positive": PositiveDiceEvent(RandomNumber); break;
            case "dice_negative": NegativeDiceEvent(RandomNumber); break;
            case "block" :
                boolean hasTool = PlayerActions.hasRightTool;
                ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
                GameMode playerGameMode = serverPlayer.interactionManager.getGameMode();
                if(playerGameMode != GameMode.CREATIVE){
                    if(!hasTool){
                        NeutralDiceEvent(RandomNumber);
                    }
                }

                break;
            default: DungeonsandMinecraft.LOGGER.info("a error occured during the type..."); break;
        }
    }

    private int GenerateRandNum(){
         return (DiceNum + ThreadLocalRandom.current().nextInt( 0, 20 + 1)) / 2;
    }

    //all events that might happen will be listed as methods in here. methods will be implemented in many other classes

    public void NegativeDiceEvent(int pRandomNum) {
        NegativeEffects negative = new NegativeEffects(DiceNum, world, player);
        ModifyingTerrain mt = new ModifyingTerrain(world, player);
        SpawnCage sc = new SpawnCage(world, player);

        switch(pRandomNum){
            case 0: negative.SpawnEntities(); break;
            case 1: sc.GatherPlayerPositionData(); break;
            case 2: sc.GatherPlayerPositionData(); break;
            case 3: negative.ChangeTime(); break;
            case 4: mt.RemoveBlocks(); break;
            case 5: negative.SpawnEntities(); break;
            case 6: negative.SpawnEntities(); break;
            case 7: negative.SpawnEntities(); break;
            case 8: negative.ApplyEffectToPlayer(); break;
            case 9: negative.ApplyEffectToPlayer(); break;
            case 10: negative.ApplyEffectToPlayer(); break;
            case 11: negative.ApplyEffectToPlayer(); break;
            case 12: negative.ApplyEffectToPlayer(); break;
            case 13: negative.ApplyEffectToPlayer(); break;
            case 14: negative.SpawnEntities(); break;
            case 15: negative.SpawnEntities(); break;
            case 16: negative.SpawnEntities(); break;
            case 17: negative.SpawnEntities(); break;
            case 18: negative.SpawnEntities(); break;
            case 19: negative.SpawnEntities(); break;
            case 20: negative.SpawnEntities(); break;
        }
    }

    public void NeutralDiceEvent(int pRandomNum) {
        NeutralEffects neutral = new NeutralEffects(DiceNum, world, player);
        ModifyingTerrain mt = new ModifyingTerrain(world, player);
        SpawnCage sc = new SpawnCage(world, player);
        switch(pRandomNum){
            case 0: neutral.SpawnEntities(); break;
            case 1: neutral.ChangeWeather(); break;
            case 2: neutral.ChangeWeather(); break;
            case 3: neutral.ChangeTime(); break;
            case 4: neutral.ChangeTime(); break;
            case 5: neutral.SpawnEntities(); break;
            case 6: neutral.SpawnEntities(); break;
            case 7: neutral.SpawnEntities(); break;
            case 8: neutral.ApplyEffectToPlayer(); break;
            case 9: neutral.ApplyEffectToPlayer(); break;
            case 10: neutral.ApplyEffectToPlayer(); break;
            case 11: neutral.ApplyEffectToPlayer(); break;
            case 12: neutral.ApplyEffectToPlayer(); break;
            case 13: neutral.ApplyEffectToPlayer(); break;
            case 14: neutral.SpawnEntities(); break;
            case 15: neutral.SpawnEntities(); break;
            case 16: neutral.SpawnEntities(); break;
            case 17: neutral.SpawnEntities(); break;
            case 18: neutral.SpawnEntities(); break;
            case 19: neutral.SpawnEntities(); break;
            case 20: neutral.SpawnEntities(); break;
        }
    }

    public void PositiveDiceEvent(int pRandomNum) {
        PositiveEffects positive = new PositiveEffects(DiceNum, world, player);
        ModifyingTerrain mt = new ModifyingTerrain(world, player);
        switch(pRandomNum){
            case 0: positive.ApplyEffectToPlayer(); break;
            case 1: positive.ChangeWeather(); break;
            case 2: positive.SpawnItems(); break;
            case 3: positive.SpawnItems(); break;
            case 4: positive.ChangeTime(); break;
            case 5: positive.ApplyEffectToPlayer(); break;
            case 6: positive.SpawnItems(); break;
            case 7: positive.SpawnEntities(); break;
            case 8: positive.ApplyEffectToPlayer(); break;
            case 9: positive.ApplyEffectToPlayer(); break;
            case 10: positive.ApplyEffectToPlayer(); break;
            case 11: positive.ApplyEffectToPlayer(); break;
            case 12: positive.SpawnItems(); break;
            case 13: positive.ApplyEffectToPlayer(); break;
            case 14: positive.SpawnEntities(); break;
            case 15: positive.SpawnItems(); break;
            case 16: positive.SpawnEntities(); break;
            case 17: positive.SpawnItems(); break;
            case 18: positive.ApplyEffectToPlayer(); break;
            case 19: positive.SpawnItems(); break;
            case 20: positive.ApplyEffectToPlayer(); break;
            }
    }

}
