//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.moonlight.windows.components.api;

import java.util.concurrent.*;
import java.util.*;

public final class ContentPane<T extends AbstractComponent> extends AbstractComponent
{
    private final CopyOnWriteArrayList<T> components;
    public final HashMap<String, Object> metaTags;
    
    public ContentPane(final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.components = new CopyOnWriteArrayList<T>();
        this.metaTags = new HashMap<String, Object>();
    }
    
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        for (final T component : this.components) {
            if (component.isVisible()) {
                component.click(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void release(final int mouseX, final int mouseY, final int mouseButton) {
        for (final T component : this.components) {
            if (component.isVisible()) {
                component.release(mouseX, mouseY, mouseButton);
            }
        }
    }
    
    public void draw(final int mouseX, final int mouseY, final float partialTicks) {
        int yVal = 0;
        for (final T component : this.components) {
            if (component.isVisible()) {
                component.x = this.x;
                component.y = this.y + yVal;
                yVal += component.height;
                component.draw(mouseX, mouseY, partialTicks);
            }
        }
    }
    
    public void typed(final char keyChar, final int keyCode) {
        for (final T component : this.components) {
            if (component.isVisible()) {
                component.typed(keyChar, keyCode);
            }
        }
    }
    
    public void clear() {
        for (final T component : this.components) {
            this.components.remove(component);
        }
    }
    
    public CopyOnWriteArrayList<T> getComponents() {
        return this.components;
    }
}
