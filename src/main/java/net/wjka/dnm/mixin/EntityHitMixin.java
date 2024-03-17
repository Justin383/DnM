package net.wjka.dnm.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.PlayerActions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class EntityHitMixin {

    @Inject(at = @At("HEAD"), method = "attack", cancellable = true)
    public void OnExecute(Entity target, CallbackInfo ci){
        ServerPlayerEntity sPlayer = (ServerPlayerEntity) (Object) this; //grab serverplayerentity
        ServerWorld world = sPlayer.getServerWorld(); //grab serverworld
        ItemStack stack = sPlayer.getStackInHand(Hand.MAIN_HAND); //gets itemstack item from MAIN hand
        PlayerActions pA = new PlayerActions((PlayerEntity)sPlayer, world); //create obj with playerentity
        pA.CalcDamageCaused(stack); //create obj
        float modifiedDamage = pA.getDamageDealt(); //get damage
        DamageSource src = target.getDamageSources().playerAttack((PlayerEntity)sPlayer); //does the thing mentioned below \\ grabs playerentity from serverplayerentity in ()
        //-> grab the damagesrc. player is in this case the damage source.
        //--> we get entity as the target and we read the damagesources the entity can get and get the playerattack src
        //---> in this case we give the player over as the damage src, so custom damage STILL applies knockback and other minecraft related stuff from the player
        target.damage(src, modifiedDamage); //applies new damage
        ci.cancel(); //cancel vanilla damage
    }

}
