//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.render;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.module.client.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.util.text.*;
import net.minecraft.client.*;
import com.kisman.cc.util.customfont.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Fps extends HudModule
{
    public Fps() {
        super("Fps", "fuck you", HudCategory.RENDER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {
            final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : -1;
            CustomFontUtil.drawStringWithShadow("Fps: " + TextFormatting.GRAY + Minecraft.getDebugFPS(), 1.0, 2 + CustomFontUtil.getFontHeight(), color);
        }
    }
}
