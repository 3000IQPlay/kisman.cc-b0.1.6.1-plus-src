//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.*;
import com.kisman.cc.event.events.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { World.class }, priority = 10000)
public class MixinWorld
{
    @Inject(method = { "onEntityAdded" }, at = { @At("HEAD") })
    public void onEntityAdded(final Entity entityIn, final CallbackInfo ci) {
        Kisman.EVENT_BUS.post((Object)new EventSpawnEntity(entityIn));
    }
}
