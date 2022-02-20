//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.*;
import com.kisman.cc.module.client.*;

public class Button extends AbstractComponent
{
    private static final int PREFERRED_WIDTH = 180;
    private static final int PREFERRED_HEIGHT = 22;
    public String title;
    private int preferredWidth;
    private int preferredHeight;
    private boolean hovered;
    private ActionEventListener listener;
    
    public Button(final IRenderer renderer, final String title, final int preferredWidth, final int preferredHeight) {
        super(renderer);
        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;
        this.setTitle(title);
    }
    
    public Button(final IRenderer renderer, final String title) {
        this(renderer, title, 180, 22);
    }
    
    public void render() {
        this.renderer.drawRect(this.x, this.y, this.getWidth(), this.getHeight(), this.hovered ? Window.SECONDARY_FOREGROUND : Window.TERTIARY_FOREGROUND);
        this.renderer.drawOutline(this.x, this.y, this.getWidth(), this.getHeight(), 1.0f, this.hovered ? (Config.instance.guiAstolfo.getValBoolean() ? this.renderer.astolfoColorToObj() : Window.SECONDARY_OUTLINE) : Window.SECONDARY_FOREGROUND);
        this.renderer.drawString(this.x + this.getWidth() / 2 - this.renderer.getStringWidth(this.title) / 2, this.y + this.getHeight() / 2 - this.renderer.getStringHeight(this.title) / 2, this.title, Window.FOREGROUND);
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
            if (this.hovered && this.listener != null) {
                this.listener.onActionEvent();
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
        this.setWidth(Math.max(this.renderer.getStringWidth(title), this.preferredWidth));
        this.setHeight(Math.max(this.renderer.getStringHeight(title) * 5 / 4, this.preferredHeight));
    }
    
    public ActionEventListener getOnClickListener() {
        return this.listener;
    }
    
    public void setOnClickListener(final ActionEventListener listener) {
        this.listener = listener;
    }
}
