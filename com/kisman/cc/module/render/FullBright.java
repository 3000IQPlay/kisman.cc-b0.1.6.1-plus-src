//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.client.*;

public class FullBright extends Module
{
    public FullBright() {
        super("FullBright", "Gamma setting", Category.RENDER);
        Kisman.instance.settingsManager.rSetting(new Setting("Gamma", this, 1.0, 1.0, 100.0, true));
    }
    
    public void update() {
        final int gamma = (int)Kisman.instance.settingsManager.getSettingByName(this, "Gamma").getValDouble();
        Minecraft.getMinecraft().gameSettings.gammaSetting = (float)gamma;
    }
    
    public void onDisable() {
        Minecraft.getMinecraft().gameSettings.gammaSetting = 1.0f;
    }
}
