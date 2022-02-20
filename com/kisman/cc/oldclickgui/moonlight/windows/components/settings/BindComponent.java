//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.moonlight.windows.components.settings;

import com.kisman.cc.oldclickgui.moonlight.windows.components.api.*;
import com.kisman.cc.module.*;
import net.minecraft.client.gui.*;
import org.lwjgl.input.*;
import com.kisman.cc.util.customfont.*;

public final class BindComponent extends SettingComponent
{
    private boolean listening;
    private Module mod;
    
    public BindComponent(final Module mod, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.mod = mod;
    }
    
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 0) {
            this.listening = !this.listening;
        }
    }
    
    public void release(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public void draw(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.isInside(mouseX, mouseY)) {
            Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, 553648127);
        }
        final String text = this.listening ? "Press a key..." : ("Keybind [" + Keyboard.getKeyName(this.mod.getKey()) + "]");
        CustomFontUtil.drawStringWithShadow(text, this.x + 2.0f, this.y + this.height / 2.0f - CustomFontUtil.getFontHeight() / 2.0f - 1.0f, -1);
    }
    
    public void typed(final char keyChar, final int keyCode) {
        if (this.listening) {
            if (keyCode == 1) {
                this.mod.setKey(0);
            }
            else {
                this.mod.setKey(keyCode);
            }
            this.listening = false;
        }
    }
    
    public boolean isVisible() {
        return true;
    }
}
