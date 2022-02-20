//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.event.events.subscribe;

import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class TotemPopEvent extends Event
{
    private Entity popEntity;
    
    public TotemPopEvent(final Entity popEntity) {
        this.popEntity = popEntity;
    }
    
    public Entity getPopEntity() {
        return this.popEntity;
    }
}
