//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import java.util.*;
import com.kisman.cc.module.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraftforge.fml.common.eventhandler.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.util.math.*;

public class Breadcrumbs extends Module
{
    private Setting lineWidth;
    private Setting removeTicks;
    private Setting alpha;
    private ArrayList<Helper> positions;
    
    public Breadcrumbs() {
        super("Breadcrumbs", ", ", Category.RENDER);
        this.lineWidth = new Setting("Line Width", this, 1.0, 1.0, 6.0, true);
        this.removeTicks = new Setting("Remove Ticks", this, 10.0, 0.0, 50.0, true);
        this.alpha = new Setting("Alpha", this, 100.0, 0.0, 255.0, true);
        this.positions = new ArrayList<Helper>();
        Breadcrumbs.setmgr.rSetting(this.lineWidth);
        Breadcrumbs.setmgr.rSetting(this.removeTicks);
        Breadcrumbs.setmgr.rSetting(this.alpha);
    }
    
    public void onEnable() {
        this.positions.clear();
    }
    
    public void onDisable() {
        this.positions.clear();
    }
    
    public void update() {
        if (Breadcrumbs.mc.player == null && Breadcrumbs.mc.world == null) {
            return;
        }
        if (PlayerUtil.isMoving((EntityLivingBase)Breadcrumbs.mc.player)) {
            this.positions.add(new Helper(Breadcrumbs.mc.player.getPositionVector()));
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDisable(2884);
        Breadcrumbs.mc.entityRenderer.disableLightmap();
        GL11.glLineWidth(this.lineWidth.getValFloat());
        GlStateManager.resetColor();
        double lastPosX = 114514.0;
        double lastPosY = 114514.0;
        double lastPosZ = 114514.0;
        GL11.glBegin(7);
        for (int i = 0; i < this.positions.size(); ++i) {
            final Helper bc = this.positions.get(i);
            ColorUtils.glColor(ColorUtils.astolfoColors(100, 100), this.alpha.getValInt());
            if (bc.timer.hasReached((float)this.removeTicks.getValInt())) {
                this.positions.remove(bc);
            }
            final double renderPosX = Breadcrumbs.mc.renderManager.renderPosX;
            final double renderPosY = Breadcrumbs.mc.renderManager.renderPosY;
            final double renderPosZ = Breadcrumbs.mc.renderManager.renderPosZ;
            if (lastPosX != 114514.0 || lastPosY != 114514.0 || lastPosZ != 114514.0) {
                GL11.glVertex3d(bc.getVector().x - renderPosX, bc.getVector().y - renderPosY, bc.getVector().z - renderPosZ);
                GL11.glVertex3d(lastPosX, lastPosY, lastPosZ);
                GL11.glVertex3d(lastPosX, lastPosY + Breadcrumbs.mc.player.height, lastPosZ);
                GL11.glVertex3d(bc.getVector().z - renderPosX, bc.getVector().y - renderPosY + Breadcrumbs.mc.player.height, bc.getVector().z - renderPosZ);
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
    
    public static class Helper
    {
        public TimerUtils timer;
        public Vec3d vec;
        
        public Helper(final Vec3d vec) {
            this.timer = new TimerUtils();
            this.vec = vec;
        }
        
        public Vec3d getVector() {
            return this.vec;
        }
    }
}
