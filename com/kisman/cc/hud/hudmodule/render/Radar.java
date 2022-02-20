//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.render;

import com.kisman.cc.hud.hudmodule.*;
import com.kisman.cc.module.client.*;
import net.minecraftforge.client.event.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.customfont.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Radar extends HudModule
{
    private int maxRange;
    private int x;
    private int y;
    private final String[] directions;
    
    public Radar() {
        super("Radar", "Radar", HudCategory.RENDER);
        this.maxRange = HUD.instance.radarDist.getValInt();
        this.x = 0;
        this.y = HUD.instance.radarY.getValInt();
        this.directions = new String[] { "X+", "Z+", "X-", "Z-" };
    }
    
    public void update() {
        this.maxRange = HUD.instance.radarDist.getValInt();
        this.y = HUD.instance.radarY.getValInt();
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
        Gui.drawRect(this.x, this.y, 150 + this.x, 150 + this.y, new Colour(70, 70, 70, 150).getRGB());
        Render2DUtil.drawRect(74.5 + this.x, 3 + CustomFontUtil.getFontHeight() + this.y, 75.5 + this.x, 147 - CustomFontUtil.getFontHeight() + this.y, new Colour(50, 50, 50, 165).getRGB());
        Render2DUtil.drawRect(3 + CustomFontUtil.getStringWidth(this.directions[3]) + this.x, 74.5 + this.y, 147 - CustomFontUtil.getStringWidth(this.directions[1]) + this.x, 75.5 + this.y, new Colour(50, 50, 50, 165).getRGB());
        for (final Entity entity : Radar.mc.world.loadedEntityList) {
            if (entity instanceof EntityPlayer && entity != Radar.mc.player) {
                this.renderEntityPoint(entity);
            }
        }
        final boolean isNorth = this.isFacing(EnumFacing.NORTH);
        final boolean isSouth = this.isFacing(EnumFacing.SOUTH);
        final boolean isEast = this.isFacing(EnumFacing.EAST);
        final boolean isWest = this.isFacing(EnumFacing.WEST);
        if (isNorth) {
            Render2DUtil.drawRect(68.5 + this.x, 74.5 + this.y, 74.5 + this.x, 75.5 + this.y, color);
        }
        else if (isSouth) {
            Render2DUtil.drawRect(75.5 + this.x, 74.5 + this.y, 81.5 + this.x, 74.5 + this.y, color);
        }
        else if (isEast) {
            Render2DUtil.drawRect(74.5 + this.x, 68.5 + this.y, 75.5 + this.x, 74.5 + this.y, color);
        }
        else if (isWest) {
            Render2DUtil.drawRect(74.5 + this.x, 75.5 + this.y, 75.5 + this.x, 81.5 + this.y, color);
        }
        CustomFontUtil.drawStringWithShadow(this.directions[0], 75 - CustomFontUtil.getStringWidth(this.directions[0]) / 2 + this.x, 2 + this.y, color);
        CustomFontUtil.drawStringWithShadow(this.directions[2], 75 - CustomFontUtil.getStringWidth(this.directions[2]) / 2 + this.x, 148 - CustomFontUtil.getFontHeight() + this.y, color);
        CustomFontUtil.drawStringWithShadow(this.directions[1], 2 + this.x, 75 - CustomFontUtil.getFontHeight() / 2 + this.y, color);
        CustomFontUtil.drawStringWithShadow(this.directions[3], 148 - CustomFontUtil.getStringWidth(this.directions[3]) + this.x, 75 - CustomFontUtil.getFontHeight() / 2 + this.y, color);
        this.drawLine(this.x, this.y, 1 + this.x, 150 + this.y, color);
        this.drawLine(this.x, this.y, 150 + this.x, 1 + this.y, color);
        this.drawLine(149 + this.x, this.y, 150 + this.x, 150 + this.y, color);
        this.drawLine(this.x, 149 + this.y, 150 + this.x, 150 + this.y, color);
    }
    
    private boolean isFacing(final EnumFacing enumFacing) {
        return Radar.mc.player.getHorizontalFacing().equals((Object)enumFacing);
    }
    
    private void renderEntityPoint(final Entity entity) {
        final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
        final int distanceX = this.findDistance1D(Radar.mc.player.posX, entity.posX);
        final int distanceY = this.findDistance1D(Radar.mc.player.posZ, entity.posZ);
        if (distanceX > this.maxRange || distanceY > this.maxRange || distanceX < -this.maxRange || distanceY < -this.maxRange) {
            return;
        }
        Render2DUtil.drawRect(75 + distanceX + this.x - 0.5, 75 + distanceY + this.y - 1.5, 75 + distanceX + this.x + 0.5, 75 + distanceY + this.y + 1.5, color);
        Render2DUtil.drawRect(75 + distanceX + this.x - 1.5, 75 + distanceY + this.y - 0.5, 75 + distanceX + this.x + 1.5, 75 + distanceY + this.y + 0.5, color);
    }
    
    private void drawLine(final int x, final int y, final int x1, final int y1, final int color) {
        Gui.drawRect(x, y, x1, y1, color);
    }
    
    private int findDistance1D(final double player, final double entity) {
        double player2 = player;
        double entity2 = entity;
        if (player2 < 0.0) {
            player2 *= -1.0;
        }
        if (entity2 < 0.0) {
            entity2 *= -1.0;
        }
        int value = (int)(entity2 - player2);
        if ((player > 0.0 && entity < 0.0) || (player < 0.0 && entity > 0.0)) {
            value = (int)(-1.0 * player + entity);
        }
        if ((player > 0.0 || player < 0.0) && entity < 0.0 && entity2 != player2) {
            value = (int)(-1.0 * player + entity);
        }
        if ((player < 0.0 && entity == 0.0) || (player == 0.0 && entity < 0.0)) {
            value = (int)(-1.0 * (entity2 - player2));
        }
        return value;
    }
}
