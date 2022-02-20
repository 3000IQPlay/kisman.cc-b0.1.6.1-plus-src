//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command.commands;

import com.kisman.cc.command.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class Tp extends Command
{
    private final String regex1 = "-[1-9][0-9]*";
    private final String regex2 = "[1-9][0-9]*";
    private final String regex3 = "-*[^0-9]*";
    
    public Tp() {
        super("tp");
    }
    
    public void runCommand(final String s, final String[] args) {
        if (args.length > 3) {
            ChatUtils.error((Object)("Usage: " + this.getSyntax()));
            return;
        }
        if (args.length == 3) {
            for (final String str : args) {
                if (str.matches("-*[^0-9]*")) {
                    ChatUtils.error((Object)("Usage: " + this.getSyntax()));
                    return;
                }
            }
            final int x = this.getCoord(args[0]);
            final int y = this.getCoord(args[1]);
            final int z = this.getCoord(args[2]);
            Tp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position((double)x, (double)y, (double)z, false));
            Tp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position((double)x, (double)y, (double)z, true));
            Tp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position((double)x, (double)y, (double)z, true));
        }
        else if (args.length == 1) {
            if (this.getPlayer(args[0]) == null) {
                ChatUtils.error((Object)("The player" + args[0] + " does not exist!"));
                return;
            }
            final double x2 = this.getPlayer(args[0]).posX;
            final double y2 = this.getPlayer(args[0]).posY;
            final double z2 = this.getPlayer(args[0]).posZ;
            Tp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x2, y2, z2, false));
            Tp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x2, y2, z2, true));
            Tp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(x2, y2, z2, true));
        }
    }
    
    public String getDescription() {
        return "bypass tp on the world";
    }
    
    public String getSyntax() {
        return "tp <x> <y> <z> or tp <player nickname>";
    }
    
    private int getCoord(final String str) {
        if (str.matches("-[1-9][0-9]*")) {
            return -Integer.parseInt(str.substring(0));
        }
        if (str.matches("[1-9][0-9]*")) {
            return Integer.parseInt(str);
        }
        return 0;
    }
    
    private EntityPlayer getPlayer(final String name) {
        for (final EntityPlayer player : Tp.mc.world.playerEntities) {
            if (player != Tp.mc.player && player.getName().equals(name)) {
                return player;
            }
        }
        return null;
    }
}
