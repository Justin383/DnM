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

import java.util.concurrent.ThreadLocalRandom;

public class NegativeEffects {
    private int DiceNum;
    private int seconds;



    public NegativeEffects(){

    }

    public /*TEMPORARILY PUBLIC*/ void ApplyEffectToPlayer(ServerWorld serverWorld, PlayerEntity player, int pDiceNum){
        World world = player.getWorld();
        //INIT EFFECTS
        //10 = DiceNum * ThreadLocalRandom.current().nextInt(20, 60 + 1);

        StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 20 * 10, 0); // 10 10 of Speed 1
        StatusEffectInstance lev = new StatusEffectInstance(StatusEffects.LEVITATION, 20 * 10, 3); // 10 10 of Jump Boost 4
        StatusEffectInstance blind = new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * 10); // 10 10 of Blindness
        StatusEffectInstance fatigue = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20 * 10, 19); // 10 10 of Haste 20
        StatusEffectInstance wither = new StatusEffectInstance(StatusEffects.WITHER, 20 * 999999999);
        StatusEffectInstance weak = new StatusEffectInstance(StatusEffects.WEAKNESS, 20 * 10, 19); // 10 10 of Haste 20
        StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 20 * 10, 19); // 10 10 of Haste 20
        StatusEffectInstance hungwy = new StatusEffectInstance(StatusEffects.HUNGER, 20 * 10, 19); // 10 10 of Haste 20
        StatusEffectInstance bado = new StatusEffectInstance(StatusEffects.BAD_OMEN, 20 * 10); // 10 10 of Haste 20
        StatusEffectInstance dark = new StatusEffectInstance(StatusEffects.DARKNESS, 20 * 10, 19); // 10 10 of Haste 20
        StatusEffectInstance idam = new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE); // 10 10 of Haste 20

        this.DiceNum = pDiceNum;
        switch(DiceNum){
            case 0: player.setHealth(0.0f); break;
            case 1: player.addStatusEffect(blind); break;
            case 2: player.addStatusEffect(lev); break;
            case 3: player.addStatusEffect(poison); break;
            case 4: player.addStatusEffect(fatigue); break;
            case 5: player.addStatusEffect(wither); break;
            case 6: player.addStatusEffect(weak); break;
            case 7: player.addStatusEffect(nausea); break;
            case 8: player.addStatusEffect(hungwy); break;
            case 9 : player.addStatusEffect(bado); break;
            case 10: player.addStatusEffect(dark); break;
            case 11: player.addStatusEffect(blind); break;
            case 12: player.addStatusEffect(lev); break;
            case 13: player.addStatusEffect(poison); break;
            case 14: player.addStatusEffect(fatigue); break;
            case 15: player.addStatusEffect(wither); break;
            case 16: player.addStatusEffect(weak); break;
            case 17: player.addStatusEffect(nausea); break;
            case 18: player.addStatusEffect(hungwy); break;
            case 19 : player.addStatusEffect(bado); break;
            case 20: player.addStatusEffect(dark); break;

        }
    }
}
