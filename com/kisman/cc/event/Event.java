//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event;

import me.zero.alpine.event.type.*;
import net.minecraft.client.*;

public class Event extends Cancellable
{
    private Era era;
    private float particalTicks;
    
    public Event() {
        this.particalTicks = Minecraft.getMinecraft().getRenderPartialTicks();
    }
    
    public Event(final Era era) {
        this.era = era;
        this.particalTicks = Minecraft.getMinecraft().getRenderPartialTicks();
    }
    
    public Era getEra() {
        return this.era;
    }
    
    public void setEra(final Era era) {
        this.era = era;
    }
    
    public float getParticalTicks() {
        return this.particalTicks;
    }
    
    public void setParticalTicks(final float particalTicks) {
        this.particalTicks = particalTicks;
    }
    
    public enum Era
    {
        PRE, 
        POST, 
        PERI;
    }
}
