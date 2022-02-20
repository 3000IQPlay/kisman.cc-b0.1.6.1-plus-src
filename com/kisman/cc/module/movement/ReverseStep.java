//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.entity.*;

public class ReverseStep extends Module
{
    public ReverseStep() {
        super("ReverseStep", "", Category.MOVEMENT);
        Kisman.instance.settingsManager.rSetting(new Setting("Height", this, 1.0, 0.5, 2.5, false));
    }
    
    public void update() {
        if (ReverseStep.mc.world == null || ReverseStep.mc.player == null || ReverseStep.mc.player.isInWater() || ReverseStep.mc.player.isInLava() || ReverseStep.mc.player.isOnLadder() || ReverseStep.mc.gameSettings.keyBindJump.isKeyDown()) {
            return;
        }
        final double height = Kisman.instance.settingsManager.getSettingByName(this, "Height").getValDouble();
        if (ReverseStep.mc.player != null && ReverseStep.mc.player.onGround && !ReverseStep.mc.player.isInWater() && !ReverseStep.mc.player.isOnLadder()) {
            for (double y = 0.0; y < height + 0.5; y += 0.01) {
                if (!ReverseStep.mc.world.getCollisionBoxes((Entity)ReverseStep.mc.player, ReverseStep.mc.player.getEntityBoundingBox().offset(0.0, -y, 0.0)).isEmpty()) {
                    ReverseStep.mc.player.motionY = -10.0;
                    break;
                }
            }
        }
    }
}
