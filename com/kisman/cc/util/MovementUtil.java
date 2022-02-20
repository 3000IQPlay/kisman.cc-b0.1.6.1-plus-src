//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class MovementUtil
{
    public static final double WALK_SPEED = 0.221;
    public static Minecraft mc;
    
    public static boolean isBlockAboveHead() {
        final AxisAlignedBB bb = new AxisAlignedBB(MovementUtil.mc.player.posX - 0.3, MovementUtil.mc.player.posY + MovementUtil.mc.player.getEyeHeight(), MovementUtil.mc.player.posZ + 0.3, MovementUtil.mc.player.posX + 0.3, MovementUtil.mc.player.posY + 2.5, MovementUtil.mc.player.posZ - 0.3);
        return !MovementUtil.mc.world.getCollisionBoxes((Entity)MovementUtil.mc.player, bb).isEmpty();
    }
    
    public static void strafe(final float speed) {
        if (!isMoving()) {
            return;
        }
        final double yaw = getDirection();
        MovementUtil.mc.player.motionX = -Math.sin(yaw) * speed;
        MovementUtil.mc.player.motionZ = Math.cos(yaw) * speed;
    }
    
    public static boolean isMoving() {
        return MovementUtil.mc.player != null && (MovementUtil.mc.player.movementInput.moveForward != 0.0f || MovementUtil.mc.player.movementInput.moveStrafe != 0.0f);
    }
    
    public static float getDirection() {
        float var1 = MovementUtil.mc.player.rotationYaw;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            var1 += 180.0f;
        }
        float forward = 1.0f;
        if (MovementUtil.mc.player.moveForward < 0.0f) {
            forward = -0.5f;
        }
        else if (MovementUtil.mc.player.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (MovementUtil.mc.player.moveStrafing > 0.0f) {
            var1 -= 90.0f * forward;
        }
        if (MovementUtil.mc.player.moveStrafing < 0.0f) {
            var1 += 90.0f * forward;
        }
        return var1 *= 0.017453292f;
    }
    
    static {
        MovementUtil.mc = Minecraft.getMinecraft();
    }
}
