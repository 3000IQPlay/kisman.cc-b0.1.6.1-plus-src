//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.module.render.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { RenderGlobal.class }, priority = 10000)
public class MixinRenderGlobal
{
    @Inject(method = { "drawSelectionBox" }, at = { @At("HEAD") }, cancellable = true)
    public void onDrawSelectionBox(final EntityPlayer player, final RayTraceResult movingObjectPositionIn, final int execute, final float partialTicks, final CallbackInfo ci) {
        if (BlockOutline.instance.isToggled()) {
            ci.cancel();
        }
    }
}
