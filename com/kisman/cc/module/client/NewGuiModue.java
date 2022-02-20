//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraft.client.gui.*;

public class NewGuiModue extends Module
{
    public NewGuiModue() {
        super("NewGui", "54", Category.CLIENT);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        NewGuiModue.mc.displayGuiScreen((GuiScreen)Kisman.instance.newGui);
        this.setToggled(false);
    }
}
