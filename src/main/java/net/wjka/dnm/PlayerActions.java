package net.wjka.dnm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.wjka.dnm.item.Dice.DiceRoll;

public class PlayerActions {

    private ServerWorld world;
    private PlayerEntity player;
    public static float damageDealt;
    private static float damageBase;

    public PlayerActions(PlayerEntity pPlayer, ServerWorld pWorld){
        this.player = pPlayer;
        this.world = pWorld;

    }


    public void CalcDamageCaused(ItemStack stack, World pWorld, PlayerEntity player, Entity entity){
        if (!stack.isEmpty() && stack.getItem() instanceof ToolItem) { //checks if player has tool in hand :3
            stack.getAttributeModifiers(EquipmentSlot.MAINHAND).forEach((attribute, modifier) -> {
                if (attribute == EntityAttributes.GENERIC_ATTACK_DAMAGE) {
                    // Found the attack damage modifier, add its value to causedDamage
                    damageBase =+ (float)modifier.getValue(); //get the val of modifier and the multiply it with the baseDam
                }
            });
            DungeonsandMinecraft.LOGGER.info("hit entity :cCCCCC");
            //roll dice from here
            DiceRoll dR = new DiceRoll("e_hit", world, player);
            dR.RollDice(); //roll dice with the param "e_hit"
            //then modify damage made :3
            //here

            // Apply modified damage to the target entity
            //entity.damage(playerDamageSource, damageDealt); //doesnt wowk for now :c
        }
        DungeonsandMinecraft.LOGGER.info("base: " + damageBase);
        DungeonsandMinecraft.LOGGER.info("damage: " + damageDealt); //TEMP DEBUG PRINT -> prints out damage made
    }

    public void ModifyDamageCaused(ItemStack stack, Entity entity){
        //apply damage to entity
    }

    public float getDamageDealt(){
        return damageDealt;
    }

    public void WriteModifier(float DiceNum){
        float modifier = DiceNum / 10; //divs the (float)DiceNum by ten to get modifier values
        DungeonsandMinecraft.LOGGER.info("base at writeMod: " + damageBase);
        damageDealt = (damageBase * modifier) + 1;
        DungeonsandMinecraft.LOGGER.info("damageD at mod: " + damageDealt);
    }

    public void GetBlockMined(){

    }

    public void CheckIfMineable(){

    }
}
