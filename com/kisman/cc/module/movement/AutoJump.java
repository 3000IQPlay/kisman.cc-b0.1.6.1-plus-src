//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;

public class AutoJump extends Module
{
    public AutoJump() {
        super("AutoJump", "Automatic jump", Category.MOVEMENT);
    }
    
    public void onDisable() {
        if (AutoJump.mc.player == null && AutoJump.mc.world == null) {
            return;
        }
        AutoJump.mc.gameSettings.keyBindJump.pressed = false;
    }
    
    public void update() {
        if (AutoJump.mc.player == null && AutoJump.mc.world == null) {
            return;
        }
        AutoJump.mc.gameSettings.keyBindJump.pressed = true;
    }
}
