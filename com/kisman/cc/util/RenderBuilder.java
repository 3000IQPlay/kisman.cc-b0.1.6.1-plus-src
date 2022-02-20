//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.util.math.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class RenderBuilder
{
    private boolean setup;
    private boolean depth;
    private boolean blend;
    private boolean texture;
    private boolean cull;
    private boolean alpha;
    private boolean shade;
    private BlockPos blockPos;
    private double height;
    private double length;
    private double width;
    private Color color;
    private Box box;
    
    public RenderBuilder() {
        this.setup = false;
        this.depth = false;
        this.blend = false;
        this.texture = false;
        this.cull = false;
        this.alpha = false;
        this.shade = false;
        this.blockPos = BlockPos.ORIGIN;
        this.height = 0.0;
        this.length = 0.0;
        this.width = 0.0;
        this.color = new Color(255, 255, 255, 255);
        this.box = Box.FILL;
    }
    
    public RenderBuilder setup() {
        GlStateManager.pushMatrix();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        this.setup = true;
        return this;
    }
    
    public RenderBuilder depth(final boolean depth) {
        if (depth) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
        }
        this.depth = depth;
        return this;
    }
    
    public RenderBuilder blend() {
        GlStateManager.enableBlend();
        this.blend = true;
        return this;
    }
    
    public RenderBuilder texture() {
        GlStateManager.disableTexture2D();
        this.texture = true;
        return this;
    }
    
    public RenderBuilder line(final float width) {
        GL11.glLineWidth(width);
        return this;
    }
    
    public RenderBuilder cull(final boolean cull) {
        if (cull) {
            GlStateManager.disableCull();
        }
        this.cull = cull;
        return this;
    }
    
    public RenderBuilder alpha(final boolean alpha) {
        if (alpha) {
            GlStateManager.disableAlpha();
        }
        this.alpha = alpha;
        return this;
    }
    
    public RenderBuilder shade(final boolean shade) {
        if (shade) {
            GlStateManager.shadeModel(7425);
        }
        this.shade = shade;
        return this;
    }
    
    public RenderBuilder build() {
        if (this.depth) {
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
        }
        if (this.texture) {
            GlStateManager.enableTexture2D();
        }
        if (this.blend) {
            GlStateManager.disableBlend();
        }
        if (this.cull) {
            GlStateManager.enableCull();
        }
        if (this.alpha) {
            GlStateManager.enableAlpha();
        }
        if (this.shade) {
            GlStateManager.shadeModel(7424);
        }
        if (this.setup) {
            GL11.glDisable(2848);
            GlStateManager.popMatrix();
        }
        return this;
    }
    
    public RenderBuilder position(final BlockPos blockPos) {
        this.blockPos = blockPos;
        return this;
    }
    
    public RenderBuilder height(final double height) {
        this.height = height;
        return this;
    }
    
    public RenderBuilder width(final double width) {
        this.width = width;
        return this;
    }
    
    public RenderBuilder length(final double length) {
        this.length = length;
        return this;
    }
    
    public RenderBuilder color(final Color color) {
        this.color = color;
        return this;
    }
    
    public RenderBuilder box(final Box box) {
        this.box = box;
        return this;
    }
    
    public BlockPos getBlockPos() {
        return this.blockPos;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getWidth() {
        return this.width;
    }
    
    public double getLength() {
        return this.length;
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public Box getBox() {
        return this.box;
    }
    
    public enum Box
    {
        FILL, 
        OUTLINE, 
        BOTH, 
        GLOW, 
        REVERSE, 
        CLAW, 
        NONE;
    }
}
