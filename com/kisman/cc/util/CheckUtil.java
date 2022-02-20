//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class CheckUtil
{
    private static Minecraft mc;
    private static double x;
    private static double y;
    private static double z;
    
    public static void savecord() {
        CheckUtil.x = CheckUtil.mc.player.posX;
        CheckUtil.y = CheckUtil.mc.player.posY;
        CheckUtil.z = CheckUtil.mc.player.posZ;
    }
    
    public static void loadcord() {
        CheckUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CheckUtil.x, CheckUtil.y, CheckUtil.z, false));
        CheckUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(CheckUtil.x, CheckUtil.y, CheckUtil.z, true));
        CheckUtil.mc.player.setPositionAndUpdate(CheckUtil.x, CheckUtil.y, CheckUtil.z);
        CheckUtil.mc.player.setPosition(CheckUtil.x, CheckUtil.y, CheckUtil.z);
    }
    
    static {
        CheckUtil.mc = Minecraft.getMinecraft();
    }
}
