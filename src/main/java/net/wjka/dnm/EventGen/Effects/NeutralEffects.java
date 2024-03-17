package net.wjka.dnm.EventGen.Effects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.level.ServerWorldProperties;
import net.wjka.dnm.PlayerActions;

import java.util.concurrent.ThreadLocalRandom;

public class NeutralEffects {
    private int DiceNum;
    private int seconds;
    private ServerWorld world;
    private PlayerEntity player;
    PlayerActions playerActions;

    public NeutralEffects(int pDiceNum, ServerWorld pWorld, PlayerEntity pPlayer){
        this.DiceNum = pDiceNum;
        this.world = pWorld;
        this.player = pPlayer;
        this.playerActions = new PlayerActions(player, world);
    }

    public void ApplyEffectToPlayer(){
        seconds = ThreadLocalRandom.current().nextInt(5, 30 + 1) * DiceNum;
        int hungerR = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        StatusEffectsList sL = new StatusEffectsList(seconds);
        int eventNum = (DiceNum / 2) + 5;
        if(DiceNum == 8){
            if(hungerR == 1){
                player.addStatusEffect(sL.getStatusEffect(8));
            }
            else {
                player.getHungerManager().setSaturationLevel(10);
            }
        }
        else if(DiceNum == 18){
            if(hungerR == 1){
                player.addStatusEffect(sL.getStatusEffect(8));
            }
            else {
                player.getHungerManager().setSaturationLevel(10);
            }
        } else {
            player.addStatusEffect(sL.getStatusEffect(eventNum));
        }
    }

    public void SpawnEntities(){
        SpawnEntities sE = new SpawnEntities(player, world);
        switch(DiceNum){
            case 0: sE.spawnSkeleton(); break;
            case 1: sE.spawnCow(); break;
            case 2: sE.spawnSheep(); break;
            case 3: sE.spawnCreeper(); break;
            case 4: sE.spawnWitch(); break;
            case 5: sE.spawnAxolotl(); break;
            case 6: sE.spawnCreeper(); break;
            case 7: sE.spawnWolf(); break;
            case 8: for (int i = 0; i < 3; i++){sE.spawnSilverfish();} break;
            case 9: sE.spawnGlowSquid(); break;
            case 10: sE.spawnCreeper(); break;
            case 11: for (int i = 0; i < 3; i++){sE.spawnBees();} break;
            case 12: sE.spawnSquid(); break;
            case 13: sE.spawnEnderman(); break;
            case 14: sE.spawnFrog(); break;
            case 15: for (int i = 0; i < 2; i++){sE.spawnPhantom();} break;
            case 16: for (int i = 0; i < 4; i++){sE.spawnSkeleton();} break;
            case 17: for (int i = 0; i < 2; i++){sE.spawnFriendlyWolf();} break;
            case 18: for (int i = 0; i < 2; i++){sE.spawnChicken();} break;
            case 19: sE.spawnPig(); break;
            case 20: sE.spawnSkeleton(); break;
        }
    }

    public void ChangeWeather() {
        boolean weatherChanged = playerActions.getWeatherChangedToggle();
        if(weatherChanged){
            world.setWeather(10, 3200, false, false);
        }
        else {
            ChangeTime();
        }
    }

    public void ChangeTime(){
        boolean timeChanged = playerActions.getTimeChangedToggle();
        if(timeChanged){
            world.setTimeOfDay(0); //14000 ticks = beginning of night //mc considers 12542 as the first night tick -> for preferences its not dark enough

        }
        else {
            ChangeWeather();
        }
    }
}
