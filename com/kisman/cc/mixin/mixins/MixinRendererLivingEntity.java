//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.mixin.mixins;

import net.minecraft.client.model.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import com.kisman.cc.module.render.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.combat.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import com.kisman.cc.util.*;
import com.kisman.cc.settings.*;
import org.spongepowered.asm.mixin.*;

@Mixin(value = { RenderLivingBase.class }, priority = 10000)
public abstract class MixinRendererLivingEntity<T extends EntityLivingBase> extends Render<T>
{
    @Shadow
    protected ModelBase mainModel;
    protected ModelBase entityModel;
    
    protected MixinRendererLivingEntity() {
        super((RenderManager)null);
    }
    
    @Inject(method = { "doRender" }, at = { @At("HEAD") })
    public void doRenderPre(final T entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo info) {
        if (Charms.instance.isToggled() && Charms.instance.render.getValBoolean() && Charms.instance.polygonMode.getValString().equalsIgnoreCase("doRender")) {
            GL11.glEnable(32823);
            GL11.glPolygonOffset(1.0f, -1100000.0f);
            if (Charms.instance.targetRender.getValBoolean() && entity instanceof EntityPlayer) {
                if (AutoCrystalBypass.instance.target == entity) {
                    GL11.glColor4f(1.0f, 0.03f, 0.9f, 1.0f);
                }
            }
            else if (Charms.instance.customColor.getValBoolean()) {
                final float[] color = { Charms.instance.color.getR() / 255.0f, Charms.instance.color.getG() / 255.0f, Charms.instance.color.getB() / 255.0f, Charms.instance.color.getA() / 255.0f };
                GL11.glColor4f(color[0], color[1], color[2], color[3]);
            }
        }
    }
    
    @Inject(method = { "doRender" }, at = { @At("RETURN") })
    public void doRenderPost(final T entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks, final CallbackInfo info) {
        if (Charms.instance.isToggled() && Charms.instance.render.getValBoolean() && Charms.instance.polygonMode.getValString().equalsIgnoreCase("doRender")) {
            GL11.glPolygonOffset(1.0f, 1000000.0f);
            GL11.glDisable(32823);
        }
    }
    
    @Overwrite
    protected void renderModel(final T p_renderModel_1_, final float p_renderModel_2_, final float p_renderModel_3_, final float p_renderModel_4_, final float p_renderModel_5_, final float p_renderModel_6_, final float p_renderModel_7_) {
        final boolean flag = this.isVisible(p_renderModel_1_);
        final boolean flag2 = !flag && !p_renderModel_1_.isInvisibleToPlayer((EntityPlayer)Minecraft.getMinecraft().player);
        if (flag || flag2) {
            if (!this.bindEntityTexture((Entity)p_renderModel_1_)) {
                return;
            }
            if (flag2) {
                GlStateManager.enableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            if (Charms.instance.isToggled() && p_renderModel_1_ instanceof EntityPlayer && Charms.instance.polygonMode.getValString().equalsIgnoreCase("RenderModel")) {
                GL11.glPushMatrix();
                GL11.glEnable(10754);
                if (Charms.instance.polygonOffset.getValBoolean()) {
                    GL11.glPolygonOffset(1.0f, 1000000.0f);
                }
                GL11.glDisable(3553);
                GL11.glDisable(2896);
                final Setting color = Charms.instance.color;
                if (Charms.instance.customColor.getValBoolean() && Charms.instance.textureMode.getValString().equalsIgnoreCase("GL")) {
                    if (Charms.instance.targetRender.getValBoolean() && p_renderModel_1_ instanceof EntityPlayer) {
                        if (AutoCrystalBypass.instance.target == p_renderModel_1_) {
                            GL11.glColor4f(1.0f, 0.03f, 0.9f, 1.0f);
                        }
                        else {
                            GL11.glColor4f(color.getR() / 255.0f, color.getG() / 255.0f, color.getB() / 255.0f, color.getA() / 255.0f);
                        }
                    }
                    else {
                        GL11.glColor4f(color.getR() / 255.0f, color.getG() / 255.0f, color.getB() / 255.0f, color.getA() / 255.0f);
                    }
                }
                GL11.glDisable(2929);
                this.mainModel.render((Entity)p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_);
                GL11.glEnable(2929);
                this.mainModel.render((Entity)p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_);
                RenderUtil.setupColor(new Color(268435455).hashCode());
                GL11.glEnable(3553);
                GL11.glEnable(2896);
                GL11.glDisable(10754);
                GL11.glPopMatrix();
            }
            else {
                this.mainModel.render((Entity)p_renderModel_1_, p_renderModel_2_, p_renderModel_3_, p_renderModel_4_, p_renderModel_5_, p_renderModel_6_, p_renderModel_7_);
            }
            if (flag2) {
                GlStateManager.disableBlendProfile(GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }
    
    protected boolean isVisible(final T p_isVisible_1_) {
        return !p_isVisible_1_.isInvisible() || this.renderOutlines;
    }
}
