//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { EntityEnderCrystal.class }, priority = 1000)
public class MixinEntityEnderCrystal
{
    @Inject(method = { "attackEntityFrom" }, at = { @At("RETURN") }, cancellable = true)
    public void attackEntityFrom(final DamageSource source, final float amount, final CallbackInfoReturnable<Boolean> info) {
        if (source.getTrueSource() != null) {
            final EventCrystalAttack event = new EventCrystalAttack(source.getTrueSource().entityId);
            Kisman.EVENT_BUS.post((Object)event);
            if (event.isCancelled()) {
                info.cancel();
            }
        }
    }
}
