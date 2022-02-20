//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import com.kisman.cc.util.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import com.kisman.cc.*;
import java.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.math.*;

public class RangeVisualisator extends Module
{
    private Setting mode;
    private Setting radius;
    private Setting lineWidth;
    private Setting own;
    private Setting raytrace;
    
    public RangeVisualisator() {
        super("RangeVisualisator", "RangeVisualisator", Category.RENDER);
        this.mode = new Setting("Mode", this, "Sphere", new ArrayList<String>(Arrays.asList("Sphere", "Circle")));
        this.radius = new Setting("Radius", this, 4.5, 0.1, 8.0, false);
        this.lineWidth = new Setting("LineWidth", this, 1.5, 0.1, 5.0, false);
        this.own = new Setting("Own", this, "None", new ArrayList<String>(Arrays.asList("None", "Sphere", "Circle")));
        this.raytrace = new Setting("RayTrace", this, true);
        RangeVisualisator.setmgr.rSetting(this.mode);
        RangeVisualisator.setmgr.rSetting(this.radius);
        RangeVisualisator.setmgr.rSetting(this.lineWidth);
        RangeVisualisator.setmgr.rSetting(this.own);
        RangeVisualisator.setmgr.rSetting(this.raytrace);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (!this.mode.getValString().equalsIgnoreCase("None")) {
            this.render(event);
        }
        if (this.mode.getValString().equalsIgnoreCase("None") && !this.own.getValString().equalsIgnoreCase("None")) {
            this.render();
        }
    }
    
    private void render(final RenderWorldLastEvent event) {
        for (final EntityPlayer en : RangeVisualisator.mc.world.playerEntities) {
            if (en.isEntityEqual((Entity)RangeVisualisator.mc.player) && this.own.getValString().equalsIgnoreCase("None")) {
                continue;
            }
            if (this.mode.getValString().equalsIgnoreCase("Sphere") || this.own.getValString().equalsIgnoreCase("Sphere")) {
                int lines = 600 / Math.round(Math.max(RangeVisualisator.mc.player.getDistance((Entity)en), 1.0f));
                lines = Math.min(lines, 25);
                final double xPos = en.lastTickPosX + (en.posX - en.lastTickPosX) * RangeVisualisator.mc.timer.renderPartialTicks - RangeVisualisator.mc.getRenderManager().renderPosX;
                final double yPos = en.getEyeHeight() + (en.lastTickPosY + (en.posY - en.lastTickPosY) * RangeVisualisator.mc.timer.renderPartialTicks) - RangeVisualisator.mc.getRenderManager().renderPosY;
                final double zPos = en.lastTickPosZ + (en.posZ - en.lastTickPosZ) * RangeVisualisator.mc.timer.renderPartialTicks - RangeVisualisator.mc.getRenderManager().renderPosZ;
                final float range = 3.5f;
                if (RangeVisualisator.mc.player.getDistance((Entity)en) >= range) {
                    if (RangeVisualisator.mc.player.isOnSameTeam((Entity)en)) {
                        RenderUtil.drawSphere(0.5, 1.0, 0.5, 0.5, xPos, yPos, zPos, range, lines, lines, (float)this.lineWidth.getValDouble());
                    }
                    else {
                        RenderUtil.drawSphere(1.0, 0.8, 0.4, 0.5, xPos, yPos, zPos, range, lines, lines, (float)this.lineWidth.getValDouble());
                    }
                }
                else if (RangeVisualisator.mc.player.isOnSameTeam((Entity)en)) {
                    RenderUtil.drawSphere(1.0, 0.4, 0.6, 0.7, xPos, yPos, zPos, range, lines, lines, (float)this.lineWidth.getValDouble());
                }
                else {
                    RenderUtil.drawSphere(1.0, 0.6, 0.4, 0.7, xPos, yPos, zPos, range, lines, lines, (float)this.lineWidth.getValDouble());
                }
            }
            else {
                if (!this.mode.getValString().equalsIgnoreCase("Circle") && !this.own.getValString().equalsIgnoreCase("Circle")) {
                    continue;
                }
                GlStateManager.pushMatrix();
                GlStateManager.enableBlend();
                GlStateManager.disableTexture2D();
                GlStateManager.enableDepth();
                GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
                final RenderManager renderManager = RangeVisualisator.mc.getRenderManager();
                float hue = System.currentTimeMillis() % 7200L / 7200.0f;
                Color color = new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
                final ArrayList<Vec3d> hVectors = new ArrayList<Vec3d>();
                final double x = en.lastTickPosX + (en.posX - en.lastTickPosX) * event.getPartialTicks() - renderManager.renderPosX;
                final double y = en.lastTickPosY + (en.posY - en.lastTickPosY) * event.getPartialTicks() - renderManager.renderPosY;
                final double z = en.lastTickPosZ + (en.posZ - en.lastTickPosZ) * event.getPartialTicks() - renderManager.renderPosZ;
                GL11.glLineWidth((float)this.lineWidth.getValDouble());
                GL11.glBegin(1);
                for (int i = 0; i <= 360; ++i) {
                    final Vec3d vec = new Vec3d(x + Math.sin(i * 3.141592653589793 / 180.0) * this.radius.getValDouble(), y + 0.1, z + Math.cos(i * 3.141592653589793 / 180.0) * this.radius.getValDouble());
                    final RayTraceResult result = RangeVisualisator.mc.world.rayTraceBlocks(new Vec3d(en.posX, en.posY + en.getEyeHeight(), en.posZ), vec, false, false, true);
                    if (result != null && this.raytrace.getValBoolean()) {
                        Kisman.LOGGER.info("raytrace was not null");
                        hVectors.add(result.hitVec);
                    }
                    else {
                        hVectors.add(vec);
                    }
                }
                for (int j = 0; j < hVectors.size() - 1; ++j) {
                    GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
                    GL11.glVertex3d(hVectors.get(j).x, hVectors.get(j).y, hVectors.get(j).z);
                    GL11.glVertex3d(hVectors.get(j + 1).x, hVectors.get(j + 1).y, hVectors.get(j + 1).z);
                    color = new Color(Color.HSBtoRGB(hue += 0.0027777778f, 1.0f, 1.0f));
                }
                GL11.glEnd();
                GlStateManager.resetColor();
                GlStateManager.disableDepth();
                GlStateManager.enableTexture2D();
                GlStateManager.disableBlend();
                GlStateManager.popMatrix();
            }
        }
    }
}
