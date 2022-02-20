//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.moonlight.windows.components.settings;

import com.kisman.cc.oldclickgui.moonlight.windows.components.api.*;
import com.kisman.cc.settings.*;
import net.minecraft.client.gui.*;
import com.mojang.realmsclient.gui.*;
import com.kisman.cc.util.customfont.*;

public final class BoolComponent extends SettingComponent
{
    private final Setting set;
    
    public BoolComponent(final Setting set, final int x, final int y, final int width, final int height) {
        super(x, y, width, height);
        this.set = set;
    }
    
    public void click(final int mouseX, final int mouseY, final int mouseButton) {
        if (this.isInside(mouseX, mouseY) && mouseButton == 0) {
            this.set.setValBoolean(!this.set.getValBoolean());
        }
    }
    
    public void release(final int mouseX, final int mouseY, final int mouseButton) {
    }
    
    public void draw(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.isInside(mouseX, mouseY)) {
            Gui.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, 553648127);
        }
        final StringBuilder sb = new StringBuilder(this.set.getName() + ":");
        if (this.set.getValBoolean()) {
            sb.append(ChatFormatting.GREEN + " [True]");
        }
        else {
            sb.append(ChatFormatting.RED + " [False]");
        }
        CustomFontUtil.drawStringWithShadow(sb.toString(), this.x + 2.0f, this.y + this.height / 2.0f - CustomFontUtil.getFontHeight() / 2.0f - 1.0f, -1);
    }
    
    public void typed(final char keyChar, final int keyCode) {
    }
    
    public boolean isVisible() {
        return true;
    }
}
