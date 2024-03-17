package net.wjka.dnm.EventGen.Effects;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;

public class SpawnEntities {


    private ServerWorld world;
    private PlayerEntity player;

    private double x, y, z;

    public SpawnEntities(PlayerEntity pPlayer, ServerWorld pWorld){
        this.player = pPlayer;
        this.world = pWorld;
        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();
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

    // Spawn a Sheep
    public void spawnSheep() {
        SheepEntity sheep = new SheepEntity(EntityType.SHEEP, world);
        sheep.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(sheep);
    }

    // Spawn a Pig
    public void spawnPig() {
        PigEntity pig = new PigEntity(EntityType.PIG, world);
        pig.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(pig);
    }

    // Spawn a Cow
    public void spawnCow() {
        CowEntity cow = new CowEntity(EntityType.COW, world);
        cow.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(cow);
    }

    // Spawn a Villager
    public void spawnVillager() {
        VillagerEntity villager = new VillagerEntity(EntityType.VILLAGER, world);
        villager.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(villager);
    }

    // Spawn an Axolotl
    public void spawnAxolotl() {
        AxolotlEntity axolotl = new AxolotlEntity(EntityType.AXOLOTL, world);
        axolotl.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(axolotl);
    }

    // Spawn a Squid
    public void spawnSquid() {
        SquidEntity squid = new SquidEntity(EntityType.SQUID, world);
        squid.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(squid);
    }

    // Spawn a Glow Squid
    public void spawnGlowSquid() {
        GlowSquidEntity glowSquid = new GlowSquidEntity(EntityType.GLOW_SQUID, world);
        glowSquid.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(glowSquid);
    }

    // Spawn a Frog
    public void spawnFrog() {
        FrogEntity frog = new FrogEntity(EntityType.FROG, world);
        frog.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(frog);
    }

    // Spawn a Bee
    public void spawnFriendlyBee() {
        BeeEntity bee = new BeeEntity(EntityType.BEE, world);
        bee.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(bee);
    }

    // Spawn a Wolf
    public void spawnFriendlyWolf() {
        WolfEntity wolf = new WolfEntity(EntityType.WOLF, world);
        wolf.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(wolf);
    }

    public void spawnChicken(){
        ChickenEntity chicken = new ChickenEntity(EntityType.CHICKEN, world);
        chicken.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(chicken);
    }

    public void spawnPrimedTNT(){
        int radius = 4;
        int pHeight = (int)player.getY() + 2;
        BlockPos playerPos = player.getBlockPos();
        for (int height = pHeight; height <= pHeight; height++) {
            for (int rX = -radius; rX <= radius; rX++) {
                for (int rY = height; rY <= height; rY++) {
                    for (int rZ = -radius; rZ <= radius; rZ++) {
                        BlockPos targetBlock = new BlockPos(playerPos.getX() + rX, rY, playerPos.getZ() + rZ);
                        TntEntity tnt = new TntEntity(EntityType.TNT, world);
                        tnt.refreshPositionAndAngles(targetBlock, 0.0F, 0.0F);
                        world.spawnEntity(tnt);
                    }
                }
            }
        }
    }

    public void spawnFox(){
        FoxEntity fox = new FoxEntity(EntityType.FOX, world);
        fox.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(fox);
    }

    public void spawnRabbit(){
        RabbitEntity rabbit = new RabbitEntity(EntityType.RABBIT, world);
        rabbit.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(rabbit);
    }

    public void spawnCat(){
        CatEntity cat = new CatEntity(EntityType.CAT, world);
        cat.refreshPositionAndAngles(x, y, z, 0.0F, 0.0F);
        world.spawnEntity(cat);
    }






}
