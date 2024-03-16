package net.wjka.dnm;

import com.sun.jna.platform.win32.Winspool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.wjka.dnm.item.Dice.DiceRoll;

import static net.minecraft.block.Block.dropStack;
import static net.minecraft.registry.tag.BlockTags.*;


public class PlayerActions {
    //DESC
    //PlayerActions is a class which contains various actions that the player can perform and events related to those actions
    //PlayerACtions should be called ONLY from mixins!


    private ServerWorld world;
    private PlayerEntity player;
    private static float damageDealt;
    private static float damageBase;
    private static float damageMultiplier;
    //block attributes
    private Block block;
    private ItemStack stack;
    private boolean isMineable;
    private static DiceRoll dR;
    public static boolean timeChanged;
    public static boolean weatherChanged;

    public static boolean lastMinedMineable;

    public PlayerActions(PlayerEntity pPlayer, ServerWorld pWorld){
        this.player = pPlayer;
        this.world = pWorld;
        this.dR = new DiceRoll("e_hit", world, player);
        damageMultiplier = 0.65f;
    }

    public boolean getTimeChangedToggle(){
        timeChanged =! timeChanged;
        return timeChanged;
    }

    public boolean getWeatherChangedToggle(){
        weatherChanged =! weatherChanged;
        return weatherChanged;
    }


    public void CalcDamageCaused(ItemStack stack){
        if (!stack.isEmpty() && stack.getItem() instanceof ToolItem) { //checks if player has tool in hand :3
            stack.getAttributeModifiers(EquipmentSlot.MAINHAND).forEach((attribute, modifier) -> {
                if (attribute == EntityAttributes.GENERIC_ATTACK_DAMAGE) { //detects if damage is made with a tool
                    // Found the attack damage modifier, add its value to causedDamage
                    damageBase =+ ((float)modifier.getValue() + 1.0f); //get the val of modifier and the multiply it with the baseDam
                }
            });
            //roll dice from here
            dR.RollDice(); //roll dice with the param "e_hit"
            WriteModifier(); //get damage modifier
        }
        else{
            damageDealt = 0.2f; //if no tool in hand
        }
        DungeonsandMinecraft.LOGGER.info("damage dealt: " + damageDealt); //TEMP DEBUG PRINT -> prints out damage made
    }

    public float getDamageDealt(){ //float method to read the val from other classes that need it
        return damageDealt;
    }

    public void WriteModifier(){ //will be called (mainly) from the diceroll method to create the new modifier val
        int pDiceNum = dR.getDiceNum(); //get dicenum
        float modifier = (float)pDiceNum / 10 * damageMultiplier; //divs the (float)DiceNum by ten to get modifier values
        damageDealt = (damageBase * modifier); //modify damage
        DungeonsandMinecraft.LOGGER.info("diceNum from playerActions: " + modifier);
    }

    public void BlockMined(Block pBlock, ItemStack pStack){
        this.block = pBlock;
        this.stack = pStack;
        isMineable = CheckIfRIghtToolIsInHand();
        lastMinedMineable = isMineable;
        DiceRoll dR = new DiceRoll("block", world, player); //create obj
        dR.RollDice(); //rolls dice
        DungeonsandMinecraft.LOGGER.info("is Mineable? :" + isMineable); //DEBUG
    }

    private boolean CheckIfRIghtToolIsInHand(){
        if (stack.getItem() instanceof AxeItem && isTaggedWith(AXE_MINEABLE)) {
            return true;
        } else if (stack.getItem() instanceof PickaxeItem && isTaggedWith(PICKAXE_MINEABLE)) {
            return true;
        } else if (stack.getItem() instanceof ShovelItem && isTaggedWith(SHOVEL_MINEABLE)) {
            return true;
        } else if (stack.getItem() instanceof HoeItem && isTaggedWith(HOE_MINEABLE)) {
            return true;
        }
        else {
            return false; //if not then return false //not working as wanted!
        }
    }

    public boolean GetMineable(){
        return lastMinedMineable;
    }

    //method to with booelan return to check if block CAN be mined with tool (more efficiently)
    private boolean isTaggedWith(TagKey<Block> tag){
        return block.getRegistryEntry().isIn(tag);
        //-> getRegistryEntry is DEPRECATED, but still works /-> replace in future
    }

    public static int getSpawnMultiplier(){ //static because it needs to be run from a static class ONLY
        int diceNum = dR.getDiceNum();
        int multipler;
        switch(diceNum){
            case 0,1,2,3: multipler = 0; break;
            case 4,5,6,7,8,9,10: multipler = 1; break;
            case 11,12,13,14,15,16,17: multipler = 2;
            case 18,19,20: multipler = 3; break;
            default: multipler = 0; DungeonsandMinecraft.LOGGER.info("unkown dicenum"); break;
        }
        DungeonsandMinecraft.LOGGER.info("getspawnMult: " + diceNum);
        return multipler;
    }


}
