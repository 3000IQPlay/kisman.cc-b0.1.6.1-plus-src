//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.moonlight.windows.components.api;

public abstract class AbstractComponent extends Rect implements IComponent
{
    private boolean isVisible;
    
    public AbstractComponent(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.isVisible = true;
    }
    
    @Override
    public boolean isVisible() {
        return this.isVisible;
    }
    
    public void setVisible(final boolean visible) {
        this.isVisible = visible;
    }
}
