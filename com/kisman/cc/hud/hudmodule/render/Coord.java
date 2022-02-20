//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.render;

import net.minecraft.client.*;
import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.module.client.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.customfont.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Coord extends HudModule
{
    Minecraft mc;
    int posX;
    int nPosX;
    int posY;
    int nPosY;
    int posZ;
    int nPosZ;
    
    public Coord() {
        super("Coords", "coord", HudCategory.RENDER);
        this.mc = Minecraft.getMinecraft();
    }
    
    public void update() {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.player != null && mc.world != null) {
            if (mc.player.dimension == 0) {
                this.posX = (int)mc.player.posX;
                this.posY = (int)mc.player.posY;
                this.posZ = (int)mc.player.posZ;
                this.nPosX = (int)(mc.player.posX / 8.0);
                this.nPosY = (int)(mc.player.posY / 8.0);
                this.nPosZ = (int)(mc.player.posZ / 8.0);
            }
            else if (mc.player.dimension == -1) {
                this.posX = (int)mc.player.posX;
                this.posY = (int)mc.player.posY;
                this.posZ = (int)mc.player.posZ;
                this.nPosX = (int)(mc.player.posX * 8.0);
                this.nPosY = (int)(mc.player.posY * 8.0);
                this.nPosZ = (int)(mc.player.posZ * 8.0);
            }
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        final ScaledResolution sr = new ScaledResolution(this.mc);
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
            CustomFontUtil.drawStringWithShadow("X: (" + this.posX + ")[" + this.nPosX + "] Y: (" + this.posY + ")[" + this.nPosY + "] Z: (" + this.posZ + ")[" + this.nPosZ + "]", 1.0, sr.getScaledHeight() - CustomFontUtil.getFontHeight() - 1, color);
            CustomFontUtil.drawStringWithShadow("Yaw: " + (int)this.mc.player.rotationYaw + " Pitch:" + (int)this.mc.player.rotationPitch, 1.0, sr.getScaledHeight() - CustomFontUtil.getFontHeight() * 2 - 2, color);
        }
    }
}
