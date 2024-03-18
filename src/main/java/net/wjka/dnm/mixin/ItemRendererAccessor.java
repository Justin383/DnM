package net.wjka.dnm.mixin;

import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemRenderer.class) //annotation to ItemRenderer class
public interface ItemRendererAccessor {
    @Accessor("models") //has access to models
    ItemModels mccourse$getModels(); //is getter method. allows the items in the users hand to be rendered as the 3d model
}
