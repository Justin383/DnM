package net.wjka.dnm.EventGen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class PositiveEffects {
    private int DiceNum;



    public PositiveEffects(){

    }

    public /*TEMPORARILY PUBLIC*/ void ApplyEffectToPlayer(ServerWorld serverWorld, PlayerEntity player, int pDiceNum){
        World world = player.getWorld();
        //INIT EFFECTS

        StatusEffectInstance speed = new StatusEffectInstance(StatusEffects.SPEED, 20 * 10, 0); // 10 seconds of Speed 1
        StatusEffectInstance jump = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 20 * 10, 3); // 10 seconds of Jump Boost 4
        StatusEffectInstance blind = new StatusEffectInstance(StatusEffects.LUCK, 20 * 10, 0); // 10 seconds of Blindness
        StatusEffectInstance haste = new StatusEffectInstance(StatusEffects.HASTE, 20 * 10, 19); // 10 seconds of Haste 20
        StatusEffectInstance slowness = new StatusEffectInstance(StatusEffects.REGENERATION, 20 * 999999999);


        this.DiceNum = pDiceNum;
        switch(DiceNum){
            case 1:
                player.addStatusEffect(jump);
                break;
            case 2:
                player.addStatusEffect(blind);
                break;
            case 3:
                player.addStatusEffect(haste);
                break;

        }
    }
}
