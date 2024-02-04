package net.wjka.dnm.item.Dice;
import java.util.concurrent.ThreadLocalRandom;

import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.DiceEventGen;

public class DiceRoll {

    public int diceNum;
    String type;

    DiceEventGen deg = new DiceEventGen();

    public void RollDice(String type){
        this.type = type;
        switch(type){
            case "negative":
                diceNum = ThreadLocalRandom.current().nextInt( -5, 15 + 1);
                if(diceNum < 0){
                    diceNum = 0;
                }
                break;
            case "neutral":
                diceNum = ThreadLocalRandom.current().nextInt( 0, 20 + 1);
                break;
            case "positive":
                diceNum = ThreadLocalRandom.current().nextInt( 5, 25 + 1);
                if(diceNum > 20){
                    diceNum = 20;
                }
                break;
        }

        deg.DecideEvent(diceNum, type); //gives the DiceEventGen the data in order for it to decide what should apply

        //add popup code here pls!

        DungeonsandMinecraft.LOGGER.info(type + ": " + String.valueOf(diceNum)); //Logging for the console...
    }
}
