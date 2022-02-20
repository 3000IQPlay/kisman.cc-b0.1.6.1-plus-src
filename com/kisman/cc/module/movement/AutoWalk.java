//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;

public class AutoWalk extends Module
{
    public AutoWalk() {
        super("AutoWalk", "auto walking", Category.MOVEMENT);
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
    
    public void onDisable() {
        if (AutoWalk.mc.player == null && AutoWalk.mc.world == null) {
            return;
        }
        AutoWalk.mc.gameSettings.keyBindForward.pressed = false;
    }
    
    public void update() {
        if (AutoWalk.mc.player == null && AutoWalk.mc.world == null) {
            return;
        }
        AutoWalk.mc.gameSettings.keyBindForward.pressed = true;
    }
}
