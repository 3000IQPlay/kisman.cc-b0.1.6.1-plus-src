//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.csgo.*;
import com.kisman.cc.module.client.*;

public class Spoiler extends AbstractComponent
{
    private static final int PREFERRED_HEIGHT = 28;
    public int preferredWidth;
    private String title;
    private int preferredHeight;
    private boolean hovered;
    private ActionEventListener listener;
    private Pane contentPane;
    private boolean opened;
    private Module mod;
    
    public Spoiler(final IRenderer renderer, final String title, final int preferredWidth, final int preferredHeight, final Pane contentPane) {
        super(renderer);
        this.opened = false;
        this.mod = null;
        this.preferredWidth = preferredWidth;
        this.preferredHeight = preferredHeight;
        this.contentPane = contentPane;
        this.setTitle(title);
    }
    
    public Spoiler(final IRenderer renderer, final String title, final int preferredWidth, final Pane contentPane) {
        this(renderer, title, preferredWidth, 28, contentPane);
    }
    
    public Spoiler(final IRenderer renderer, final String title, final int preferredWidth, final Pane contentPane, final Module mod) {
        this(renderer, title, preferredWidth, 28, contentPane);
        this.mod = mod;
    }
    
    public void render() {
        if (this.hovered) {
            this.renderer.drawRect(this.x, this.y, this.getWidth(), this.preferredHeight, Window.SECONDARY_FOREGROUND);
        }
        this.renderer.drawOutline(this.x, this.y, this.getWidth(), this.preferredHeight, 1.0f, Window.SECONDARY_FOREGROUND);
        this.renderer.drawString(this.x + this.getWidth() / 2 - this.renderer.getStringWidth(this.title) / 2, this.y + this.preferredHeight / 2 - this.renderer.getStringHeight(this.title) / 2, this.title, (Config.instance.guiAstolfo.getValBoolean() && this.isToggled()) ? this.renderer.astolfoColorToObj() : Window.FOREGROUND);
        if (this.opened) {
            this.updateBounds();
            this.contentPane.setX(this.getX());
            this.contentPane.setY(this.getY() + this.preferredHeight);
            this.contentPane.render();
            this.renderer.drawOutline(this.x, this.y, this.getWidth(), this.getHeight(), 1.0f, Window.SECONDARY_FOREGROUND);
        }
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        this.updateHovered(x, y, offscreen);
        return this.opened && this.contentPane.mouseMove(x, y, offscreen);
    }
    
    private void updateHovered(final int x, final int y, final boolean offscreen) {
        this.hovered = (!offscreen && x >= this.x && y >= this.y && x <= this.x + this.getWidth() && y <= this.y + this.preferredHeight);
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        if (button == 0) {
            this.updateHovered(x, y, offscreen);
            if (this.hovered) {
                this.opened = !this.opened;
                this.contentPane.updateLayout();
                this.updateBounds();
                return true;
            }
        }
        return this.opened && this.contentPane.mousePressed(button, x, y, offscreen);
    }
    
    public boolean isToggled() {
        return this.mod != null && this.mod.isToggled();
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
        this.updateBounds();
    }
    
    private void updateBounds() {
        this.setHeight(Math.max(this.renderer.getStringHeight(this.getTitle()) * 5 / 4, this.preferredHeight) + (this.opened ? this.contentPane.getHeight() : 0));
    }
    
    public void setListener(final ActionEventListener listener) {
        this.listener = listener;
    }
    
    public boolean mouseReleased(final int button, final int x, final int y, final boolean offscreen) {
        return this.opened && this.contentPane.mouseReleased(button, x, y, offscreen);
    }
    
    public boolean mouseWheel(final int change) {
        return this.opened && this.contentPane.mouseWheel(change);
    }
    
    public boolean keyPressed(final int key, final char c) {
        return this.opened && this.contentPane.keyPressed(key, c);
    }
}
