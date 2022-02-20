//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.util.math.*;
import com.kisman.cc.util.pyro.*;
import net.minecraft.util.*;

public class VecRotation
{
    private Vec3d vec;
    private Rotation rotation;
    private EnumFacing sideHit;
    
    public VecRotation(final Vec3d vec, final Rotation rotation) {
        this.setVec(vec);
        this.setRotation(rotation);
    }
    
    public VecRotation(final Vec3d vec, final Rotation rot, final EnumFacing sideHit) {
        this(vec, rot);
        this.setSideHit(sideHit);
    }
    
    public Vec3d getVec() {
        return this.vec;
    }
    
    public void setVec(final Vec3d vec) {
        this.vec = vec;
    }
    
    public Rotation getRotation() {
        return this.rotation;
    }
    
    public void setRotation(final Rotation rotation) {
        this.rotation = rotation;
    }
    
    public EnumFacing getSideHit() {
        return this.sideHit;
    }
    
    public void setSideHit(final EnumFacing sideHit) {
        this.sideHit = sideHit;
    }
}
