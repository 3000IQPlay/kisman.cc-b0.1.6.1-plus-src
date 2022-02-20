//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.player;

import com.kisman.cc.hud.hudmodule.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.text.*;
import com.kisman.cc.util.customfont.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ServerIp extends HudModule
{
    public ServerIp() {
        super("ServerIP", "", HudCategory.PLAYER);
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final ScaledResolution sr = new ScaledResolution(ServerIp.mc);
        final String str = TextFormatting.WHITE + "Server: " + TextFormatting.GRAY + (ServerIp.mc.isSingleplayer() ? "SingePlayer" : ServerIp.mc.getCurrentServerData().serverIP.toLowerCase());
        CustomFontUtil.drawStringWithShadow(str, sr.getScaledWidth() - 1 - CustomFontUtil.getStringWidth(str), sr.getScaledHeight() - 5 - CustomFontUtil.getFontHeight() * 3, -1);
    }
}
