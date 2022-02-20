//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;

public class CameraClip extends Module
{
    public CameraClip() {
        super("CameraClip", "CameraClip", Category.RENDER);
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
}
