//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.util.hwid;

public class NoStackTraceThrowable extends RuntimeException
{
    public NoStackTraceThrowable(final String msg) {
        super(msg);
        this.setStackTrace(new StackTraceElement[0]);
    }
    
    @Override
    public String toString() {
        return "HWID issue!";
    }
    
    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
