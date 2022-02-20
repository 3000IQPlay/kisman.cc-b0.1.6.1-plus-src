//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class Zoom extends Module
{
    public Zoom() {
        super("Zoom", "Zoom", Category.MOVEMENT);
    }
    
    public void update() {
        if (Zoom.mc.player == null && Zoom.mc.world == null) {
            return;
        }
        this.setSpeed(9.9);
        if (Zoom.mc.world.getBlockState(new BlockPos(Zoom.mc.player.posX, Zoom.mc.player.posY + 1.0E-7, Zoom.mc.player.posZ)).getBlock() == Block.getBlockById(9)) {
            Zoom.mc.player.fallDistance = 0.0f;
            Zoom.mc.player.motionX = 0.0;
            Zoom.mc.player.motionY = 0.05999999865889549;
            Zoom.mc.player.jumpMovementFactor = 0.01f;
            Zoom.mc.player.motionZ = 0.0;
        }
    }
    
    public void setSpeed(final double speed) {
        double forward = new MovementInput().moveForward;
        double strafe = new MovementInput().moveStrafe;
        float yaw = Zoom.mc.player.rotationYaw;
        if (forward == 0.0 && strafe == 0.0) {
            Zoom.mc.player.motionX = 0.0;
            Zoom.mc.player.motionZ = 0.0;
        }
        else {
            if (forward != 0.0) {
                if (strafe > 0.0) {
                    yaw += ((forward > 0.0) ? -45 : 45);
                }
                else if (strafe < 0.0) {
                    yaw += ((forward > 0.0) ? 45 : -45);
                }
                strafe = 0.0;
                if (forward > 0.0) {
                    forward = 1.0;
                }
                else if (forward < 0.0) {
                    forward = -1.0;
                }
            }
            Zoom.mc.player.motionX = forward * speed * Math.cos(Math.toRadians(yaw + 90.0f)) + strafe * speed * Math.sin(Math.toRadians(yaw + 90.0f));
            Zoom.mc.player.motionZ = forward * speed * Math.sin(Math.toRadians(yaw + 90.0f)) - strafe * speed * Math.cos(Math.toRadians(yaw + 90.0f));
        }
    }
}
