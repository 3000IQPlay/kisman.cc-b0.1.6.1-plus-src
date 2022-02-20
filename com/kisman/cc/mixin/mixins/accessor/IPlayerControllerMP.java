//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins.accessor;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import org.spongepowered.asm.mixin.gen.*;

@Mixin({ PlayerControllerMP.class })
public interface IPlayerControllerMP
{
    @Accessor("curBlockDamageMP")
    void setCurrentBlockDamage(final float p0);
    
    @Accessor("blockHitDelay")
    void setBlockHitDelay(final int p0);
    
    @Invoker("syncCurrentPlayItem")
    void syncCurrentPlayItem();
}
