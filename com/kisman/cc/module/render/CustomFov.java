//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;

public class CustomFov extends Module
{
    public CustomFov() {
        super("CustomFov", "customisated your fov", Category.RENDER);
        Kisman.instance.settingsManager.rSetting(new Setting("Fov", this, 30.0, 30.0, 150.0, true));
    }
    
    public void update() {
        CustomFov.mc.gameSettings.fovSetting = (float)Kisman.instance.settingsManager.getSettingByName(this, "Fov").getValInt();
    }
    
    public void onDisable() {
        CustomFov.mc.gameSettings.fovSetting = 110.0f;
    }
}
