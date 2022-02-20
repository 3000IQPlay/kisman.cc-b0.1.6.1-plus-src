//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;

public class EventWorldRender extends Event
{
    public final float particalTicks;
    
    public EventWorldRender(final float particalTicks) {
        this.particalTicks = particalTicks;
    }
    
    public float getParticalTicks() {
        return this.particalTicks;
    }
}
