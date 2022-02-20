//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import com.kisman.cc.util.*;

public class Anchor extends Module
{
    private int holeblocks;
    private Vec3d center;
    
    public Anchor() {
        super("Anchor", "help with holes", Category.MOVEMENT);
        this.center = Vec3d.ZERO;
        Kisman.instance.settingsManager.rSetting(new Setting("Pull", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Pitch", this, 60.0, 0.0, 90.0, false));
    }
    
    private boolean isBlockHole(final BlockPos blockpos) {
        this.holeblocks = 0;
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK || Anchor.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK || Anchor.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK || Anchor.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK || Anchor.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.ENDER_CHEST) {
            ++this.holeblocks;
        }
        if (Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK || Anchor.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.ENDER_CHEST) {
            ++this.holeblocks;
        }
        return this.holeblocks >= 9;
    }
    
    private Vec3d getCenter(final double posX, final double posY, final double posZ) {
        final double x = Math.floor(posX) + 0.5;
        final double y = Math.floor(posY);
        final double z = Math.floor(posZ) + 0.5;
        return new Vec3d(x, y, z);
    }
    
    public void update() {
        if (Anchor.mc.world == null && Anchor.mc.player == null) {
            return;
        }
        if (Anchor.mc.player.posY < 0.0) {
            return;
        }
        final double pitch = Kisman.instance.settingsManager.getSettingByName(this, "Pitch").getValDouble();
        final boolean pull = Kisman.instance.settingsManager.getSettingByName(this, "Pull").getValBoolean();
        if (Anchor.mc.player.rotationPitch >= pitch && (this.isBlockHole(PlayerUtil.getPlayerPos().down(1)) || this.isBlockHole(PlayerUtil.getPlayerPos().down(2)) || this.isBlockHole(PlayerUtil.getPlayerPos().down(3)) || this.isBlockHole(PlayerUtil.getPlayerPos().down(4)))) {
            if (!pull) {
                Anchor.mc.player.motionX = 0.0;
                Anchor.mc.player.motionZ = 0.0;
                Anchor.mc.player.movementInput.moveForward = 0.0f;
                Anchor.mc.player.movementInput.moveStrafe = 0.0f;
            }
            else {
                this.center = this.getCenter(Anchor.mc.player.posX, Anchor.mc.player.posY, Anchor.mc.player.posZ);
                final double xDiff = Math.abs(this.center.x - Anchor.mc.player.posX);
                final double zDiff = Math.abs(this.center.z - Anchor.mc.player.posZ);
                if (xDiff <= 0.1 && zDiff <= 0.1) {
                    this.center = Vec3d.ZERO;
                }
                else {
                    final double motionX = this.center.x - Anchor.mc.player.posX;
                    final double motionZ = this.center.z - Anchor.mc.player.posZ;
                    Anchor.mc.player.motionX = motionX / 2.0;
                    Anchor.mc.player.motionZ = motionZ / 2.0;
                }
            }
        }
    }
    
    public void onDisable() {
        this.holeblocks = 0;
    }
}
