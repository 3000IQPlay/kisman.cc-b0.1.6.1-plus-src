//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class AngleUtil
{
    private static Minecraft mc;
    
    public static float[] calculateAngles(final Entity entity) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), InterpolationUtil.interpolateEntityTime(entity, AngleUtil.mc.getRenderPartialTicks()));
    }
    
    public static float[] calculateAngles(final BlockPos blockPos) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), new Vec3d((Vec3i)blockPos));
    }
    
    public static float[] calculateCenter(final Entity entity) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), InterpolationUtil.interpolateEntityTime(entity, AngleUtil.mc.getRenderPartialTicks()).add(new Vec3d((double)(entity.width / 2.0f), (double)(entity.getEyeHeight() / 2.0f), (double)(entity.width / 2.0f))));
    }
    
    public static float[] calculateCenter(final BlockPos blockPos) {
        return calculateAngle(InterpolationUtil.interpolateEntityTime((Entity)AngleUtil.mc.player, AngleUtil.mc.getRenderPartialTicks()), new Vec3d((Vec3i)blockPos).add(new Vec3d(0.5, 0.0, 0.5)));
    }
    
    public static float[] calculateAngle(final Vec3d from, final Vec3d to) {
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(to.z - from.z, to.x - from.x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2((to.y - from.y) * -1.0, MathHelper.sqrt(Math.pow(to.x - from.x, 2.0) + Math.pow(to.z - from.z, 2.0))))) };
    }
    
    public static float calculateAngleDifference(final float serverValue, final float currentValue, final double divisions, final int steps) {
        return (float)(serverValue - currentValue / (divisions * steps));
    }
    
    public static float calculateAngleDifference(final float direction, final float rotationYaw) {
        final float phi = Math.abs(rotationYaw - direction) % 360.0f;
        return (phi > 180.0f) ? (360.0f - phi) : phi;
    }
    
    static {
        AngleUtil.mc = Minecraft.getMinecraft();
    }
}
