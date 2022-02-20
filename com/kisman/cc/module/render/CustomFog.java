//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraftforge.client.event.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class CustomFog extends Module
{
    private Setting red;
    private Setting green;
    private Setting blue;
    private Setting rainbow;
    private Setting saturatuon;
    private Setting bringhtness;
    private Setting delay;
    
    public CustomFog() {
        super("CustomFog", Category.RENDER);
        this.red = new Setting("Red", this, 1.0, 0.0, 1.0, false);
        this.green = new Setting("Green", this, 0.0, 0.0, 1.0, false);
        this.blue = new Setting("Blue", this, 0.0, 0.0, 1.0, false);
        this.rainbow = new Setting("Rainbow", this, true);
        this.saturatuon = new Setting("Saturation", this, 1.0, 0.0, 1.0, false);
        this.bringhtness = new Setting("Bringhtness", this, 1.0, 0.0, 1.0, false);
        this.delay = new Setting("Delay", this, 100.0, 1.0, 2000.0, true);
        CustomFog.setmgr.rSetting(this.red);
        CustomFog.setmgr.rSetting(this.green);
        CustomFog.setmgr.rSetting(this.blue);
        CustomFog.setmgr.rSetting(this.rainbow);
        CustomFog.setmgr.rSetting(this.saturatuon);
        CustomFog.setmgr.rSetting(this.bringhtness);
        CustomFog.setmgr.rSetting(this.delay);
    }
    
    @SubscribeEvent
    public void onRenderSky(final EntityViewRenderEvent.FogColors event) {
        if (this.rainbow.getValBoolean()) {
            event.setRed(ColorUtils.rainbowRGB(this.delay.getValInt(), this.saturatuon.getValFloat(), this.bringhtness.getValFloat()).getRed() / 255.0f);
            event.setGreen(ColorUtils.rainbowRGB(this.delay.getValInt(), this.saturatuon.getValFloat(), this.bringhtness.getValFloat()).getGreen() / 255.0f);
            event.setBlue(ColorUtils.rainbowRGB(this.delay.getValInt(), this.saturatuon.getValFloat(), this.bringhtness.getValFloat()).getBlue() / 255.0f);
        }
        else {
            event.setRed(this.red.getValFloat());
            event.setGreen(this.green.getValFloat());
            event.setBlue(this.blue.getValFloat());
        }
    }
}
