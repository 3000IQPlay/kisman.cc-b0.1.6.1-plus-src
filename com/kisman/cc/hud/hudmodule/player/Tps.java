//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.player;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.text.*;
import com.kisman.cc.*;
import com.kisman.cc.util.customfont.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Tps extends HudModule
{
    public Tps() {
        super("Tps", "", HudCategory.PLAYER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final ScaledResolution sr = new ScaledResolution(Tps.mc);
        final String str = TextFormatting.WHITE + "TPS: " + TextFormatting.GRAY + Kisman.instance.serverManager.getTps();
        CustomFontUtil.drawStringWithShadow(str, sr.getScaledWidth() - 1 - CustomFontUtil.getStringWidth(str), sr.getScaledHeight() - 3 - CustomFontUtil.getFontHeight() * 2, -1);
    }
}
