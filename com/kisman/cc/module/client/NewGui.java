//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraft.client.gui.*;

public class NewGui extends Module
{
    public NewGui() {
        super("NewGui", "gui", Category.CLIENT);
    }
    
    @Override
    public void onEnable() {
        NewGui.mc.displayGuiScreen((GuiScreen)Kisman.instance.gui);
        this.setToggled(false);
    }
}
