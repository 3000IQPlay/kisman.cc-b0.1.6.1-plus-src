//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;

public class NoJumpDelay extends Module
{
    public NoJumpDelay() {
        super("NoJumpDelay", "disable jump", Category.MOVEMENT);
    }
    
    public void update() {
        if (NoJumpDelay.mc.player == null && NoJumpDelay.mc.world == null) {
            return;
        }
        NoJumpDelay.mc.player.jumpTicks = 0;
        NoJumpDelay.mc.player.nextStepDistance = 0;
    }
}
