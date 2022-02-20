//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.client;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;

public class CustomFont extends Module
{
    public Setting mode;
    public Setting bold;
    public Setting italic;
    public static boolean turnOn;
    public static CustomFont instance;
    
    public CustomFont() {
        super("CustomFont", "custom font", Category.CLIENT);
        this.mode = new Setting("Mode", this, "Comfortaa", new ArrayList<String>(Arrays.asList("Verdana", "Comfortaa", "Comfortaa Light", "Consolas")));
        this.bold = new Setting("Bold", this, false);
        this.italic = new Setting("Italic", this, false);
        CustomFont.instance = this;
        CustomFont.setmgr.rSetting(this.mode);
        CustomFont.setmgr.rSetting(this.bold);
        CustomFont.setmgr.rSetting(this.italic);
    }
    
    @Override
    public void update() {
        CustomFont.turnOn = true;
    }
    
    @Override
    public void onDisable() {
        CustomFont.turnOn = false;
    }
    
    static {
        CustomFont.turnOn = false;
    }
}
