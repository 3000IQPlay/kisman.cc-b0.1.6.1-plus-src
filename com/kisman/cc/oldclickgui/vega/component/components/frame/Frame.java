//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components.frame;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.vega.component.components.frame.sub.*;
import java.util.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.*;

public class Frame
{
    public ArrayList<Component> buttons;
    public Module mod;
    public int width;
    public int height;
    public int x;
    public int y;
    public int dragX;
    public int dragY;
    public boolean dragging;
    public boolean open;
    
    public Frame(final int x, final int y, final Module mod) {
        this.width = 114;
        this.height = 13;
        this.dragX = 0;
        this.dragY = 0;
        this.dragging = false;
        this.open = true;
        this.buttons = new ArrayList<Component>();
        this.x = x;
        this.y = y;
        this.mod = mod;
        int offset = this.height;
        if (!Kisman.instance.settingsManager.getSettingsByMod(mod).isEmpty()) {
            for (final Setting set : Kisman.instance.settingsManager.getSettingsByMod(mod)) {
                if (set.isCombo()) {
                    this.buttons.add(new ModeButton(this.x, this.y, offset, this.width, this.height, this, set));
                    offset += this.height;
                }
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
        CustomFontUtil.drawCenteredStringWithShadow(this.mod.getName(), (float)(this.x + this.width / 2), (float)(this.y + (this.height - CustomFontUtil.getFontHeight()) / 2), this.open ? ColorUtils.astolfoColors(100, 100) : -1);
        if (this.open && !this.buttons.isEmpty()) {
            for (final Component button : this.buttons) {
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
}
