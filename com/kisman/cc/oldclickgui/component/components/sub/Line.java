//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub;

import com.kisman.cc.oldclickgui.component.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.*;

public class Line extends Component
{
    private boolean cat;
    public Button button;
    private Category parent;
    private Setting line;
    private ColorUtil colorUtil;
    public int offset;
    private int cOffset;
    public int x;
    public int y;
    
    public Line(final Setting line, final Button button, final int offset) {
        this.colorUtil = new ColorUtil();
        this.button = button;
        this.line = line;
        this.cat = false;
        this.offset = offset;
        this.x = button.parent.getX();
        this.y = button.parent.getY();
    }
    
    public Line(final Setting line, final int offset, final int cOffset, final Category parent) {
        this.colorUtil = new ColorUtil();
        this.line = line;
        this.cat = true;
        this.parent = parent;
        this.offset = offset;
        this.cOffset = cOffset;
    }
    
    public void setSubOff(final int offset) {
    }
    
    public void setOff(final int offset) {
        this.offset = offset;
    }
    
    public void renderComponent() {
        if (!this.cat) {
            Gui.drawRect(this.button.parent.getX() + (this.cat ? 4 : 3), this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() * 1 - (this.cat ? 4 : 3), this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
            Gui.drawRect(this.button.parent.getX() + (this.cat ? 4 : 3), this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 5 + 5, this.button.parent.getX() + 7 + this.button.parent.getWidth() - 7 - (this.cat ? 4 : 3), this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4 + 5, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.line.getTitle(), (float)((this.button.parent.getX() + 4) * 2), (float)((this.button.parent.getY() + this.offset) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
            GL11.glPopMatrix();
        }
        else {
            System.out.println("test");
            Gui.drawRect(this.parent.x + (this.cat ? 4 : 3), this.parent.y + this.offset + this.cOffset, this.parent.x + 88 - (this.cat ? 4 : 3), this.parent.y + this.offset + this.cOffset + 12, new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
            Gui.drawRect(this.parent.x + (this.cat ? 4 : 3), this.parent.y + this.offset + this.cOffset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 5 + 5, this.parent.x + 7 + 88 - 7 - (this.cat ? 4 : 3), this.button.parent.getY() + this.offset + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT - 4 + 5, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
            GL11.glPushMatrix();
            GL11.glScalef(0.5f, 0.5f, 0.5f);
            Minecraft.getMinecraft().fontRenderer.drawStringWithShadow(this.line.getTitle(), (float)((this.parent.x + 4) * 2), (float)((this.parent.y + this.offset) * 2 + 5), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
            GL11.glPopMatrix();
        }
        Gui.drawRect(this.button.parent.getX() + 2, this.button.parent.getY() + this.offset, this.button.parent.getX() + 3, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        if (ClickGui.getSetLineMode() == LineMode.SETTINGONLYSET || ClickGui.getSetLineMode() == LineMode.SETTINGALL) {
            Gui.drawRect(this.button.parent.getX() + 88 - 3, this.button.parent.getY() + this.offset, this.button.parent.getX() + this.button.parent.getWidth() - 2, this.button.parent.getY() + this.offset + 12, new Color(ClickGui.getRLine(), ClickGui.getGLine(), ClickGui.getBLine(), ClickGui.getALine()).getRGB());
        }
    }
}
