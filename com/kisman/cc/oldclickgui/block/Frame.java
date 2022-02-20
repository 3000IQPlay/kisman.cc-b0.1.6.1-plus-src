//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.block;

import java.util.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class Frame
{
    public ArrayList<Component> components;
    public String name;
    public int x;
    public int y;
    public int heigth;
    public int width;
    public int barHeigth;
    
    public Frame(final String name, final int x, final int y, final int heigth, final int width) {
        this.components = new ArrayList<Component>();
        this.name = name;
        this.x = x;
        this.y = y;
        this.heigth = heigth;
        this.width = width;
        this.barHeigth = 13;
    }
    
    public void renderFrame(final FontRenderer fr) {
        GuiScreen.drawRect(this.x, this.y, this.x + this.width, this.y + this.heigth, new Color(ClickGui.getRBackground(), ClickGui.getGBackground(), ClickGui.getBBackground(), ClickGui.getABackground()).getRGB());
        fr.drawStringWithShadow(this.name, (float)(this.x + 5), (float)(this.y + (this.heigth - fr.FONT_HEIGHT) / 2), new Color(ClickGui.getRText(), ClickGui.getGText(), ClickGui.getBText(), ClickGui.getAText()).getRGB());
    }
    
    public void updatePosition(final int mouseX, final int mouseY) {
    }
    
    public void mouseCliced(final int mouseX, final int mouseY, final int button) {
    }
}
