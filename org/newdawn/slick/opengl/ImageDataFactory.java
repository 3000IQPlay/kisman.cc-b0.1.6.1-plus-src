//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import org.newdawn.slick.util.*;
import java.security.*;

public class ImageDataFactory
{
    private static boolean usePngLoader;
    private static boolean pngLoaderPropertyChecked;
    private static final String PNG_LOADER = "org.newdawn.slick.pngloader";
    
    private static void checkProperty() {
        if (!ImageDataFactory.pngLoaderPropertyChecked) {
            ImageDataFactory.pngLoaderPropertyChecked = true;
            try {
                AccessController.doPrivileged((PrivilegedAction<Object>)new PrivilegedAction() {
                    @Override
                    public Object run() {
                        final String val = System.getProperty("org.newdawn.slick.pngloader");
                        if ("false".equalsIgnoreCase(val)) {
                            ImageDataFactory.usePngLoader = false;
                        }
                        Log.info("Use Java PNG Loader = " + ImageDataFactory.usePngLoader);
                        return null;
                    }
                });
            }
            catch (Throwable t) {}
        }
    }
    
    public static LoadableImageData getImageDataFor(String ref) {
        checkProperty();
        ref = ref.toLowerCase();
        if (ref.endsWith(".tga")) {
            return new TGAImageData();
        }
        if (ref.endsWith(".png")) {
            final CompositeImageData data = new CompositeImageData();
            if (ImageDataFactory.usePngLoader) {
                data.add((LoadableImageData)new PNGImageData());
            }
            data.add((LoadableImageData)new ImageIOImageData());
            return (LoadableImageData)data;
        }
        return new ImageIOImageData();
    }
    
    static {
        ImageDataFactory.usePngLoader = true;
        ImageDataFactory.pngLoaderPropertyChecked = false;
    }
}
