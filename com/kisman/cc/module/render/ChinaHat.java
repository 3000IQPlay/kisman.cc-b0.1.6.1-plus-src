//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.*;
import net.minecraft.client.renderer.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChinaHat extends Module
{
    private Setting color;
    
    public ChinaHat() {
        super("ChinaHat", "ChinaHat", Category.RENDER);
        this.color = new Setting("Color", this, "Color", new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, false);
        ChinaHat.setmgr.rSetting(this.color);
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent event) {
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glColor4d((double)(this.color.getR() / 255.0f), (double)(this.color.getG() / 255.0f), (double)this.color.getB(), (double)this.color.getA());
        GL11.glTranslatef(0.0f, ChinaHat.mc.player.height + 0.4f, 0.0f);
        GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
        final Cylinder cylinder = new Cylinder();
        cylinder.setDrawStyle(100012);
        cylinder.draw(0.0f, 0.8f, 0.4f, 30, 1);
        GlStateManager.resetColor();
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }
}
