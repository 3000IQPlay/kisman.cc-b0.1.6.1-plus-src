//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.event.events.*;

@Mixin(value = { EntityPlayer.class }, priority = Integer.MAX_VALUE)
public abstract class MixinEntityPlayer extends MixinEntityLivingBase
{
    @Shadow
    protected abstract void doWaterSplashEffect();
    
    @Inject(method = { "travel" }, at = { @At("HEAD") }, cancellable = true)
    private void onTravel(final float strafe, final float vertical, final float forward, final CallbackInfo ci) {
        final EventPlayerTravel event = new EventPlayerTravel(strafe, vertical, forward);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            ci.cancel();
        }
    }
    
    @Inject(method = { "applyEntityCollision" }, at = { @At("HEAD") }, cancellable = true)
    private void applyEntityCollision(final Entity entity, final CallbackInfo ci) {
        final EventPlayerApplyCollision event = new EventPlayerApplyCollision(entity);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "isPushedByWater()Z" }, at = { @At("HEAD") }, cancellable = true)
    private void isPushedByWater(final CallbackInfoReturnable<Boolean> cir) {
        final EventPlayerPushedByWater event = new EventPlayerPushedByWater();
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }
}
