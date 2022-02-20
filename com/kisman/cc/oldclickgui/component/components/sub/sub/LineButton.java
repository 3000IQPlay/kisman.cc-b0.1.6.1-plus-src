//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.oldclickgui.component.components.sub.sub;

import net.minecraft.client.*;

public class LineButton extends SubComponent
{
    int count;
    
    public LineButton() {
        this.count = 0;
    }
    
    @Override
    public void renderComponent() {
        Minecraft.getMinecraft().player.sendChatMessage("SubLine renderer! " + this.count);
        ++this.count;
    }
}
