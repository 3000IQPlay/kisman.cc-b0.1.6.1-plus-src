//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import java.awt.*;
import i.gishreloaded.gishcode.utils.visual.*;

public class NameTags extends Module
{
    private Setting range;
    private Setting scale;
    private Setting yPos;
    private Setting bg;
    private Setting bgLight;
    private Setting bgAlpha;
    private Setting outline;
    private Setting outlineColor;
    private Setting textR;
    private Setting textG;
    private Setting textB;
    private Setting textA;
    private Setting textColorMode;
    private Setting saturatuon;
    private Setting bringhtness;
    private Setting delay;
    public static NameTags instance;
    
    public NameTags() {
        super("NameTags", Category.RENDER);
        this.range = new Setting("Range", this, 0.0, 50.0, 100.0, false);
        this.scale = new Setting("Scale", this, 0.8, 0.1, 1.5, false);
        this.yPos = new Setting("YPos", this, 0.1, 0.0, 1.5, false);
        this.bg = new Setting("Background", this, true);
        this.bgLight = new Setting("BGLight", this, 15.0, 0.0, 100.0, true);
        this.bgAlpha = new Setting("BGAlpha", this, 0.0, 0.0, 30.0, true);
        this.outline = new Setting("OutLine", this, true);
        this.outlineColor = new Setting("OutlineColor", this, "Outline", new float[] { 0.3f, 0.3f, 0.3f, 1.0f });
        this.textR = new Setting("TextR", this, 200.0, 0.0, 255.0, true);
        this.textG = new Setting("TextG", this, 200.0, 0.0, 255.0, true);
        this.textB = new Setting("TextB", this, 200.0, 0.0, 255.0, true);
        this.textA = new Setting("TextA", this, 255.0, 0.0, 255.0, true);
        this.textColorMode = new Setting("ColorMode", this, "Astolfo", Arrays.asList("Astolfo", "Rainbow", "Static"));
        this.saturatuon = new Setting("Saturation", this, 1.0, 0.0, 1.0, false);
        this.bringhtness = new Setting("Bringhtness", this, 1.0, 0.0, 1.0, false);
        this.delay = new Setting("Delay", this, 100.0, 1.0, 2000.0, true);
        NameTags.instance = this;
        NameTags.setmgr.rSetting(this.range);
        NameTags.setmgr.rSetting(this.scale);
        NameTags.setmgr.rSetting(new Setting("BackgroundLine", this, "Background"));
        NameTags.setmgr.rSetting(this.bg);
        NameTags.setmgr.rSetting(this.bgLight);
        NameTags.setmgr.rSetting(this.bgAlpha);
        NameTags.setmgr.rSetting(this.outline);
        NameTags.setmgr.rSetting(this.outlineColor);
        NameTags.setmgr.rSetting(new Setting("TextLine", this, "Text"));
        NameTags.setmgr.rSetting(this.textR);
        NameTags.setmgr.rSetting(this.textG);
        NameTags.setmgr.rSetting(this.textB);
        NameTags.setmgr.rSetting(this.textA);
        NameTags.setmgr.rSetting(this.textColorMode);
        NameTags.setmgr.rSetting(new Setting("ColorLine", this, "Color"));
        NameTags.setmgr.rSetting(this.saturatuon);
        NameTags.setmgr.rSetting(this.bringhtness);
        NameTags.setmgr.rSetting(this.delay);
    }
    
    public Color getColor() {
        final String valString = this.textColorMode.getValString();
        switch (valString) {
            case "Astolfo": {
                return ColorUtils.astolfoColorsToColorObj(100, 100);
            }
            case "Rainbow": {
                return ColorUtils.rainbow(this.delay.getValInt(), this.saturatuon.getValFloat(), this.bringhtness.getValFloat());
            }
            default: {
                return new Color(this.textR.getValInt(), this.textG.getValInt(), this.textB.getValInt(), this.textA.getValInt());
            }
        }
    }
}
