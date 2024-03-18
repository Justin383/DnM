package net.wjka.dnm;

import com.sun.jna.platform.win32.Winspool;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.ToastManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.wjka.dnm.GUI.DiceToast;
import net.wjka.dnm.GUI.PopUpScreen;
import net.wjka.dnm.item.Dice.DiceRoll;

import static net.minecraft.block.Block.dropStack;
import static net.minecraft.registry.tag.BlockTags.*;


public class PlayerActions {
    //DESC

    /*
    PlayerActions is the class which stores all neccessary information about the player and its actions.
     */
    private ServerWorld world;
    private PlayerEntity player;
    private static float damageDealt;
    private static float damageBase;
    private static float damageMultiplier;
    private Block block;
    private ItemStack stack;
    private static DiceRoll dR;
    public static boolean timeChanged;
    public static boolean weatherChanged;
    private static boolean isSilentGUI = true;
    private static boolean lastBlockIsBrokenByPlayer;
    public static boolean hasRightTool;
    public static boolean lastMinedMineable;

    public PlayerActions(PlayerEntity pPlayer, ServerWorld pWorld){ //constructor for accessibility through server-side code
        this.player = pPlayer;
        this.world = pWorld;
        this.dR = new DiceRoll("e_hit", world, player);
        damageMultiplier = 0.65f;
    }
    public PlayerActions(PlayerEntity pPlayer){
        this.player = pPlayer;
    } //constructor for accessibility through non server-side code

    public PlayerActions(){ } //constructor for cmd class

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
//        DungeonsandMinecraft.LOGGER.info("damage dealt: " + damageDealt); //TEMP DEBUG PRINT -> prints out damage made
    }

    public float getDamageDealt(){ //float method to read the val from other classes that need it
        return damageDealt;
    }

    public void WriteModifier(){ //will be called (mainly) from the diceroll method to create the new modifier val
        int pDiceNum = dR.getDiceNum(); //get dicenum
        float modifier = (float)pDiceNum / 10 * damageMultiplier; //divs the (float)DiceNum by ten to get modifier values
        damageDealt = (damageBase * modifier); //modify damage
//        DungeonsandMinecraft.LOGGER.info("diceNum from playerActions: " + modifier);
    }

    public void BlockMined(Block pBlock, ItemStack pStack){
        this.block = pBlock;
        this.stack = pStack;
        lastMinedMineable = CheckIfRIghtToolIsInHand();
        DiceRoll dR = new DiceRoll("block", world, player); //create obj
        dR.RollDice(); //rolls dice
//        DungeonsandMinecraft.LOGGER.info("is Mineable? :" + isMineable); //DEBUG
    }

    private boolean CheckIfRIghtToolIsInHand(){
        if (stack.getItem() instanceof AxeItem && isTaggedWith(AXE_MINEABLE)) {
            hasRightTool = true;
            return true;
        } else if (stack.getItem() instanceof PickaxeItem && isTaggedWith(PICKAXE_MINEABLE)) {
            hasRightTool = true;
            return true;
        } else if (stack.getItem() instanceof ShovelItem && isTaggedWith(SHOVEL_MINEABLE)) {
            hasRightTool = true;
            return true;
        } else if (stack.getItem() instanceof HoeItem && isTaggedWith(HOE_MINEABLE)) {
            hasRightTool = true;
            return true;
        }
        else {
            hasRightTool = false;
            return false; //if not then return false //not working as wanted!
        }
    }

    public boolean GetMineable(){
        return lastMinedMineable;
    }

    //method to with booelan return to check if block CAN be mined with tool (more efficiently)
    private boolean isTaggedWith(TagKey<Block> tag){
        return block.getRegistryEntry().isIn(tag); //DEPRECATED
        //-> getRegistryEntry is DEPRECATED, but still works /-> replace in future
    }

    public static int getSpawnMultiplier(){ //static because it needs to be run from a static class ONLY
        int diceNum = dR.getDiceNum();
        int multipler;
        switch(diceNum){
            case 0,1,2: multipler = 0; break;
            case 3,4,5,6,7: multipler = 1; break;
            case 8,9,10,11,12,13,14: multipler = 2; break;
            case 15,16,17,18: multipler = 3; break;
            case 19, 20: multipler = 4; break;
            default: multipler = 0; DungeonsandMinecraft.LOGGER.info("unkown dicenum"); break;
        }
//        DungeonsandMinecraft.LOGGER.info("getspawnMult: " + diceNum);
        return multipler;
    }

    public void changeGuiType(boolean mod){
        isSilentGUI = mod;
    }
    public void CallGUI(){
//        isSilentGUI = true; //temp
        if(isSilentGUI){
            boolean isExistent = DiceToast.isExistent;
            if(!isExistent){
                MinecraftClient client = MinecraftClient.getInstance();
                ToastManager toastManager = client.getToastManager();
                DiceToast toast = new DiceToast();
                toastManager.add(toast); //call toast
            }
        }
        if(!isSilentGUI){
            MinecraftClient.getInstance().setScreen(new PopUpScreen());
        }
    }

    public void setLastBlockBrokeByPlayer(boolean pLastBlockIsBrokenByPlayer){
        lastBlockIsBrokenByPlayer = pLastBlockIsBrokenByPlayer;
//        DungeonsandMinecraft.LOGGER.info("broke by player IN SETTER: " + lastBlockIsBrokenByPlayer);
    }

    public boolean getLastBlockBrokeByPlayer(){
//        DungeonsandMinecraft.LOGGER.info("broke by player IN RETURN: " + lastBlockIsBrokenByPlayer);
        return lastBlockIsBrokenByPlayer;
    }

    public boolean getIfRightTool(){
        return hasRightTool;
    }

}


