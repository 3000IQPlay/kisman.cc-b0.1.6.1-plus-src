//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;

public class EventPlayerTravel extends Event
{
    public float strafe;
    public float vertical;
    public float forward;
    
    public EventPlayerTravel(final float strafe, final float vertical, final float forward) {
        this.strafe = strafe;
        this.vertical = vertical;
        this.forward = forward;
    }
}
