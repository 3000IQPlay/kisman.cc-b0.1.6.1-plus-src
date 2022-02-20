//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.init.*;

public class IceSpeed extends Module
{
    private double speed;
    
    public IceSpeed() {
        super("IceSpeed", "IceSpeed", Category.MOVEMENT);
        Kisman.instance.settingsManager.rSetting(new Setting("Speed", this, 0.4000000059604645, 0.20000000298023224, 1.5, false));
    }
    
    public void onEnable() {
        this.speed = Kisman.instance.settingsManager.getSettingByName(this, "Speed").getValDouble();
        Blocks.ICE.slipperiness = (float)this.speed;
        Blocks.PACKED_ICE.slipperiness = (float)this.speed;
        Blocks.FROSTED_ICE.slipperiness = (float)this.speed;
    }
    
    public void update() {
        this.speed = Kisman.instance.settingsManager.getSettingByName(this, "Speed").getValDouble();
    }
    
    public void onDisable() {
        Blocks.ICE.slipperiness = 0.98f;
        Blocks.PACKED_ICE.slipperiness = 0.98f;
        Blocks.FROSTED_ICE.slipperiness = 0.98f;
    }
}
