//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo;

public abstract class AbstractComponent
{
    protected int x;
    protected int y;
    protected IRenderer renderer;
    private int width;
    private int height;
    private boolean sizeChanged;
    
    public AbstractComponent(final IRenderer renderer) {
        this.renderer = renderer;
    }
    
    public abstract void render();
    
    public int getEventPriority() {
        return 0;
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        if (this.x != x) {
            this.setSizeChanged(true);
        }
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        if (this.y != y) {
            this.setSizeChanged(true);
        }
        this.y = y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        if (this.width != width) {
            this.setSizeChanged(true);
        }
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        if (this.height != height) {
            this.setSizeChanged(true);
        }
        this.height = height;
    }
    
    public boolean isSizeChanged() {
        return this.sizeChanged;
    }
    
    public void setSizeChanged(final boolean sizeChanged) {
        this.sizeChanged = sizeChanged;
    }
    
    public boolean keyPressed(final int key, final char c) {
        return false;
    }
    
    public boolean mouseReleased(final int button, final int x, final int y, final boolean offscreen) {
        return false;
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        return false;
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        return false;
    }
    
    public boolean mouseWheel(final int change) {
        return false;
    }
}
