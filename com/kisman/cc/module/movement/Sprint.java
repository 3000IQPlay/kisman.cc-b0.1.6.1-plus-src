//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;

public class Sprint extends Module
{
    public static Sprint instance;
    
    public Sprint() {
        super("Sprint", "i like sprinting", Category.MOVEMENT);
        Sprint.instance = this;
    }
    
    public void update() {
        if (Sprint.mc.player != null && Sprint.mc.world != null) {
            Sprint.mc.player.setSprinting(true);
        }
    }
    
    public void onDisable() {
        if (Sprint.mc.player != null && Sprint.mc.world != null) {
            Sprint.mc.player.setSprinting(false);
        }
    }
}
