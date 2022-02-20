//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;
import net.minecraft.client.entity.*;

public class EventRenderEntityName extends Event
{
    public AbstractClientPlayer entityIn;
    public double x;
    public double y;
    public double z;
    public double distanceSq;
    public String name;
    
    public EventRenderEntityName(final AbstractClientPlayer entityIn, final double x, final double y, final double z, final String name, final double distanceSq) {
        this.entityIn = entityIn;
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
        this.distanceSq = distanceSq;
    }
}
