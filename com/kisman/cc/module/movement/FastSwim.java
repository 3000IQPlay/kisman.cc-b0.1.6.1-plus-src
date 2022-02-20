//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;

public class FastSwim extends Module
{
    private boolean isSprint;
    
    public FastSwim() {
        super("FastSwim", "swim", Category.MOVEMENT);
        this.isSprint = false;
    }
    
    public void onDisable() {
        if (this.isSprint && FastSwim.mc.player.isSprinting()) {
            FastSwim.mc.player.setSprinting(false);
        }
    }
    
    public void update() {
        if (FastSwim.mc.player == null && FastSwim.mc.world == null) {
            return;
        }
        if ((FastSwim.mc.player.isInLava() || FastSwim.mc.player.isInWater()) && PlayerUtil.isMoving((EntityLivingBase)FastSwim.mc.player)) {
            FastSwim.mc.player.setSprinting(true);
            this.isSprint = true;
            if (FastSwim.mc.gameSettings.keyBindJump.isKeyDown()) {
                FastSwim.mc.player.motionY = 0.098;
            }
        }
    }
}
