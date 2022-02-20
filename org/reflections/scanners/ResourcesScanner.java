//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.reflections.scanners;

import org.reflections.vfs.*;

public class ResourcesScanner extends AbstractScanner
{
    public boolean acceptsInput(final String file) {
        return !file.endsWith(".class");
    }
    
    public Object scan(final Vfs.File file, final Object classObject) {
        this.getStore().put((Object)file.getName(), (Object)file.getRelativePath());
        return classObject;
    }
    
    public void scan(final Object cls) {
        throw new UnsupportedOperationException();
    }
}
