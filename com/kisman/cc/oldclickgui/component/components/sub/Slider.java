//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import com.kisman.cc.util.*;
import java.math.*;

public class Slider extends Component
{
    private boolean hovered;
    private Setting set;
    public Button button;
    private ColorUtil colorUtil;
    public int offset;
    public int x;
    public int y;
    public int x1;
    public int y1;
    private boolean dragging;
    private double renderWidth;
    
    public Slider(final Setting value, final Button button, final int offset) {
        this.colorUtil = new ColorUtil();
        this.dragging = false;
        this.set = value;
        this.button = button;
        this.x = button.parent.getX();
        this.y = button.parent.getY();
        this.x1 = button.parent.getX() + button.parent.getWidth();
        this.y1 = button.parent.getY() + button.offset;
        this.offset = offset;
    }
    
    public void renderComponent() {
        if (!this.set.isVisible()) {
            return;
        }
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 3, this.button.parent.getY() + this.offset + 12, this.hovered ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
        final int drag = (int)(this.set.getValDouble() / this.set.getMax() * this.button.parent.getWidth());
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + (int)this.renderWidth, this.button.parent.getY() + this.offset + 12, this.hovered ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB() : (ClickGui.isRainbowBackground() ? this.colorUtil.getColor() : new Color(ClickGui.getRBackground(), ClickGui.getGBackground(), ClickGui.getBBackground(), ClickGui.getABackground()).getRGB()));
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.set.getName() + ": " + this.set.getValDouble(), (float)(this.button.parent.getX() * 2 + 15), (float)((this.button.parent.getY() + this.offset + 2) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        GL11.glPopMatrix();
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        if (!this.set.isVisible()) {
            return;
        }
        this.hovered = (this.isMouseOnButtonD(mouseX, mouseY) || this.isMouseOnButtonI(mouseX, mouseY));
        this.y = this.button.parent.getY() + this.offset;
        this.x = this.button.parent.getX();
        final double diff = Math.min(88, Math.max(0, mouseX - this.x));
        final double min = this.set.getMin();
        final double max = this.set.getMax();
        this.renderWidth = 88.0 * (this.set.getValDouble() - min) / (max - min);
        if (this.dragging) {
            if (diff == 0.0) {
                this.set.setValDouble(this.set.getMin());
            }
            else {
                final double newValue = roundToPlace(diff / 88.0 * (max - min) + min, 2);
                this.set.setValDouble(newValue);
            }
        }
    }
    
    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (!this.set.isVisible()) {
            return;
        }
        if (this.isMouseOnButtonD(mouseX, mouseY) && button == 0 && this.button.open) {
            this.dragging = true;
        }
        if (this.isMouseOnButtonI(mouseX, mouseY) && button == 0 && this.button.open) {
            this.dragging = true;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int mouseButton) {
        if (!this.set.isVisible()) {
            return;
        }
        this.dragging = false;
    }
    
    public boolean isMouseOnButtonD(final int x, final int y) {
        return x > this.x && x < this.x + (this.button.parent.getWidth() / 2 + 1) && y > this.y && y < this.y + 12;
    }
    
    public boolean isMouseOnButtonI(final int x, final int y) {
        return x > this.x + this.button.parent.getWidth() / 2 && x < this.x + this.button.parent.getWidth() && y > this.y && y < this.y + 12;
    }
}
