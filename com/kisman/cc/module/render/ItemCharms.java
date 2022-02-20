//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import java.awt.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.oldclickgui.csgo.components.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.renderer.*;

public class ItemCharms extends Module
{
    public static ItemCharms instance;
    public Setting glowESP;
    public Setting red;
    public Setting green;
    public Setting blue;
    public Setting alpha;
    public Setting radiusESP;
    public Setting qualityESP;
    public Setting gradientAlpha;
    public Setting alphaOutline;
    public Setting piOutline;
    public Setting radOutline;
    public Setting glintModify;
    public Color color;
    
    public ItemCharms() {
        super("ItemCharms", "", Category.RENDER);
        this.glowESP = new Setting("GlowESP", this, "None", Arrays.asList("None", "Color", "Astral", "RainbowCube", "Gradient", "Aqua", "Circle", "Smoke"));
        this.red = new Setting("ColorESPRed", this, 1.0, 0.0, 1.0, false);
        this.green = new Setting("ColorESPGreen", this, 1.0, 0.0, 1.0, false);
        this.blue = new Setting("ColorESPBlue", this, 1.0, 0.0, 1.0, false);
        this.alpha = new Setting("ColorESPAlpha", this, 1.0, 0.0, 1.0, false);
        this.radiusESP = new Setting("RadiusESP", this, 1.0, 0.0, 5.0, Slider.NumberType.DECIMAL);
        this.qualityESP = new Setting("QualityESP", this, 1.0, 0.0, 20.0, Slider.NumberType.DECIMAL);
        this.gradientAlpha = new Setting("GradientAlpha", this, false);
        this.alphaOutline = new Setting("AlphaOutline", this, 255.0, 0.0, 255.0, Slider.NumberType.INTEGER).setVisible(() -> !this.gradientAlpha.getValBoolean());
        this.piOutline = new Setting("PIOutline", this, 3.141592653, 0.0, 10.0, Slider.NumberType.DECIMAL).setVisible(() -> this.glowESP.getValString().equalsIgnoreCase("Circle"));
        this.radOutline = new Setting("RADOutline", this, 0.75, 0.0, 5.0, Slider.NumberType.DECIMAL).setVisible(() -> this.glowESP.getValString().equalsIgnoreCase("Circle"));
        this.glintModify = new Setting("GlintModify", this, false);
        this.color = new Color(this.red.getRed(), this.red.getGreen(), this.red.getBlue(), this.red.getAlpha());
        ItemCharms.instance = this;
        ItemCharms.setmgr.rSetting(this.red);
        ItemCharms.setmgr.rSetting(this.green);
        ItemCharms.setmgr.rSetting(this.blue);
        ItemCharms.setmgr.rSetting(this.alpha);
        ItemCharms.setmgr.rSetting(this.glintModify);
    }
    
    public void onDisable() {
        this.color = new Color(255, 255, 255);
    }
    
    public void update() {
    }
    
    public void onRenderWorld(final RenderWorldLastEvent event) {
        GlStateManager.pushMatrix();
        GlStateManager.pushAttrib();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.enableDepth();
        GlStateManager.depthMask(true);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
        GlStateManager.popAttrib();
        GlStateManager.popMatrix();
    }
}
