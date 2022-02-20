//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraftforge.common.*;
import net.minecraft.network.play.client.*;

public class PacketCancel extends Module
{
    private boolean input;
    private boolean player;
    private boolean entityAction;
    private boolean useEntity;
    private boolean vehicleMove;
    @EventHandler
    private final Listener<PacketEvent.Send> sendListener;
    
    public PacketCancel() {
        super("PacketCancel", "PacketCancel", Category.PLAYER);
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if ((event.getPacket() instanceof CPacketInput && this.input) || (event.getPacket() instanceof CPacketPlayer && this.player) || (event.getPacket() instanceof CPacketEntityAction && this.entityAction) || (event.getPacket() instanceof CPacketUseEntity && this.useEntity) || (event.getPacket() instanceof CPacketVehicleMove && this.vehicleMove)) {
                event.cancel();
                System.out.println("test");
            }
        }, new Predicate[0]);
        Kisman.instance.settingsManager.rSetting(new Setting("Packets", this, "Packets"));
        Kisman.instance.settingsManager.rSetting(new Setting("CPacketInput", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("CPacketPlayer", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("CPacketEntityAction", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("CPacketUseEntity", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("CPacketVehicleMove", this, false));
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
        Kisman.EVENT_BUS.subscribe((Listener)this.sendListener);
        this.input = Kisman.instance.settingsManager.getSettingByName(this, "CPacketInput").getValBoolean();
        this.player = Kisman.instance.settingsManager.getSettingByName(this, "CPacketPlayer").getValBoolean();
        this.entityAction = Kisman.instance.settingsManager.getSettingByName(this, "CPacketEntityAction").getValBoolean();
        this.useEntity = Kisman.instance.settingsManager.getSettingByName(this, "CPacketUseEntity").getValBoolean();
        this.vehicleMove = Kisman.instance.settingsManager.getSettingByName(this, "CPacketVehicleMove").getValBoolean();
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.sendListener);
    }
}
