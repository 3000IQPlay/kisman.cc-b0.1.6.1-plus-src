//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.entity.*;
import net.minecraft.util.math.*;

public class WorldUtil
{
    public static String vectorToString(final Vec3d vector, final boolean... includeY) {
        final boolean reallyIncludeY = includeY.length <= 0 || includeY[0];
        final StringBuilder builder = new StringBuilder();
        builder.append('(');
        builder.append((int)Math.floor(vector.x));
        builder.append(", ");
        if (reallyIncludeY) {
            builder.append((int)Math.floor(vector.y));
            builder.append(", ");
        }
        builder.append((int)Math.floor(vector.z));
        builder.append(")");
        return builder.toString();
    }
    
    public static float getDistance(final Entity entityOut, final BlockPos pos) {
        final float f = (float)(entityOut.posX - pos.getX());
        final float f2 = (float)(entityOut.posY - pos.getY());
        final float f3 = (float)(entityOut.posZ - pos.getZ());
        return MathHelper.sqrt(f * f + f2 * f2 + f3 * f3);
    }
}
