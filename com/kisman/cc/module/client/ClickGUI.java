//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.client.gui.*;

public class ClickGUI extends Module
{
    public static ClickGUI instance;
    
    public ClickGUI() {
        super("ClickGUI", "ClickGUI", Category.CLIENT);
        this.setKey(22);
        ClickGUI.instance = this;
        Kisman.instance.settingsManager.rSetting(new Setting("TestButton", this, false));
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        ClickGUI.mc.displayGuiScreen((GuiScreen)Kisman.instance.clickGui);
        this.setToggled(false);
    }
}
