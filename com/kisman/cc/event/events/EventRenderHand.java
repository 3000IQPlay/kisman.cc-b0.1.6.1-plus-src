//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;

public class EventRenderHand extends Event
{
    private final float ticks;
    
    public EventRenderHand(final float ticks) {
        this.ticks = ticks;
    }
    
    public float getPartialTicks() {
        return this.ticks;
    }
    
    public static class PostOutline extends EventRenderHand
    {
        public PostOutline(final float ticks) {
            super(ticks);
        }
    }
    
    public static class PreOutline extends EventRenderHand
    {
        public PreOutline(final float ticks) {
            super(ticks);
        }
    }
    
    public static class PostFill extends EventRenderHand
    {
        public PostFill(final float ticks) {
            super(ticks);
        }
    }
    
    public static class PreFill extends EventRenderHand
    {
        public PreFill(final float ticks) {
            super(ticks);
        }
    }
}
