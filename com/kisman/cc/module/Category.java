//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module;

public enum Category
{
    COMBAT("Combat"), 
    CLIENT("Client"), 
    CHAT("Chat"), 
    MOVEMENT("Movement"), 
    PLAYER("Player"), 
    RENDER("Render"), 
    MISC("Misc"), 
    EXPLOIT("Exploit");
    
    private final String name;
    
    private Category(final String name) {
        this.name = name;
    }
}
