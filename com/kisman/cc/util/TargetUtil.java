//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;

public class TargetUtil
{
    static float minrange;
    static EntityPlayer targetPlayer;
    static Minecraft mc;
    
    public static EntityPlayer getTarget() {
        TargetUtil.mc.world.playerEntities.stream().filter(entityPlayer -> ((EntityPlayer)entityPlayer).getDistance(entityPlayer) <= TargetUtil.minrange).filter(entityPlayer -> entityPlayer != TargetUtil.mc.player).forEach(entityPlayer -> {
            TargetUtil.targetPlayer = (EntityPlayer)entityPlayer;
            TargetUtil.minrange = ((EntityPlayer)entityPlayer).getDistance(entityPlayer);
            return;
        });
        try {
            return TargetUtil.targetPlayer;
        }
        catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    static {
        TargetUtil.minrange = 100.0f;
        TargetUtil.mc = Minecraft.getMinecraft();
    }
}
