package net.wjka.dnm.EventGen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.WolfEntity;
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
    private ServerWorld world;
    private PlayerEntity player;



    public NegativeEffects(int pDiceNum, ServerWorld pWorld, PlayerEntity pPlayer){
        this.DiceNum = pDiceNum;
        this.world = pWorld;
        this.player = pPlayer;

    }

    public void ApplyEffectToPlayer(){
        seconds = ThreadLocalRandom.current().nextInt(5, 30 + 1) * DiceNum;
        World world = player.getWorld();
        //INIT EFFECTS
        //10 = DiceNum * ThreadLocalRandom.current().nextInt(20, 60 + 1);

        StatusEffectInstance poison = new StatusEffectInstance(StatusEffects.POISON, 20 * seconds, 0); // 10 10 of Speed 1
        StatusEffectInstance lev = new StatusEffectInstance(StatusEffects.LEVITATION, 20 * seconds, 3); // 10 10 of Jump Boost 4
        StatusEffectInstance blind = new StatusEffectInstance(StatusEffects.BLINDNESS, 20 * seconds); // 10 10 of Blindness
        StatusEffectInstance fatigue = new StatusEffectInstance(StatusEffects.MINING_FATIGUE, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance wither = new StatusEffectInstance(StatusEffects.WITHER, 20 * seconds / 2);
        StatusEffectInstance weak = new StatusEffectInstance(StatusEffects.WEAKNESS, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance nausea = new StatusEffectInstance(StatusEffects.NAUSEA, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance hungwy = new StatusEffectInstance(StatusEffects.HUNGER, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance bado = new StatusEffectInstance(StatusEffects.BAD_OMEN, 20 * seconds); // 10 10 of Haste 20
        StatusEffectInstance dark = new StatusEffectInstance(StatusEffects.DARKNESS, 20 * seconds, 19); // 10 10 of Haste 20
        StatusEffectInstance idam = new StatusEffectInstance(StatusEffects.INSTANT_DAMAGE); // 10 10 of Haste 20

        int hungerR = ThreadLocalRandom.current().nextInt(1, 2 + 1);
        switch(DiceNum){
            case 0: player.addStatusEffect(dark); break;
            case 1: player.addStatusEffect(blind); break;
            case 2: player.addStatusEffect(lev); break;
            case 3: player.addStatusEffect(poison); break;
            case 4: player.addStatusEffect(fatigue); break;
            case 5: player.addStatusEffect(wither); break;
            case 6: player.addStatusEffect(weak); break;
            case 7: player.addStatusEffect(nausea); break;
            case 8: if (hungerR == 1) {player.addStatusEffect(hungwy);} else {player.getHungerManager().setSaturationLevel(0);} break;
            case 9: player.addStatusEffect(bado); break;
            case 10: player.addStatusEffect(dark); break;
            case 11: player.addStatusEffect(blind); break;
            case 12: player.addStatusEffect(lev); break;
            case 13: player.addStatusEffect(poison); break;
            case 14: player.addStatusEffect(fatigue); break;
            case 15: player.addStatusEffect(wither); break;
            case 16: player.addStatusEffect(weak); break;
            case 17: player.addStatusEffect(nausea); break;
            case 18: if (hungerR == 1) {player.addStatusEffect(hungwy);} else {player.getHungerManager().setSaturationLevel(0);} break; //sets hunger level to 0 -> player takes damage if doesnt eat OR gives hunder effect
            case 19: player.addStatusEffect(bado); break;
            case 20: player.setHealth(0.0f); break;

        }
    }

    public void SpawnCage(ServerWorld world, PlayerEntity player){
        SpawnCage sc = new SpawnCage();
        sc.GatherPlayerPositionData(world, player);
    }
    public void SpawnEntities(ServerWorld world, PlayerEntity player){
        //get player coords
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        //create entity instances
        ZombieEntity zombie = EntityType.ZOMBIE.create(world);
        SkeletonEntity skeleton = EntityType.SKELETON.create(world);
        CreeperEntity creeper = EntityType.CREEPER.create(world);
        WitchEntity witch = EntityType.WITCH.create(world);
        WitherEntity wither = EntityType.WITHER.create(world);
        SlimeEntity slime = EntityType.SLIME.create(world);
        WitherSkeletonEntity wskeleton = EntityType.WITHER_SKELETON.create(world);
        MagmaCubeEntity magmaslime = EntityType.MAGMA_CUBE.create(world);
        WolfEntity wolf = EntityType.WOLF.create(world);
        SilverfishEntity sfish = EntityType.SILVERFISH.create(world);
        GiantEntity gz = EntityType.GIANT.create(world);
        WardenEntity warden = EntityType.WARDEN.create(world);
        EndermanEntity ender = EntityType.ENDERMAN.create(world);
        BlazeEntity blaze = EntityType.BLAZE.create(world);
        BeeEntity abees = EntityType.BEE.create(world);
        EnderDragonEntity dragon = EntityType.ENDER_DRAGON.create(world);
        PhantomEntity phantom = EntityType.PHANTOM.create(world);
        VindicatorEntity vindicator = EntityType.VINDICATOR.create(world);

        switch(DiceNum){
            //zombie
            case 0: zombie.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(zombie); break;
            //skeleton
            case 1: skeleton.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(skeleton); break;
            //slime
            case 2: slime.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(slime); break;
            //creeper
            case 3: creeper.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(creeper); break;
            //witch
            case 4: witch.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(witch); break;
            //magmaslime
            case 5: magmaslime.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(magmaslime); break;
            //wskeleton
            case 6: wskeleton.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(wskeleton); break;
            //angry wolf
            case 7: wolf.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(wolf); wolf.setTarget(player); break;
            //siverfish
            case 8: for (int i = 0; i < 3; i++){sfish.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(sfish);} break;
            //Giant Zombie
            case 9: gz.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(gz); break;
            //end dragon :o
            case 10: dragon.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(dragon); break;
            //angry bees
            case 11: for (int i = 0; i < 3; i++){abees.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(abees); abees.setTarget(player);} break;
            //blazes
            case 12: blaze.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(blaze); break;
            //enderman
            case 13: ender.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(ender); break;
            //warden
            case 14: warden.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(warden); break;
            //phantom horde
            case 15: for (int i = 0; i < 2; i++){phantom.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(phantom);} break;
            //zombie horde
            case 16: for (int i = 0; i < 4; i++){zombie.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(zombie);} break;
            //creeper horde
            case 17: for (int i = 0; i < 2; i++){creeper.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(creeper);} break;
            //skeleton horde
            case 18: for (int i = 0; i < 2; i++){skeleton.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(skeleton);} break;
            //vindikator
            case 19: vindicator.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(vindicator); break;
            //wither
            case 20: wither.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(wither); break;
        }
    }


}
