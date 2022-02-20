//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components.sub;

import com.kisman.cc.oldclickgui.vega.component.*;
import net.minecraft.client.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.vega.component.components.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.*;
import com.kisman.cc.event.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.*;
import net.minecraft.util.*;

public class StringButton extends Component
{
    private Minecraft mc;
    private FontRenderer fr;
    private int modeIndex;
    public Setting set;
    public Button b;
    public int offset;
    public int x;
    public int y;
    private int width;
    private int height;
    private String currentString;
    private String dString;
    private String regex;
    public boolean drag;
    private boolean active;
    
    public StringButton(final Button b, final Setting set, final int offset) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRenderer;
        this.modeIndex = 0;
        this.currentString = "";
        this.regex = "-*[1-9][0-9]*";
        this.drag = false;
        this.active = false;
        this.set = set;
        this.b = b;
        this.offset = offset;
        this.x = b.parent.x;
        this.y = b.parent.y;
        this.width = b.parent.width;
        this.height = b.parent.height;
        this.dString = set.getdString();
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
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
        CustomFontUtil.drawCenteredStringWithShadow(this.set.getName() + ": " + this.set.getValString(), (float)(this.x + this.width / 2), (float)(this.y + 3 + this.offset + (height - CustomFontUtil.getFontHeight()) / 2), this.drag ? ColorUtils.astolfoColors(100, 100) : -1);
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.x = this.b.parent.x;
        this.y = this.b.parent.y;
        this.width = this.b.parent.width;
        this.height = this.b.parent.height;
    }
    
    public void mouseReleased(final int mouseX, final int mouseY, final int button) {
        this.drag = false;
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.set.getOptions() != null) {
            final int maxIndex = this.set.getOptions().size();
            if (this.modeIndex + 1 >= maxIndex) {
                this.modeIndex = 0;
            }
            else {
                ++this.modeIndex;
            }
            this.set.setValString(this.set.getOptions().get(this.modeIndex));
        }
        if (button == 0 && this.isMouseOnButton(mouseX, mouseY)) {
            this.drag = true;
        }
    }
    
    public void newOff(final int newOff) {
        this.offset = newOff;
    }
    
    public void keyTyped(final char typedChar, final int key) {
        final StringEvent event1 = new StringEvent(this.set, "" + typedChar, Event.Era.PRE, this.active);
        Kisman.EVENT_BUS.post((Object)event1);
        if (event1.isCancelled()) {
            return;
        }
        if (key == 1) {
            return;
        }
        if (28 == key && this.active) {
            this.enterString();
        }
        else if (key == 14 && this.active) {
            if (this.currentString != null) {
                if (!this.currentString.isEmpty()) {
                    this.currentString = this.currentString.substring(0, this.currentString.length() - 1);
                }
            }
            else {
                this.active = false;
            }
        }
        else if (ChatAllowedCharacters.isAllowedCharacter(typedChar) && this.active && !this.set.isOnlyNumbers()) {
            this.setString(this.currentString + typedChar);
            final StringEvent event2 = new StringEvent(this.set, "" + typedChar, Event.Era.POST, this.active);
            Kisman.EVENT_BUS.post((Object)event2);
            if (event2.isCancelled()) {
                this.active = false;
                return;
            }
            if (this.set.isOnlyOneWord() && this.active) {
                this.active = false;
            }
        }
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x > this.b.parent.x && x < this.b.parent.x + 88 && y > this.b.parent.y + this.offset && y < this.b.parent.y + 12 + this.offset;
    }
    
    private void setString(final String newString) {
        this.currentString = newString;
    }
    
    private String removeLastChar(final String str) {
        String output = "";
        if (str != null && str.length() > 0) {
            output = str.substring(0, str.length() - 1);
        }
        return output;
    }
    
    private void enterString() {
        this.active = false;
        if (this.currentString.isEmpty()) {
            this.set.setValString(this.set.getdString());
        }
        else {
            this.set.setValString(this.currentString);
        }
    }
}
