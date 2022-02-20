//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components;

import com.kisman.cc.module.*;
import com.kisman.cc.hud.hudmodule.*;
import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.sub.*;
import java.util.*;
import com.kisman.cc.hud.hudgui.component.components.sub.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.customfont.*;
import net.minecraft.client.*;
import net.minecraft.util.text.*;

public class Button extends Component
{
    public int x;
    public int y;
    int color;
    public Module mod;
    public HudModule hudMod;
    public boolean hud;
    public Frame parent;
    public ColorUtil colorUtil;
    public int offset;
    public int opY;
    private boolean isHovered;
    public ArrayList<Component> subcomponents;
    private ArrayList<Component> drawBoxHud;
    public boolean open;
    private int height;
    
    public Button(final Module mod, final Frame parent, final int offset) {
        this.colorUtil = new ColorUtil();
        this.hud = false;
        this.mod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.open = false;
        this.height = 12;
        this.opY = offset + 12;
        if (Kisman.instance.settingsManager.getSettingsByMod(mod) != null) {
            for (final Setting s : Kisman.instance.settingsManager.getSettingsByMod(mod)) {
                if (s.isCombo()) {
                    this.subcomponents.add(new ModeButton(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isSlider()) {
                    this.subcomponents.add(new Slider(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isCheck()) {
                    this.subcomponents.add(new Checkbox(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isLine()) {
                    this.subcomponents.add(new Line(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isColorPicker()) {
                    this.subcomponents.add(new ColorPickerButton(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isColorPickerSimple()) {
                    this.subcomponents.add(new ColorPickerSimpleButton(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isString()) {
                    this.subcomponents.add(new StringButton(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isBind()) {
                    this.subcomponents.add(new Keybind(this, s, this.opY));
                    this.opY += 12;
                }
                if (s.isPreview()) {
                    this.subcomponents.add(new PreviewButton(s, this, this.opY));
                    this.opY += 12;
                }
                if (s.isItems()) {
                    this.subcomponents.add(new ItemsButton(this, s, this.opY));
                    this.opY += 12;
                }
                if (s.isExampleColor()) {
                    this.subcomponents.add(new ExampleColorButton(s, this, this.opY));
                    this.opY += 12;
                }
            }
        }
        this.subcomponents.add(new Keybind(this, this.opY));
        this.subcomponents.add(new VisibleButton(this, mod, this.opY));
    }
    
    public Button(final HudModule mod, final Frame parent, final int offset) {
        this.colorUtil = new ColorUtil();
        this.hud = true;
        this.hudMod = mod;
        this.parent = parent;
        this.offset = offset;
        this.subcomponents = new ArrayList<Component>();
        this.drawBoxHud = new ArrayList<Component>();
        this.open = false;
        this.height = 12;
        int opY = offset + 12;
        if (Kisman.instance.settingsManager.getSettingsByHudMod(this.hudMod) != null) {
            for (final Setting s : Kisman.instance.settingsManager.getSettingsByHudMod(this.hudMod)) {
                if (s.isCheckHud()) {
                    this.subcomponents.add(new Checkbox(s, this, opY));
                    opY += 12;
                }
                if (s.isColorPickerHud()) {
                    this.subcomponents.add(new ColorPickerButton(s, this, opY));
                    opY += 12;
                }
                if (s.isDrawHud()) {
                    this.drawBoxHud.add((Component)new DrawHudButton(s, this));
                }
            }
        }
    }
    
    public int getOff() {
        return this.offset;
    }
    
    public void setOff(final int newOff) {
        this.offset = newOff;
        int opY = this.offset + 12;
        for (final Component comp : this.subcomponents) {
            comp.setOff(opY);
            opY += 12;
        }
    }
    
    public void setOff(final int newOff, final Component comp) {
        boolean finded = false;
        int opY = 12;
        for (final Component comp2 : this.subcomponents) {
            if (comp2 == comp) {
                finded = true;
                opY += 12;
            }
            else {
                if (finded) {
                    comp2.setOff(opY + newOff);
                }
                opY += 12;
            }
        }
    }
    
    public void renderComponent() {
        Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + 12 + this.offset, this.hud ? (this.isHovered ? (this.hudMod.isToggled() ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).darker().getRGB() : new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB()) : (this.hudMod.isToggled() ? new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).darker().getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB())) : (this.isHovered ? (this.mod.isToggled() ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).darker().getRGB() : new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB()) : (this.mod.isToggled() ? new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).darker().getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB())));
        if (ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            if (Config.instance.guiGlow.getValBoolean()) {
                final int offset = Config.instance.glowOffset.getValInt();
                Render2DUtil.drawGlow(this.parent.getX() - offset, this.parent.getY() + this.offset - offset, this.parent.getX() + 1 + offset, this.parent.getY() + this.offset + 12 + offset, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
                Render2DUtil.drawGlow(this.parent.getX() + this.parent.getWidth() - 1 - offset, this.parent.getY() + this.offset - offset, this.parent.getX() + this.parent.getWidth() + offset, this.parent.getY() + this.offset + 12 + offset, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
            Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset, this.parent.getX() + 1, this.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            Gui.drawRect(this.parent.getX() + this.parent.getWidth() - 1, this.parent.getY() + this.offset, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            if (this.parent.components.size() == this.parent.components.indexOf(this)) {
                Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset - 1, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            }
        }
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        if (Config.instance.guiGlow.getValBoolean()) {
            final int offset = Config.instance.glowOffset.getValInt();
            Label_0968: {
                if (this.hud) {
                    if (!this.hudMod.isToggled()) {
                        break Label_0968;
                    }
                }
                else if (!this.mod.isToggled()) {
                    break Label_0968;
                }
                Render2DUtil.drawGlow((this.parent.getX() + 2) * 2 - offset, (this.parent.getY() + this.offset + 2) * 2 + 4 - offset, (this.parent.getX() + 2) * 2 + CustomFontUtil.getStringWidth(this.hud ? this.hudMod.getName() : this.mod.getName()) + offset, (this.parent.getY() + this.offset + 2) * 2 + 4 + offset, new Color(ClickGui.getAActiveText(), ClickGui.getGActiveText(), ClickGui.getBActiveText(), ClickGui.getAActiveText()).getRGB());
            }
            if (this.open) {
                Render2DUtil.drawGlow((this.parent.getX() + this.parent.getWidth() - 10) * 2 - offset, (this.parent.getY() + this.offset + 2) * 2 + 4 - offset, (this.parent.getX() + this.parent.getWidth() - 10) * 2 + CustomFontUtil.getStringWidth(this.open ? "-" : "+") + offset, (this.parent.getY() + this.offset + 2) * 2 + 4 + offset, new Color(ClickGui.getAActiveText(), ClickGui.getGActiveText(), ClickGui.getBActiveText(), ClickGui.getAActiveText()).getRGB());
            }
        }
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow((this.hud ? (this.hudMod.isToggled() ? TextFormatting.BOLD : "") : (this.mod.isToggled() ? TextFormatting.BOLD : "")) + (this.hud ? this.hudMod.getName() : this.mod.getName()), (float)((this.parent.getX() + 2) * 2), (float)((this.parent.getY() + this.offset + 2) * 2 + 4), this.hud ? (this.hudMod.isToggled() ? new Color(ClickGui.getAActiveText(), ClickGui.getGActiveText(), ClickGui.getBActiveText(), ClickGui.getAActiveText()).getRGB() : new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB()) : (this.mod.isToggled() ? new Color(ClickGui.getAActiveText(), ClickGui.getGActiveText(), ClickGui.getBActiveText(), ClickGui.getAActiveText()).getRGB() : new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB()));
        if (this.subcomponents.size() > 2) {
            CustomFontUtil.drawString(this.open ? "-" : "+", (this.parent.getX() + this.parent.getWidth() - 10) * 2, (this.parent.getY() + this.offset + 2) * 2 + 4, this.open ? new Color(ClickGui.getAActiveText(), ClickGui.getGActiveText(), ClickGui.getBActiveText(), ClickGui.getAActiveText()).getRGB() : new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        }
        GL11.glPopMatrix();
        if (this.open && this.parent.components.indexOf(this) == this.parent.components.size() && ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.parent.getX(), this.parent.getY() + this.offset + 11, this.parent.getX() + this.parent.getWidth(), this.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
        if (this.open && !this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.renderComponent();
            }
        }
        if (this.hud && this.hudMod.isToggled() && !this.drawBoxHud.isEmpty()) {
            for (final Component comp : this.drawBoxHud) {
                comp.renderComponent();
            }
        }
    }
    
    public int getHeight() {
        if (this.open) {
            int subHeigth = 0;
            for (final Component comp : this.subcomponents) {
                subHeigth += comp.getHeight();
            }
            return 12 * (this.subcomponents.size() + 1) + subHeigth;
        }
        return 12;
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.isHovered = this.isMouseOnButton(mouseX, mouseY);
        if (!this.subcomponents.isEmpty()) {
            for (final Component comp : this.subcomponents) {
                comp.updateComponent(mouseX, mouseY);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            if (this.hud) {
                this.hudMod.toggle();
            }
            else {
                this.mod.toggle();
            }
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = !this.open;
            this.parent.refreshPosition();
        }
        for (final Component comp : this.subcomponents) {
            comp.mouseClicked(mouseX, mouseY, button);
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        for (final Component comp : this.subcomponents) {
            comp.mouseReleased(mouseX, mouseY, mouseButton);
        }
    }
    
    public void keyTyped(final char typedChar, final int key) {
        for (final Component comp : this.subcomponents) {
            comp.keyTyped(typedChar, key);
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.parent.getX() && x < this.parent.getX() + this.parent.getWidth() && y > this.parent.getY() + this.offset && y < this.parent.getY() + 12 + this.offset;
    }
    
    private void setColor(final int color) {
        this.color = color;
    }
}
