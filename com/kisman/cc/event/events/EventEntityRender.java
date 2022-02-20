//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;

public class EventEntityRender extends Event
{
    private float partialTicks;
    private Event.Era era;
    
    public EventEntityRender(final float partialTicks, final Event.Era era) {
        this.partialTicks = partialTicks;
        this.era = era;
    }
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public Event.Era getEra() {
        return this.era;
    }
}
