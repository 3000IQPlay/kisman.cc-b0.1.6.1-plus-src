//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;

public class CrystalModifier extends Module
{
    public static CrystalModifier instance;
    public Setting mode;
    public Setting preview;
    private Setting scaleLine;
    public Setting scaleX;
    public Setting scaleY;
    public Setting scaleZ;
    private Setting translateLine;
    public Setting translateX;
    public Setting translateY;
    public Setting translateZ;
    private Setting rotateLine;
    public Setting rotateX;
    public Setting rotateY;
    public Setting rotateZ;
    private Setting crystalSettingLine;
    public Setting insideCube;
    public Setting outsideCube;
    public Setting outsideCube2;
    public Setting texture;
    public Setting customColor;
    public Setting crystalColor;
    private Setting outlineLine;
    public Setting outline;
    public Setting outlineMode;
    public Setting lineWidth;
    public Setting color;
    private Setting enchantedLine;
    public Setting enchanted;
    public Setting enchColor;
    private Setting speedLine;
    public Setting speed;
    public Setting bounce;
    
    public CrystalModifier() {
        super("CrystalCharms", "r", Category.RENDER);
        this.mode = new Setting("Mode", this, Modes.Fill);
        this.preview = new Setting("Crystal", this, "Crystal", (Entity)new EntityEnderCrystal((World)CrystalModifier.mc.world));
        this.scaleLine = new Setting("ScaleLine", this, "Scale");
        this.scaleX = new Setting("ScaleX", this, 0.0, -2.0, 2.0, false);
        this.scaleY = new Setting("ScaleY", this, 0.0, -2.0, 2.0, false);
        this.scaleZ = new Setting("ScaleZ", this, 0.0, -2.0, 2.0, false);
        this.translateLine = new Setting("TranslateLine", this, "Translate");
        this.translateX = new Setting("TranslateX", this, 0.0, -2.0, 2.0, false);
        this.translateY = new Setting("TranslateY", this, 0.0, -2.0, 2.0, false);
        this.translateZ = new Setting("TranslateZ", this, 0.0, -2.0, 2.0, false);
        this.rotateLine = new Setting("RotateLine", this, "Rotate");
        this.rotateX = new Setting("RotateX", this, 0.0, 0.0, 360.0, true);
        this.rotateY = new Setting("RotateY", this, 0.0, 0.0, 360.0, true);
        this.rotateZ = new Setting("RotateZ", this, 0.0, 0.0, 360.0, true);
        this.crystalSettingLine = new Setting("CrystalSettingLine", this, "CrystalSetting");
        this.insideCube = new Setting("InsideCube", this, true);
        this.outsideCube = new Setting("OutsideCube", this, true);
        this.outsideCube2 = new Setting("OutsideCube2", this, true);
        this.texture = new Setting("Texture", this, false);
        this.customColor = new Setting("CustomColor", this, false);
        this.crystalColor = new Setting("CrystalColor", this, "Color", new float[] { 0.0f, 0.0f, 1.0f, 1.0f });
        this.outlineLine = new Setting("OutLineLine", this, "OutLine");
        this.outline = new Setting("Outline", this, false);
        this.outlineMode = new Setting("OutlineMode", this, OutlineModes.Wire);
        this.lineWidth = new Setting("LineWidht", this, 3.0, 0.5, 5.0, false);
        this.color = new Setting("Color", this, "Color", new float[] { 1.0f, 0.0f, 0.0f, 1.0f });
        this.enchantedLine = new Setting("EnchantedLine", this, "Enchanted");
        this.enchanted = new Setting("Enchanted", this, false);
        this.enchColor = new Setting("EnchColor", this, "Color", new float[] { 0.0f, 1.0f, 0.0f, 1.0f });
        this.speedLine = new Setting("SpeedLine", this, "Speed");
        this.speed = new Setting("CrystalSpeed", this, 3.0, 0.0, 50.0, false);
        this.bounce = new Setting("CrystalBounce", this, 0.20000000298023224, 0.0, 10.0, false);
        CrystalModifier.instance = this;
        CrystalModifier.setmgr.rSetting(this.mode);
        CrystalModifier.setmgr.rSetting(this.preview);
        CrystalModifier.setmgr.rSetting(this.scaleLine);
        CrystalModifier.setmgr.rSetting(this.scaleX);
        CrystalModifier.setmgr.rSetting(this.scaleY);
        CrystalModifier.setmgr.rSetting(this.scaleZ);
        CrystalModifier.setmgr.rSetting(this.translateLine);
        CrystalModifier.setmgr.rSetting(this.translateX);
        CrystalModifier.setmgr.rSetting(this.translateY);
        CrystalModifier.setmgr.rSetting(this.translateZ);
        CrystalModifier.setmgr.rSetting(this.rotateLine);
        CrystalModifier.setmgr.rSetting(this.rotateX);
        CrystalModifier.setmgr.rSetting(this.rotateY);
        CrystalModifier.setmgr.rSetting(this.rotateZ);
        CrystalModifier.setmgr.rSetting(this.crystalSettingLine);
        CrystalModifier.setmgr.rSetting(this.insideCube);
        CrystalModifier.setmgr.rSetting(this.outsideCube);
        CrystalModifier.setmgr.rSetting(this.outsideCube2);
        CrystalModifier.setmgr.rSetting(this.texture);
        CrystalModifier.setmgr.rSetting(this.customColor);
        CrystalModifier.setmgr.rSetting(this.crystalColor);
        CrystalModifier.setmgr.rSetting(this.outlineLine);
        CrystalModifier.setmgr.rSetting(this.outlineMode);
        CrystalModifier.setmgr.rSetting(this.lineWidth);
        CrystalModifier.setmgr.rSetting(this.color);
        CrystalModifier.setmgr.rSetting(this.enchantedLine);
        CrystalModifier.setmgr.rSetting(this.enchanted);
        CrystalModifier.setmgr.rSetting(this.enchColor);
        CrystalModifier.setmgr.rSetting(this.speedLine);
        CrystalModifier.setmgr.rSetting(this.speed);
        CrystalModifier.setmgr.rSetting(this.bounce);
    }
    
    public enum OutlineModes
    {
        Wire, 
        Flat;
    }
    
    public enum Modes
    {
        Fill, 
        Wireframe;
    }
}
