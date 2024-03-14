package net.wjka.dnm.mixin;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.GameMode;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.EventGen.SpawnCage;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin {

    @Unique
    public String mIdentifier = "[MIXINS_DnM]";
    public DamageSource src;

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void onPlayerDeath(DamageSource damageSource, CallbackInfo ci) {
        this.src = damageSource;

        //Change Gamemode
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this; // cursed, but get the serverplayerentity from the object which was called within this mixin [in this case serverplayerentity]
        ServerWorld world = player.getServerWorld(); //get serverworld
        PlayerEntity pEntity = (PlayerEntity)player; //get playerentity from serverplayerentity
        DungeonsandMinecraft.LOGGER.info("A player " + player.getName() + " presented their lack of skill"); //outputs debug to cons EVEN with genderneutral terms :3
        //much conversion :o
        SpawnCage sc = new SpawnCage(world, pEntity); //create obj
        sc.ChangeToSurvival(player); //cast it with serverplayerentity
    }





}


