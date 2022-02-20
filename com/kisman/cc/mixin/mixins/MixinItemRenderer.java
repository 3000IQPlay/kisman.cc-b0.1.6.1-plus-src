//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import com.kisman.cc.module.render.*;
import com.kisman.cc.util.*;
import com.kisman.cc.*;
import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.injection.*;
import com.kisman.cc.settings.*;

@Mixin(value = { ItemRenderer.class }, priority = 10000)
public class MixinItemRenderer
{
    private final Minecraft mc;
    
    public MixinItemRenderer() {
        this.mc = Minecraft.getMinecraft();
    }
    
    @Shadow
    private void transformSideFirstPerson(final EnumHandSide hand, final float p_187459_2_) {
    }
    
    @Redirect(method = { "renderItemInFirstPerson(Lnet/minecraft/client/entity/AbstractClientPlayer;FFLnet/minecraft/util/EnumHand;FLnet/minecraft/item/ItemStack;F)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/ItemRenderer;transformSideFirstPerson(Lnet/minecraft/util/EnumHandSide;F)V"))
    public void transformRedirect(final ItemRenderer renderer, final EnumHandSide hand, final float y) {
        if (ViewModel.instance.isToggled() || SwingAnimation.instance.isToggled()) {
            float rotateMainX = 0.0f;
            float rotateMainY = 0.0f;
            float rotateMainZ = 0.0f;
            final boolean isSwing = this.mc.player.swingProgress > 0.0f && SwingAnimation.instance.isToggled() && SwingAnimation.instance.mode.getValString().equalsIgnoreCase("Strong");
            final boolean isSwingMain = isSwing && hand == EnumHandSide.RIGHT && (!SwingAnimation.instance.ignoreEating.getValBoolean() || !PlayerUtil.IsEating());
            if (isSwing) {
                switch ((SwingAnimation.StrongMode)SwingAnimation.instance.strongMode.getValEnum()) {
                    case Blockhit1: {
                        if (hand == EnumHandSide.RIGHT) {
                            rotateMainX = 72.0f;
                            rotateMainY = 180.0f;
                            rotateMainZ = 240.0f;
                            break;
                        }
                        break;
                    }
                    case Blockhit2: {
                        if (hand == EnumHandSide.RIGHT) {
                            rotateMainX = 344.0f;
                            rotateMainY = 225.0f;
                            rotateMainZ = 0.0f;
                            break;
                        }
                        break;
                    }
                }
            }
            else if (this.mc.player.swingProgress == 0.0f) {
                rotateMainX = 0.0f;
                rotateMainY = 0.0f;
                rotateMainZ = 0.0f;
            }
            if (Kisman.instance.moduleManager.getModule("ViewModel").isToggled() && hand == EnumHandSide.RIGHT) {
                GlStateManager.translate(this.getSet("RightX").getValDouble(), this.getSet("RightY").getValDouble(), this.getSet("RightZ").getValDouble());
                GlStateManager.rotate(isSwingMain ? rotateMainX : (ViewModel.instance.autoRotateRigthX.getValBoolean() ? (System.currentTimeMillis() % 22600L / 5.0f) : ((float)this.getSet("RotateRightX").getValDouble())), 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(isSwingMain ? rotateMainY : (ViewModel.instance.autoRotateRigthY.getValBoolean() ? (System.currentTimeMillis() % 22600L / 5.0f) : ((float)this.getSet("RotateRightY").getValDouble())), 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(isSwingMain ? rotateMainZ : (ViewModel.instance.autoRotateRigthZ.getValBoolean() ? (System.currentTimeMillis() % 22600L / 5.0f) : ((float)this.getSet("RotateRightZ").getValDouble())), 0.0f, 0.0f, 1.0f);
                GlStateManager.scale(ViewModel.instance.scaleRightX.getValDouble(), ViewModel.instance.scaleRightY.getValDouble(), ViewModel.instance.scaleRightZ.getValDouble());
            }
            if (Kisman.instance.moduleManager.getModule("ViewModel").isToggled() && hand == EnumHandSide.LEFT) {
                GlStateManager.translate(this.getSet("LeftX").getValDouble(), this.getSet("LeftY").getValDouble(), this.getSet("LeftZ").getValDouble());
                GlStateManager.rotate(ViewModel.instance.autoRotateLeftX.getValBoolean() ? (System.currentTimeMillis() % 22600L / 5.0f) : ((float)this.getSet("RotateLeftX").getValDouble()), 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(ViewModel.instance.autoRotateLeftY.getValBoolean() ? (System.currentTimeMillis() % 22600L / 5.0f) : ((float)this.getSet("RotateLeftY").getValDouble()), 0.0f, 1.0f, 0.0f);
                GlStateManager.rotate(ViewModel.instance.autoRotateLeftZ.getValBoolean() ? (System.currentTimeMillis() % 22600L / 5.0f) : ((float)this.getSet("RotateLeftZ").getValDouble()), 0.0f, 0.0f, 1.0f);
                GlStateManager.scale(ViewModel.instance.scaleLeftX.getValDouble(), ViewModel.instance.scaleLeftY.getValDouble(), ViewModel.instance.scaleLeftZ.getValDouble());
            }
        }
        else {
            this.transformSideFirstPerson(hand, y);
        }
    }
    
    private Setting getSet(final String name) {
        return Kisman.instance.settingsManager.getSettingByName(Kisman.instance.moduleManager.getModule("ViewModel"), name);
    }
}
