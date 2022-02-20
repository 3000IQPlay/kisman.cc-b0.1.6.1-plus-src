//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.*;
import java.util.function.*;
import com.kisman.cc.*;
import com.kisman.cc.event.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import com.kisman.cc.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;

public class AimBot extends Module
{
    private Setting mode;
    public static AimBot instance;
    public RotationSpoof rotationSpoof;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public AimBot() {
        super("AimBot", "f", Category.COMBAT);
        this.mode = new Setting("Mode", this, "Packet", new ArrayList<String>(Arrays.asList("Packet", "Client")));
        this.rotationSpoof = null;
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.getEra() != Event.Era.PRE) {
                return;
            }
            if (this.rotationSpoof == null) {
                return;
            }
            event.cancel();
            final boolean sprint = AimBot.mc.player.isSprinting();
            if (sprint != AimBot.mc.player.serverSprintState) {
                if (sprint) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AimBot.mc.player, CPacketEntityAction.Action.START_SPRINTING));
                }
                else {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AimBot.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
                }
            }
            final boolean sneak = AimBot.mc.player.isSneaking();
            if (sneak != AimBot.mc.player.serverSneakState) {
                if (sneak) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AimBot.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                }
                else {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AimBot.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
            }
            if (PlayerUtil.isCurrentViewEntity()) {
                float yaw = AimBot.mc.player.cameraYaw;
                float pitch = AimBot.mc.player.cameraPitch;
                if (this.rotationSpoof != null) {
                    yaw = this.rotationSpoof.yaw;
                    pitch = this.rotationSpoof.pitch;
                    final String valString = this.mode.getValString();
                    switch (valString) {
                        case "Client": {
                            AimBot.mc.player.rotationYaw = yaw;
                            AimBot.mc.player.rotationPitch = pitch;
                            break;
                        }
                        default: {
                            AimBot.mc.player.rotationYawHead = yaw;
                            break;
                        }
                    }
                }
                final AxisAlignedBB axisalignedbb = AimBot.mc.player.getEntityBoundingBox();
                final double posXDifference = AimBot.mc.player.posX - AimBot.mc.player.lastReportedPosX;
                final double posYDifference = axisalignedbb.minY - AimBot.mc.player.lastReportedPosY;
                final double posZDifference = AimBot.mc.player.posZ - AimBot.mc.player.lastReportedPosZ;
                final double yawDifference = yaw - AimBot.mc.player.lastReportedYaw;
                final double rotationDifference = pitch - AimBot.mc.player.lastReportedPitch;
                final EntityPlayerSP player = AimBot.mc.player;
                ++player.positionUpdateTicks;
                boolean movedXYZ = posXDifference * posXDifference + posYDifference * posYDifference + posZDifference * posZDifference > 9.0E-4 || AimBot.mc.player.positionUpdateTicks >= 20;
                final boolean movedRotation = yawDifference != 0.0 || rotationDifference != 0.0;
                if (AimBot.mc.player.isRiding()) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(AimBot.mc.player.motionX, -999.0, AimBot.mc.player.motionZ, yaw, pitch, AimBot.mc.player.onGround));
                    movedXYZ = false;
                }
                else if (movedXYZ && movedRotation) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(AimBot.mc.player.posX, axisalignedbb.minY, AimBot.mc.player.posZ, yaw, pitch, AimBot.mc.player.onGround));
                }
                else if (movedXYZ) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(AimBot.mc.player.posX, axisalignedbb.minY, AimBot.mc.player.posZ, AimBot.mc.player.onGround));
                }
                else if (movedRotation) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, AimBot.mc.player.onGround));
                }
                else if (AimBot.mc.player.prevOnGround != AimBot.mc.player.onGround) {
                    AimBot.mc.player.connection.sendPacket((Packet)new CPacketPlayer(AimBot.mc.player.onGround));
                }
                if (movedXYZ) {
                    AimBot.mc.player.lastReportedPosX = AimBot.mc.player.posX;
                    AimBot.mc.player.lastReportedPosY = axisalignedbb.minY;
                    AimBot.mc.player.lastReportedPosZ = AimBot.mc.player.posZ;
                    AimBot.mc.player.positionUpdateTicks = 0;
                }
                if (movedRotation) {
                    AimBot.mc.player.lastReportedYaw = yaw;
                    AimBot.mc.player.lastReportedPitch = pitch;
                }
                AimBot.mc.player.prevOnGround = AimBot.mc.player.onGround;
                AimBot.mc.player.autoJumpEnabled = AimBot.mc.player.mc.gameSettings.autoJump;
            }
        }, new Predicate[0]);
        AimBot.instance = this;
        AimBot.setmgr.rSetting(this.mode);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
}
