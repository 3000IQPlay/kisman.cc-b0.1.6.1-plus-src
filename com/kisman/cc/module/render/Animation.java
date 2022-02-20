//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.render;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import com.kisman.cc.oldclickgui.csgo.components.*;

public class Animation extends Module
{
    public static Animation instance;
    public Setting speed;
    
    public Animation() {
        super("Animation", Category.RENDER);
        this.speed = new Setting("Speed", this, 13.0, 1.0, 20.0, Slider.NumberType.INTEGER);
        Animation.instance = this;
        Animation.setmgr.rSetting(this.speed);
    }
}
