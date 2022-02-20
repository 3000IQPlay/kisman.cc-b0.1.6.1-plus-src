//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.*;

public class Charms extends Module
{
    public Setting textureMode;
    public Setting polygonOffset;
    public Setting polygonMode;
    public Setting targetRender;
    public Setting render;
    public Setting customColor;
    public Setting color;
    public static Charms instance;
    
    public Charms() {
        super("Charms", "Charms", Category.RENDER);
        this.textureMode = new Setting("TextureMode", this, "Texture", new ArrayList<String>(Arrays.asList("Texture", "GL")));
        this.polygonOffset = new Setting("PolygonOffset", this, true);
        this.polygonMode = new Setting("PolygonMode", this, "RenderModel", new ArrayList<String>(Arrays.asList("RenderModel", "doRender")));
        this.targetRender = new Setting("TargetRender", this, true);
        this.render = new Setting("Redner", this, false);
        this.customColor = new Setting("CustomColor", this, false);
        this.color = new Setting("Color", this, "Color", new float[] { 1.0f, 0.0f, 0.0f, 1.0f });
        Charms.instance = this;
        Charms.setmgr.rSetting(this.textureMode);
        Charms.setmgr.rSetting(this.polygonOffset);
        Charms.setmgr.rSetting(this.polygonMode);
        Kisman.instance.settingsManager.rSetting(new Setting("Texture", this, false));
        Charms.setmgr.rSetting(this.render);
        Charms.setmgr.rSetting(this.targetRender);
        Charms.setmgr.rSetting(this.customColor);
        Charms.setmgr.rSetting(this.color);
    }
    
    public void onEnable() {
        if (Kisman.instance.moduleManager.getModule("KismanESP").isToggled()) {
            Kisman.instance.moduleManager.getModule("KismanESP").setToggled(false);
        }
    }
}
