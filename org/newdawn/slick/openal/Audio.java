//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.openal;

public interface Audio
{
    void stop();
    
    int getBufferID();
    
    boolean isPlaying();
    
    int playAsSoundEffect(final float p0, final float p1, final boolean p2);
    
    int playAsSoundEffect(final float p0, final float p1, final boolean p2, final float p3, final float p4, final float p5);
    
    int playAsMusic(final float p0, final float p1, final boolean p2);
    
    boolean setPosition(final float p0);
    
    float getPosition();
}
