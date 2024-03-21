package net.wjka.dnm.item.Items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.wjka.dnm.DungeonsandMinecraft;

public class Randomizer extends Item {
    public Randomizer(Settings settings) {
        super(settings);
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand){
        ItemStack itemStack = user.getStackInHand(hand);

        if(!world.isClient){
            //server code -> nothing
        }
        if(world.isClient){
            //client code -> nothing
            DungeonsandMinecraft.LOGGER.info("player right clicked this. what did he expect?");
        }

        return new TypedActionResult<>(ActionResult.PASS, itemStack);
    }
}
