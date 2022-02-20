//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.*;
import com.kisman.cc.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { MovementInputFromOptions.class }, priority = 10000)
public class MixinMovementInputFromOptions extends MovementInput
{
    @Inject(method = { "updatePlayerMoveState" }, at = { @At("RETURN") })
    public void updatePlayerMoveState(final CallbackInfo ci) {
        Kisman.EVENT_BUS.post((Object)new EventPlayerUpdateMoveState());
    }
}
