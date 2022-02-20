//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.vega.component.components.sub.patterns;

import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;

public class DefaultButton
{
    public int offset;
    public int x;
    public int y;
    public int width;
    public int height;
    
    public DefaultButton(final int x, final int y, final int offset, final int width, final int height) {
        this.offset = 0;
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.width = height;
    }
    
    public void update(final int mouseX, final int mouseY) {
    }
    
    public void render() {
        Gui.drawRect(this.x - 3, this.y + 3 + this.offset, this.x + this.width + 3, this.y + this.height + 3 + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 3, this.y + this.offset, this.x + this.width + 3, this.y + this.height + this.offset, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(this.x - 2, this.y + 2 + this.offset, this.x + this.width + 2, this.y + this.height + 2 + this.offset, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 2, this.y + this.offset, this.x + this.width + 2, this.y + this.height + this.offset, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(this.x - 1, this.y + 1 + this.offset, this.x + this.width + 1, this.y + this.height + 1 + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x - 1, this.y + this.offset, this.x + this.width + 1, this.y + this.height + this.offset, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(this.x, this.y + this.offset, this.x + this.width, this.y + this.height + this.offset, ColorUtils.getColor(34, 34, 40));
    }
    
    public void updatePos(final int x, final int y, final int offset) {
        this.x = x;
        this.y = y;
        this.offset = offset;
    }
}
