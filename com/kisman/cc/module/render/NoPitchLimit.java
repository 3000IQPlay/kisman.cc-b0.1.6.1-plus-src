//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import com.kisman.cc.event.*;

public class NoPitchLimit extends Module
{
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public NoPitchLimit() {
        super("NoPitchLimit", "", Category.RENDER);
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.getEra() == Event.Era.PRE && NoPitchLimit.mc.player.rotationPitch < 90.0f && NoPitchLimit.mc.player.rotationPitch <= -90.0f) {
                NoPitchLimit.mc.player.rotationPitch = -90.0f;
            }
        }, new Predicate[0]);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
}
