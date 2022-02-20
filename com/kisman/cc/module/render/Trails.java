//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import java.util.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import i.gishreloaded.gishcode.utils.*;

public class Trails extends Module
{
    private Setting removeTicks;
    private ArrayList<TrailUtil> bcs;
    
    public Trails() {
        super("Trails", "akrien moment", Category.RENDER);
        this.removeTicks = new Setting("RemoveTicks", this, 1.0, 1.0, 50.0, true);
        this.bcs = new ArrayList<TrailUtil>();
        Trails.setmgr.rSetting(this.removeTicks);
    }
    
    public void onEnable() {
        this.bcs.clear();
    }
    
    public void update() {
        if (Trails.mc.player == null && Trails.mc.world == null) {
            return;
        }
        this.bcs.add(new TrailUtil(Trails.mc.player.getPositionVector()));
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        final int time = this.removeTicks.getValInt() * 500;
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDisable(2884);
        Trails.mc.entityRenderer.disableLightmap();
        GL11.glLineWidth(3.0f);
        GlStateManager.resetColor();
        double lastPosX = 114514.0;
        double lastPosY = 114514.0;
        double lastPosZ = 114514.0;
        GL11.glBegin(7);
        for (int i = 0; i < this.bcs.size(); ++i) {
            final TrailUtil bc = this.bcs.get(i);
            ColorUtils.glColor(ColorUtils.astolfoColors(100, 100), 100);
            if (bc.getTimer().hasReached((float)time)) {
                this.bcs.remove(bc);
            }
            final double renderPosX = Trails.mc.getRenderManager().renderPosX;
            final double renderPosY = Trails.mc.getRenderManager().renderPosY;
            final double renderPosZ = Trails.mc.getRenderManager().renderPosZ;
            if (lastPosX != 114514.0 || lastPosY != 114514.0 || lastPosZ != 114514.0) {
                GL11.glVertex3d(bc.getVector().x - renderPosX, bc.getVector().y - renderPosY, bc.getVector().z - renderPosZ);
                GL11.glVertex3d(lastPosX, lastPosY, lastPosZ);
                GL11.glVertex3d(lastPosX, lastPosY + Trails.mc.player.height, lastPosZ);
                GL11.glVertex3d(bc.getVector().x - renderPosX, bc.getVector().y - renderPosY + Trails.mc.player.height, bc.getVector().z - renderPosZ);
            }
            lastPosX = bc.getVector().x - renderPosX;
            lastPosY = bc.getVector().y - renderPosY;
            lastPosZ = bc.getVector().z - renderPosZ;
        }
        GL11.glEnd();
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
    
    public class TrailUtil
    {
        private final Vec3d vector;
        private final TimerUtils timer;
        
        public TrailUtil(final Vec3d vector) {
            this.timer = new TimerUtils();
            this.vector = vector;
        }
        
        public TimerUtils getTimer() {
            return this.timer;
        }
        
        public Vec3d getVector() {
            return this.vector;
        }
    }
}
