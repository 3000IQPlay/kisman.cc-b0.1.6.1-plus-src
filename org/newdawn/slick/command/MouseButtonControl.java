//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.command;

public class MouseButtonControl implements Control
{
    private int button;
    
    public MouseButtonControl(final int button) {
        this.button = button;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof MouseButtonControl && ((MouseButtonControl)o).button == this.button;
    }
    
    @Override
    public int hashCode() {
        return this.button;
    }
}
