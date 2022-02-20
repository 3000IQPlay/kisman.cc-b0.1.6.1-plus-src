//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui.component.settingcomponents.components;

import com.kisman.cc.newclickgui.component.settingcomponents.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.util.customfont.*;
import net.minecraft.client.gui.*;
import java.math.*;

public class Slider extends Component
{
    private int x;
    private int y;
    private int width;
    private int heigth;
    private int offset;
    private int sX;
    private int sY;
    private SettingButton button;
    private Setting set;
    private Module mod;
    private boolean dragging;
    private double renderWidth;
    
    public Slider(final int x, final int y, final int offset, final int width, final int heigth, final SettingButton button, final Setting set, final Module mod) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.heigth = heigth;
        this.offset = offset;
        this.sX = this.x;
        this.sY = this.y + this.offset + 2 + CustomFontUtil.getFontHeight();
        this.button = button;
        this.set = set;
        this.mod = mod;
    }
    
    public void renderComponent() {
        CustomFontUtil.drawStringWithShadow(this.set.getName(), this.x + 1, this.y + this.offset + 1, 3158064);
        GuiScreen.drawRect(this.sX - 1, this.sY, this.sX + (int)this.renderWidth - 1, this.sY + 1, -1);
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        final double diff = Math.min(200, Math.max(0, mouseX - this.x));
        final double min = this.set.getMin();
        final double max = this.set.getMax();
        this.renderWidth = 200.0 * (this.set.getValDouble() - min) / (max - min);
        if (this.dragging) {
            if (diff == 0.0) {
                this.set.setValDouble(this.set.getMin());
            }
            else {
                final double newValue = roundToPlace(diff / 200.0 * (max - min) + min, 2);
                this.set.setValDouble(newValue);
            }
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButtonD(mouseX, mouseY) && button == 0) {
            this.dragging = true;
        }
        if (this.isMouseOnButtonI(mouseX, mouseY) && button == 0) {
            this.dragging = true;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        this.dragging = false;
    }
    
    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
    public boolean isMouseOnButtonD(final int x, final int y) {
        return x > this.x && x < this.x + 101 && y > this.y + this.offset && y < this.y + this.offset + 12;
    }
    
    public boolean isMouseOnButtonI(final int x, final int y) {
        return x > this.x + 100 && x < this.x + 200 && y > this.y && y < this.y + this.offset + this.offset + 12;
    }
}
