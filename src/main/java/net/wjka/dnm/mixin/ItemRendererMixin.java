package net.wjka.dnm.mixin;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.wjka.dnm.DungeonsandMinecraft;
import net.wjka.dnm.item.ModdedItems;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    //this Mixin renders the Item´s 3d model
    //models are hard-coded in Minecraft, so to change that we need this method:
    @ModifyVariable(method = "renderItem", at = @At(value = "HEAD"), argsOnly = true)
    public BakedModel useNegativeDiceModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        //so basically, IF we are currently looking for a specific item AND we are not in a Gui (inventory, crafting, smelting, etc.), display new specified model.
        //every custom Item need a separate IF statement
        if (stack.isOf(ModdedItems.NEGATIVEDICE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(DungeonsandMinecraft.MOD_ID, "3d_negative", "inventory"));
        }
        if (stack.isOf(ModdedItems.NEUTRALDICE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(DungeonsandMinecraft.MOD_ID, "3d_neutral", "inventory"));
        }
        if (stack.isOf(ModdedItems.POSITIVEDICE) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this).mccourse$getModels().getModelManager().getModel(new ModelIdentifier(DungeonsandMinecraft.MOD_ID, "3d_positive", "inventory"));
        }
        return value;

    }
}