//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;

public class MoveUtil
{
    private static Minecraft mc;
    
    public static float getSpeed() {
        final float speed = (float)Math.sqrt(MoveUtil.mc.player.motionX * MoveUtil.mc.player.motionX + MoveUtil.mc.player.motionZ * MoveUtil.mc.player.motionZ);
        return speed;
    }
    
    static {
        MoveUtil.mc = Minecraft.getMinecraft();
    }
}
