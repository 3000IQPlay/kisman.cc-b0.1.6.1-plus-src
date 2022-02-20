//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import java.awt.*;
import net.minecraft.client.renderer.*;
import com.kisman.cc.settings.*;
import net.minecraft.client.*;
import net.minecraft.client.shader.*;
import org.lwjgl.opengl.*;

public class OutlineUtils
{
    public static void renderOne(final float f) {
        checkSetupFBO();
        GL11.glPushAttrib(1048575);
        GL11.glDisable(3008);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(f);
        GL11.glEnable(2848);
        GL11.glEnable(2960);
        GL11.glClear(1024);
        GL11.glClearStencil(15);
        GL11.glStencilFunc(512, 1, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void renderTwo() {
        GL11.glStencilFunc(512, 0, 15);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glPolygonMode(1032, 6914);
    }
    
    public static void renderThree() {
        GL11.glStencilFunc(514, 1, 15);
        GL11.glStencilOp(7680, 7680, 7680);
        GL11.glPolygonMode(1032, 6913);
    }
    
    public static void renderFour() {
        setColor(new Color(255, 255, 255));
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
        GL11.glEnable(10754);
        GL11.glPolygonOffset(Float.intBitsToFloat(Float.floatToIntBits(22.41226f) ^ 0x7E334C4F), Float.intBitsToFloat(Float.floatToIntBits(-1.3566593E-5f) ^ 0x7E97B813));
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, Float.intBitsToFloat(Float.floatToIntBits(0.01000088f) ^ 0x7F53DABB), Float.intBitsToFloat(Float.floatToIntBits(0.011808969f) ^ 0x7F317A68));
    }
    
    public static void renderFive() {
        GL11.glPolygonOffset(Float.intBitsToFloat(Float.floatToIntBits(12.714713f) ^ 0x7ECB6F77), Float.intBitsToFloat(Float.floatToIntBits(1.3271895E-5f) ^ 0x7EAA8E5B));
        GL11.glDisable(10754);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(2960);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glEnable(3042);
        GL11.glEnable(2896);
        GL11.glEnable(3553);
        GL11.glEnable(3008);
        GL11.glPopAttrib();
    }
    
    public static void setColor(final Color c) {
        GL11.glColor4d((double)(c.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.0098663345f) ^ 0x7F5EA668)), (double)(c.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(1.0011557f) ^ 0x7CFF25DF)), (double)(c.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.114814304f) ^ 0x7E9423C3)), (double)(c.getAlpha() / Float.intBitsToFloat(Float.floatToIntBits(0.09551593f) ^ 0x7EBC9DDB)));
    }
    
    public static void setColor(final Setting color) {
        setColor(new Color(color.getR(), color.getG(), color.getB(), color.getA()));
    }
    
    public static void checkSetupFBO() {
        final Framebuffer fbo = Minecraft.getMinecraft().getFramebuffer();
        if (fbo != null && fbo.depthBuffer > -1) {
            setupFBO(fbo);
            fbo.depthBuffer = -1;
        }
    }
    
    public static void setupFBO(final Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.depthBuffer);
        final int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, stencil_depth_buffer_ID);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencil_depth_buffer_ID);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencil_depth_buffer_ID);
    }
}
