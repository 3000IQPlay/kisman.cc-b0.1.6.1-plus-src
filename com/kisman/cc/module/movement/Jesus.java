//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import net.minecraft.util.text.*;
import com.kisman.cc.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import net.minecraft.client.entity.*;

public class Jesus extends Module
{
    private Setting mode;
    
    public Jesus() {
        super("Jesus", "", Category.MOVEMENT);
        this.mode = new Setting("Mode", this, "Matrix", new ArrayList<String>(Arrays.asList("Matrix", "Solid")));
        super.setDisplayInfo("[" + this.mode.getValString() + TextFormatting.GRAY + "]");
        Jesus.setmgr.rSetting(this.mode);
        Kisman.instance.settingsManager.rSetting(new Setting("SpeedMatrix", this, 0.6000000238418579, 0.0, 1.0, false));
        Kisman.instance.settingsManager.rSetting(new Setting("SpeedSolid", this, 1.0, 0.0, 2.0, false));
    }
    
    public void update() {
        if (Jesus.mc.player == null && Jesus.mc.world == null) {
            return;
        }
        if (this.mode.getValString().equalsIgnoreCase("Matrix")) {
            final float speed = (float)Kisman.instance.settingsManager.getSettingByName(this, "SpeedMatrix").getValDouble();
            if (Jesus.mc.world.getBlockState(new BlockPos(Jesus.mc.player.posX, Jesus.mc.player.posY + 0.3700000047683716, Jesus.mc.player.posZ)).getBlock() == Blocks.WATER) {
                Jesus.mc.player.jump();
                Jesus.mc.player.jumpMovementFactor = 0.0f;
                final EntityPlayerSP player = Jesus.mc.player;
                player.motionX *= speed;
                final EntityPlayerSP player2 = Jesus.mc.player;
                player2.motionZ *= speed;
                Jesus.mc.player.onGround = false;
                if (Jesus.mc.player.isInWater() || Jesus.mc.player.isInLava()) {
                    Jesus.mc.player.onGround = false;
                }
            }
        }
        else if (this.mode.getValString().equalsIgnoreCase("Solid")) {
            final float speed = (float)Kisman.instance.settingsManager.getSettingByName(this, "SpeedSolid").getValDouble();
            if (Jesus.mc.world.getBlockState(new BlockPos(Jesus.mc.player.posX, Jesus.mc.player.posY + 1.0, Jesus.mc.player.posZ)).getBlock() == Block.getBlockById(9)) {
                Jesus.mc.player.motionY = 0.18000000715255737;
            }
            else if (Jesus.mc.world.getBlockState(new BlockPos(Jesus.mc.player.posX, Jesus.mc.player.posY + 1.0E-7, Jesus.mc.player.posZ)).getBlock() == Block.getBlockById(9)) {
                Jesus.mc.player.fallDistance = 0.0f;
                Jesus.mc.player.motionX = 0.0;
                Jesus.mc.player.motionY = 0.05999999865889549;
                Jesus.mc.player.jumpMovementFactor = speed;
                Jesus.mc.player.motionY = 0.0;
            }
        }
    }
}
