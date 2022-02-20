//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class TeleportUtil
{
    private static Minecraft mc;
    
    public static void teleportPlayer(final double x, final double y, final double z) {
        TeleportUtil.mc.player.setVelocity(0.0, 0.0, 0.0);
        TeleportUtil.mc.player.setPosition(x, y, z);
        TeleportUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x, y, z, true));
    }
    
    public static void teleportPlayerNoPacket(final double x, final double y, final double z) {
        TeleportUtil.mc.player.setVelocity(0.0, 0.0, 0.0);
        TeleportUtil.mc.player.setPosition(x, y, z);
    }
    
    public static void teleportPlayerKeepMotion(final double x, final double y, final double z) {
        TeleportUtil.mc.player.setPosition(x, y, z);
    }
    
    static {
        TeleportUtil.mc = Minecraft.getMinecraft();
    }
}
