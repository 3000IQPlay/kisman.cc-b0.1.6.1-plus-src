//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import com.kisman.cc.module.render.*;
import org.spongepowered.asm.mixin.*;

@Mixin(value = { ModelEnderCrystal.class }, priority = 10000)
public class MixinModelEnderCrystal
{
    @Shadow
    private ModelRenderer cube;
    @Shadow
    private ModelRenderer glass;
    @Shadow
    private ModelRenderer base;
    
    @Overwrite
    public void render(final Entity entityIn, final float limbSwing, final float limbSwingAmount, final float ageInTicks, final float netHeadYaw, final float headPitch, final float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.translate(0.0f, -0.5f, 0.0f);
        if (this.base != null) {
            this.base.render(scale);
        }
        GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.8f + ageInTicks, 0.0f);
        GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
        if (CrystalModifier.instance.isToggled()) {
            if (CrystalModifier.instance.outsideCube.getValBoolean()) {
                this.glass.render(scale);
            }
        }
        else {
            this.glass.render(scale);
        }
        final float f = 0.875f;
        GlStateManager.scale(0.875f, 0.875f, 0.875f);
        GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
        GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
        if (CrystalModifier.instance.isToggled()) {
            if (CrystalModifier.instance.outsideCube2.getValBoolean()) {
                this.glass.render(scale);
            }
        }
        else {
            this.glass.render(scale);
        }
        GlStateManager.scale(0.875f, 0.875f, 0.875f);
        GlStateManager.rotate(60.0f, 0.7071f, 0.0f, 0.7071f);
        GlStateManager.rotate(limbSwingAmount, 0.0f, 1.0f, 0.0f);
        if (CrystalModifier.instance.isToggled()) {
            if (CrystalModifier.instance.insideCube.getValBoolean()) {
                this.cube.render(scale);
            }
        }
        else {
            this.cube.render(scale);
        }
        GlStateManager.popMatrix();
    }
}
