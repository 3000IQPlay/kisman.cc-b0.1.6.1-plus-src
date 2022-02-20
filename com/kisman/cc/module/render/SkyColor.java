//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraftforge.client.event.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class SkyColor extends Module
{
    private float[] color;
    
    public SkyColor() {
        super("SkyColor", "colorsky", Category.RENDER);
        this.color = new float[] { 1.0f, 1.0f, 1.0f, 1.0f };
        Kisman.instance.settingsManager.rSetting(new Setting("Color", this, "Color", new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, false));
    }
    
    public void update() {
        this.color[0] = Kisman.instance.settingsManager.getSettingByName(this, "Color").getColor(0);
        this.color[1] = Kisman.instance.settingsManager.getSettingByName(this, "Color").getColor(1);
        this.color[2] = Kisman.instance.settingsManager.getSettingByName(this, "Color").getColor(2);
        this.color[3] = Kisman.instance.settingsManager.getSettingByName(this, "Color").getColor(3);
    }
    
    @SubscribeEvent
    public void fogColor(final EntityViewRenderEvent.FogColors event) {
        event.setRed((float)new Color(Color.HSBtoRGB(this.color[0], this.color[1], this.color[2])).getRed());
        event.setGreen((float)new Color(Color.HSBtoRGB(this.color[0], this.color[1], this.color[2])).getGreen());
        event.setBlue((float)new Color(Color.HSBtoRGB(this.color[0], this.color[1], this.color[2])).getBlue());
    }
    
    @SubscribeEvent
    public void fog(final EntityViewRenderEvent.FogDensity event) {
        event.setDensity(0.0f);
        event.setCanceled(false);
    }
}
