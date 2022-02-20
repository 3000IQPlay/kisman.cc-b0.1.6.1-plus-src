//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.play.server.*;

public class NoRotate extends Module
{
    private Setting waitDelay;
    private TimerUtils timer;
    private boolean cancelPackets;
    private boolean timerReset;
    @EventHandler
    private final Listener<PacketEvent.Receive> sendListener;
    
    public NoRotate() {
        super("NoRotate", "NoRotate", Category.MOVEMENT);
        this.waitDelay = new Setting("Delay", this, 2500.0, 0.0, 10000.0, true);
        this.timer = new TimerUtils();
        this.cancelPackets = true;
        this.timerReset = false;
        this.sendListener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (this.cancelPackets && event.getPacket() instanceof SPacketPlayerPosLook) {
                final SPacketPlayerPosLook packet = (SPacketPlayerPosLook)event.getPacket();
                packet.yaw = NoRotate.mc.player.rotationYaw;
                packet.pitch = NoRotate.mc.player.rotationPitch;
            }
        }, new Predicate[0]);
        NoRotate.setmgr.rSetting(this.waitDelay);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.sendListener);
        ChatUtils.message((Object)(TextFormatting.GOLD + "[NoRotate] " + TextFormatting.GRAY + "This module might desync you!"));
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.sendListener);
    }
    
    public void update() {
        if (this.timerReset && !this.cancelPackets && this.timer.passedMillis((long)this.waitDelay.getValInt())) {
            ChatUtils.message((Object)(TextFormatting.GOLD + "[NoRotate] " + TextFormatting.GRAY + "This module might desync you!"));
            this.cancelPackets = true;
            this.timerReset = false;
        }
    }
    
    @SubscribeEvent
    public void onConnect(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        this.timer.reset();
        this.timerReset = true;
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.cancelPackets = false;
    }
}
