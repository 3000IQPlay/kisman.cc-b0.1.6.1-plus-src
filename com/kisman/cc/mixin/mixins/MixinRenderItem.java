//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.renderer.block.model.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;
import com.kisman.cc.module.render.*;
import java.awt.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.renderer.*;

@Mixin(value = { RenderItem.class }, priority = 10000)
public class MixinRenderItem
{
    @Shadow
    private void renderModel(final IBakedModel model, final int color, final ItemStack stack) {
    }
    
    @ModifyArg(method = { "renderEffect" }, at = @At(value = "INVOKE", target = "net/minecraft/client/renderer/RenderItem.renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;I)V"), index = 1)
    private int renderEffect(final int oldValue) {
        return ViewModel.instance.isToggled() ? new Color(255, 255, 255, ViewModel.instance.alpha.getValInt()).getRGB() : oldValue;
    }
    
    @Redirect(method = { "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V"))
    private void POOOOOP(final RenderItem renderItem, final IBakedModel model, final ItemStack stack) {
        if (ViewModel.instance.isToggled()) {
            this.renderModel(model, new Color(255, 255, 255, ViewModel.instance.alpha.getValInt()).getRGB(), stack);
        }
        else {
            this.renderModel(model, -1, stack);
        }
    }
    
    @Redirect(method = { "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/GlStateManager;color(FFFF)V"))
    private void renderItem(final float colorRed, final float colorGreen, final float colorBlue, final float alpha) {
        if (ViewModel.instance.isToggled()) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, ViewModel.instance.alpha.getValFloat() / 255.0f);
        }
        else {
            GlStateManager.color(colorRed, colorGreen, colorBlue, alpha);
        }
    }
}
