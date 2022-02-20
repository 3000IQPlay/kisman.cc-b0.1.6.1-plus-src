//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.util.math.*;

public class VecCircle
{
    public Vec3d vec3d;
    public float yaw;
    public float pitch;
    public long time;
    
    public static Vec3d Method719(final VecCircle class442) {
        return class442.vec3d;
    }
    
    public VecCircle(final Vec3d vec3d, final float f, final float f2) {
        this.vec3d = vec3d;
        this.yaw = f;
        this.pitch = f2;
        this.time = System.currentTimeMillis();
    }
    
    public static float Method720(final VecCircle class442) {
        return class442.pitch;
    }
    
    public static float Method721(final VecCircle class442) {
        return class442.yaw;
    }
    
    public static long Method722(final VecCircle class442) {
        return class442.time;
    }
}
