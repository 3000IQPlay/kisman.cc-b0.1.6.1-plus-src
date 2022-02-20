//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component;

import com.kisman.cc.*;
import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.component.components.*;
import java.util.*;
import com.kisman.cc.hud.hudmodule.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.text.*;
import com.kisman.cc.oldclickgui.component.components.sub.*;

public class Frame
{
    public ArrayList<Component> components;
    public Category category;
    public HudCategory hudCategory;
    public boolean hud;
    public ColorUtil colorUtil;
    private boolean open;
    private int width;
    private int y;
    private int x;
    private int barHeight;
    private boolean isDragging;
    public int dragX;
    public int dragY;
    public int offset;
    
    public Frame(final Category cat) {
        this.colorUtil = new ColorUtil();
        this.components = new ArrayList<Component>();
        this.category = cat;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        this.hud = false;
        int tY = this.barHeight;
        for (final Module mod : Kisman.instance.moduleManager.getModulesInCategory(this.category)) {
            final Button modButton = new Button(mod, this, tY);
            this.components.add((Component)modButton);
            tY += 12;
        }
    }
    
    public Frame(final HudCategory cat) {
        this.colorUtil = new ColorUtil();
        this.components = new ArrayList<Component>();
        this.hudCategory = cat;
        this.width = 88;
        this.x = 5;
        this.y = 5;
        this.barHeight = 13;
        this.dragX = 0;
        this.open = true;
        this.isDragging = false;
        this.hud = true;
        int tY = this.barHeight;
        for (final HudModule mod : Kisman.instance.hudModuleManager.getModulesInCategory(this.hudCategory)) {
            final Button modButton = new Button(mod, this, tY);
            this.components.add((Component)modButton);
            tY += 12;
        }
    }
    
    public ArrayList<Component> getComponents() {
        return this.components;
    }
    
    public void setX(final int newX) {
        this.x = newX;
    }
    
    public void setY(final int newY) {
        this.y = newY;
    }
    
    public void setDrag(final boolean drag) {
        this.isDragging = drag;
    }
    
    public boolean isOpen() {
        return this.open;
    }
    
    public void setOpen(final boolean open) {
        this.open = open;
    }
    
    public void renderFrame(final FontRenderer fontRenderer) {
        if (Config.instance.guiGlow.getValBoolean() && ClickGui.isLine()) {
            final int offset = Config.instance.glowOffset.getValInt();
            Render2DUtil.drawGlow(this.x - offset, this.y - offset, this.x + this.width + offset, this.y + this.barHeight + offset, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
        Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.barHeight, ClickGui.isRainbowBackground() ? this.colorUtil.getColor() : new Color(ClickGui.getRBackground(), ClickGui.getGBackground(), ClickGui.getBBackground(), ClickGui.getABackground()).getRGB());
        if (ClickGui.isLine()) {
            if (ClickGui.getLineMode() == LineMode.LEFT) {
                Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.barHeight, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
            else if (ClickGui.getLineMode() == LineMode.LEFTONTOP) {
                Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.barHeight, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
                Gui.drawRect(this.x, this.y, this.x + this.width, this.y + 1, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
            else {
                Gui.drawRect(this.x, this.y, this.x + 1, this.y + this.barHeight, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
                Gui.drawRect(this.x, this.y, this.x + this.width, this.y + 1, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
                Gui.drawRect(this.x + this.width - 1, this.y, this.x + this.width, this.y + this.barHeight, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
                Gui.drawRect(this.x, this.y + this.barHeight - 1, this.x + this.width, this.y + this.barHeight, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        String str = this.hud ? this.hudCategory.name() : this.category.name();
        if (Config.instance.guiRenderSIze.getValBoolean()) {
            str = str + " [" + this.components.size() + "]";
        }
        fontRenderer.drawStringWithShadow(TextFormatting.BOLD + str, (float)((this.x + 2) * 2 + 5), (this.y + 2.5f) * 2.0f + 5.0f, new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        fontRenderer.drawStringWithShadow(this.open ? "-" : "+", (float)((this.x + this.width - 10) * 2 + 5), (this.y + 2.5f) * 2.0f + 5.0f, new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        if (!this.open || !this.components.isEmpty()) {}
        GL11.glPopMatrix();
        if (this.open && !this.components.isEmpty()) {
            for (final Component component : this.components) {
                component.renderComponent();
            }
        }
    }
    
    public void refresh() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            comp.setOff(off);
            off += comp.getHeight();
            this.offset = off;
        }
    }
    
    public void refreshPosition() {
        int off = this.barHeight;
        for (final Component comp : this.components) {
            final Button button = (Button)comp;
            comp.setOff(off);
            off += 12;
            if (comp instanceof Button) {
                if (!button.open) {
                    continue;
                }
                for (final Component set : button.subcomponents) {
                    set.setOff(off);
                    if (set instanceof ColorPickerSimpleButton) {
                        if (((ColorPickerSimpleButton)set).open) {
                            off += (int)((ColorPickerSimpleButton)set).PICKER_HEIGHT;
                        }
                        else {
                            off += 12;
                        }
                    }
                    else if (set instanceof com.kisman.cc.oldclickgui.component.components.sub.Category) {
                        final com.kisman.cc.oldclickgui.component.components.sub.Category cat1 = (com.kisman.cc.oldclickgui.component.components.sub.Category)set;
                        if (cat1.open) {
                            off += cat1.opY;
                        }
                        else {
                            off += 12;
                        }
                    }
                    else if (set instanceof PreviewButton) {
                        if (((PreviewButton)set).open) {
                            off += 112;
                        }
                        else {
                            off += 12;
                        }
                    }
                    else if (set instanceof ItemsButton) {
                        if (((ItemsButton)set).open) {
                            off += ((ItemsButton)set).blocksOffset;
                        }
                        else {
                            off += 12;
                        }
                    }
                    else {
                        off += 12;
                    }
                }
            }
        }
    }
    
    public int getX() {
        return this.x;
    }
    
    public int getY() {
        return this.y;
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public void updatePosition(final int mouseX, final int mouseY) {
        if (this.isDragging) {
            this.setX(mouseX - this.dragX);
            this.setY(mouseY - this.dragY);
        }
    }
    
    public boolean isWithinHeader(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.barHeight;
    }
}
