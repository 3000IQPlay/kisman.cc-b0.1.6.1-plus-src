//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub.sub;

import com.kisman.cc.settings.*;
import com.kisman.cc.oldclickgui.component.components.*;
import com.kisman.cc.util.*;
import net.minecraft.client.*;
import com.kisman.cc.oldclickgui.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class CheckBox extends SubComponent
{
    private String name;
    private int x;
    private int y;
    private int offset;
    private Setting op;
    private Button button;
    private ColorUtil colorUtil;
    private Minecraft mc;
    private ScaledResolution sr;
    private boolean toggle;
    
    public CheckBox(final Setting option, final Button button, final int offset) {
        this.colorUtil = new ColorUtil();
        this.mc = Minecraft.getMinecraft();
        this.sr = new ScaledResolution(this.mc);
        this.op = option;
        this.toggle = this.op.getValBoolean();
        this.button = button;
        this.offset = offset;
        this.x = this.sr.getScaledWidth() - 44;
        this.y = this.sr.getScaledHeight() - 6;
    }
    
    @Override
    public void renderComponent() {
        Gui.drawRect(this.sr.getScaledWidth() - 44, this.sr.getScaledHeight() - 6 + this.offset, this.sr.getScaledWidth() + 44, this.sr.getScaledHeight() + 7 + this.offset, new Color(ClickGui.getRNoHoveredModule(), ClickGui.getGNoHoveredModule(), ClickGui.getBNoHoveredModule(), ClickGui.getANoHoveredModule()).getRGB());
        Gui.drawRect(this.x + 2, this.y + 2 + this.offset, this.x + 10, this.y + 10 + this.offset, ClickGui.isRainbowBackground() ? this.colorUtil.getColor() : new Color(ClickGui.getRBackground(), ClickGui.getGBackground(), ClickGui.getBBackground(), ClickGui.getABackground()).getRGB());
        if (this.toggle) {
            Gui.drawRect(this.x + 3, this.y + 3 + this.offset, this.x + 9, this.y + 9 + this.offset, -1);
        }
    }
    
    @Override
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.op.setValBoolean(true);
        }
    }
    
    public boolean isMouseOnButton(final int x, final int y) {
        return x < this.x && x > this.x + 88 && y < this.y && y > this.y + 12;
    }
}
