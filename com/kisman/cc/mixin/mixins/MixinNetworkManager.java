//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.network.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.event.events.*;
import io.netty.channel.*;

@Mixin(value = { NetworkManager.class }, priority = 10000)
public class MixinNetworkManager
{
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void onSendPacket(final Packet<?> p_Packet, final CallbackInfo callbackInfo) {
        final NetworkPacketEvent event = new NetworkPacketEvent((Packet)p_Packet);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("HEAD") }, cancellable = true)
    private void preSendPacket(final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Send event = new PacketEvent.Send((Packet)packet);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("HEAD") }, cancellable = true)
    private void preChannelRead(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.Receive event = new PacketEvent.Receive((Packet)packet);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "sendPacket(Lnet/minecraft/network/Packet;)V" }, at = { @At("TAIL") }, cancellable = true)
    private void postSendPacket(final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.PostSend event = new PacketEvent.PostSend((Packet)packet);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = { "channelRead0" }, at = { @At("TAIL") }, cancellable = true)
    private void postChannelRead(final ChannelHandlerContext context, final Packet<?> packet, final CallbackInfo callbackInfo) {
        final PacketEvent.PostReceive event = new PacketEvent.PostReceive((Packet)packet);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            callbackInfo.cancel();
        }
    }
}
