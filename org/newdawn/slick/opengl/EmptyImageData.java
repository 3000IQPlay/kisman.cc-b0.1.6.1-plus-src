//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import java.nio.*;
import org.lwjgl.*;

public class EmptyImageData implements ImageData
{
    private int width;
    private int height;
    
    public EmptyImageData(final int width, final int height) {
        this.width = width;
        this.height = height;
    }
    
    @Override
    public int getDepth() {
        return 32;
    }
    
    @Override
    public int getHeight() {
        return this.height;
    }
    
    @Override
    public ByteBuffer getImageBufferData() {
        return BufferUtils.createByteBuffer(this.getTexWidth() * this.getTexHeight() * 4);
    }
    
    @Override
    public int getTexHeight() {
        return InternalTextureLoader.get2Fold(this.height);
    }
    
    @Override
    public int getTexWidth() {
        return InternalTextureLoader.get2Fold(this.width);
    }
    
    @Override
    public int getWidth() {
        return this.width;
    }
}
