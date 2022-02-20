//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.settings.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.entity.*;

public class Velocity extends Module
{
    private String mode;
    private boolean subscribing;
    private Setting packet;
    private Setting exp;
    private Setting bobbers;
    private Setting noPush;
    @EventHandler
    private final Listener<EventPlayerApplyCollision> listener;
    @EventHandler
    private final Listener<EventPlayerPushedByWater> listener1;
    @EventHandler
    private final Listener<EventPlayerPushOutOfBlocks> listener2;
    @EventHandler
    private final Listener<PacketEvent.Receive> receiveListener;
    
    public Velocity() {
        super("Velocity", "akb", Category.PLAYER);
        this.packet = new Setting("Packet", this, "Packet");
        this.exp = new Setting("Explotion", this, true);
        this.bobbers = new Setting("Bobbers", this, true);
        this.noPush = new Setting("NoPush", this, true);
        this.listener = (Listener<EventPlayerApplyCollision>)new Listener(event -> {
            if (this.noPush.getValBoolean()) {
                event.cancel();
            }
        }, new Predicate[0]);
        this.listener1 = (Listener<EventPlayerPushedByWater>)new Listener(event -> {
            if (this.noPush.getValBoolean()) {
                event.cancel();
            }
        }, new Predicate[0]);
        this.listener2 = (Listener<EventPlayerPushOutOfBlocks>)new Listener(event -> {
            if (this.noPush.getValBoolean()) {
                event.cancel();
            }
        }, new Predicate[0]);
        this.receiveListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)event.getPacket()).getEntityID() == Velocity.mc.player.getEntityId()) {
                event.cancel();
            }
            if (event.getPacket() instanceof SPacketExplosion && this.exp.getValBoolean()) {
                event.cancel();
            }
            if (event.getPacket() instanceof SPacketEntityStatus && this.bobbers.getValBoolean()) {
                final SPacketEntityStatus packet = (SPacketEntityStatus)event.getPacket();
                if (packet.getOpCode() == 31) {
                    final Entity entity = packet.getEntity((World)Velocity.mc.world);
                    if (entity != null && entity instanceof EntityFishHook) {
                        final EntityFishHook fishHook = (EntityFishHook)entity;
                        if (fishHook.caughtEntity == Velocity.mc.player) {
                            event.cancel();
                        }
                    }
                }
            }
        }, new Predicate[0]);
        Kisman.instance.settingsManager.rSetting(new Setting("Mode", this, "Packet", new ArrayList<String>(Arrays.asList("Packet", "Matrix1", "Matrix2"))));
        Velocity.setmgr.rSetting(this.packet);
        Velocity.setmgr.rSetting(this.exp);
        Velocity.setmgr.rSetting(this.bobbers);
        Velocity.setmgr.rSetting(this.noPush);
    }
    
    public void onEnable() {
        this.mode = Kisman.instance.settingsManager.getSettingByName(this, "Mode").getValString();
        if (this.mode.equalsIgnoreCase("Packet")) {
            this.subscribing = true;
            Kisman.EVENT_BUS.subscribe((Listener)this.receiveListener);
            Kisman.EVENT_BUS.subscribe((Listener)this.listener);
            Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
            Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
        }
    }
    
    public void update() {
        if (!this.subscribing) {
            if (this.mode.equalsIgnoreCase("Matrix1")) {
                if (Velocity.mc.world.getBlockState(new BlockPos(Velocity.mc.player.posX, Velocity.mc.player.posY, Velocity.mc.player.posZ)).getBlock() == Block.getBlockById(0) && Velocity.mc.player.hurtTime > 0) {
                    float ticks = 0.2f;
                    Velocity.mc.player.motionY = -ticks;
                    ticks += 1.5f;
                }
            }
            else if (this.mode.equalsIgnoreCase("Matrix2") && Velocity.mc.player.hurtTime > 8) {
                Velocity.mc.player.onGround = true;
            }
        }
    }
    
    public void onDisable() {
        if (this.subscribing) {
            this.subscribing = false;
            Kisman.EVENT_BUS.unsubscribe((Listener)this.receiveListener);
            Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
            Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
            Kisman.EVENT_BUS.unsubscribe((Listener)this.listener2);
        }
    }
}
