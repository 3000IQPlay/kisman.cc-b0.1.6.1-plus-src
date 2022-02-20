//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.network.play.server.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.entity.*;

public class TraceTeleport extends Module
{
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    
    public TraceTeleport() {
        super("TraceTeleport", "", Category.CHAT);
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketEntityTeleport) {
                final SPacketEntityTeleport packet = (SPacketEntityTeleport)event.getPacket();
                if (Math.abs(TraceTeleport.mc.player.posX - packet.getX()) > 500.0 || Math.abs(TraceTeleport.mc.player.posZ - packet.getZ()) > 500.0) {
                    String name = "Unknown";
                    final Entity entity = TraceTeleport.mc.world.getEntityByID(packet.getEntityId());
                    if (entity != null) {
                        name = entity.getClass().getSimpleName();
                    }
                    final double distance = Math.sqrt(Math.pow(TraceTeleport.mc.player.posX - packet.getX(), 2.0) + Math.pow(TraceTeleport.mc.player.posZ - packet.getZ(), 2.0));
                    final String warn = String.format("Entity [%s] teleported to [%.2f, %.2f, %.2f], %.2f blocks away", name, packet.getX(), packet.getY(), packet.getZ(), distance);
                    ChatUtils.warning((Object)warn);
                    Kisman.LOGGER.warn("[TraceTeleport]: " + warn);
                }
            }
        }, new Predicate[0]);
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
