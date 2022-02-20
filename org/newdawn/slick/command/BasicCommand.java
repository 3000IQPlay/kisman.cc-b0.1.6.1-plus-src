//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.command;

public class BasicCommand implements Command
{
    private String name;
    
    public BasicCommand(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }
    
    @Override
    public boolean equals(final Object other) {
        return other instanceof BasicCommand && ((BasicCommand)other).name.equals(this.name);
    }
    
    @Override
    public String toString() {
        return "[Command=" + this.name + "]";
    }
}
