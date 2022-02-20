//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.*;
import com.kisman.cc.module.client.*;
import java.awt.*;

public class CheckBox extends AbstractComponent
{
    private static final int PREFERRED_HEIGHT = 22;
    private boolean selected;
    private String title;
    private int preferredHeight;
    private boolean hovered;
    private ValueChangeListener<Boolean> listener;
    
    public CheckBox(final IRenderer renderer, final String title, final int preferredHeight) {
        super(renderer);
        this.preferredHeight = preferredHeight;
        this.setTitle(title);
    }
    
    public CheckBox(final IRenderer renderer, final String title) {
        this(renderer, title, 22);
    }
    
    public void render() {
        this.renderer.drawRect(this.x, this.y, this.preferredHeight, this.preferredHeight, this.hovered ? Window.SECONDARY_FOREGROUND : Window.TERTIARY_FOREGROUND);
        if (this.selected) {
            final Color color = this.hovered ? (Config.instance.guiAstolfo.getValBoolean() ? this.renderer.astolfoColorToObj() : Window.TERTIARY_FOREGROUND) : Window.SECONDARY_FOREGROUND;
            this.renderer.drawRect(this.x, this.y, this.preferredHeight, this.preferredHeight, color);
        }
        this.renderer.drawOutline(this.x, this.y, this.preferredHeight, this.preferredHeight, 1.0f, this.hovered ? (Config.instance.guiAstolfo.getValBoolean() ? this.renderer.astolfoColorToObj() : Window.SECONDARY_OUTLINE) : Window.SECONDARY_FOREGROUND);
        this.renderer.drawString(this.x + this.preferredHeight + this.preferredHeight / 4, this.y + this.getHeight() / 2 - this.renderer.getStringHeight(this.title) / 2, this.title, Window.FOREGROUND);
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        this.updateHovered(x, y, offscreen);
        return false;
    }
    
    private void updateHovered(final int x, final int y, final boolean offscreen) {
        this.hovered = (!offscreen && x >= this.x && y >= this.y && x <= this.x + this.getWidth() && y <= this.y + this.getHeight());
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        if (button == 0) {
            this.updateHovered(x, y, offscreen);
            if (this.hovered) {
                final boolean newVal = !this.selected;
                boolean change = true;
                if (this.listener != null) {
                    change = this.listener.onValueChange(newVal);
                }
                if (change) {
                    this.selected = newVal;
                }
                return true;
            }
        }
        return false;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
        this.setWidth(this.renderer.getStringWidth(title) + this.preferredHeight + this.preferredHeight / 4);
        this.setHeight(this.preferredHeight);
    }
    
    public void setListener(final ValueChangeListener<Boolean> listener) {
        this.listener = listener;
    }
    
    public boolean isSelected() {
        return this.selected;
    }
    
    public void setSelected(final boolean selected) {
        this.selected = selected;
    }
}
