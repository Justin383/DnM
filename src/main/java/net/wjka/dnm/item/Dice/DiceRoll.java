package net.wjka.dnm.item.Dice;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.DiceEventGen;
import net.wjka.dnm.NetworkingManager;

public class DiceRoll {

    public static int diceNum;
    public String type;
    public PlayerEntity player;
    public ServerWorld world;

    public DiceRoll(String pType, ServerWorld pWorld, PlayerEntity pPlayer){
        this.world = pWorld;
        this.type = pType;
        this.player = pPlayer;
    }

    public int getDiceNum(){
        return diceNum;
    }


    public void RollDice(){
        this.type = type;
        switch(type){
            case "dice_negative": diceNum = ThreadLocalRandom.current().nextInt( -5, 20 + 1);NormaliceDiceNum(); sendPackets(); break; //call DisruptingDiceRoll
            case "dice_neutral": diceNum = ThreadLocalRandom.current().nextInt( 0, 20 + 1); NormaliceDiceNum(); sendPackets(); break; //call DisruptingDiceRoll
            case "dice_positive": diceNum = ThreadLocalRandom.current().nextInt( 0, 25 + 1); NormaliceDiceNum(); sendPackets(); break; //call DisruptingDiceRoll
            case "block": diceNum = ThreadLocalRandom.current().nextInt(0, 20 + 1); break; //call SilentDiceRoll
            case "e_hit": diceNum = ThreadLocalRandom.current().nextInt(0, 20 + 1); break; //call SilentDiceRoll
            case "num": break;
            default: DungeonsandMinecraft.LOGGER.info("ERROR: UNKNOWN DICE PARAMETER"); break;
        }

        if(type != "e_hit"){ //doesnt need it if "e_hit"
            DiceEventGen deg = new DiceEventGen(world, player, type, diceNum);
            deg.DecideEvent(); //gives the DiceEventGen the data in order for it to decide what should apply!
        }
        DungeonsandMinecraft.LOGGER.info(type + " in class DR: " + String.valueOf(diceNum)); //Logging for the console... //remove before FINAL release
    }

    private void NormaliceDiceNum(){
        if(diceNum > 20){
            diceNum = 20;
        } else if (diceNum < 0) {
            diceNum = 0;
        }
    }

    private void sendPackets(){
        ServerPlayerEntity sp = (ServerPlayerEntity)player; //get spe
        NetworkingManager.sendDiceNumPacket(sp, diceNum);
        DungeonsandMinecraft.LOGGER.info("Type in DR: " + type); //PASS
        NetworkingManager.sendDiceTypePacket(sp, type); //PASS
        DungeonsandMinecraft.LOGGER.info(type + "in NM: " + diceNum); //Logging for the console...
    }
}
