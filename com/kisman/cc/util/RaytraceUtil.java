//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import com.kisman.cc.util.cosmos.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class RaytraceUtil
{
    private static Minecraft mc;
    
    public static boolean raytraceBlock(final BlockPos blockPos, final Raytrace raytrace) {
        return RaytraceUtil.mc.world.rayTraceBlocks(new Vec3d(RaytraceUtil.mc.player.posX, RaytraceUtil.mc.player.posY + RaytraceUtil.mc.player.getEyeHeight(), RaytraceUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() + raytrace.getOffset(), blockPos.getZ() + 0.5), false, true, false) != null;
    }
    
    public static boolean raytraceEntity(final Entity entity, final double offset) {
        return RaytraceUtil.mc.world.rayTraceBlocks(new Vec3d(RaytraceUtil.mc.player.posX, RaytraceUtil.mc.player.posY + RaytraceUtil.mc.player.getEyeHeight(), RaytraceUtil.mc.player.posZ), new Vec3d(entity.posX, entity.posY + offset, entity.posZ), false, true, false) == null;
    }
    
    static {
        RaytraceUtil.mc = Minecraft.getMinecraft();
    }
}
