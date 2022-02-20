//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.entity.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import net.minecraft.potion.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.module.render.*;
import net.minecraft.init.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { EntityLivingBase.class }, priority = 10000)
public abstract class MixinEntityLivingBase extends MixinEntity
{
    @Shadow
    public EnumHand swingingHand;
    @Shadow
    public ItemStack activeItemStack;
    @Shadow
    public float moveStrafing;
    @Shadow
    public float moveVertical;
    @Shadow
    public float moveForward;
    
    @Shadow
    protected void jump() {
    }
    
    @Shadow
    public abstract boolean isElytraFlying();
    
    @Shadow
    public boolean isPotionActive(final Potion potionIn) {
        return false;
    }
    
    @Shadow
    public PotionEffect getActivePotionEffect(final Potion potionIn) {
        return null;
    }
    
    @Inject(method = { "getArmSwingAnimationEnd" }, at = { @At("HEAD") }, cancellable = true)
    private void yesido(final CallbackInfoReturnable<Integer> cir) {
        if (Animation.instance.isToggled()) {
            if (this.isPotionActive(MobEffects.HASTE)) {
                cir.setReturnValue((Object)(Animation.instance.speed.getValInt() - this.getActivePotionEffect(MobEffects.HASTE).getAmplifier()));
            }
            else {
                cir.setReturnValue((Object)(this.isPotionActive(MobEffects.MINING_FATIGUE) ? (Animation.instance.speed.getValInt() + (this.getActivePotionEffect(MobEffects.MINING_FATIGUE).getAmplifier() + 1) * 2) : Animation.instance.speed.getValInt()));
            }
        }
    }
}
