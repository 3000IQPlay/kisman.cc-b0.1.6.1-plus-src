//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.player;

import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.math.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.customfont.*;

public class Indicators extends HudModule
{
    private final TimerUtils timer;
    private int sliderWidth;
    private int sliderHeight;
    private final String header = "Indicators";
    private double cooldownBarWidth;
    private double hurttimeBarWidth;
    private double speedBarWidth;
    private double healthBarWidth;
    
    public Indicators() {
        super("Indicators", "", HudCategory.PLAYER);
        this.timer = new TimerUtils();
        this.sliderWidth = 51;
        this.sliderHeight = 2;
        this.cooldownBarWidth = 0.0;
        this.hurttimeBarWidth = 0.0;
        this.speedBarWidth = 0.0;
        this.healthBarWidth = 0.0;
    }
    
    public void update() {
        this.setX(3);
        this.setY(HUD.instance.indicY.getValInt() + 8);
        this.setW(this.sliderWidth + 4);
        this.setH(6 + this.getHeight() + 4 * (this.getHeight() + this.sliderHeight + 3));
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final int x = this.getX();
        final int y = this.getY();
        final int width = this.getW();
        final int height = this.getH();
        int offset = 0;
        final int offsetHeight = this.getHeight() + this.sliderHeight + 3;
        final double prevX = Indicators.mc.player.posX - Indicators.mc.player.prevPosX;
        final double prevZ = Indicators.mc.player.posZ - Indicators.mc.player.prevPosZ;
        final double lastDist = Math.sqrt(prevX * prevX + prevZ * prevZ);
        final double currSpeed = lastDist * 15.3571428571 / 4.0;
        final double cooldownPercentage = MathHelper.clamp((double)Indicators.mc.player.getCooledAttackStrength(0.0f), 0.1, 1.0);
        this.cooldownBarWidth = AnimationUtils.animate(cooldownPercentage * 51.0, this.cooldownBarWidth, 0.05);
        final double hurttimePercentage = MathHelper.clamp(Indicators.mc.player.hurtTime, 0, 1);
        this.hurttimeBarWidth = AnimationUtils.animate(hurttimePercentage * 51.0, this.hurttimeBarWidth, 0.05);
        if (this.hurttimeBarWidth < 0.0) {
            this.hurttimeBarWidth = 0.0;
        }
        final double speedPercentage = MathHelper.clamp(currSpeed / 2.4, 0.1, 1.0);
        this.speedBarWidth = AnimationUtils.animate(speedPercentage * 51.0, this.speedBarWidth, 0.05);
        final double healthPercentage = Indicators.mc.player.getHealth() / Indicators.mc.player.getMaxHealth();
        if (this.timer.passedMillis(15L)) {
            this.healthBarWidth = AnimationUtils.animate(healthPercentage * 51.0, this.healthBarWidth, 0.05);
            this.timer.reset();
        }
        Gui.drawRect(x + 3, y + 3, x + width + 3, y + height - 3, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(x + 3, y, x + width + 3, y + height, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(x + 2, y + 2, x + width + 2, y + height - 2, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(x + 2, y, x + width + 2, y + height, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(x + 1, y + 1, x + width + 1, y + height - 1, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(x + 1, y, x + width + 1, y + height, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(x - 3, y - 8, x + width + 3, y + height - 3, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(x - 3, y, x + width + 3, y + height, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(x - 2, y - 7, x + width + 2, y + height - 2, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(x - 2, y, x + width + 2, y + height, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(x - 1, y - 6, x + width + 1, y + height - 1, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(x - 1, y, x + width + 1, y + height, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(x, y - 5, x + width, y + height, ColorUtils.astolfoColors(100, 100));
        Gui.drawRect(x - 3, y - 1, x + width + 3, y + height + 3, ColorUtils.getColor(33, 33, 42));
        Gui.drawRect(x - 2, y - 2, x + width + 2, y + height + 2, ColorUtils.getColor(45, 45, 55));
        Gui.drawRect(x - 1, y - 3, x + width + 1, y + height + 1, ColorUtils.getColor(60, 60, 70));
        Gui.drawRect(x, y - 4, x + width, y + height, ColorUtils.getColor(34, 34, 40));
        this.drawStringWithShadow("Indicators", x + (width / 2 - this.getStringWidth("Indicators") / 2), y + 2, ColorUtils.astolfoColors(100, 100));
        offset += this.getHeight() + 6;
        this.drawStringWithShadow("Cooldown", x + 2, y + offset, ColorUtils.astolfoColors(100, 100));
        Render2DUtil.drawRect(x + 2, y + offset + this.getHeight() + 1, x + this.cooldownBarWidth, y + offset + this.getHeight() + 3, ColorUtils.astolfoColors(100, 100));
        offset += offsetHeight;
        this.drawStringWithShadow("HurtTime", x + 2, y + offset, ColorUtils.astolfoColors(100, 100));
        if (this.hurttimeBarWidth > 0.0) {
            Render2DUtil.drawRect(x + 2, y + offset + this.getHeight() + 1, x + this.hurttimeBarWidth, y + offset + this.getHeight() + 3, ColorUtils.astolfoColors(100, 100));
        }
        offset += offsetHeight;
        this.drawStringWithShadow("Speed", x + 2, y + offset, ColorUtils.astolfoColors(100, 100));
        Render2DUtil.drawRect(x + 2, y + offset + this.getHeight() + 1, x + this.speedBarWidth, y + offset + this.getHeight() + 3, ColorUtils.astolfoColors(100, 100));
        offset += offsetHeight;
        this.drawStringWithShadow("Health", x + 2, y + offset, ColorUtils.astolfoColors(100, 100));
        Render2DUtil.drawRect(x + 2, y + offset + this.getHeight() + 1, x + this.healthBarWidth, y + offset + this.getHeight() + 3, ColorUtils.astolfoColors(100, 100));
    }
    
    private void drawStringWithShadow(final String text, final int x, final int y, final int color) {
        if (CustomFont.turnOn) {
            CustomFontUtil.consolas15.drawStringWithShadow(text, x, y, color);
        }
        else {
            Indicators.mc.fontRenderer.drawStringWithShadow(text, (float)x, (float)y, color);
        }
    }
    
    private int getHeight() {
        if (CustomFont.turnOn) {
            return CustomFontUtil.consolas15.getStringHeight();
        }
        return Indicators.mc.fontRenderer.FONT_HEIGHT;
    }
    
    private int getStringWidth(final String text) {
        if (CustomFont.turnOn) {
            return CustomFontUtil.consolas15.getStringWidth(text);
        }
        return Indicators.mc.fontRenderer.getStringWidth(text);
    }
}
