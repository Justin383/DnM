package net.wjka.dnm.item.Dice;

import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.DiceEventGen;

import java.util.concurrent.ThreadLocalRandom;

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

        deg.DecideEvent(diceNum, type); //gives the DiceEventGen the data in order for it to decide what should apply!

        //add popup code here pls!

        //opens GUI upon right-click:
        //MinecraftClient.getInstance().setScreen(new Screen(new Gui()));   ----> doesn't work rn 3: Caused by: java.lang.UnsupportedOperationException

        DungeonsandMinecraft.LOGGER.info(type + ": " + diceNum); //Logging for the console...
    }
}
