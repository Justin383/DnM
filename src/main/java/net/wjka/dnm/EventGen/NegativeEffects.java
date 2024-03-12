package net.wjka.dnm.EventGen;

import com.mojang.datafixers.types.templates.Check;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.level.ServerWorldProperties;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.Effects.StatusEffectsList;

import java.util.concurrent.ThreadLocalRandom;

public class NegativeEffects {
    private int DiceNum;
    private int seconds;
    private ServerWorld world;
    private PlayerEntity player;
    ServerWorldProperties properties;
    boolean isClear;
    boolean isDay;

    public NegativeEffects(int pDiceNum, ServerWorld pWorld, PlayerEntity pPlayer){
        this.DiceNum = pDiceNum;
        this.world = pWorld;
        this.player = pPlayer;

    }

    public void ApplyEffectToPlayer(){
        seconds = ThreadLocalRandom.current().nextInt(5, 30 + 1) * DiceNum;
        int hungerR = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        StatusEffectsList sL = new StatusEffectsList(seconds);
        int eventNum = DiceNum / 2;
        if(DiceNum == 8){
            if(hungerR == 1){
                player.addStatusEffect(sL.getStatusEffect(8));
            }
            else {
                player.getHungerManager().setSaturationLevel(0);
            }
        }
        else if(DiceNum == 18){
            if(hungerR == 1){
                player.addStatusEffect(sL.getStatusEffect(8));
            }
            else {
                player.getHungerManager().setSaturationLevel(0);
            }
        } else {
            player.addStatusEffect(sL.getStatusEffect(eventNum));
        }
    }

    public void SpawnEntities(){
        SpawnEntities sE = new SpawnEntities(player, world);
        switch(DiceNum){
            case 0: sE.spawnZombie(); break;
            case 1: sE.spawnSkeleton(); break;
            case 2: sE.spawnSlime(); break;
            case 3: sE.spawnCreeper(); break;
            case 4: sE.spawnWitch(); break;
            case 5: sE.spawnMagmaCube(); break;
            case 6: sE.spawnWitherSkeleton(); break;
            case 7: sE.spawnWolf(); break;
            case 8: for (int i = 0; i < 3; i++){sE.spawnSilverfish();} break;
            case 9: sE.spawnGiantZombie(); break;
            case 10: sE.spawnEnderDragon(); break;
            case 11: for (int i = 0; i < 3; i++){sE.spawnBees();} break;
            case 12: sE.spawnBlaze(); break;
            case 13: sE.spawnEnderman(); break;
            case 14: sE.spawnWarden(); break;
            case 15: for (int i = 0; i < 2; i++){sE.spawnPhantom();} break;
            case 16: for (int i = 0; i < 4; i++){sE.spawnZombie();} break;
            case 17: for (int i = 0; i < 2; i++){sE.spawnCreeper();} break;
            case 18: for (int i = 0; i < 2; i++){sE.spawnSkeleton();} break;
            case 19: sE.spawnVindicator(); break;
            case 20: sE.spawnWither(); break;
        }
    }

    //not working. try to fix this later :c
//    public void ChangeWeather(){
//        isThunder = world.isThundering(); //checks if weather is storm
//        isDay = world.isDay();
//        DungeonsandMinecraft.LOGGER.info(Boolean.toString(isThunder));
//        if(isThunder && isDay){
//            ChangeTime();
//        }
//        if(!isThunder){
//            //world.setWeather(1, -1, false, true); //sets infinite thunder
//            world.setWeather(1, -1, false, true);
//        }
//    }

    public void ModifyDealtDamage(){

    }

    public void ChangeTime(){
        isDay = world.isDay(); //mc func. if its day then it returns true
        if(isDay){
            world.setTimeOfDay(14000); //14000 ticks = beginning of night //mc considers 12542 as the first night tick -> for preferences its not dark enough
        }
    }
}
