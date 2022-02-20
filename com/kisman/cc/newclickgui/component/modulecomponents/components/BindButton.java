//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.newclickgui.component.modulecomponents.components;

import com.kisman.cc.module.*;
import java.awt.*;
import com.kisman.cc.util.customfont.*;
import org.lwjgl.input.*;

public class BindButton
{
    private int x;
    private int y;
    private char chat;
    private int key;
    private boolean listen;
    private int offset;
    private Module parent;
    private String str;
    
    public BindButton(final int x, final int y, final int offset, final Module parent) {
        this.str = "";
        this.x = x;
        this.y = y;
        this.offset = offset;
        this.parent = parent;
    }
    
    public void renderComponent() {
        CustomFontUtil.drawStringWithShadow(this.str, this.x, this.y + this.offset, this.listen ? new Color(6379211).hashCode() : new Color(2105376).hashCode());
    }
    
    public void updateComponent(final int mouseX, final int mouseY) {
        this.str = "[" + (this.listen ? "Press a key..." : Keyboard.getKeyName(this.parent.getKey())) + "]";
    }
    
    public void keyTyped(final char typedChar, final int keyCode) {
        if (this.listen) {
            this.parent.setKey(keyCode);
            this.listen = false;
        }
    }
    
    public void mouseClicked(final int mouseX, final int mouseY, final int button) {
        if (this.isMouseOnButton(mouseX, mouseY) && button == 0) {
            this.listen = !this.listen;
        }
    }
    
    private boolean isMouseOnButton(final int x, final int y) {
        return x > this.x - 1 && x < this.x + CustomFontUtil.getStringWidth(this.str) + 1 && y > this.y + this.offset - 1 && y < this.y + this.offset + CustomFontUtil.getFontHeight() + 1;
    }
}
