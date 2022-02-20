//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo;

import java.awt.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;
import com.kisman.cc.util.customfont.*;
import org.lwjgl.opengl.*;

public class ClientBaseRendererImpl implements IRenderer
{
    @Override
    public void drawRect(final double x, final double y, final double w, final double h, final Color c) {
        GLUtil.drawRect(7, (int)x / 2, (int)y / 2, (int)x / 2 + (int)w / 2, (int)y / 2 + (int)h / 2, ColorUtils.getColor(c));
    }
    
    @Override
    public void drawOutline(final double x, final double y, final double w, final double h, final float lineWidth, final Color c) {
        GL11.glLineWidth(lineWidth);
        GLUtil.drawRect(2, (int)x / 2, (int)y / 2, (int)x / 2 + (int)w / 2, (int)y / 2 + (int)h / 2, ColorUtils.getColor(c));
    }
    
    @Override
    public void setColor(final Color c) {
        ColorUtils.glColor(c);
    }
    
    @Override
    public void drawString(final int x, final int y, final String text, final Color color) {
        CustomFontUtil.drawString(text, x / 2.0f, y / 2.0f + 1.0f, ColorUtils.getColor(color), true);
    }
    
    @Override
    public int getStringWidth(final String str) {
        return CustomFontUtil.getStringWidth(str) * 2;
    }
    
    @Override
    public int getStringHeight(final String str) {
        return CustomFontUtil.getFontHeight(true) * 2;
    }
    
    @Override
    public void drawTriangle(final double x1, final double y1, final double x2, final double y2, final double x3, final double y3, final Color color) {
    }
    
    @Override
    public void initMask() {
        GL41.glClearDepthf(1.0f);
        GL11.glClear(256);
        GL11.glColorMask(false, false, false, false);
        GL11.glDepthFunc(513);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
    }
    
    @Override
    public void useMask() {
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthMask(true);
        GL11.glDepthFunc(514);
    }
    
    @Override
    public void disableMask() {
        GL41.glClearDepthf(1.0f);
        GL11.glClear(1280);
        GL11.glDisable(2929);
        GL11.glDepthFunc(515);
        GL11.glDepthMask(false);
    }
    
    @Override
    public int astolfoColor() {
        return ColorUtils.astolfoColors(100, 100);
    }
    
    @Override
    public Color astolfoColorToObj() {
        return ColorUtils.astolfoColorsToColorObj(100, 100);
    }
}
