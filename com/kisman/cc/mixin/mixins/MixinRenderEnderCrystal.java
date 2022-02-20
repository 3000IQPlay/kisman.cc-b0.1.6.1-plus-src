//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.client.model.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import com.kisman.cc.module.render.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import com.kisman.cc.util.*;
import java.awt.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(value = { RenderEnderCrystal.class }, priority = 10000)
public abstract class MixinRenderEnderCrystal
{
    @Shadow
    public ModelBase modelEnderCrystal;
    @Shadow
    public ModelBase modelEnderCrystalNoBase;
    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    private static final ResourceLocation RES_ITEM_GLINT;
    
    @Shadow
    public abstract void doRender(final EntityEnderCrystal p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    private void render1(final ModelBase var1, final Entity var2, final float var3, final float var4, final float var5, final float var6, final float var7, final float var8) {
        if (!CrystalModifier.instance.isToggled()) {
            var1.render(var2, var3, var4, var5, var6, var7, var8);
        }
    }
    
    @Redirect(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V", ordinal = 1))
    private void render2(final ModelBase var1, final Entity var2, final float var3, final float var4, final float var5, final float var6, final float var7, final float var8) {
        if (!CrystalModifier.instance.isToggled()) {
            var1.render(var2, var3, var4, var5, var6, var7, var8);
        }
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = { @At("RETURN") }, cancellable = true)
    public void IdoRender(final EntityEnderCrystal entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo ci) {
        final Minecraft mc = Minecraft.getMinecraft();
        mc.gameSettings.fancyGraphics = false;
        if (CrystalModifier.instance.isToggled()) {
            final float var14 = entity.innerRotation + partialTicks;
            GL11.glPushMatrix();
            if (entity.equals((Object)CrystalModifier.instance.preview.getEntity())) {
                GL11.glScalef(1.0f, 1.0f, 1.0f);
            }
            else {
                GL11.glScaled(CrystalModifier.instance.scaleX.getValDouble(), CrystalModifier.instance.scaleY.getValDouble(), CrystalModifier.instance.scaleZ.getValDouble());
            }
            GL11.glRotated(CrystalModifier.instance.rotateX.getValDouble(), 1.0, 0.0, 0.0);
            GL11.glRotated(CrystalModifier.instance.rotateY.getValDouble(), 0.0, 1.0, 0.0);
            GL11.glRotated(CrystalModifier.instance.rotateZ.getValDouble(), 0.0, 0.0, 1.0);
            GL11.glTranslated(x + CrystalModifier.instance.translateX.getValDouble(), y + CrystalModifier.instance.translateY.getValDouble(), z + CrystalModifier.instance.translateZ.getValDouble());
            float var15 = MathHelper.sin(var14 * 0.2f) / 2.0f + 0.5f;
            var15 += var15 * var15;
            final float spinSpeed = (float)CrystalModifier.instance.speed.getValDouble();
            final float bounceSpeed = (float)CrystalModifier.instance.bounce.getValDouble();
            if (CrystalModifier.instance.texture.getValBoolean()) {
                if (entity.shouldShowBottom()) {
                    this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                }
                else {
                    this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                }
            }
            GL11.glPushAttrib(1048575);
            if (CrystalModifier.instance.mode.getValEnum().equals(CrystalModifier.Modes.Wireframe)) {
                GL11.glPolygonMode(1032, 6913);
            }
            mc.renderManager.renderEngine.bindTexture(MixinRenderEnderCrystal.ENDER_CRYSTAL_TEXTURES);
            GL11.glDisable(3008);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.5f);
            GL11.glEnable(2960);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glEnable(10754);
            if (CrystalModifier.instance.customColor.getValBoolean()) {
                GL11.glColor4f((float)(CrystalModifier.instance.crystalColor.getR() / 255), (float)(CrystalModifier.instance.crystalColor.getG() / 255), (float)(CrystalModifier.instance.crystalColor.getB() / 255), 1.0f);
            }
            else {
                GL11.glColor3f(1.0f, 1.0f, 1.0f);
            }
            if (entity.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
            }
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            if (CrystalModifier.instance.enchanted.getValBoolean()) {
                mc.getTextureManager().bindTexture(MixinRenderEnderCrystal.RES_ITEM_GLINT);
                GL11.glTexCoord3d(1.0, 1.0, 1.0);
                GL11.glEnable(3553);
                GL11.glBlendFunc(768, 771);
                GL11.glColor4f((float)(CrystalModifier.instance.enchColor.getR() / 255), (float)(CrystalModifier.instance.enchColor.getG() / 255), (float)(CrystalModifier.instance.enchColor.getB() / 255), (float)(CrystalModifier.instance.enchColor.getA() / 255));
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
            if (CrystalModifier.instance.outline.getValBoolean()) {
                if (CrystalModifier.instance.outlineMode.getValEnum().equals(CrystalModifier.OutlineModes.Wire)) {
                    GL11.glPushAttrib(1048575);
                    GL11.glPolygonMode(1032, 6913);
                    GL11.glDisable(3008);
                    GL11.glDisable(3553);
                    GL11.glDisable(2896);
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 771);
                    GL11.glLineWidth((float)CrystalModifier.instance.lineWidth.getValDouble());
                    GL11.glEnable(2960);
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                    GL11.glEnable(10754);
                    GL11.glColor4f((float)(CrystalModifier.instance.color.getR() / 255), (float)(CrystalModifier.instance.color.getG() / 255), (float)(CrystalModifier.instance.color.getB() / 255), 1.0f);
                    if (entity.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                    if (entity.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    GL11.glEnable(3042);
                    GL11.glEnable(2896);
                    GL11.glEnable(3553);
                    GL11.glEnable(3008);
                    GL11.glPopAttrib();
                }
                else {
                    OutlineUtils.setColor(CrystalModifier.instance.color);
                    OutlineUtils.renderOne((float)CrystalModifier.instance.lineWidth.getValDouble());
                    if (entity.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    OutlineUtils.renderTwo();
                    if (entity.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    OutlineUtils.renderThree();
                    OutlineUtils.renderFour();
                    OutlineUtils.setColor(CrystalModifier.instance.color);
                    if (entity.shouldShowBottom()) {
                        this.modelEnderCrystal.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    else {
                        this.modelEnderCrystalNoBase.render((Entity)entity, 0.0f, var14 * spinSpeed, var15 * bounceSpeed, 0.0f, 0.0f, 0.0625f);
                    }
                    OutlineUtils.renderFive();
                    OutlineUtils.setColor(Color.WHITE);
                }
            }
            GL11.glPopMatrix();
        }
    }
    
    static {
        RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
    }
}
