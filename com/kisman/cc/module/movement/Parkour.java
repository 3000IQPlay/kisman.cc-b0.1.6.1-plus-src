//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import net.minecraft.entity.*;

public class Parkour extends Module
{
    public Parkour() {
        super("Parkour", "555", Category.MOVEMENT);
    }
    
    public void update() {
        if (Parkour.mc.player == null && Parkour.mc.world == null) {
            return;
        }
        if (Parkour.mc.player.onGround && !Parkour.mc.player.isSneaking() && !Parkour.mc.gameSettings.keyBindJump.isPressed() && Parkour.mc.world.getCollisionBoxes((Entity)Parkour.mc.player, Parkour.mc.player.getEntityBoundingBox().offset(0.0, -0.5, 0.0).expand(-1.0E-28, 0.0, -1.0E-29)).isEmpty()) {
            Parkour.mc.player.jump();
        }
    }
}
