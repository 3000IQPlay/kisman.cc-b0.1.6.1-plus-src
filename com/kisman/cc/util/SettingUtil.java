//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;

public class SettingUtil
{
    public static Module parent;
    
    public static void ColorSetting(final Module parent, final String name) {
        SettingUtil.parent = parent;
        Kisman.instance.settingsManager.rSetting(new Setting(name + "R", parent, 255.0, 0.0, 255.0, true));
        Kisman.instance.settingsManager.rSetting(new Setting(name + "G", parent, 255.0, 0.0, 255.0, true));
        Kisman.instance.settingsManager.rSetting(new Setting(name + "B", parent, 255.0, 0.0, 255.0, true));
        Kisman.instance.settingsManager.rSetting(new Setting(name + "A", parent, 255.0, 0.0, 255.0, true));
    }
}
