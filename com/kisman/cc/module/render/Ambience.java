//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import java.awt.*;
import net.minecraft.network.play.server.*;

public class Ambience extends Module
{
    public static Ambience instance;
    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting alpha;
    public Setting light;
    public Setting time;
    public Setting infinity;
    public Setting speed;
    public Setting useSaturation;
    public Setting saturation;
    public int cyrcle;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    
    public Ambience() {
        super("Ambience", "minecraqft color", Category.RENDER);
        this.red = new Setting("Red", this, 255.0, 0.0, 255.0, false);
        this.green = new Setting("Green", this, 255.0, 0.0, 255.0, false);
        this.blue = new Setting("Blue", this, 255.0, 0.0, 255.0, false);
        this.alpha = new Setting("Alpha", this, 255.0, 0.0, 255.0, false);
        this.light = new Setting("Light", this, false);
        this.time = new Setting("Time", this, 24.0, 5.0, 25.0, true);
        this.infinity = new Setting("InfinityCyrcle", this, true);
        this.speed = new Setting("Speed", this, 100.0, 10.0, 1000.0, true);
        this.useSaturation = new Setting("UseSaturation", this, false);
        this.saturation = new Setting("Saturation", this, 0.5, 0.0, 1.0, false);
        this.cyrcle = 0;
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketTimeUpdate) {
                event.cancel();
            }
        }, new Predicate[0]);
        Ambience.instance = this;
        Ambience.setmgr.rSetting(this.red);
        Ambience.setmgr.rSetting(this.green);
        Ambience.setmgr.rSetting(this.blue);
        Ambience.setmgr.rSetting(this.alpha);
        Ambience.setmgr.rSetting(this.light);
        Ambience.setmgr.rSetting(this.time);
        Ambience.setmgr.rSetting(this.infinity);
        Ambience.setmgr.rSetting(this.speed);
        Ambience.setmgr.rSetting(this.useSaturation);
        Ambience.setmgr.rSetting(this.saturation);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    public void update() {
        if (Ambience.mc.world == null) {
            return;
        }
        this.cyrcle += this.speed.getValInt();
        Ambience.mc.world.setWorldTime(this.infinity.getValBoolean() ? ((long)this.cyrcle) : (this.time.getValLong() * 1000L));
        if (this.cyrcle >= 24000) {
            this.cyrcle = 0;
        }
    }
    
    public Color getColor() {
        return new Color(this.red.getValInt(), this.green.getValInt(), this.blue.getValInt(), this.alpha.getValInt());
    }
}
