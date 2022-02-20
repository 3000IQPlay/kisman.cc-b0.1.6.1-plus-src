//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components.sub;

import com.kisman.cc.oldclickgui.vega.component.*;
import com.kisman.cc.oldclickgui.vega.component.components.*;
import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.*;
import java.math.*;

public class Slider extends Component
{
    public Button b;
    public Setting s;
    public int offset;
    private int x;
    private int y;
    private int width;
    private int height;
    private double renderWidth;
    private boolean drag;
    private boolean hover;
    
    public Slider(final Button b, final Setting s, final int offset) {
        this.drag = false;
        this.hover = false;
        this.b = b;
        this.s = s;
        this.offset = offset;
        this.x = b.parent.x;
        this.y = b.parent.y;
        this.width = b.parent.width;
        this.height = b.parent.height;
    }
    
    public void renderComponent() {
        final int height = this.height - 1;
        Gui.drawRect(this.x - 3, this.y + 3 + this.offset, (int)(this.x + (double)this.width + 3.0), this.y + this.height + 3 + this.offset, ColorUtils.getColor(40, 40, 50));
        Gui.drawRect(this.x - 2, this.y + 4 + this.offset, (int)(this.x + (double)this.width + 2.0), this.y + this.height + 1 + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x - 1, this.y + 5 + this.offset, (int)(this.x + (double)this.width + 1.0), this.y + this.height + this.offset, ColorUtils.getColor(34, 34, 40));
        Gui.drawRect(this.x - 1, this.y + 5 + this.offset, (int)(this.x + this.renderWidth + 1.0), this.y + this.height + this.offset, ColorUtils.getColor(24, 24, 30));
        Gui.drawRect(this.x, this.y + 6 + this.offset, (int)(this.x + 3.0 + this.renderWidth - 3.0), this.y + this.height - 1 + this.offset, ColorUtils.getColor(65, 65, 80));
        Gui.drawRect(this.x, this.y + 7 + this.offset, (int)(this.x + 3.0 + this.renderWidth - 3.0), this.y + this.height - 2 + this.offset, ColorUtils.getColor(80, 80, 95));
        Gui.drawRect(this.x, this.y + 8 + this.offset, (int)(this.x + 3.0 + this.renderWidth - 3.0), this.y + this.height - 3 + this.offset, ColorUtils.getColor(95, 95, 115));
        CustomFontUtil.drawCenteredStringWithShadow(this.s.getName() + ": " + this.s.getValDouble(), (float)(this.x + this.width / 2), (float)(this.y + 3 + this.offset + (height - CustomFontUtil.getFontHeight()) / 2), this.drag ? ColorUtils.astolfoColors(100, 100) : -1);
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.x = this.b.parent.x;
        this.y = this.b.parent.y;
        this.hover = this.isMouseOnButton(mouseX, mouseY);
        final double diff = Math.min(88, Math.max(0, mouseX - this.x));
        final double min = this.s.getMin();
        final double max = this.s.getMax();
        this.renderWidth = 88.0 * (this.s.getValDouble() - min) / (max - min);
        if (this.drag) {
            System.out.println(diff);
            if (diff == 0.0) {
                this.s.setValDouble(this.s.getMin());
            }
            else {
                final double newValue = roundToPlace(diff / 88.0 * (max - min) + min, 2);
                this.s.setValDouble(newValue);
            }
        }
    }
    
    public void newOff(final int newOff) {
        this.offset = newOff;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (button == 0 && this.isMouseOnButton(mouseX, mouseY)) {
            this.drag = true;
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        this.drag = false;
    }
    
    public boolean isMouseOnButtonD(final int x, final int y) {
        return x > this.x && x < this.x + (this.b.parent.width / 2 + 1) && y > this.y && y < this.y + 12;
    }
    
    public boolean isMouseOnButtonI(final int x, final int y) {
        return x > this.x + this.b.parent.width / 2 && x < this.x + this.b.parent.width && y > this.y && y < this.y + 12;
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + this.width && y > this.y + this.offset && y < this.y + this.height + this.offset;
    }
    
    private static double roundToPlace(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
