//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.*;
import com.kisman.cc.oldclickgui.*;
import com.kisman.cc.util.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class ColorModule extends Module
{
    public static ColorModule instance;
    private Setting synsLine;
    public Setting synsColor;
    ColorUtil colorUtil;
    boolean rainbowLine;
    
    public ColorModule() {
        super("Color", "color setting", Category.CLIENT);
        this.synsLine = new Setting("SynsLine", this, "Syns");
        this.synsColor = new Setting("Syns", this, "SynsColor", new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, false);
        this.rainbowLine = false;
        this.colorUtil = new ColorUtil();
        ColorModule.instance = this;
        final ArrayList<String> lineMode = new ArrayList<String>();
        lineMode.add("LeftLine");
        lineMode.add("LLine+TLine");
        lineMode.add("Box");
        final ArrayList<String> setLineMode = new ArrayList<String>();
        setLineMode.add("Default");
        setLineMode.add("All");
        setLineMode.add("OnlySettings");
        Kisman.instance.settingsManager.rSetting(new Setting("LineSetting", this, "Line"));
        Kisman.instance.settingsManager.rSetting(new Setting("Line", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("LineMode", this, "LeftLine", lineMode));
        Kisman.instance.settingsManager.rSetting(new Setting("SetLineMode", this, "Default", setLineMode));
        Kisman.instance.settingsManager.rSetting(new Setting("LineColor", this, "LineColor", new float[] { 0.0f, 1.0f, 1.0f, 1.0f }, false));
        Kisman.instance.settingsManager.rSetting(new Setting("BackgroundSetting", this, "Background"));
        Kisman.instance.settingsManager.rSetting(new Setting("BackgroundColor", this, "BackgroundColor", new float[] { 0.0f, 0.02f, 0.59f, 0.6f }, false));
        Kisman.instance.settingsManager.rSetting(new Setting("ABackgroundColor", this, "ABackgroundColor", new float[] { 0.52f, 0.74f, 0.73f, 1.0f }, false));
        Kisman.instance.settingsManager.rSetting(new Setting("TextSetting", this, "Text"));
        Kisman.instance.settingsManager.rSetting(new Setting("TextColor", this, "TextColor", new float[] { 3.5f, 0.04f, 0.65f, 1.0f }, false));
        Kisman.instance.settingsManager.rSetting(new Setting("ATextColor", this, "ATextColor", new float[] { 1.0f, 1.0f, 1.0f, 1.0f }, false));
        Kisman.instance.settingsManager.rSetting(new Setting("DifferentSetting", this, "Different"));
        Kisman.instance.settingsManager.rSetting(new Setting("HoveredColor", this, "HoveredColor", new float[] { 0.6f, 0.03f, 0.62f, 0.6f }, false));
        Kisman.instance.settingsManager.rSetting(new Setting("NoHoveredColor", this, "NoHoveredColor", new float[] { 0.0f, 0.0f, 0.05f, 1.0f }, false));
        ColorModule.setmgr.rSetting(this.synsLine);
        ColorModule.setmgr.rSetting(this.synsColor);
    }
    
    @Override
    public void update() {
        final int RLine = Kisman.instance.settingsManager.getSettingByName(this, "LineColor").getR();
        final int GLine = Kisman.instance.settingsManager.getSettingByName(this, "LineColor").getG();
        final int BLine = Kisman.instance.settingsManager.getSettingByName(this, "LineColor").getB();
        final int ALine = Kisman.instance.settingsManager.getSettingByName(this, "LineColor").getA();
        final boolean line = Kisman.instance.settingsManager.getSettingByName(this, "Line").getValBoolean();
        final String lineMode = Kisman.instance.settingsManager.getSettingByName(this, "LineMode").getValString();
        final String setLineMode = Kisman.instance.settingsManager.getSettingByName(this, "SetLineMode").getValString();
        final int RBackground = Kisman.instance.settingsManager.getSettingByName(this, "BackgroundColor").getR();
        final int GBackground = Kisman.instance.settingsManager.getSettingByName(this, "BackgroundColor").getG();
        final int BBackground = Kisman.instance.settingsManager.getSettingByName(this, "BackgroundColor").getB();
        final int ABackground = Kisman.instance.settingsManager.getSettingByName(this, "BackgroundColor").getA();
        Kisman.instance.settingsManager.getSettingByName(this, "BackgroundColor").setSyns(true);
        Kisman.instance.settingsManager.getSettingByName(this, "LineColor").setSyns(true);
        final int RText = Kisman.instance.settingsManager.getSettingByName(this, "TextColor").getR();
        final int GText = Kisman.instance.settingsManager.getSettingByName(this, "TextColor").getG();
        final int BText = Kisman.instance.settingsManager.getSettingByName(this, "TextColor").getB();
        final int AText = Kisman.instance.settingsManager.getSettingByName(this, "TextColor").getA();
        final int RAText = Kisman.instance.settingsManager.getSettingByName(this, "ATextColor").getR();
        final int GAText = Kisman.instance.settingsManager.getSettingByName(this, "ATextColor").getG();
        final int BAText = Kisman.instance.settingsManager.getSettingByName(this, "ATextColor").getB();
        final int AAText = Kisman.instance.settingsManager.getSettingByName(this, "ATextColor").getA();
        final int RHovered = Kisman.instance.settingsManager.getSettingByName(this, "HoveredColor").getR();
        final int GHovered = Kisman.instance.settingsManager.getSettingByName(this, "HoveredColor").getG();
        final int BHovered = Kisman.instance.settingsManager.getSettingByName(this, "HoveredColor").getB();
        final int AHovered = Kisman.instance.settingsManager.getSettingByName(this, "HoveredColor").getA();
        final int RNoHovered = Kisman.instance.settingsManager.getSettingByName(this, "NoHoveredColor").getR();
        final int GNoHovered = Kisman.instance.settingsManager.getSettingByName(this, "NoHoveredColor").getG();
        final int BNoHovered = Kisman.instance.settingsManager.getSettingByName(this, "NoHoveredColor").getB();
        final int ANoHovered = Kisman.instance.settingsManager.getSettingByName(this, "NoHoveredColor").getA();
        ClickGui.setLine(line);
        if (lineMode.equalsIgnoreCase("LeftLine")) {
            ClickGui.setLineMode(LineMode.LEFT);
        }
        else if (lineMode.equalsIgnoreCase("LLine+TLine")) {
            ClickGui.setLineMode(LineMode.LEFTONTOP);
        }
        else {
            ClickGui.setLineMode(LineMode.BOX);
        }
        if (setLineMode.equalsIgnoreCase("Default")) {
            ClickGui.setSetLineMode(LineMode.SETTINGDEFAULT);
        }
        else if (setLineMode.equalsIgnoreCase("All")) {
            ClickGui.setSetLineMode(LineMode.SETTINGALL);
        }
        else {
            ClickGui.setSetLineMode(LineMode.SETTINGONLYSET);
        }
        if (!Config.instance.guiAstolfo.getValBoolean()) {
            ClickGui.setRLine(RLine);
            ClickGui.setGLine(GLine);
            ClickGui.setBLine(BLine);
            ClickGui.setALine(ALine);
            ClickGui.setRActiveText(RAText);
            ClickGui.setGActiveText(GAText);
            ClickGui.setBActiveText(BAText);
            ClickGui.setAActiveText(AAText);
            ClickGui.setRBackground(RBackground);
            ClickGui.setGBackground(GBackground);
            ClickGui.setBBackground(BBackground);
            ClickGui.setABackground(ABackground);
            ClickGui.setRHoveredModule(RHovered);
            ClickGui.setGHoveredModule(GHovered);
            ClickGui.setBHoveredModule(BHovered);
            ClickGui.setAHoveredModule(AHovered);
            ClickGui.setRNoHoveredModule(RNoHovered);
            ClickGui.setGNoHoveredModule(GNoHovered);
            ClickGui.setBNoHoveredModule(BNoHovered);
            ClickGui.setANoHoveredModule(ANoHovered);
        }
        else {
            ClickGui.setRLine(ColorUtils.getRed(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setGLine(ColorUtils.getGreen(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setBLine(ColorUtils.getBlue(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setALine(ColorUtils.getAlpha(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setRActiveText(ColorUtils.getRed(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setGActiveText(ColorUtils.getGreen(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setBActiveText(ColorUtils.getBlue(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setAActiveText(ColorUtils.getAlpha(ColorUtils.astolfoColors(100, 100)));
            ClickGui.setRBackground(255);
            ClickGui.setGBackground(255);
            ClickGui.setBBackground(255);
            ClickGui.setABackground(150);
            ClickGui.setRHoveredModule(54);
            ClickGui.setGHoveredModule(54);
            ClickGui.setBHoveredModule(54);
            ClickGui.setAHoveredModule(90);
            ClickGui.setRNoHoveredModule(54);
            ClickGui.setGNoHoveredModule(54);
            ClickGui.setBNoHoveredModule(54);
            ClickGui.setANoHoveredModule(43);
        }
        ClickGui.setRText(RText);
        ClickGui.setGText(GText);
        ClickGui.setBText(BText);
        ClickGui.setAText(AText);
    }
}
