//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat.holefiller;

import net.minecraft.util.math.*;

public class Hole
{
    public BlockPos pos;
    public float distToPlayer;
    public float distToTarget;
    
    public Hole(final BlockPos pos, final float distToPlayer, final float distToTarget) {
        this.pos = pos;
        this.distToPlayer = distToPlayer;
        this.distToTarget = distToTarget;
    }
    
    public BlockPos getDownHoleBlock() {
        if (this.pos == null) {
            return BlockPos.ORIGIN;
        }
        return this.pos.down();
    }
}
