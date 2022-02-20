//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

public interface Texture
{
    boolean hasAlpha();
    
    String getTextureRef();
    
    void bind();
    
    int getImageHeight();
    
    int getImageWidth();
    
    float getHeight();
    
    float getWidth();
    
    int getTextureHeight();
    
    int getTextureWidth();
    
    void release();
    
    int getTextureID();
    
    byte[] getTextureData();
    
    void setTextureFilter(final int p0);
}
