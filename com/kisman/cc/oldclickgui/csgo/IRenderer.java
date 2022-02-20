//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo;

import java.awt.*;

public interface IRenderer
{
    void drawRect(final double p0, final double p1, final double p2, final double p3, final Color p4);
    
    void drawOutline(final double p0, final double p1, final double p2, final double p3, final float p4, final Color p5);
    
    void setColor(final Color p0);
    
    void drawString(final int p0, final int p1, final String p2, final Color p3);
    
    int getStringWidth(final String p0);
    
    int getStringHeight(final String p0);
    
    void drawTriangle(final double p0, final double p1, final double p2, final double p3, final double p4, final double p5, final Color p6);
    
    void initMask();
    
    void useMask();
    
    void disableMask();
    
    int astolfoColor();
    
    Color astolfoColorToObj();
}
