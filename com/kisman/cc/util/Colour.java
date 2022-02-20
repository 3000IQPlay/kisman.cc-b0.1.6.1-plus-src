//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import i.gishreloaded.gishcode.utils.visual.*;
import java.awt.*;
import net.minecraft.client.renderer.*;

public class Colour
{
    public int r;
    public int g;
    public int b;
    public int a;
    public float r1;
    public float g1;
    public float b1;
    public float a1;
    private boolean isInt;
    
    public Colour(final int r, final int g, final int b, final int a) {
        this.isInt = true;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }
    
    public Colour(final int r, final int g, final int b) {
        this.isInt = true;
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = 255;
    }
    
    public Colour(final Colour color, final int a) {
        this.isInt = true;
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = a;
    }
    
    public Colour(final float r, final float g, final float b) {
        this.isInt = true;
        this.r1 = r;
        this.g1 = g;
        this.b1 = b;
        this.isInt = false;
    }
    
    public Colour(final float r, final float g, final float b, final float a) {
        this.isInt = true;
        this.r1 = r;
        this.g1 = g;
        this.b1 = b;
        this.a1 = a;
        this.isInt = false;
    }
    
    public Colour(final int color) {
        this.isInt = true;
        this.r1 = (float)ColorUtils.getRed(color);
        this.g1 = (float)ColorUtils.getGreen(color);
        this.b1 = (float)ColorUtils.getBlue(color);
        this.a1 = 1.0f;
        this.isInt = false;
    }
    
    public Color getColor() {
        if (this.isInt) {
            return new Color(this.r, this.g, this.b, this.a);
        }
        return new Color(this.r1 * 255.0f, this.g1 * 255.0f, this.b1 * 255.0f, this.a1 * 255.0f);
    }
    
    public int getRGB() {
        if (this.isInt) {
            return new Color(this.r, this.g, this.b, this.a).getRGB();
        }
        return new Color(this.r1 * 255.0f, this.g1 * 255.0f, this.b1 * 255.0f, this.a1 * 255.0f).getRGB();
    }
    
    public float getR() {
        if (this.isInt) {
            return this.r / 255.0f;
        }
        return this.r1;
    }
    
    public float getG() {
        if (this.isInt) {
            return this.g / 255.0f;
        }
        return this.g1;
    }
    
    public float getB() {
        if (this.isInt) {
            return this.b / 255.0f;
        }
        return this.b1;
    }
    
    public float getA() {
        if (this.isInt) {
            return this.a / 255.0f;
        }
        return this.a1;
    }
    
    public int getAlpha() {
        if (this.isInt) {
            return this.a;
        }
        return (int)this.a1 * 255;
    }
    
    public void glColor() {
        if (this.isInt) {
            GlStateManager.color(this.r / 255.0f, this.g / 255.0f, this.b / 255.0f, this.a / 255.0f);
        }
        else {
            GlStateManager.color(this.r1, this.g1, this.b1, this.a1);
        }
    }
}
