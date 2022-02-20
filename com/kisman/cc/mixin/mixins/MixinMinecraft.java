//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.*;
import net.minecraft.client.settings.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.multiplayer.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.module.exploit.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.mixin.mixins.accessor.*;

@Mixin(value = { Minecraft.class }, priority = 10000)
public class MixinMinecraft
{
    @Shadow
    public GameSettings gameSettings;
    @Shadow
    public EntityPlayerSP player;
    @Shadow
    public PlayerControllerMP playerController;
    private boolean mt_handActive;
    private boolean mt_isHittingBlock;
    
    public MixinMinecraft() {
        this.mt_handActive = false;
        this.mt_isHittingBlock = false;
    }
    
    @Shadow
    private void clickMouse() {
    }
    
    @Inject(method = { "processKeyBinds" }, at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/settings/KeyBinding;isKeyDown()Z", shift = At.Shift.BEFORE, ordinal = 2) })
    public void mt_processKeyBinds(final CallbackInfo info) {
        if (MultiTask.instance.isToggled()) {
            while (this.gameSettings.keyBindAttack.isPressed()) {
                this.clickMouse();
            }
        }
    }
    
    @Inject(method = { "rightClickMouse" }, at = { @At("HEAD") })
    public void mt_rightClickMouse(final CallbackInfo info) {
        if (MultiTask.instance.isToggled()) {
            this.mt_isHittingBlock = this.playerController.getIsHittingBlock();
            ((AccessorPlayerControllerMP)this.playerController).mm_setIsHittingBlock(false);
        }
    }
    
    @Inject(method = { "rightClickMouse" }, at = { @At("RETURN") })
    public void mt_rightClickMousePost(final CallbackInfo ci) {
        if (MultiTask.instance.isToggled() && !this.playerController.getIsHittingBlock()) {
            ((AccessorPlayerControllerMP)this.playerController).mm_setIsHittingBlock(this.mt_isHittingBlock);
        }
    }
    
    @Inject(method = { "sendClickBlockToController" }, at = { @At("HEAD") })
    public void mt_sendClickBlockToControllerPre(final boolean leftClick, final CallbackInfo ci) {
        if (MultiTask.instance.isToggled()) {
            this.mt_handActive = this.player.isHandActive();
            ((IEntityPlayerSP)this.player).mm_setHandActive(false);
        }
    }
    
    @Inject(method = { "sendClickBlockToController" }, at = { @At("RETURN") })
    public void mt_sendClickBlockToControllerPost(final boolean leftClick, final CallbackInfo ci) {
        if (MultiTask.instance.isToggled() && !this.player.isHandActive()) {
            ((IEntityPlayerSP)this.player).mm_setHandActive(this.mt_handActive);
        }
    }
}
