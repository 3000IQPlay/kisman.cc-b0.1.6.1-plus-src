//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package org.newdawn.slick.command;

public class KeyControl implements Control
{
    private int keycode;
    
    public KeyControl(final int keycode) {
        this.keycode = keycode;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof KeyControl && ((KeyControl)o).keycode == this.keycode;
    }
    
    @Override
    public int hashCode() {
        return this.keycode;
    }
}
