//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.moonlight.windows.components.api;

public class Rect
{
    public int x;
    public int y;
    public int width;
    public int height;
    
    public Rect(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public boolean isInside(final int posX, final int posY) {
        return posX > this.x && posY > this.y && posX < this.x + this.width && posY < this.y + this.height;
    }
}
