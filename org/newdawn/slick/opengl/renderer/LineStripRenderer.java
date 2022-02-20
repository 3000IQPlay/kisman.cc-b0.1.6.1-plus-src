//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl.renderer;

public interface LineStripRenderer
{
    boolean applyGLLineFixes();
    
    void start();
    
    void end();
    
    void vertex(final float p0, final float p1);
    
    void color(final float p0, final float p1, final float p2, final float p3);
    
    void setWidth(final float p0);
    
    void setAntiAlias(final boolean p0);
    
    void setLineCaps(final boolean p0);
}
