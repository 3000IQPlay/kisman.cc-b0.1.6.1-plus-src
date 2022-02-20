//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.*;
import com.kisman.cc.util.*;

public class VisibleButton extends Component
{
    private boolean hovered;
    public Button button;
    private ColorUtil colorUtil;
    public int offset;
    public int x;
    public int y;
    private int x1;
    private int y1;
    private Module mod;
    
    public VisibleButton(final Button button, final Module mod, final int offset) {
        this.colorUtil = new ColorUtil();
        this.button = button;
        this.mod = mod;
        this.x = button.parent.getX();
        this.y = button.parent.getY();
        this.x1 = button.parent.getX() + button.parent.getWidth();
        this.y1 = button.parent.getY() + button.offset;
        this.offset = offset;
    }
    
    public void setOff(final int newOff) {
        this.offset = newOff;
    }
    
    public void renderComponent() {
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - 3, this.button.parent.getY() + this.offset + 12, this.hovered ? new Color(ClickGui.getRHoveredModule(), ClickGui.getGHoveredModule(), ClickGui.getBHoveredModule(), ClickGui.getAHoveredModule()).getRGB() : new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
        GL11.glPushMatrix();
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        Minecraft.getMinecraft().fontRenderer.drawStringWithShadow("Visible: " + this.mod.visible, (float)((this.button.parent.getX() + 7) * 2), (float)((this.button.parent.getY() + this.offset + 2) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
        GL11.glPopMatrix();
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.hovered = this.isMouseOnButton(mouseX, mouseY);
        this.y1 = this.button.parent.getY() + this.offset;
        this.x1 = this.button.parent.getX();
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0 && this.button.open) {
            this.mod.visible = !this.mod.visible;
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x > this.x1 && x < this.x1 + 88 && y > this.y1 && y < this.y1 + 12;
    }
}
