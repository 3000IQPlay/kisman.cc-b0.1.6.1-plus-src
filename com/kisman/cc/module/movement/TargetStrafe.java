//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import net.minecraft.entity.*;
import com.kisman.cc.module.*;

public class TargetStrafe extends Module
{
    private Setting range;
    private Setting motion;
    private Setting dynamic;
    private Setting damageBoost;
    private Entity target;
    private float format;
    private float strafe;
    
    public TargetStrafe() {
        super("TargetStrafe", "TargetStrafe", Category.MOVEMENT);
        this.range = new Setting("Range", this, 3.5999999046325684, 0.10000000149011612, 7.0, false);
        this.motion = new Setting("Motion", this, 0.20000000298023224, 0.009999999776482582, 2.0, false);
        this.dynamic = new Setting("DynamicSpeed", this, true);
        this.damageBoost = new Setting("DamageBoost", this, false);
        this.format = 0.0f;
        this.strafe = 1.0f;
        TargetStrafe.setmgr.rSetting(this.range);
        TargetStrafe.setmgr.rSetting(this.motion);
        TargetStrafe.setmgr.rSetting(this.dynamic);
        TargetStrafe.setmgr.rSetting(this.damageBoost);
    }
}
