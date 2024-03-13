package net.wjka.dnm.item.Dice;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.DiceEventGen;

public class DiceRoll {

    public int diceNum;
    String type;
    boolean hasRightTool;
    PlayerEntity player;
    ServerWorld world;

    public DiceRoll(String pType, ServerWorld pWorld, PlayerEntity pPlayer){
        this.world = pWorld;
        this.type = pType;
        this.player = pPlayer;
    }

    public int getDiceNum(){
        int gDiceNum = diceNum;
        return gDiceNum;
    }


    public void RollDice(){
        this.type = type;
        switch(type){
            case "dice_negative": diceNum = ThreadLocalRandom.current().nextInt( -5, 20 + 1); break; //call DisruptingDiceRoll
            case "dice_neutral": diceNum = ThreadLocalRandom.current().nextInt( 0, 20 + 1); break; //call DisruptingDiceRoll
            case "dice_positive": diceNum = ThreadLocalRandom.current().nextInt( 0, 25 + 1); break; //call DisruptingDiceRoll
            case "normal_block", "m_block": if (hasRightTool){ diceNum = ThreadLocalRandom.current().nextInt(0, 20 + 1); } break; //call SilentDiceRoll
            case "e_hit": diceNum = ThreadLocalRandom.current().nextInt(0, 20 + 1); break; //call SilentDiceRoll
            default: DungeonsandMinecraft.LOGGER.info("ERROR: UNKNOWN DICE PARAMETER"); break;
        }

        //reset diceNum if too high or too low
        if(diceNum > 20){
            diceNum = 20;
        } else if (diceNum < 0) {
            diceNum = 0;
        }
        DiceEventGen deg = new DiceEventGen(world, player, type, diceNum);
        deg.DecideEvent(); //gives the DiceEventGen the data in order for it to decide what should apply!

        DungeonsandMinecraft.LOGGER.info(type + ": " + String.valueOf(diceNum)); //Logging for the console... //remove before FINAL release
    }
}
