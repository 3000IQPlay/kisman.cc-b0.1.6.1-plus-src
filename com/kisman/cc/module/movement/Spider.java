//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;

public class Spider extends Module
{
    public Spider() {
        super("Spider", "HackCategory.PLAYER", Category.MOVEMENT);
    }
    
    public void update() {
        if (Spider.mc.world != null && Spider.mc.player != null && !Spider.mc.player.isOnLadder() && Spider.mc.player.collidedHorizontally && Spider.mc.player.motionY < 0.2) {
            Spider.mc.player.motionY = 0.2;
        }
    }
}
