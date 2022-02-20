//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick;

public interface MouseListener extends ControlledInputReciever
{
    void mouseWheelMoved(final int p0);
    
    void mouseClicked(final int p0, final int p1, final int p2, final int p3);
    
    void mousePressed(final int p0, final int p1, final int p2);
    
    void mouseReleased(final int p0, final int p1, final int p2);
    
    void mouseMoved(final int p0, final int p1, final int p2, final int p3);
    
    void mouseDragged(final int p0, final int p1, final int p2, final int p3);
}
