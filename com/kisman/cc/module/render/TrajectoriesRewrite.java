//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.util.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;

public class TrajectoriesRewrite extends Module
{
    private double motionX;
    private double motionZ;
    private double motionY;
    private double r;
    private double g;
    private double b;
    private double x;
    private double y;
    private double z;
    
    public TrajectoriesRewrite() {
        super("TrajectoriesRewrite", Category.RENDER);
        this.r = 0.0;
        this.g = 1.0;
        this.b = 0.0;
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        final EntityPlayerSP player = TrajectoriesRewrite.mc.player;
        if (player.inventory.getCurrentItem() != null && this.isThrowable(player.inventory.getCurrentItem().getItem())) {
            this.x = player.lastTickPosX + (player.posX - player.lastTickPosX) * TrajectoriesRewrite.mc.timer.renderPartialTicks - MathHelper.cos((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationYaw)) * 0.16f;
            this.y = TrajectoriesRewrite.mc.player.lastTickPosY + (TrajectoriesRewrite.mc.player.posY - TrajectoriesRewrite.mc.player.lastTickPosY) * TrajectoriesRewrite.mc.timer.renderPartialTicks + TrajectoriesRewrite.mc.player.getEyeHeight() - 0.100149011612;
            this.z = TrajectoriesRewrite.mc.player.lastTickPosZ + (TrajectoriesRewrite.mc.player.posZ - TrajectoriesRewrite.mc.player.lastTickPosZ) * TrajectoriesRewrite.mc.timer.renderPartialTicks - MathHelper.sin((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationYaw)) * 0.16f;
            float con = 1.0f;
            if (!(TrajectoriesRewrite.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow)) {
                con = 0.4f;
            }
            this.motionX = -MathHelper.sin((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationYaw)) * MathHelper.cos((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationPitch)) * con;
            this.motionZ = MathHelper.cos((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationYaw)) * MathHelper.cos((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationPitch)) * con;
            this.motionY = -MathHelper.sin((float)Math.toRadians(TrajectoriesRewrite.mc.player.rotationPitch)) * con;
            final double ssum = Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
            this.motionX /= ssum;
            this.motionY /= ssum;
            this.motionZ /= ssum;
            if (TrajectoriesRewrite.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow) {
                float pow = (72000 - TrajectoriesRewrite.mc.player.getItemInUseCount()) / 20.0f;
                pow = (pow * pow + pow * 2.0f) / 3.0f;
                if (pow > 1.0f) {
                    pow = 1.0f;
                }
                if (pow <= 0.1f) {
                    pow = 1.0f;
                }
                pow *= 2.0f;
                pow *= 1.5f;
                this.motionX *= pow;
                this.motionY *= pow;
                this.motionZ *= pow;
            }
            else {
                this.motionX *= 1.5;
                this.motionY *= 1.5;
                this.motionZ *= 1.5;
            }
            final Vec3d playerVector = new Vec3d(TrajectoriesRewrite.mc.player.posX, TrajectoriesRewrite.mc.player.posY + TrajectoriesRewrite.mc.player.getEyeHeight(), TrajectoriesRewrite.mc.player.posZ);
            GL11.glPushMatrix();
            this.enableDefaults();
            GL11.glLineWidth(1.8f);
            GL11.glColor3d(this.r, this.g, this.b);
            GL11.glBegin(3);
            final double gravity = this.getGravity(TrajectoriesRewrite.mc.player.inventory.getCurrentItem().getItem());
            for (int q = 0; q < 1000; ++q) {
                final double rx = this.x * 1.0 - TrajectoriesRewrite.mc.renderManager.renderPosX;
                final double ry = this.y * 1.0 - TrajectoriesRewrite.mc.renderManager.renderPosY;
                final double rz = this.z * 1.0 - TrajectoriesRewrite.mc.renderManager.renderPosZ;
                GL11.glVertex3d(rx, ry, rz);
                this.x += this.motionX;
                this.y += this.motionY;
                this.z += this.motionZ;
                this.motionX *= 0.99;
                this.motionY *= 0.99;
                this.motionZ *= 0.99;
                this.motionY -= gravity;
                if (TrajectoriesRewrite.mc.world.rayTraceBlocks(playerVector, new Vec3d(this.x, this.y, this.z)) != null) {
                    break;
                }
            }
            GL11.glEnd();
            GL11.glTranslated(this.x - TrajectoriesRewrite.mc.renderManager.renderPosX, this.y - TrajectoriesRewrite.mc.renderManager.renderPosY, this.z - TrajectoriesRewrite.mc.renderManager.renderPosZ);
            GL11.glRotatef(TrajectoriesRewrite.mc.player.rotationYaw, 0.0f, (float)(this.y - TrajectoriesRewrite.mc.renderManager.renderPosY), 0.0f);
            GL11.glTranslated(-(this.x - TrajectoriesRewrite.mc.renderManager.renderPosX), -(this.y - TrajectoriesRewrite.mc.renderManager.renderPosY), -(this.z - TrajectoriesRewrite.mc.renderManager.renderPosZ));
            RenderUtil.drawESP(this.x - 0.35 - TrajectoriesRewrite.mc.renderManager.renderPosX, this.y - 0.5 - TrajectoriesRewrite.mc.renderManager.renderPosY, this.z - 0.5 - TrajectoriesRewrite.mc.renderManager.renderPosZ, this.r, this.b, this.g);
            this.disableDefaults();
            GL11.glPopMatrix();
        }
    }
    
    public void enableDefaults() {
        GL11.glDisable(2896);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glEnable(32925);
        GL11.glDepthMask(false);
    }
    
    public void disableDefaults() {
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(32925);
        GL11.glDepthMask(true);
        GL11.glDisable(2848);
        GL11.glEnable(2896);
    }
    
    private double getGravity(final Item item) {
        return (item instanceof ItemBow) ? 0.05 : 0.03;
    }
    
    private boolean isThrowable(final Item item) {
        return item instanceof ItemBow || item instanceof ItemSnowball || item instanceof ItemEgg || item instanceof ItemEnderPearl;
    }
    
    public void drawLine3D(final float var1, final float var2, final float var3, final float var4, final float var5, final float var6) {
        GL11.glVertex3d((double)var1, (double)var2, (double)var3);
        GL11.glVertex3d((double)var4, (double)var5, (double)var6);
    }
}
