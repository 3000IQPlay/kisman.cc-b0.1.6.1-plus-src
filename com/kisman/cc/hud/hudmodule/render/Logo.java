//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.render;

import com.kisman.cc.hud.hudmodule.*;
import com.kisman.cc.*;
import net.minecraftforge.client.event.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.customfont.*;
import com.kisman.cc.util.*;
import net.minecraft.util.text.*;
import com.kisman.cc.module.client.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Logo extends HudModule
{
    public static int height;
    public static int x;
    public static int y;
    public static int x1;
    public static int y1;
    private String name;
    private String version;
    
    public Logo() {
        super("Logo", "lava-hack on top", HudCategory.RENDER);
        this.name = "kisman.cc+";
        this.version = "b0.1.6.1";
    }
    
    public void update() {
        this.name = Kisman.getName();
        this.version = Kisman.getVersion();
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        if (HUD.instance.logoMode.getValString().equals("Simple")) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
                final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
                if (HUD.instance.logoGlow.getValBoolean()) {
                    final int glowOffset = HUD.instance.glowOffset.getValInt();
                    Render2DUtil.drawGlow(1 - glowOffset, 1 - glowOffset, 1 + CustomFontUtil.getStringWidth(this.name + this.version) + glowOffset, 1 + CustomFontUtil.getFontHeight() + glowOffset, color);
                }
                CustomFontUtil.drawStringWithShadow((HUD.instance.logoBold.getValBoolean() ? TextFormatting.BOLD : "") + this.name + " " + TextFormatting.GRAY + this.version, 1.0, 1.0, color);
                this.setHeight(1 + CustomFontUtil.getFontHeight() + 2);
            }
        }
        else if (HUD.instance.logoMode.getValString().equals("CSGO")) {
            final String text = (CustomFont.instance.mode.getValString().equals("Verdana") ? TextFormatting.BOLD : "") + this.name + TextFormatting.GRAY + " | " + TextFormatting.RESET + Logo.mc.player.getName() + TextFormatting.GRAY + " | " + TextFormatting.RESET + (Logo.mc.isSingleplayer() ? 0 : Kisman.instance.serverManager.getPing()) + " mc" + TextFormatting.GRAY + " | " + TextFormatting.RESET + "FPS " + Minecraft.getDebugFPS();
            final int x = 3;
            final int y = 8;
            final int width = 4 + CustomFontUtil.getStringWidth(text);
            final int height = 4 + CustomFontUtil.getFontHeight();
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
            CustomFontUtil.drawStringWithShadow((HUD.instance.logoBold.getValBoolean() ? TextFormatting.BOLD : "") + text, x + 2, y + 2, ColorUtils.astolfoColors(100, 100));
        }
    }
    
    public int getHeight() {
        return Logo.height;
    }
    
    public void setHeight(final int height) {
        Logo.height = height;
    }
    
    static {
        Logo.height = 0;
        Logo.x = 1;
        Logo.y = 1;
        Logo.x1 = (Logo.x = CustomFontUtil.getStringWidth("kisman.cc+ b0.1.6.1"));
        Logo.y1 = Logo.y + Minecraft.getMinecraft().fontRenderer.FONT_HEIGHT;
    }
}
