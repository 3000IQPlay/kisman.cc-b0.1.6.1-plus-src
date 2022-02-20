//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.renderer.*;
import com.kisman.cc.module.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderPlayer.class })
public class MixinRenderPlayer
{
    @Shadow
    public ResourceLocation getEntityTexture(final AbstractClientPlayer abstractClientPlayer) {
        return null;
    }
    
    @Inject(method = { "preRenderCallback*" }, at = { @At("HEAD") })
    public void renderCallback(final AbstractClientPlayer entitylivingbaseIn, final float partialTickTime, final CallbackInfo ci) {
        if (Spin.instance.isToggled()) {
            final float f = 0.9357f;
            final float hue = System.currentTimeMillis() % 22600L / 5.0f;
            GlStateManager.scale(f, f, f);
            GlStateManager.rotate(hue, 1.0f, 0.0f, hue);
        }
        else if (Reverse.instance.isToggled() && !Spin.instance.isToggled()) {
            GlStateManager.rotate(180.0f, 1.0f, 0.0f, 0.0f);
        }
    }
}
