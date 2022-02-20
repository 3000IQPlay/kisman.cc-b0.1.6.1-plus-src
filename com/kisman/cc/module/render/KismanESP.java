//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;

public class KismanESP extends Module
{
    public KismanESP() {
        super("KismanESP", "3", Category.RENDER);
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
    
    public void onEnable() {
        if (Kisman.instance.moduleManager.getModule("Charms").isToggled()) {
            Kisman.instance.moduleManager.getModule("Charms").setToggled(false);
        }
    }
}
