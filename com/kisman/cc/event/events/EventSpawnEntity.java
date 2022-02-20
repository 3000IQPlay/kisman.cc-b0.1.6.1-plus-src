//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events;

import com.kisman.cc.event.*;
import net.minecraft.entity.*;

public class EventSpawnEntity extends Event
{
    public Entity entity;
    
    public EventSpawnEntity(final Entity entity) {
        this.entity = entity;
    }
}
