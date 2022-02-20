//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick;

public interface Game
{
    void init(final GameContainer p0) throws SlickException;
    
    void update(final GameContainer p0, final int p1) throws SlickException;
    
    void render(final GameContainer p0, final Graphics p1) throws SlickException;
    
    boolean closeRequested();
    
    String getTitle();
}
