//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import org.lwjgl.input.*;
import org.newdawn.slick.util.*;
import org.lwjgl.*;
import java.io.*;
import java.nio.*;

public class CursorLoader
{
    private static CursorLoader single;
    
    public static CursorLoader get() {
        return CursorLoader.single;
    }
    
    private CursorLoader() {
    }
    
    public Cursor getCursor(final String ref, final int x, final int y) throws IOException, LWJGLException {
        LoadableImageData imageData = null;
        imageData = ImageDataFactory.getImageDataFor(ref);
        imageData.configureEdging(false);
        final ByteBuffer buf = imageData.loadImage(ResourceLoader.getResourceAsStream(ref), true, true, null);
        for (int i = 0; i < buf.limit(); i += 4) {
            final byte red = buf.get(i);
            final byte green = buf.get(i + 1);
            final byte blue = buf.get(i + 2);
            final byte alpha = buf.get(i + 3);
            buf.put(i + 2, red);
            buf.put(i + 1, green);
            buf.put(i, blue);
            buf.put(i + 3, alpha);
        }
        try {
            int yspot = imageData.getHeight() - y - 1;
            if (yspot < 0) {
                yspot = 0;
            }
            return new Cursor(imageData.getTexWidth(), imageData.getTexHeight(), x, yspot, 1, buf.asIntBuffer(), (IntBuffer)null);
        }
        catch (Throwable e) {
            Log.info("Chances are you cursor is too small for this platform");
            throw new LWJGLException(e);
        }
    }
    
    public Cursor getCursor(final ByteBuffer buf, final int x, final int y, final int width, final int height) throws IOException, LWJGLException {
        for (int i = 0; i < buf.limit(); i += 4) {
            final byte red = buf.get(i);
            final byte green = buf.get(i + 1);
            final byte blue = buf.get(i + 2);
            final byte alpha = buf.get(i + 3);
            buf.put(i + 2, red);
            buf.put(i + 1, green);
            buf.put(i, blue);
            buf.put(i + 3, alpha);
        }
        try {
            int yspot = height - y - 1;
            if (yspot < 0) {
                yspot = 0;
            }
            return new Cursor(width, height, x, yspot, 1, buf.asIntBuffer(), (IntBuffer)null);
        }
        catch (Throwable e) {
            Log.info("Chances are you cursor is too small for this platform");
            throw new LWJGLException(e);
        }
    }
    
    public Cursor getCursor(final ImageData imageData, final int x, final int y) throws IOException, LWJGLException {
        final ByteBuffer buf = imageData.getImageBufferData();
        for (int i = 0; i < buf.limit(); i += 4) {
            final byte red = buf.get(i);
            final byte green = buf.get(i + 1);
            final byte blue = buf.get(i + 2);
            final byte alpha = buf.get(i + 3);
            buf.put(i + 2, red);
            buf.put(i + 1, green);
            buf.put(i, blue);
            buf.put(i + 3, alpha);
        }
        try {
            int yspot = imageData.getHeight() - y - 1;
            if (yspot < 0) {
                yspot = 0;
            }
            return new Cursor(imageData.getTexWidth(), imageData.getTexHeight(), x, yspot, 1, buf.asIntBuffer(), (IntBuffer)null);
        }
        catch (Throwable e) {
            Log.info("Chances are you cursor is too small for this platform");
            throw new LWJGLException(e);
        }
    }
    
    public Cursor getAnimatedCursor(final String ref, final int x, final int y, final int width, final int height, final int[] cursorDelays) throws IOException, LWJGLException {
        final IntBuffer cursorDelaysBuffer = ByteBuffer.allocateDirect(cursorDelays.length * 4).order(ByteOrder.nativeOrder()).asIntBuffer();
        for (int i = 0; i < cursorDelays.length; ++i) {
            cursorDelaysBuffer.put(cursorDelays[i]);
        }
        cursorDelaysBuffer.flip();
        final LoadableImageData imageData = new TGAImageData();
        final ByteBuffer buf = imageData.loadImage(ResourceLoader.getResourceAsStream(ref), false, null);
        return new Cursor(width, height, x, y, cursorDelays.length, buf.asIntBuffer(), cursorDelaysBuffer);
    }
    
    static {
        CursorLoader.single = new CursorLoader();
    }
}
