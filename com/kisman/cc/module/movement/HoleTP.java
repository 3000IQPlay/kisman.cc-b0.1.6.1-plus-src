//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import com.kisman.cc.event.*;
import com.kisman.cc.util.*;
import net.minecraft.block.material.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class HoleTP extends Module
{
    private double[] oneblockPositions;
    private int packets;
    private boolean jumped;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public HoleTP() {
        super("HoleTP", "g", Category.MOVEMENT);
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if ((event.getEra() == Event.Era.PRE && !Speed.instance.isToggled()) || Speed.instance.speedMode.getValString().equalsIgnoreCase("Sti")) {
                if (!HoleTP.mc.player.onGround) {
                    if (HoleTP.mc.gameSettings.keyBindJump.isKeyDown()) {
                        this.jumped = true;
                    }
                }
                else {
                    this.jumped = false;
                }
                if (!this.jumped && HoleTP.mc.player.fallDistance < 0.5 && BlockUtil.isInHole() && HoleTP.mc.player.posY - BlockUtil.getNearestBlockBelow() <= 1.125 && HoleTP.mc.player.posY - BlockUtil.getNearestBlockBelow() <= 0.95 && !EntityUtil.isOnLiquid() && !EntityUtil.isInLiquid()) {
                    if (!HoleTP.mc.player.onGround) {
                        ++this.packets;
                    }
                    if (!HoleTP.mc.player.onGround && !HoleTP.mc.player.isInsideOfMaterial(Material.WATER) && !HoleTP.mc.player.isInsideOfMaterial(Material.LAVA) && !HoleTP.mc.gameSettings.keyBindJump.isKeyDown() && !HoleTP.mc.player.isOnLadder() && this.packets > 0) {
                        final BlockPos blockPos = new BlockPos(HoleTP.mc.player.posX, HoleTP.mc.player.posY, HoleTP.mc.player.posZ);
                        for (final double position : this.oneblockPositions) {
                            HoleTP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position((double)(blockPos.getX() + 0.5f), HoleTP.mc.player.posY - position, (double)(blockPos.getZ() + 0.5f), true));
                        }
                        HoleTP.mc.player.setPosition((double)(blockPos.getX() + 0.5f), BlockUtil.getNearestBlockBelow() + 0.1, (double)(blockPos.getZ() + 0.5f));
                        this.packets = 0;
                    }
                }
            }
        }, new Predicate[0]);
        this.oneblockPositions = new double[] { 0.42, 0.75 };
        this.jumped = false;
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
}
