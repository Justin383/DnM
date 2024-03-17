package net.wjka.dnm.EventGen.Effects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.command.WeatherCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.wjka.dnm.PlayerActions;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class PositiveEffects {
    private int DiceNum;
    private int seconds;
    private ServerWorld world;
    private PlayerEntity player;
    public PlayerActions playerActions;

    public PositiveEffects(int pDiceNum, ServerWorld pWorld, PlayerEntity pPlayer){
        this.DiceNum = pDiceNum;
        this.world = pWorld;
        this.player = pPlayer;
        this.playerActions = new PlayerActions(player, world);
    }

    public void ApplyEffectToPlayer(){
        seconds = ThreadLocalRandom.current().nextInt(5, 30 + 1) * DiceNum;
        int hungerR = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        StatusEffectsList sL = new StatusEffectsList(seconds);
        int eventNum = (DiceNum / 2) + 13;
        if(DiceNum == 8){
            if(hungerR == 1){
                player.addStatusEffect(sL.getStatusEffect(8));
            }
            else {
                player.getHungerManager().setSaturationLevel(20);
            }
        }
        else if(DiceNum == 18){
            if(hungerR == 1){
                player.addStatusEffect(sL.getStatusEffect(8));
            }
            else {
                player.getHungerManager().setSaturationLevel(20);
            }
        } else {
            player.addStatusEffect(sL.getStatusEffect(eventNum));
        }
    }

    public void SpawnEntities(){
        SpawnEntities sE = new SpawnEntities(player, world);
        switch(DiceNum){
            case 0: sE.spawnVillager(); break;
            case 1: sE.spawnAxolotl(); break;
            case 2: sE.spawnGlowSquid(); break;
            case 3: sE.spawnFox(); break;
            case 4: sE.spawnRabbit(); break;
            case 5: sE.spawnCow(); break;
            case 6: sE.spawnPig(); break;
            case 7: sE.spawnFriendlyWolf(); break;
            case 8: for (int i = 0; i < 3; i++){sE.spawnChicken();} break;
            case 9: sE.spawnFriendlyBee(); break;
            case 10: sE.spawnPig(); break;
            case 11: for (int i = 0; i < 3; i++){sE.spawnFriendlyWolf();} break;
            case 12: sE.spawnFrog(); break;
            case 13: sE.spawnCat(); break;
            case 14: sE.spawnFriendlyWolf(); break;
            case 15: for (int i = 0; i < 2; i++){sE.spawnCow();} break;
            case 16: for (int i = 0; i < 4; i++){sE.spawnFox();} break;
            case 17: for (int i = 0; i < 2; i++){sE.spawnChicken();} break;
            case 18: for (int i = 0; i < 2; i++){sE.spawnVillager();} break;
            case 19: sE.spawnGlowSquid(); break;
            case 20: sE.spawnSquid(); break;
        }
    }

    public void SpawnItems(){
        BlockPos pos = player.getBlockPos();
        SpawnItems spawnItems = new SpawnItems(pos, world);
        spawnItems.GetInfo(DiceNum);
    }

    public void ChangeWeather() {
        boolean weatherChanged = playerActions.getWeatherChangedToggle();
        if(weatherChanged){
            world.setWeather(0, 0, false, false);
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
