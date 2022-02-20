//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import com.kisman.cc.util.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PenisESP extends Module
{
    boolean enabled;
    private float spin;
    private float cumsize;
    private float amount;
    
    public PenisESP() {
        super("PenisESP", "PenisESP", Category.RENDER);
    }
    
    public void onEnable() {
        this.spin = 0.0f;
        this.cumsize = 0.0f;
        this.amount = 0.0f;
    }
    
    @SubscribeEvent
    public void onRenderLast(final RenderWorldLastEvent event) {
        for (final Object o : PenisESP.mc.world.loadedEntityList) {
            if (!(o instanceof EntityPlayer)) {
                continue;
            }
            final EntityPlayer player = (EntityPlayer)o;
            final double x2 = player.lastTickPosX + (player.posX - player.lastTickPosX) * PenisESP.mc.getRenderPartialTicks();
            final double x3 = x2 - PenisESP.mc.getRenderManager().viewerPosX;
            final double y2 = player.lastTickPosY + (player.posY - player.lastTickPosY) * PenisESP.mc.getRenderPartialTicks();
            final double y3 = y2 - PenisESP.mc.getRenderManager().viewerPosY;
            final double z2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * PenisESP.mc.getRenderPartialTicks();
            final double z3 = z2 - PenisESP.mc.getRenderManager().viewerPosZ;
            GL11.glPushMatrix();
            RenderHelper.disableStandardItemLighting();
            RenderUtil.drawPenis(player, x3, y3, z3, this.spin, this.cumsize, this.amount);
            RenderHelper.enableStandardItemLighting();
            GL11.glPopMatrix();
        }
    }
}
