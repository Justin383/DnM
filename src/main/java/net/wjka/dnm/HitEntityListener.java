package net.wjka.dnm;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class HitEntityListener {

    float damage;


    //fabric doesnt have a built in function for listening to hits :ccc
    public static void register(){ //registers the hit listener
        AttackEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
            if(!world.isClient() && player instanceof PlayerEntity){ //check if player exists
                DungeonsandMinecraft.LOGGER.info("Entity " + entity + " has been hit");
                ItemStack stack = player.getStackInHand(hand); // Get the item in the players hand
                float damageBase = 1f; // standart damage without weapons

                if (!stack.isEmpty() && stack.getItem() instanceof ToolItem) {
                    ToolItem toolItem = (ToolItem) stack.getItem();
                    damageBase = toolItem.getMaterial().getAttackDamage();
                    DungeonsandMinecraft.LOGGER.info("damage: " + damageBase);
                }
            }
            return ActionResult.PASS; //if successfull return PASS
        }));
    }




    private float getDamage(PlayerEntity player, Hand hand){
        ItemStack stack = player.getStackInHand(hand); //get stack from item in players hand
        ToolItem toolItem = (ToolItem)stack.getItem(); //get toolitem from stack
        float causedDamage = 1f; //base val
        causedDamage += toolItem.getMaterial().getAttackDamage(); //read attaccDamage from tool and multiply it with base so we calc the dealt damage
        return causedDamage;
    }
}
