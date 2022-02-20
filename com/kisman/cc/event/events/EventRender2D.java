//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;
import net.minecraft.client.gui.*;

public class EventRender2D extends Event
{
    private final ScaledResolution resolution;
    private final float partialticks;
    
    public EventRender2D(final ScaledResolution resolution, final float partialticks) {
        this.resolution = resolution;
        this.partialticks = partialticks;
    }
    
    public ScaledResolution getResolution() {
        return this.resolution;
    }
    
    public float getPartialTicks() {
        return this.partialticks;
    }
}
