package net.wjka.dnm.EventGen;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;

import static net.minecraft.registry.tag.BlockTags.*;

public class VerifyMineable {

    private Block block;
    private ItemStack handItem;

    public void CheckIfRightToolIsInHand(Block minedBlock, ItemStack handItem){
        this.handItem = handItem;
        this.block = minedBlock;

        //if (handItem.getItem() instanceof AxeItem && block()){

        //}



    }

}
