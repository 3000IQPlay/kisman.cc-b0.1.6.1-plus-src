//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import net.minecraft.client.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.*;
import com.kisman.cc.event.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.*;
import net.minecraft.util.*;

public class StringButton extends Component
{
    private Minecraft mc;
    private FontRenderer fr;
    public Setting set;
    public Button button;
    public int offset;
    public int x;
    public int y;
    private String currentString;
    private String dString;
    private String regex;
    private boolean active;
    
    public StringButton(final Setting set, final Button button, final int offset) {
        this.mc = Minecraft.getMinecraft();
        this.fr = this.mc.fontRenderer;
        this.currentString = "";
        this.regex = "-*[1-9][0-9]*";
        this.active = false;
        this.set = set;
        this.button = button;
        this.offset = offset;
        this.x = button.parent.getX();
        this.y = button.parent.getY();
        this.dString = set.getdString();
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
    }
    
    public void renderComponent() {
        if (!this.set.isVisible()) {
            return;
        }
        GuiScreen.drawRect(this.button.parent.getX(), this.button.parent.getY() + this.offset, this.button.parent.getX() + 88, this.button.parent.getY() + 12 + this.offset, this.active ? new Color(ClickGui.getRBackground(), ClickGui.getGBackground(), ClickGui.getBBackground(), ClickGui.getABackground()).getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
        if (this.active) {
            this.fr.drawStringWithShadow(this.currentString + "_", (float)(this.button.parent.getX() + 4), (float)(this.button.parent.getY() + this.offset + 1 + (12 - this.fr.FONT_HEIGHT) / 2), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        }
        else if (!this.active) {
            this.fr.drawStringWithShadow(this.currentString.isEmpty() ? this.set.getdString() : this.currentString, (float)(this.button.parent.getX() + 4), (float)(this.button.parent.getY() + this.offset + 1 + (12 - this.fr.FONT_HEIGHT) / 2), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        }
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (!this.set.isVisible()) {
            return;
        }
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.set.isOpening() && !(this.active = !this.active)) {
            this.enterString();
        }
    }
    
    public void keyTyped(final char typedChar, final int key) {
        if (!this.set.isVisible()) {
            return;
        }
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
        return x > this.button.parent.getX() && x < this.button.parent.getX() + 88 && y > this.button.parent.getY() + this.offset && y < this.button.parent.getY() + 12 + this.offset;
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
