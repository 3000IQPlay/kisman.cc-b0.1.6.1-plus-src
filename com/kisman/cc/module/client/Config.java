//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.csgo.components.*;

public class Config extends Module
{
    public static Config instance;
    public Setting friends;
    public Setting nameMode;
    public Setting customName;
    public Setting scrollSpeed;
    public Setting guiGlow;
    public Setting glowOffset;
    public Setting guiAstolfo;
    public Setting guiRenderSIze;
    public Setting pulseMin;
    public Setting pulseMax;
    public Setting pulseSpeed;
    
    public Config() {
        super("Config", "cfg for this client", Category.CLIENT);
        this.friends = new Setting("Friends", this, true);
        this.nameMode = new Setting("Name Mode", this, NameMode.kismancc);
        this.customName = new Setting("CustomName", this, "kisman.cc", "kisman.cc", true);
        this.scrollSpeed = new Setting("ScrollSpeed", this, 15.0, 0.0, 100.0, Slider.NumberType.PERCENT);
        this.guiGlow = new Setting("GuiGlow", this, false);
        this.glowOffset = new Setting("GlowOffset", this, 6.0, 0.0, 20.0, true);
        this.guiAstolfo = new Setting("GuiAstolfo", this, false);
        this.guiRenderSIze = new Setting("GuiRenderSize", this, false);
        this.pulseMin = new Setting("PulseMin", this, 255.0, 0.0, 255.0, true);
        this.pulseMax = new Setting("PulseMax", this, 110.0, 0.0, 255.0, true);
        this.pulseSpeed = new Setting("PulseSpeed", this, 1.5, 0.1, 10.0, false);
        Config.instance = this;
        Config.setmgr.rSetting(this.friends);
        Config.setmgr.rSetting(this.nameMode);
        Config.setmgr.rSetting(this.customName);
        Config.setmgr.rSetting(this.scrollSpeed);
        Config.setmgr.rSetting(this.guiGlow);
        Config.setmgr.rSetting(this.glowOffset);
        Config.setmgr.rSetting(this.guiAstolfo);
        Config.setmgr.rSetting(this.guiRenderSIze);
        Config.setmgr.rSetting(this.pulseMin);
        Config.setmgr.rSetting(this.pulseMax);
        Config.setmgr.rSetting(this.pulseSpeed);
    }
    
    public enum NameMode
    {
        kismancc, 
        lavahack, 
        custom;
    }
}
