//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;

public class Step extends Module
{
    public static Step instance;
    
    public Step() {
        super("Step", "setting your step", Category.MOVEMENT);
        Step.instance = this;
        Kisman.instance.settingsManager.rSetting(new Setting("Heigth", this, 0.5, 0.5, 2.5, false));
    }
    
    public void update() {
        if (Step.mc.player == null && Step.mc.world == null) {
            return;
        }
        final float height = (float)Kisman.instance.settingsManager.getSettingByName(this, "Heigth").getValDouble();
        Step.mc.player.stepHeight = height;
    }
    
    public void onDisable() {
        if (Step.mc.player != null && Step.mc.world != null) {
            Step.mc.player.stepHeight = 0.5f;
        }
    }
}
