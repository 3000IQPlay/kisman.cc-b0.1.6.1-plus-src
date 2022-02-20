//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.render;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.module.client.*;
import i.gishreloaded.gishcode.utils.visual.*;
import java.awt.*;
import com.kisman.cc.util.customfont.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Welcomer extends HudModule
{
    public Welcomer() {
        super("Welcomer", "", HudCategory.RENDER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final ScaledResolution sr = new ScaledResolution(Welcomer.mc);
        final int color = HUD.instance.astolfoColor.getValBoolean() ? ColorUtils.astolfoColors(100, 100) : new Color(HUD.instance.welColor.getR(), HUD.instance.welColor.getG(), HUD.instance.welColor.getB(), HUD.instance.welColor.getA()).getRGB();
        CustomFontUtil.drawStringWithShadow("Welcome " + Welcomer.mc.player.getName(), sr.getScaledWidth() / 2 - CustomFontUtil.getStringWidth("Welcome " + Welcomer.mc.player.getName()) / 2, 1.0, color);
    }
}
