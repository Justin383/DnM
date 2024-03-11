package net.wjka.dnm.EventGen;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;

public class SpawnEntities {


    private ServerWorld world;
    private PlayerEntity player;

    private double x;
    private double z;
    private double y;
    public SpawnEntities(PlayerEntity pPlayer, ServerWorld pWorld){
        this.player = pPlayer;
        this.world = pWorld;
        x = player.getX();
        y = player.getY();
        z = player.getZ();
    }


    public void spawnZombie(){
        ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, world);
        zombie.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(zombie);
    }

    public void spawnSkeleton(){
        SkeletonEntity skeleton = new SkeletonEntity(EntityType.SKELETON, world);
        skeleton.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        ItemStack bow = new ItemStack(Items.BOW);
        skeleton.equipStack(EquipmentSlot.MAINHAND, bow);
        world.spawnEntity(skeleton); //spawn skeleton with bow in hand
    }

    public void spawnCreeper(){
        CreeperEntity creeper = new CreeperEntity(EntityType.CREEPER, world);
        creeper.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(creeper);
    }

    public void spawnWitch(){
        WitchEntity witch = new WitchEntity(EntityType.WITCH, world);
        witch.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(witch);
    }

    public void spawnWither(){
        WitherEntity wither = new WitherEntity(EntityType.WITHER, world);
        wither.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(wither);
    }

    public void spawnSlime(){
        SlimeEntity slime = new SlimeEntity(EntityType.SLIME, world);
        slime.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(slime);
    }

    public void spawnWitherSkeleton(){
        WitherSkeletonEntity wskeleton = new WitherSkeletonEntity(EntityType.WITHER_SKELETON, world);
            wskeleton.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(wskeleton);
    }

    public void spawnMagmaCube(){
        MagmaCubeEntity mcube = new MagmaCubeEntity(EntityType.MAGMA_CUBE, world);
        mcube.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(mcube);
    }

    public void spawnWolf(){
        WolfEntity wolf = new WolfEntity(EntityType.WOLF, world);
        wolf.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(wolf);
    }

    public void spawnSilverfish(){
        SilverfishEntity sfish = new SilverfishEntity(EntityType.SILVERFISH, world);
        sfish.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(sfish);
    }

    public void spawnGiantZombie(){
        GiantEntity giant = new GiantEntity(EntityType.GIANT, world);
        giant.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(giant);
    }

    public void spawnWarden(){
        WardenEntity warden = new WardenEntity(EntityType.WARDEN, world);
        warden.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(warden);
    }

    public void spawnEnderman(){
        EndermanEntity ender = new EndermanEntity(EntityType.ENDERMAN, world);
        ender.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(ender);
    }

    public void spawnBlaze(){
        BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, world);
        blaze.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(blaze);
    }

    public void spawnBees(){
        BeeEntity bee = new BeeEntity(EntityType.BEE, world);
        bee.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(bee);
    }

    public void spawnEnderDragon(){
        EnderDragonEntity dragon = new EnderDragonEntity(EntityType.ENDER_DRAGON, world);
        dragon.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(dragon);
    }

    public void spawnPhantom(){
        PhantomEntity phantom = new PhantomEntity(EntityType.PHANTOM, world);
        phantom.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(phantom);
    }

    public void spawnVindicator(){
        VindicatorEntity vindicator = new VindicatorEntity(EntityType.VINDICATOR, world);
        vindicator.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F); world.spawnEntity(vindicator);
    }




}
