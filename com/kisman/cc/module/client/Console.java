//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.client.gui.*;

public class Console extends Module
{
    public Console() {
        super("Console", "da", Category.CLIENT);
        Kisman.instance.settingsManager.rSetting(new Setting("CmdPrompt", this, false));
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        Console.mc.displayGuiScreen((GuiScreen)Kisman.instance.guiConsole);
        this.setToggled(false);
    }
}
