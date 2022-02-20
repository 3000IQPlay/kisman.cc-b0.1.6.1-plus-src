//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;

public class EventPlayerPushOutOfBlocks extends Event
{
    public double x;
    public double y;
    public double z;
    
    public EventPlayerPushOutOfBlocks(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
