//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.command;

import net.minecraft.client.*;

public abstract class Command
{
    protected static Minecraft mc;
    private String command;
    private String execute;
    private int key;
    
    public Command(final String command) {
        this.command = command;
        this.key = -1;
    }
    
    public abstract void runCommand(final String p0, final String[] p1);
    
    public abstract String getDescription();
    
    public abstract String getSyntax();
    
    public String getCommand() {
        return this.command;
    }
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    public String getExecute() {
        return this.execute;
    }
    
    public void setExecute(final String execute) {
        this.execute = execute;
    }
    
    static {
        Command.mc = Minecraft.getMinecraft();
    }
}
