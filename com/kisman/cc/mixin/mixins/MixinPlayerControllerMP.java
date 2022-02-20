//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { PlayerControllerMP.class }, priority = 10000)
public class MixinPlayerControllerMP
{
    @Inject(method = { "onPlayerDestroyBlock" }, at = { @At("RETURN") })
    public void playerDestroyBlock(final BlockPos pos, final CallbackInfoReturnable<Boolean> cir) {
        final DestroyBlockEvent event = new DestroyBlockEvent(pos);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }
}
