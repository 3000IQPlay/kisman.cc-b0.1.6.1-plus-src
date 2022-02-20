//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.oldclickgui.csgo.components.*;
import com.kisman.cc.util.customfont.*;
import com.kisman.cc.*;
import net.minecraft.client.gui.*;

public class HUD extends Module
{
    public static HUD instance;
    public Setting astolfoColor;
    private Setting arrLine;
    public Setting arrMode;
    public Setting arrY;
    public Setting arrColor;
    public Setting arrGragient;
    public Setting arrGradientDiff;
    public Setting arrOffsets;
    private Setting welLine;
    public Setting welColor;
    private Setting pvpLine;
    public Setting pvpY;
    private Setting armLine;
    public Setting armExtra;
    public Setting armDmg;
    private Setting radarLine;
    public Setting radarDist;
    public Setting radarY;
    private Setting speedLine;
    public Setting speedMode;
    private Setting logoLine;
    public Setting logoMode;
    public Setting logoGlow;
    public Setting glowOffset;
    public Setting logoBold;
    public Setting indicLine;
    public Setting indicY;
    
    public HUD() {
        super("HudEditor", "hud editor", Category.CLIENT);
        this.astolfoColor = new Setting("AstolfoColor", this, false);
        this.arrLine = new Setting("ArrLine", this, "ArrayList");
        this.arrMode = new Setting("ArrayListMode", this, "RIGHT", new ArrayList<String>(Arrays.asList("LEFT", "RIGHT")));
        this.arrY = new Setting("ArrayListY", this, 150.0, 0.0, HUD.mc.displayHeight, true);
        this.arrColor = new Setting("ArrayListColor", this, "Color", new float[] { 3.0f, 0.03f, 0.33f, 1.0f }, false);
        this.arrGragient = new Setting("ArrayGradient", this, Gradient.None);
        this.arrGradientDiff = new Setting("ArrayGradientDiff", this, 200.0, 0.0, 1000.0, Slider.NumberType.TIME);
        this.arrOffsets = new Setting("Offsets", this, 1.0, 0.0, 10.0, true);
        this.welLine = new Setting("WelLine", this, "Welcomer");
        this.welColor = new Setting("WelColor", this, "WelcomerColor", new float[] { 3.0f, 0.03f, 0.33f, 1.0f }, false);
        this.pvpLine = new Setting("PvpLine", this, "PvpInfo");
        this.pvpY = new Setting("PvpInfoY", this, 200.0, 0.0, HUD.mc.displayHeight, true);
        this.armLine = new Setting("ArmLine", this, "Armor");
        this.armExtra = new Setting("ExtraInfo", this, false);
        this.armDmg = new Setting("Damage", this, false);
        this.radarLine = new Setting("RadarLine", this, "Radar");
        this.radarDist = new Setting("MaxDistance", this, 50.0, 10.0, 50.0, true);
        this.radarY = new Setting("RadarY", this, 3 + CustomFontUtil.getFontHeight() * 2, 0.0, HUD.mc.displayHeight, true);
        this.speedLine = new Setting("SpeedLine", this, "Speed");
        this.speedMode = new Setting("SpeedMode", this, "km/h", new ArrayList<String>(Arrays.asList("b/s", "km/h")));
        this.logoLine = new Setting("LogoLine", this, "Logo");
        this.logoMode = new Setting("LogoMode", this, LogoMode.Simple);
        this.logoGlow = new Setting("Glow", this, false);
        this.glowOffset = new Setting("GlowOffset", this, 6.0, 0.0, 20.0, true);
        this.logoBold = new Setting("Name Bold", this, false);
        this.indicLine = new Setting("IndicLine", this, "Indicators");
        this.indicY = new Setting("IndicatorsY", this, 20.0, 0.0, HUD.mc.displayHeight, true);
        HUD.instance = this;
        HUD.setmgr.rSetting(this.astolfoColor);
        HUD.setmgr.rSetting(this.arrLine);
        HUD.setmgr.rSetting(this.arrMode);
        HUD.setmgr.rSetting(this.arrY);
        HUD.setmgr.rSetting(this.arrColor);
        HUD.setmgr.rSetting(this.arrGragient);
        HUD.setmgr.rSetting(this.arrGradientDiff);
        HUD.setmgr.rSetting(this.arrOffsets);
        HUD.setmgr.rSetting(this.welLine);
        HUD.setmgr.rSetting(this.welColor);
        HUD.setmgr.rSetting(this.pvpLine);
        HUD.setmgr.rSetting(this.pvpY);
        HUD.setmgr.rSetting(this.armLine);
        HUD.setmgr.rSetting(this.armExtra);
        HUD.setmgr.rSetting(this.armDmg);
        HUD.setmgr.rSetting(this.radarLine);
        HUD.setmgr.rSetting(this.radarDist);
        HUD.setmgr.rSetting(this.radarY);
        HUD.setmgr.rSetting(this.speedLine);
        HUD.setmgr.rSetting(this.speedMode);
        HUD.setmgr.rSetting(this.logoLine);
        HUD.setmgr.rSetting(this.logoMode);
        HUD.setmgr.rSetting(this.logoGlow);
        HUD.setmgr.rSetting(this.glowOffset);
        HUD.setmgr.rSetting(this.logoBold);
        HUD.setmgr.rSetting(this.indicLine);
        HUD.setmgr.rSetting(this.indicY);
    }
    
    @Override
    public void onEnable() {
        super.onEnable();
        HUD.mc.displayGuiScreen((GuiScreen)Kisman.instance.hudGui);
        super.setToggled(false);
    }
    
    public enum LogoMode
    {
        Simple, 
        CSGO;
    }
    
    public enum Gradient
    {
        None, 
        Simple, 
        Sideway;
    }
}
