//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick;

public interface ControlledInputReciever
{
    void setInput(final Input p0);
    
    boolean isAcceptingInput();
    
    void inputEnded();
    
    void inputStarted();
}
