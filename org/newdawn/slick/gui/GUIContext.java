//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.gui;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.*;
import org.lwjgl.input.*;

public interface GUIContext
{
    Input getInput();
    
    long getTime();
    
    int getScreenWidth();
    
    int getScreenHeight();
    
    int getWidth();
    
    int getHeight();
    
    Font getDefaultFont();
    
    void setMouseCursor(final String p0, final int p1, final int p2) throws SlickException;
    
    void setMouseCursor(final ImageData p0, final int p1, final int p2) throws SlickException;
    
    void setMouseCursor(final Cursor p0, final int p1, final int p2) throws SlickException;
    
    void setDefaultMouseCursor();
}
