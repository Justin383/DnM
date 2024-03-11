package net.wjka.dnm.EventGen;

import com.mojang.datafixers.types.templates.Check;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.registry.tag.TagKey;
import net.wjka.dnm.DungeonsandMinecraft;

import static net.minecraft.registry.tag.BlockTags.*;

public class VerifyMineable {

    private Block block;
    private ItemStack handItem;
    boolean mineable;
    private Block minedBlock;

    public VerifyMineable(Block pMinedBlock, ItemStack pHandItem){
        this.minedBlock = pMinedBlock;
        this.handItem = pHandItem;
    }

    //create a boolean to check if block is in the Mineable with tool category
    public boolean isTaggedWith(TagKey<Block> tag) {
        return block.getRegistryEntry().isIn(tag);
    }

    public boolean CheckIfRightToolIsInHand() {
        this.handItem = handItem;
        this.block = minedBlock;
        if (handItem.getItem() instanceof AxeItem && isTaggedWith(AXE_MINEABLE)) {
            return true;
        } else if (handItem.getItem() instanceof PickaxeItem && isTaggedWith(PICKAXE_MINEABLE)) {
            return true;
        } else if (handItem.getItem() instanceof ShovelItem && isTaggedWith(SHOVEL_MINEABLE)) {
            return true;
        } else if (handItem.getItem() instanceof HoeItem && isTaggedWith(HOE_MINEABLE)) {
            return true;
        }
        else {
            return false; //if not then return false
        }

    }

    public void CheckIfMineable(){
        mineable = CheckIfRightToolIsInHand();
        DungeonsandMinecraft.LOGGER.info(Boolean.toString(mineable));
    }

}
