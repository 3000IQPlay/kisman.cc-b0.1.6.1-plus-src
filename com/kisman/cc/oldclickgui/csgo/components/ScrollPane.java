//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.csgo.components;

import com.kisman.cc.oldclickgui.csgo.layout.*;
import java.awt.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.oldclickgui.csgo.*;
import java.util.*;

public class ScrollPane extends Pane
{
    private static final double SCROLL_AMOUNT = 0.25;
    private int scrollOffset;
    private boolean hovered;
    private int realHeight;
    
    public ScrollPane(final IRenderer renderer, final ILayoutManager layoutManager) {
        super(renderer, layoutManager);
        this.scrollOffset = 0;
        this.hovered = false;
    }
    
    public void updateLayout() {
        this.updateLayout(this.getWidth(), Integer.MAX_VALUE, true);
    }
    
    protected void updateLayout(final int width, final int height, final boolean changeHeight) {
        super.updateLayout(width, height, false);
        this.realHeight = this.layout.getMaxHeight();
        this.validateOffset();
    }
    
    public void render() {
        this.renderer.initMask();
        this.renderer.drawRect(this.x, this.y, this.getWidth(), this.getHeight(), Color.white);
        this.renderer.useMask();
        super.render();
        this.renderer.disableMask();
        final int maxY = this.realHeight - this.getHeight();
        if (maxY > 0) {
            final int sliderHeight = (int)(this.getHeight() / (double)this.realHeight * this.getHeight());
            final int sliderWidth = 3;
            this.renderer.drawRect(this.x + this.getWidth() - sliderWidth, this.y + (this.getHeight() - sliderHeight) * (this.scrollOffset / (double)maxY), sliderWidth, sliderHeight, Config.instance.guiAstolfo.getValBoolean() ? this.renderer.astolfoColorToObj() : Window.SECONDARY_OUTLINE);
        }
    }
    
    protected void updateComponentLocation() {
        for (final AbstractComponent component : this.components) {
            final int[] ints = this.componentLocations.get(component);
            if (ints == null) {
                this.updateLayout();
                this.updateComponentLocation();
                return;
            }
            component.setX(this.x + ints[0]);
            component.setY(this.y + ints[1] - this.scrollOffset);
        }
    }
    
    private void updateHovered(final int x, final int y, final boolean offscreen) {
        this.hovered = (!offscreen && x >= this.x && y >= this.y && x <= this.x + this.getWidth() && y <= this.y + this.getHeight());
    }
    
    public boolean mouseWheel(final int change) {
        this.scrollOffset -= (int)(change * 0.25);
        this.validateOffset();
        return super.mouseWheel(change);
    }
    
    private void validateOffset() {
        if (this.scrollOffset > this.realHeight - this.getHeight()) {
            this.scrollOffset = this.realHeight - this.getHeight();
        }
        if (this.scrollOffset < 0) {
            this.scrollOffset = 0;
        }
    }
    
    public boolean mouseMove(final int x, final int y, final boolean offscreen) {
        this.updateHovered(x, y, offscreen);
        return super.mouseMove(x, y, offscreen || x < this.x || y < this.y || x > this.x + this.getWidth() || y > this.y + this.getHeight());
    }
    
    public boolean mousePressed(final int button, final int x, final int y, final boolean offscreen) {
        return super.mousePressed(button, x, y, offscreen || x < this.x || y < this.y || x > this.x + this.getWidth() || y > this.y + this.getHeight());
    }
    
    public boolean mouseReleased(final int button, final int x, final int y, final boolean offscreen) {
        return super.mouseReleased(button, x, y, offscreen || x < this.x || y < this.y || x > this.x + this.getWidth() || y > this.y + this.getHeight());
    }
    
    public void addComponent(final AbstractComponent component) {
        super.addComponent(component);
    }
}
