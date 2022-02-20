//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.player;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.text.*;
import com.kisman.cc.*;
import com.kisman.cc.module.client.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.customfont.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Ping extends HudModule
{
    public Ping() {
        super("Ping", "", HudCategory.PLAYER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final ScaledResolution sr = new ScaledResolution(Ping.mc);
        final String str = "Ping: " + TextFormatting.GRAY + (Ping.mc.isSingleplayer() ? 0 : Kisman.instance.serverManager.getPing());
        final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
        CustomFontUtil.drawStringWithShadow(str, sr.getScaledWidth() - 1 - CustomFontUtil.getStringWidth(str), sr.getScaledHeight() - 1 - CustomFontUtil.getFontHeight(), color);
    }
}
