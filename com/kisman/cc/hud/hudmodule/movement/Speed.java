//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.hud.hudmodule.movement;

import com.kisman.cc.hud.hudmodule.*;
import com.kisman.cc.*;
import net.minecraftforge.client.event.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.*;
import net.minecraft.util.text.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Speed extends HudModule
{
    private int i;
    
    public Speed() {
        super("Speed", "Speed", HudCategory.MOVEMENT);
    }
    
    public void update() {
        if (Kisman.instance.hudModuleManager.getModule("Coords").isToggled()) {
            this.i = 1;
        }
    }
    
    @SubscribeEvent
    public void onRender(final RenderGameOverlayEvent.Text event) {
        final FontRenderer fr = Speed.mc.fontRenderer;
        final ScaledResolution sr = new ScaledResolution(Speed.mc);
        final String str1 = HUD.instance.speedMode.getValString().equalsIgnoreCase("b/s") ? "b/s " : "km/h ";
        final String str2 = HUD.instance.speedMode.getValString().equalsIgnoreCase("b/s") ? String.valueOf(MoveUtil.getSpeed()) : String.valueOf(this.getSpeed());
        fr.drawStringWithShadow(TextFormatting.WHITE + "Speed: " + TextFormatting.GRAY + str1 + str2, 1.0f, (float)(sr.getScaledHeight() - 1 - fr.FONT_HEIGHT - ((this.i == 1) ? (fr.FONT_HEIGHT * 2 + 4) : 0)), -1);
    }
    
    private int getSpeed() {
        float speed = MoveUtil.getSpeed();
        speed = speed * 1000.0f * 3600.0f;
        return (int)speed;
    }
}
