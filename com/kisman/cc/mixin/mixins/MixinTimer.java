//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { Timer.class }, priority = 10000)
public class MixinTimer
{
    @Shadow
    public float elapsedPartialTicks;
    
    @Inject(method = { "updateTimer" }, at = { @At(value = "FIELD", target = "net/minecraft/util/Timer.elapsedPartialTicks:F", ordinal = 1) })
    public void updateTimer(final CallbackInfo ci) {
        this.elapsedPartialTicks *= Kisman.TICK_TIMER;
    }
}
