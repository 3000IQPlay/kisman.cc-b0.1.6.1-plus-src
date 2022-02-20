//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component;

import com.kisman.cc.oldclickgui.vega.component.components.*;
import com.kisman.cc.util.*;
import com.kisman.cc.*;
import com.kisman.cc.module.*;
import java.util.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.customfont.*;

public class Frame
{
    public ArrayList<Button> buttons;
    public Category cat;
    public int width;
    public int height;
    public int x;
    public int y;
    public int dragX;
    public int dragY;
    public boolean dragging;
    public boolean open;
    public Timer renderTimer;
    
    public Frame(final Category cat, final int x, final int y) {
        this.width = 114;
        this.height = 13;
        this.dragX = 0;
        this.dragY = 0;
        this.dragging = false;
        this.open = true;
        this.buttons = new ArrayList<Button>();
        this.x = x;
        this.y = y;
        this.cat = cat;
        (this.renderTimer = new Timer()).reset();
        int offset = this.height;
        for (final Module mod : Kisman.instance.moduleManager.modules) {
            if (mod.getCategory().equals((Object)cat)) {
                this.buttons.add(new Button(this.x, this.y, offset, this.width, this.height, this, mod));
                offset += this.height;
            }
        }
    }
    
    public void renderComponent() {
        Gui.drawRect(this.x + 3, this.y + 3, this.x + this.width + 3, this.y + this.height - 3, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x + 3, this.y, this.x + this.width + 3, this.y + this.height, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x + 2, this.y + 2, this.x + this.width + 2, this.y + this.height - 2, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x + 2, this.y, this.x + this.width + 2, this.y + this.height, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x + 1, this.y + 1, this.x + this.width + 1, this.y + this.height - 1, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x + 1, this.y, this.x + this.width + 1, this.y + this.height, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x - 3, this.y - 8, this.x + this.width + 3, this.y + this.height - 3, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 3, this.y, this.x + this.width + 3, this.y + this.height, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 2, this.y - 7, this.x + this.width + 2, this.y + this.height - 2, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 2, this.y, this.x + this.width + 2, this.y + this.height, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 1, this.y - 6, this.x + this.width + 1, this.y + this.height - 1, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x - 1, this.y, this.x + this.width + 1, this.y + this.height, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x, this.y - 5, this.x + this.width, this.y + this.height, ColorUtils.astolfoColors(100, 100));
        Gui.drawRect(this.x - 3, this.y - 1, this.x + this.width + 3, this.y + this.height + 3, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 2, this.y - 2, this.x + this.width + 2, this.y + this.height + 2, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 1, this.y - 3, this.x + this.width + 1, this.y + this.height + 1, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x, this.y - 4, this.x + this.width, this.y + this.height, ColorUtils.getColor(34, 34, 40));
        final String str = this.cat.name() + ((Config.instance.guiRenderSIze.getValBoolean() && !this.buttons.isEmpty()) ? (" [" + this.buttons.size() + "]") : "");
        CustomFontUtil.drawCenteredStringWithShadow(str, (float)(this.x + this.width / 2), (float)(this.y + (this.height - CustomFontUtil.getFontHeight()) / 2), this.open ? ColorUtils.astolfoColors(100, 100) : -1);
        if (this.open && !this.buttons.isEmpty()) {
            for (final Button button : this.buttons) {
                button.renderComponent();
            }
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        if (this.dragging) {
            this.x = mouseX - this.dragX;
            this.y = mouseY - this.dragY;
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y && y <= this.y + this.height;
    }
    
    public void refresh() {
        int off = this.height;
        for (final Button b : this.buttons) {
            b.offset = off;
            off += this.height;
            if (b.open) {
                for (final Component comp : b.comp) {
                    comp.newOff(off);
                    comp.renderComponent();
                    off += this.height;
                }
                off += 3;
            }
        }
    }
}
