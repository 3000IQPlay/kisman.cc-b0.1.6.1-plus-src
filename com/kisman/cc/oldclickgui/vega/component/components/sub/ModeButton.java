//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components.sub;

import com.kisman.cc.oldclickgui.vega.component.*;
import com.kisman.cc.oldclickgui.vega.component.components.*;
import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.*;

public class ModeButton extends Component
{
    public Button b;
    public Setting set;
    public int offset;
    public boolean drag;
    private int x;
    private int y;
    private int width;
    private int height;
    
    public ModeButton(final Button b, final Setting set, final int offset) {
        this.drag = false;
        this.b = b;
        this.set = set;
        this.offset = offset;
        this.x = b.parent.x;
        this.y = b.parent.y;
        this.width = b.parent.width;
        this.height = b.parent.height;
    }
    
    public void renderComponent() {
        final int height = this.height - 1;
        Gui.drawRect(this.x - 3, this.y + 3 + this.offset, this.x + this.width + 3, this.y + this.height + 3 + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 3, this.y + this.offset, this.x + this.width + 3, this.y + this.height + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 2, this.y + 2 + this.offset, this.x + this.width + 2, this.y + this.height + 2 + this.offset, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 2, this.y + this.offset, this.x + this.width + 2, this.y + this.height + this.offset, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 1, this.y + 1 + this.offset, this.x + this.width + 1, this.y + this.height + 1 + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x - 1, this.y + this.offset, this.x + this.width + 1, this.y + this.height + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x, this.y + this.offset, this.x + this.width, this.y + this.height + this.offset, ColorUtils.getColor(34, 34, 40));
        CustomFontUtil.drawCenteredStringWithShadow(this.set.getName() + ": " + this.set.getValBoolean(), (float)(this.x + this.width / 2), (float)(this.y + 3 + this.offset + (height - CustomFontUtil.getFontHeight()) / 2), this.drag ? ColorUtils.astolfoColors(100, 100) : -1);
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.x = this.b.parent.x;
        this.y = this.b.parent.y;
        this.width = this.b.parent.width;
        this.height = this.b.parent.height;
    }
    
    public void newOff(final int newOff) {
        this.offset = newOff;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (button == 0 && this.isMouseOnButton(mouseX, mouseY)) {
            this.set.setValBoolean(!this.set.getValBoolean());
        }
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        this.drag = false;
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x > this.x && x < this.x + this.width && y > this.y + this.offset && y < this.y + this.height + this.offset;
    }
}
