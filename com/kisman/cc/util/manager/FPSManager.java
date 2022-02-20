//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.manager;

import java.util.*;

public class FPSManager
{
    private int fps;
    private final LinkedList<Long> frames;
    
    public FPSManager() {
        this.frames = new LinkedList<Long>();
    }
    
    public void update() {
        final long time = System.nanoTime();
        this.frames.add(time);
        while (true) {
            final long f = this.frames.getFirst();
            final long ONE_SECOND = 1000000000L;
            if (time - f <= 1000000000L) {
                break;
            }
            this.frames.remove();
        }
        this.fps = this.frames.size();
    }
    
    public int getFPS() {
        return this.fps;
    }
    
    public float getFrametime() {
        return 1.0f / this.fps;
    }
}
