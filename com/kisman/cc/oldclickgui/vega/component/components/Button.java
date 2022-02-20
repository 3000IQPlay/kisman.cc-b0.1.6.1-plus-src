//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components;

import com.kisman.cc.oldclickgui.vega.component.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.vega.component.components.sub.*;
import java.util.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.*;
import com.kisman.cc.util.customfont.*;

public class Button
{
    public ArrayList<Component> comp;
    public int x;
    public int y;
    public Frame parent;
    public Module mod;
    public boolean open;
    public int width;
    public int height;
    public int animation;
    public int offset;
    
    public Button(final int x, final int y, final int offset, final int width, final int height, final Frame parent, final Module mod) {
        this.animation = 0;
        this.offset = 0;
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.mod = mod;
        this.width = width;
        this.height = height;
        this.offset = offset;
        this.comp = new ArrayList<Component>();
        int opY = offset + 12;
        if (mod != null) {
            this.comp.add(new KeyBind(this, opY));
            opY += 12;
            if (Kisman.instance.settingsManager.getSettingsByMod(mod) != null) {
                for (final Setting set : Kisman.instance.settingsManager.getSettingsByMod(mod)) {
                    if (set.isCheck()) {
                        this.comp.add(new ModeButton(this, set, opY));
                        opY += height;
                    }
                    if (set.isSlider()) {
                        this.comp.add(new Slider(this, set, opY));
                        opY += height;
                    }
                    if (set.isCombo()) {
                        this.comp.add(new StringButton(this, set, opY));
                        opY += height;
                    }
                }
            }
        }
    }
    
    public void renderComponent() {
        Gui.drawRect(this.x - 3, this.y + 3 + this.offset, this.x + this.width + 3, this.y + this.height + 3 + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 3, this.y + this.offset, this.x + this.width + 3, this.y + this.height + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 2, this.y + 2 + this.offset, this.x + this.width + 2, this.y + this.height + 2 + this.offset, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 2, this.y + this.offset, this.x + this.width + 2, this.y + this.height + this.offset, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 1, this.y + 1 + this.offset, this.x + this.width + 1, this.y + this.height + 1 + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x - 1, this.y + this.offset, this.x + this.width + 1, this.y + this.height + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x, this.y + this.offset, this.x + this.width, this.y + this.height + this.offset, ColorUtils.getColor(34, 34, 40));
        GL11.glPushMatrix();
        Gui.drawRect(this.x + 3, this.y + this.offset, this.x + this.animation, this.y + this.height + this.offset, ColorUtils.getColor(60, 60, 70));
        Render2DUtil.drawRect(this.x + 2, this.y + 0.5 + this.offset, this.x + this.animation, this.y + this.height + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x, this.y + 1 + this.offset, this.x + this.animation, this.y + this.height + this.offset, ColorUtils.astolfoColors(100, 100));
        GL11.glPopMatrix();
        if (this.mod.isToggled() && this.animation <= this.width - 113) {
            ++this.animation;
        }
        if (!this.mod.isToggled()) {
            --this.animation;
            if (this.animation < 0) {
                this.animation = 0;
            }
        }
        CustomFontUtil.drawStringWithShadow(this.mod.getName(), this.x + 6, this.y + (this.height - CustomFontUtil.getFontHeight()) / 2 + this.offset, this.mod.isToggled() ? ColorUtils.astolfoColors(100, 100) : -1);
        if (Kisman.instance.settingsManager.getSettingsByMod(this.mod) != null && Kisman.instance.settingsManager.getSettingsByMod(this.mod).size() > 2) {
            CustomFontUtil.drawStringWithShadow(this.open ? "<" : "=", this.x + this.width - 8, this.y + (this.height - CustomFontUtil.getFontHeight()) / 2 + this.offset, this.open ? ColorUtils.astolfoColors(100, 100) : -1);
        }
        if (this.open && !this.comp.isEmpty()) {
            for (final Component comp : this.comp) {
                comp.renderComponent();
            }
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.x = this.parent.x;
        this.y = this.parent.y;
        for (final Component comp : this.comp) {
            comp.updateComponent(mouseX, mouseY);
            this.parent.refresh();
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.mod.toggle();
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refresh();
        }
        if (!this.comp.isEmpty()) {
            for (final Component comp : this.comp) {
                comp.mouseClicked(mouseX, mouseY, button);
            }
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        if (!this.comp.isEmpty()) {
            for (final Component comp : this.comp) {
                comp.mouseReleased(mouseX, mouseY, button);
            }
        }
    }
    
    public void keyTyped(final char typedChar, final int key) {
        if (!this.comp.isEmpty()) {
            for (final Component comp : this.comp) {
                comp.keyTyped(typedChar, key);
            }
        }
    }
    
    public void newOff(final int newOff) {
        this.offset = newOff;
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x >= this.x && x <= this.x + this.width && y >= this.y + this.offset && y <= this.y + this.offset + this.height;
    }
}
