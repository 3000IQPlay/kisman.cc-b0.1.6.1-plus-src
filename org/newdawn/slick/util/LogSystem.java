//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

public interface LogSystem
{
    void error(final String p0, final Throwable p1);
    
    void error(final Throwable p0);
    
    void error(final String p0);
    
    void warn(final String p0);
    
    void warn(final String p0, final Throwable p1);
    
    void info(final String p0);
    
    void debug(final String p0);
}
