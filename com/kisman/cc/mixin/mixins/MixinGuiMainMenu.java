//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.*;
import com.kisman.cc.module.client.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.text.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { GuiMainMenu.class }, priority = 10000)
public class MixinGuiMainMenu extends GuiScreen
{
    @Inject(method = { "initGui" }, at = { @At("RETURN") })
    private void init(final CallbackInfo ci) {
        Kisman.instance.sandBoxShaders.init();
    }
    
    @Inject(method = { "drawScreen" }, at = { @At("HEAD") })
    public void drawText(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        if (SandBox.instance.isToggled() && Kisman.instance.sandBoxShaders.currentshader != null) {
            GlStateManager.disableCull();
            Kisman.instance.sandBoxShaders.currentshader.useShader(this.width * 2, this.height * 2, (float)(mouseX * 2), (float)(mouseY * 2), (System.currentTimeMillis() - Kisman.instance.sandBoxShaders.time) / 1000.0f);
            GL11.glBegin(7);
            GL11.glVertex2f(-1.0f, -1.0f);
            GL11.glVertex2f(-1.0f, 1.0f);
            GL11.glVertex2f(1.0f, 1.0f);
            GL11.glVertex2f(1.0f, -1.0f);
            GL11.glEnd();
            GL20.glUseProgram(0);
        }
        this.mc.fontRenderer.drawStringWithShadow(TextFormatting.WHITE + Kisman.getName() + " " + TextFormatting.GRAY + Kisman.getVersion(), 1.0f, 1.0f, -1);
        this.mc.fontRenderer.drawStringWithShadow(TextFormatting.WHITE + "made by " + TextFormatting.GRAY + "_kisman_#5039", 1.0f, (float)(this.mc.fontRenderer.FONT_HEIGHT + 1), -1);
    }
    
    @Inject(method = { "renderSkybox" }, at = { @At("HEAD") }, cancellable = true)
    public void renderSkybox(final int mouseX, final int mouseY, final float partialTicks, final CallbackInfo ci) {
        if (SandBox.instance.isToggled() && Kisman.instance.sandBoxShaders.currentshader != null) {
            ci.cancel();
        }
    }
    
    @Redirect(method = { "drawScreen" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawGradientRect(IIIIII)V", ordinal = 0))
    public void drawGradientRect1(final GuiMainMenu guiMainMenu, final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        if (!SandBox.instance.isToggled() && Kisman.instance.sandBoxShaders.currentshader == null) {
            this.drawGradientRect(left, top, right, bottom, startColor, endColor);
        }
    }
    
    @Redirect(method = { "drawScreen" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;drawGradientRect(IIIIII)V", ordinal = 1))
    public void drawGradientRect2(final GuiMainMenu guiMainMenu, final int left, final int top, final int right, final int bottom, final int startColor, final int endColor) {
        if (!SandBox.instance.isToggled() && Kisman.instance.sandBoxShaders.currentshader == null) {
            this.drawGradientRect(left, top, right, bottom, startColor, endColor);
        }
    }
}
