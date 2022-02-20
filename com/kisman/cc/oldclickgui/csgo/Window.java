//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo;

import java.awt.*;
import com.kisman.cc.oldclickgui.csgo.components.*;
import com.kisman.cc.module.client.*;

public class Window
{
    public static final Color SECONDARY_FOREGROUND;
    public static final Color TERTIARY_FOREGROUND;
    public static final Color SECONDARY_OUTLINE;
    public static final Color BACKGROUND;
    public static final Color FOREGROUND;
    private String title;
    private int x;
    private int y;
    private int width;
    private int height;
    private int headerHeight;
    private boolean beingDragged;
    private int dragX;
    private int dragY;
    private Pane contentPane;
    
    public Window(final String title, final int x, final int y, final int width, final int height) {
        this.title = title;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    public void render(final IRenderer renderer) {
        final int fontHeight = renderer.getStringHeight(this.title);
        final int headerFontOffset = fontHeight / 4;
        this.headerHeight = headerFontOffset * 2 + fontHeight;
        renderer.drawRect((double)this.x, (double)this.y, (double)this.width, (double)this.height, Window.BACKGROUND);
        renderer.drawRect((double)this.x, (double)this.y, (double)this.width, (double)this.headerHeight, Window.SECONDARY_FOREGROUND);
        renderer.drawString(this.x + this.width / 2 - renderer.getStringWidth(this.title) / 2, this.y + headerFontOffset, this.title, Config.instance.guiAstolfo.getValBoolean() ? renderer.astolfoColorToObj() : Window.FOREGROUND);
        if (this.contentPane != null) {
            if (this.contentPane.isSizeChanged()) {
                this.contentPane.setSizeChanged(false);
            }
            this.contentPane.setX(this.x);
            this.contentPane.setY(this.y + this.headerHeight);
            this.contentPane.setWidth(this.width);
            this.contentPane.setHeight(this.height - this.headerHeight);
            this.contentPane.render();
        }
    }
    
    public void mousePressed(final int button, final int x, final int y) {
        if (this.contentPane != null) {
            this.contentPane.mousePressed(button, x, y, false);
        }
        if (button == 0 && x >= this.x && y >= this.y && x <= this.x + this.width && y <= this.y + this.headerHeight) {
            this.beingDragged = true;
            this.dragX = this.x - x;
            this.dragY = this.y - y;
        }
    }
    
    private void drag(final int mouseX, final int mouseY) {
        if (this.beingDragged) {
            this.x = mouseX + this.dragX;
            this.y = mouseY + this.dragY;
        }
    }
    
    public void mouseReleased(final int button, final int x, final int y) {
        if (this.contentPane != null) {
            this.contentPane.mouseReleased(button, x, y, false);
        }
        if (button == 0) {
            this.beingDragged = false;
        }
    }
    
    public void mouseMoved(final int x, final int y) {
        if (this.contentPane != null) {
            this.contentPane.mouseMove(x, y, false);
        }
        this.drag(x, y);
    }
    
    public Pane getContentPane() {
        return this.contentPane;
    }
    
    public void setContentPane(final Pane contentPane) {
        this.contentPane = contentPane;
    }
    
    public void keyPressed(final int key, final char c) {
        if (this.contentPane != null) {
            this.contentPane.keyPressed(key, c);
        }
    }
    
    public void mouseWheel(final int change) {
        if (this.contentPane != null) {
            this.contentPane.mouseWheel(change);
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public void setX(final int x) {
        this.x = x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public void setY(final int y) {
        this.y = y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(final String title) {
        this.title = title;
    }
    
    static {
        SECONDARY_FOREGROUND = new Color(80, 80, 80, 240);
        TERTIARY_FOREGROUND = new Color(20, 20, 20, 150);
        SECONDARY_OUTLINE = new Color(10, 10, 10, 255);
        BACKGROUND = new Color(20, 20, 20, 220);
        FOREGROUND = Color.white;
    }
}
