//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package javassist.compiler;

import java.util.*;

public final class KeywordTable extends HashMap
{
    public int lookup(final String name) {
        final Object found = this.get(name);
        if (found == null) {
            return -1;
        }
        return (int)found;
    }
    
    public void append(final String name, final int t) {
        this.put(name, new Integer(t));
    }
}
