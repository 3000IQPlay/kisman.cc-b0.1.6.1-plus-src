//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.settings.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import com.kisman.cc.oldclickgui.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.*;

public class ColorPickerButton extends Component
{
    public Button button;
    private Setting set;
    private ColorUtil colorUtil;
    ColorPicker colorPicker;
    float alpha;
    boolean rainbow;
    public boolean open;
    public int x;
    public int y;
    public int offset;
    private int r;
    private int g;
    private int b;
    
    public ColorPickerButton(final Setting set, final Button button, final int offset) {
        this.colorUtil = new ColorUtil();
        this.colorPicker = new ColorPicker();
        this.alpha = this.colorPicker.getColor(3);
        this.rainbow = this.colorPicker.isRainbowState();
        this.open = false;
        this.button = button;
        this.set = set;
        this.x = button.parent.getX();
        this.y = button.parent.getY();
        this.offset = offset;
        this.colorPicker.setColor(set.getColorHSB());
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
    }
    
    public void renderComponent() {
        if (!this.set.isVisible()) {
            return;
        }
        Gui.drawRect(this.button.parent.getX() + 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - 3, this.button.parent.getY() + this.offset + 12, this.alpha(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3)));
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft().fontRenderer.drawString(this.set.getTitle(), (this.button.parent.getX() + 4) * 2, (this.button.parent.getY() + this.offset + 2) * 2 + 4, new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        Minecraft.getMinecraft().fontRenderer.drawString(this.open ? "+" : "-", (this.button.parent.getX() + this.button.parent.getWidth() - 10) * 2, (this.button.parent.getY() + this.offset + 2) * 2 + 4, new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        GL11.glPopMatrix();
        if (this.open) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)this.colorPicker);
            this.open = false;
        }
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        if (!this.set.isVisible()) {
            return;
        }
        this.colorPicker.syns = this.set.isSyns();
        this.set.setColor(this.alpha(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3)));
        if (!this.colorPicker.syns) {
            this.set.setR(this.colour(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3), 1));
            this.set.setG(this.colour(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3), 2));
            this.set.setB(this.colour(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3), 3));
            this.set.setA(this.colour(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3), 4));
            this.set.setColour(this.getColour(new Color(Color.HSBtoRGB(this.colorPicker.getColor(0), this.colorPicker.getColor(1), this.colorPicker.getColor(2))), this.colorPicker.getColor(3)));
        }
        else {
            this.set.setR(ColorModule.instance.synsColor.getR());
            this.set.setG(ColorModule.instance.synsColor.getG());
            this.set.setB(ColorModule.instance.synsColor.getB());
            this.set.setA(ColorModule.instance.synsColor.getA());
            this.set.setColor(ColorModule.instance.synsColor.getColorHSB());
        }
        this.set.setColour(new Colour(this.set.getR(), this.set.getG(), this.set.getB(), this.set.getA()));
        this.set.setSyns(this.colorPicker.syns);
        this.set.setRainbow(this.colorPicker.isRainbowState());
        this.set.setColorPicker(this.colorPicker);
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (!this.set.isVisible()) {
            return;
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 1) {
            this.open = true;
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.button.parent.getX() + 2 && x < this.button.parent.getX() + this.button.parent.getWidth() && y > this.button.parent.getY() + this.offset && y < this.button.parent.getY() + 12 + this.offset;
    }
    
    public int getR(final Color color, final float alpha) {
        return color.getRed();
    }
    
    public int getG(final Color color, final float alpha) {
        return color.getGreen();
    }
    
    public int getB(final Color color, final float alpha) {
        return color.getBlue();
    }
    
    final int alpha(final Color color, final float alpha) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        return new Color(red, green, blue, alpha).getRGB();
    }
    
    final int colour(final Color color, final float alpha, final int index) {
        final float red = color.getRed() / 255.0f;
        final float green = color.getGreen() / 255.0f;
        final float blue = color.getBlue() / 255.0f;
        if (index == 1) {
            return new Color(red, green, blue, alpha).getRed();
        }
        if (index == 2) {
            return new Color(red, green, blue, alpha).getGreen();
        }
        if (index == 3) {
            return new Color(red, green, blue, alpha).getBlue();
        }
        if (index == 4) {
            return new Color(red, green, blue, alpha).getAlpha();
        }
        return 5;
    }
    
    final Colour getColour(final Color color, final float alpha) {
        final int alpha1eee = (int)(alpha * 255.0f);
        return new Colour(color.getRed(), color.getGreen(), color.getGreen(), alpha1eee);
    }
}
