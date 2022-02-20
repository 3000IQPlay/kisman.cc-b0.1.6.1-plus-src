//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.gen.*;
import net.minecraft.util.*;

@Mixin({ Minecraft.class })
public interface IMinecraft
{
    @Accessor("session")
    void setSession(final Session p0);
    
    @Accessor("rightClickDelayTimer")
    void setRightClickDelayTimer(final int p0);
    
    @Accessor("timer")
    Timer getTimer();
}
