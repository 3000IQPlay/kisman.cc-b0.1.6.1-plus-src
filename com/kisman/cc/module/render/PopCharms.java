//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import net.minecraft.client.entity.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import com.kisman.cc.module.*;
import com.kisman.cc.event.events.subscribe.*;
import com.mojang.authlib.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class PopCharms extends Module
{
    public Setting selfPop;
    private Setting angle;
    private Setting angleSpeed;
    private Setting fadeSpeed;
    private Setting width;
    private Setting colorR;
    private Setting colorG;
    private Setting colorB;
    private Setting colorA;
    private Setting fillColor;
    private Setting outColor;
    private EntityOtherPlayerMP player;
    private EntityPlayer entity;
    private Color color;
    public long startTime;
    public float opacity;
    public long time;
    public long duration;
    public float startAlpha;
    
    public PopCharms() {
        super("PopCharms", "and?", Category.RENDER);
        this.selfPop = new Setting("SelfPop", this, false);
        this.angle = new Setting("Angle", this, false);
        this.angleSpeed = new Setting("AngleSpeed", this, 150.0, 0.0, 500.0, true);
        this.fadeSpeed = new Setting("FadeSpeed", this, 200.0, 0.0, 500.0, true);
        this.width = new Setting("Width", this, Double.longBitsToDouble(Double.doubleToLongBits(0.10667784123174527) ^ 0x7FB34F3D2F4C588FL), Double.longBitsToDouble(Double.doubleToLongBits(2.8356779810862056) ^ 0x7FE6AF77EFF6053EL), Double.longBitsToDouble(Double.doubleToLongBits(0.14239240361793695) ^ 0x7FD639EA0E5E7291L), false);
        this.colorR = new Setting("ColorR", this, 1.0, 0.0, 1.0, false);
        this.colorG = new Setting("ColorG", this, 1.0, 0.0, 1.0, false);
        this.colorB = new Setting("ColorB", this, 1.0, 0.0, 1.0, false);
        this.colorA = new Setting("ColorA", this, 1.0, 0.0, 1.0, false);
        this.fillColor = new Setting("FillColor", this, "FillColor", new float[] { 0.3f, 0.3f, 0.3f, 1.0f });
        this.outColor = new Setting("OutlineColor", this, "OutlineColor", new float[] { 0.3f, 0.3f, 0.3f, 1.0f });
        PopCharms.setmgr.rSetting(this.selfPop);
        PopCharms.setmgr.rSetting(this.angle);
        PopCharms.setmgr.rSetting(this.angleSpeed);
        PopCharms.setmgr.rSetting(this.fadeSpeed);
        PopCharms.setmgr.rSetting(this.width);
        PopCharms.setmgr.rSetting(this.colorR);
        PopCharms.setmgr.rSetting(this.colorG);
        PopCharms.setmgr.rSetting(this.colorB);
        PopCharms.setmgr.rSetting(this.colorA);
    }
    
    @SubscribeEvent
    public void onPop(final TotemPopEvent event) {
        if (!this.selfPop.getValBoolean() && event.getPopEntity() == PopCharms.mc.player) {
            return;
        }
        if (!(event.getPopEntity() instanceof EntityPlayer)) {
            return;
        }
        this.entity = (EntityPlayer)event.getPopEntity();
        final GameProfile profile = new GameProfile(PopCharms.mc.player.getUniqueID(), "");
        (this.player = new EntityOtherPlayerMP((World)PopCharms.mc.world, profile)).copyLocationAndAnglesFrom((Entity)this.entity);
        this.player.rotationYaw = this.entity.rotationYaw;
        this.player.rotationYawHead = this.entity.rotationYawHead;
        this.player.rotationPitch = this.entity.rotationPitch;
        this.player.prevRotationPitch = this.entity.prevRotationPitch;
        this.player.prevRotationYaw = this.entity.prevRotationYaw;
        this.player.renderYawOffset = this.entity.renderYawOffset;
        this.startTime = System.currentTimeMillis();
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (this.player == null || this.entity == null) {
            return;
        }
        this.color = new Color(255, 255, 255, 255);
        this.opacity = Float.intBitsToFloat(Float.floatToIntBits(1.6358529E38f) ^ 0x7EF622C3);
        this.time = System.currentTimeMillis();
        this.duration = this.time - this.startTime;
        this.startAlpha = this.color.getAlpha() / 255.0f;
        if (this.duration < this.fadeSpeed.getValInt() * 10) {
            this.opacity = this.startAlpha - this.duration / (float)(this.fadeSpeed.getValInt() * 10);
        }
        if (this.duration < this.fadeSpeed.getValInt() * 10) {
            GL11.glPushMatrix();
            if (this.angle.getValBoolean()) {
                GlStateManager.translate(Float.intBitsToFloat(Float.floatToIntBits(1.240196E38f) ^ 0x7EBA9A9D), this.duration / (float)(this.angleSpeed.getValInt() * 10), Float.intBitsToFloat(Float.floatToIntBits(3.0414126E38f) ^ 0x7F64CF7A));
            }
            PopCharms.mc.renderManager.renderEntityStatic((Entity)this.player, Float.intBitsToFloat(Float.floatToIntBits(6.159893f) ^ 0x7F451DD8), false);
            GlStateManager.translate(Float.intBitsToFloat(Float.floatToIntBits(3.0715237E38f) ^ 0x7F671365), Float.intBitsToFloat(Float.floatToIntBits(1.9152719E37f) ^ 0x7D668ADF), Float.intBitsToFloat(Float.floatToIntBits(1.9703683E38f) ^ 0x7F143BEA));
            GL11.glPopMatrix();
        }
    }
}
