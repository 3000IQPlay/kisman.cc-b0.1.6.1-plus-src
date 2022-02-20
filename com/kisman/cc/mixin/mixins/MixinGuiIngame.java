//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.module.render.*;

@Mixin(value = { GuiIngame.class }, priority = 10000)
public class MixinGuiIngame
{
    @Inject(method = { "renderGameOverlay" }, at = { @At("HEAD") }, cancellable = true)
    private void renderGameOverlay(final float p_renderGameOverlay_1_, final CallbackInfo ci) {
        final EventRender2D event = new EventRender2D(new ScaledResolution(Minecraft.getMinecraft()), p_renderGameOverlay_1_);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "renderPumpkinOverlay" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPumpkinOverlayHook(final ScaledResolution scaledRes, final CallbackInfo callbackInfo) {
        if (NoRender.instance.isToggled() && NoRender.instance.overlay.getValBoolean()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "renderPotionEffects" }, at = { @At("HEAD") }, cancellable = true)
    protected void renderPotionEffectsHook(final ScaledResolution scaledRes, final CallbackInfo callbackInfo) {
        if (NoRender.instance.isToggled() && NoRender.instance.overlay.getValBoolean()) {
            callbackInfo.cancel();
        }
    }
}
