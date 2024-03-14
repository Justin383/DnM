//MARKED FOR DELETE -> MADE REDUNDANT THROUGH MIXIN

//package net.wjka.dnm.Listener;
//
//import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
//import net.minecraft.entity.Entity;
//import net.minecraft.entity.EquipmentSlot;
//import net.minecraft.entity.attribute.EntityAttributes;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.item.ToolItem;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.Hand;
//import net.minecraft.world.World;
//import net.wjka.dnm.DungeonsandMinecraft;
//import net.wjka.dnm.item.Dice.DiceRoll;
//
//import java.util.concurrent.atomic.AtomicReference;
//
//public class HitEntityListener {
//
//
//    float damageDealt;
//    private AtomicReference<Float> modifiedDamage = new AtomicReference<>(1f);
//    AtomicReference<Float> damageBase = new AtomicReference<>(1f); // need atomicreference for method below
//
//
//    //fabric doesnt have a built in function for listening to hits :ccc
//    public static void register(){ //registers the hit listener
//        AttackEntityCallback.EVENT.register(((player, world, hand, entity, hitResult) -> {
//            if(!world.isClient() && player instanceof PlayerEntity){ //check if player exists
//                ItemStack stack = player.getStackInHand(hand); // Get the item in the players hand
//                HitEntityListener Hel = new HitEntityListener();
//                Hel.ModifyDamage(stack, world, player, entity);
//            }
//            return ActionResult.PASS; //if successfull return PASS
//        }));
//    }
//
//    private void ModifyDamage(ItemStack stack, World world, PlayerEntity player, Entity entity){
//        if (!stack.isEmpty() && stack.getItem() instanceof ToolItem) { //checks if player has tool in hand :3
//            stack.getAttributeModifiers(EquipmentSlot.MAINHAND).forEach((attribute, modifier) -> {
//                if (attribute == EntityAttributes.GENERIC_ATTACK_DAMAGE) {
//                    // Found the attack damage modifier, add its value to causedDamage
//                    damageBase.updateAndGet(v -> v + (float)modifier.getValue()); //grabs the damage val wowks :3
//                }
//            });
//            //roll dice from here
//            ServerWorld sWorld = (ServerWorld)world; //grab serverworld from current world
////            DiceRoll dR = new DiceRoll("e_hit", sWorld, player);
////            dR.RollDice(); //call diceroll
//            //then modify damage made :3
//            //here
//
//            // Apply modified damage to the target entity
//            //entity.damage(playerDamageSource, damageDealt); //doesnt wowk for now :c
//        }
//        DungeonsandMinecraft.LOGGER.info("damage: " + damageBase); //TEMP DEBUG PRINT -> prints out damage made
//    }
//
//
//
//    public void ModifyDealtDamage(float modifier){
//        float damage = damageBase.get(); //get float from atomicreference float
//        damageDealt = damage * modifier; //calc the damagemodifier
////        modifiedDamage.updateAndGet(v -> damageBase.get() * modifier);
//
//    }
//
//
//
//
//    private float getDamage(PlayerEntity player, Hand hand){
//        ItemStack stack = player.getStackInHand(hand); //get stack from item in players hand
//        ToolItem toolItem = (ToolItem)stack.getItem(); //get toolitem from stack
//        float causedDamage = 1f; //base val
//        causedDamage += toolItem.getMaterial().getAttackDamage(); //read attaccDamage from tool and multiply it with base so we calc the dealt damage
//        return causedDamage;
//    }
//}
