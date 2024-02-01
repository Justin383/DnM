package net.wjka.dnm.item.Dice;
import java.util.concurrent.ThreadLocalRandom;

import net.wjka.dnm.DungeonsandMinecraft;

public class DiceRoll {

    public int diceNum;

    public void RollDice(String type){
        switch(type){
            case "negative":
                //code
                diceNum = ThreadLocalRandom.current().nextInt( -5, 15 + 1);
                if(diceNum < 0){
                    diceNum = 0;
                }
                break;
            case "neutral":
                //code
                diceNum = ThreadLocalRandom.current().nextInt( 0, 20 + 1);
                break;
            case "positive":
                //code
                diceNum = ThreadLocalRandom.current().nextInt( 5, 25 + 1);
                if(diceNum > 20){
                    diceNum = 20;
                }
                break;
        }

        DungeonsandMinecraft.LOGGER.info(type + ": " + String.valueOf(diceNum)); //Logging for the console...
    }
}
