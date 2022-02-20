//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util;

import net.minecraft.client.*;
import java.text.*;
import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import java.util.function.*;
import net.minecraftforge.common.*;
import com.kisman.cc.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.network.*;
import java.util.*;

public class ServerManager
{
    private Minecraft mc;
    private float[] tpsCount;
    private DecimalFormat format;
    private TimerUtils timer;
    private float tps;
    private long lastUpdate;
    private String serverBrand;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener1;
    
    public ServerManager() {
        this.mc = Minecraft.getMinecraft();
        this.listener1 = (Listener<PacketEvent.Receive>)new Listener(event -> this.timer.reset(), new Predicate[0]);
        this.tpsCount = new float[10];
        this.format = new DecimalFormat("##.00##");
        this.timer = new TimerUtils();
        this.tps = 20.0f;
        this.lastUpdate = -1L;
        this.serverBrand = "";
        MinecraftForge.EVENT_BUS.register((Object)this);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent event) {
        final long currentTime = System.currentTimeMillis();
        if (this.lastUpdate == -1L) {
            this.lastUpdate = currentTime;
            return;
        }
        final long timeDiff = currentTime - this.lastUpdate;
        float tickTime = (float)(timeDiff / 20L);
        if (tickTime == 0.0f) {
            tickTime = 50.0f;
        }
        float tps = 1000.0f / tickTime;
        if (tps > 20.0f) {
            tps = 20.0f;
        }
        System.arraycopy(this.tpsCount, 0, this.tpsCount, 1, this.tpsCount.length - 1);
        this.tpsCount[0] = tps;
        double total = 0.0;
        for (final float f : this.tpsCount) {
            total += f;
        }
        if ((total /= this.tpsCount.length) > 20.0) {
            total = 20.0;
        }
        tps = Float.parseFloat(this.format.format(total));
        this.lastUpdate = currentTime;
    }
    
    public void reset() {
        Arrays.fill(this.tpsCount, 20.0f);
        this.tps = 20.0f;
    }
    
    public float getTpsFactor() {
        return 20.0f / this.tps;
    }
    
    public float getTps() {
        return this.tps;
    }
    
    public String getServerBrand() {
        return this.serverBrand;
    }
    
    public void setServerBrand(final String brand) {
        this.serverBrand = brand;
    }
    
    public int getPing() {
        if (this.mc.player == null && this.mc.world == null) {
            return 0;
        }
        try {
            return Objects.requireNonNull(this.mc.getConnection()).getPlayerInfo(this.mc.getConnection().getGameProfile().getId()).getResponseTime();
        }
        catch (Exception e) {
            return 0;
        }
    }
}
