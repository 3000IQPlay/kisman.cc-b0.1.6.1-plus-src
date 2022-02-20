//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ EntityPlayer.class })
public interface AccessorEntityPlayer
{
    @Accessor("speedInAir")
    void setSpeedInAir(final float p0);
}
