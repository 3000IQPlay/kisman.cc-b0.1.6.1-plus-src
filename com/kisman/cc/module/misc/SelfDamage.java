//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;

public class SelfDamage extends Module
{
    private final Setting jump;
    private final Setting timer;
    private int jumpCount;
    
    public SelfDamage() {
        super("SelfDamage", "SelfDamage", Category.MISC);
        Kisman.instance.settingsManager.rSetting(this.jump = new Setting("Jumps", this, 3.0, 3.0, 50.0, true));
        Kisman.instance.settingsManager.rSetting(this.timer = new Setting("JumpTimer", this, 3.0, 1.0, 1000.0, true));
    }
    
    @Override
    public void onEnable() {
        this.jumpCount = 0;
    }
    
    @Override
    public void onDisable() {
        SelfDamage.mc.timer.tickLength = 1.0f;
    }
    
    @Override
    public void update() {
        if (SelfDamage.mc.player == null && SelfDamage.mc.world == null) {
            return;
        }
        if (this.jumpCount < this.jump.getValDouble()) {
            SelfDamage.mc.timer.tickLength = (float)this.timer.getValDouble();
            SelfDamage.mc.player.onGround = false;
        }
        if (SelfDamage.mc.player.onGround && this.jumpCount < this.jump.getValDouble()) {
            SelfDamage.mc.player.jump();
            ++this.jumpCount;
        }
    }
}
