//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat.totempopcounter;

import net.minecraft.entity.player.*;

public class Totem
{
    public EntityPlayer player;
    public int popsCount;
    
    public Totem(final EntityPlayer player) {
        this.player = player;
        this.popsCount = 0;
    }
    
    public void addPop() {
        ++this.popsCount;
    }
}
