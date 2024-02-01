package net.wjka.dnm;
import java.util.concurrent.*;


public class Dice {
    int diceNum;

    public Dice(){
        //dk.
    }

    public void rollDice(){
        diceNum = ThreadLocalRandom.current().nextInt(0, 21);
        DungeonsandMinecraft.LOGGER.info(String.valueOf(diceNum));

    }
}
