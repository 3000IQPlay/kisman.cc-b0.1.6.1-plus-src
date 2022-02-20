//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.state;

import org.newdawn.slick.*;

public interface GameState extends InputListener
{
    int getID();
    
    void init(final GameContainer p0, final StateBasedGame p1) throws SlickException;
    
    void render(final GameContainer p0, final StateBasedGame p1, final Graphics p2) throws SlickException;
    
    void update(final GameContainer p0, final StateBasedGame p1, final int p2) throws SlickException;
    
    void enter(final GameContainer p0, final StateBasedGame p1) throws SlickException;
    
    void leave(final GameContainer p0, final StateBasedGame p1) throws SlickException;
}
