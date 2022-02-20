//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import java.nio.*;

public interface ImageData
{
    int getDepth();
    
    int getWidth();
    
    int getHeight();
    
    int getTexWidth();
    
    int getTexHeight();
    
    ByteBuffer getImageBufferData();
}
