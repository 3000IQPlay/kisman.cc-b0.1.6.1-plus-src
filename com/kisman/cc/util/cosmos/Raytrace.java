//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.cosmos;

public enum Raytrace
{
    NONE(-1.0), 
    BASE(0.5), 
    NORMAL(1.5), 
    DOUBLE(2.5), 
    TRIPLE(3.5);
    
    private final double offset;
    
    private Raytrace(final double offset) {
        this.offset = offset;
    }
    
    public double getOffset() {
        return this.offset;
    }
}
