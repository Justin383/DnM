package net.wjka.dnm.EventGen.Effects;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class StatusEffectsList {

    public int seconds;

    StatusEffectInstance[] instance = new StatusEffectInstance[24];

    public StatusEffectsList(int pSeconds){
        this.seconds = pSeconds;


        StatusEffectInstance poison = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.POISON, 20 * seconds, 0); // 10 10 of Speed 1
        StatusEffectInstance lev = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.LEVITATION, 20 * seconds, 3); // 10 10 of Jump Boost 4
        StatusEffectInstance blind = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.BLINDNESS, 20 * seconds); // 10 10 of Blindness
        StatusEffectInstance fatigue = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.MINING_FATIGUE, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance wither = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.WITHER, 20 * seconds / 2);
        StatusEffectInstance weak = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.WEAKNESS, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance nausea = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.NAUSEA, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance hungwy = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.HUNGER, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance bado = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.BAD_OMEN, 20 * seconds); // 10 10 of Haste 20
        StatusEffectInstance dark = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.DARKNESS, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance idam = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.INSTANT_DAMAGE); // 10 10 of Haste 20
        //more positive ones
        StatusEffectInstance lucc = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.LUCK);
        StatusEffectInstance heal = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.REGENERATION, 20 * seconds, 2);
        StatusEffectInstance fullregen = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.INSTANT_HEALTH, 20, 200);
        StatusEffectInstance speed1 = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.SPEED, 20 * seconds, 1);
        StatusEffectInstance speed2 = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.SPEED, 20 * seconds, 2 );
        StatusEffectInstance jump1 = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.JUMP_BOOST, 20 * seconds, 1);
        StatusEffectInstance jump2 = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.JUMP_BOOST, 20 * seconds, 2);
        StatusEffectInstance haste = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.HASTE, 20 * seconds, 1);
        StatusEffectInstance fresistance = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.FIRE_RESISTANCE, 20 * seconds, 1);
        StatusEffectInstance resistance = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.RESISTANCE, 20 * seconds, 1);
        StatusEffectInstance saturation = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.SATURATION, 20 * seconds, 1);
        StatusEffectInstance nvision = new StatusEffectInstance(net.minecraft.entity.effect.StatusEffects.NIGHT_VISION, 20 * seconds, 1);
        StatusEffectInstance bluck = new StatusEffectInstance(StatusEffects.UNLUCK, 20 * seconds, 1);

        //assigns effect the arrau
        instance[0] = poison;
        instance[1] = lev;
        instance[2] = blind;
        instance[3] = fatigue;
        instance[4] = wither;
        instance[5] = weak;
        instance[6] = nausea;
        instance[7] = bado;
        instance[8] = hungwy;
        instance[9] = dark;
        instance[10] = idam;
        instance[11] = bluck;
        instance[12] = heal;
        instance[13] = fullregen;
        instance[14] = speed1;
        instance[15] = speed2;
        instance[16] = jump1;
        instance[17] = jump2;
        instance[18] = haste;
        instance[19] = fresistance;
        instance[20] = resistance;
        instance[21] = saturation;
        instance[22] = nvision;
        instance[23] = lucc;
    }

    //0-11 negative
    //12-23 positive
    public StatusEffectInstance getStatusEffect(int num){
        return instance[num];
    }
}


