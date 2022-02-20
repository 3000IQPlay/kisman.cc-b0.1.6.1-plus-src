//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.*;
import com.kisman.cc.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.event.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.event.events.*;

@Mixin(value = { EntityPlayerSP.class }, priority = 10000)
public abstract class MixinEntityPlayerSP extends MixinAbstractClientPlayer
{
    @Shadow
    public MovementInput movementInput;
    
    @Shadow
    protected abstract boolean isCurrentViewEntity();
    
    @Inject(method = { "move" }, at = { @At("HEAD") }, cancellable = true)
    public void move(final MoverType type, final double x, final double y, final double z, final CallbackInfo ci) {
        final EventPlayerMove event = new EventPlayerMove(type, x, y, z);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            this.move(type, event.x, event.y, event.z);
            ci.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("HEAD") }, cancellable = true)
    public void onPreUpdateWalkingPlayer(final CallbackInfo ci) {
        final EventPlayerMotionUpdate event = new EventPlayerMotionUpdate(Event.Era.PRE, this.posX, this.getEntityBoundingBox().minY, this.posZ, this.onGround);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "onUpdateWalkingPlayer" }, at = { @At("RETURN") }, cancellable = true)
    public void onPostUpdateWalkingPlayer(final CallbackInfo ci) {
        final EventPlayerMotionUpdate event = new EventPlayerMotionUpdate(Event.Era.POST, this.posX, this.getEntityBoundingBox().minY, this.posZ, this.onGround);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "onUpdate" }, at = { @At("HEAD") }, cancellable = true)
    public void onUpdate(final CallbackInfo ci) {
        final EventPlayerUpdate event = new EventPlayerUpdate();
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            ci.cancel();
        }
    }
    
    @Inject(method = { "pushOutOfBlocks(DDD)Z" }, at = { @At("HEAD") }, cancellable = true)
    public void pushOutOfBlocks(final double x, final double y, final double z, final CallbackInfoReturnable<Boolean> cir) {
        final EventPlayerPushOutOfBlocks event = new EventPlayerPushOutOfBlocks(x, y, z);
        Kisman.EVENT_BUS.post((Object)event);
        if (event.isCancelled()) {
            cir.setReturnValue((Object)false);
        }
    }
}
