//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;

public class EventCrystalAttack extends Event
{
    public int entityId;
    
    public EventCrystalAttack(final int entityId) {
        this.entityId = entityId;
    }
    
    public int getEntityID() {
        return this.entityId;
    }
}
