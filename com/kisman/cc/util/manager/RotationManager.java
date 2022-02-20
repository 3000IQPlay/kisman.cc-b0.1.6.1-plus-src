//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.manager;

import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import javax.annotation.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class RotationManager
{
    private static Minecraft mc;
    private float yaw;
    private float pitch;
    
    public void update() {
        this.yaw = RotationManager.mc.player.rotationYaw;
        this.pitch = RotationManager.mc.player.rotationPitch;
    }
    
    public void reset() {
        RotationManager.mc.player.rotationYaw = this.yaw;
        RotationManager.mc.player.rotationPitch = this.pitch;
        RotationManager.mc.player.rotationYawHead = this.yaw;
    }
    
    public static void set(final float yaw, final float pitch, final boolean packet) {
        if (packet) {
            RotationManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, RotationManager.mc.player.onGround));
        }
        else {
            RotationManager.mc.player.rotationYaw = yaw;
            RotationManager.mc.player.rotationYawHead = yaw;
            RotationManager.mc.player.rotationPitch = pitch;
        }
    }
    
    public static Rotation calcRotation(@Nonnull final BlockPos bp) {
        final float[] angles = calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), new Vec3d((double)(bp.getX() + 0.5f), (double)(bp.getY() + 0.5f), (double)(bp.getZ() + 0.5f)));
        return new Rotation(angles[0], angles[1]);
    }
    
    public static Rotation calcRotation(@Nonnull final Entity e) {
        final float[] angles = calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), e.getPositionEyes(RotationManager.mc.getRenderPartialTicks()));
        return new Rotation(angles[0], angles[1]);
    }
    
    public static float[] look(final BlockPos bp, final boolean packet) {
        return look(bp, packet, true);
    }
    
    public static float[] look(final Entity bp, final boolean packet) {
        return look(bp, packet, true);
    }
    
    public static float[] look(final BlockPos bp, final boolean packet, final boolean set) {
        final float[] angles = calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), new Vec3d((double)(bp.getX() + 0.5f), (double)(bp.getY() + 0.5f), (double)(bp.getZ() + 0.5f)));
        if (set) {
            set(angles[0], angles[1], packet);
        }
        return angles;
    }
    
    public static float[] look(final Entity entity, final boolean packet, final boolean set) {
        final float[] angles = calcAngle(RotationManager.mc.player.getPositionEyes(RotationManager.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationManager.mc.getRenderPartialTicks()));
        if (set) {
            set(angles[0], angles[1], packet);
        }
        return angles;
    }
    
    public static float[] calcAngle(final Vec3d from, final Vec3d to) {
        final double difX = to.x - from.x;
        final double difY = (to.y - from.y) * -1.0;
        final double difZ = to.z - from.z;
        final double dist = MathHelper.sqrt(difX * difX + difZ * difZ);
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difZ, difX)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(difY, dist))) };
    }
    
    static {
        RotationManager.mc = Minecraft.getMinecraft();
    }
}
