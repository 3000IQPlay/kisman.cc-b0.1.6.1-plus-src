//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class TeamRusherLag extends Module
{
    private Setting time;
    private Setting text;
    private boolean canSend;
    private long lastPacket;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    
    public TeamRusherLag() {
        super("TeamRusherLag", "TeamRusherLag", Category.MISC);
        this.time = new Setting("Lag Time (in ms)", this, 3000.0, 1000.0, 10000.0, true);
        this.text = new Setting("Message", this, "> #TeamRusher Lag: ON", "> #TeamRusher Lag: ON", true);
        this.canSend = false;
        this.lastPacket = 0L;
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> this.lastPacket = System.currentTimeMillis(), new Predicate[0]);
        TeamRusherLag.setmgr.rSetting(this.time);
        TeamRusherLag.setmgr.rSetting(this.text);
    }
    
    @Override
    public void onEnable() {
        this.lastPacket = 0L;
    }
    
    @Override
    public void onDisable() {
        this.lastPacket = 0L;
    }
    
    @Override
    public void update() {
        if (this.lastPacket != 0L && System.currentTimeMillis() > this.lastPacket + this.time.getValDouble() && this.canSend) {
            this.canSend = false;
            TeamRusherLag.mc.player.sendChatMessage(this.text.getValString());
        }
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.lastPacket = 0L;
    }
}
