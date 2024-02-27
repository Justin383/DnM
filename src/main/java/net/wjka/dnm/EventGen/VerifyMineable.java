package net.wjka.dnm.EventGen;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public class VerifyMineable {

    private Block block;
    private ItemStack handItem;

    public void CheckIfRightToolIsInHand(Block minedBlock, ItemStack handItem){
        this.handItem = handItem;
        this.block = minedBlock;

        if(minedBlock.getHardness() != 0.0f){

        }



    }

}
