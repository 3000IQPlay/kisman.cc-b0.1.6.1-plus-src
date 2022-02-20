//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.*;
import net.minecraft.client.entity.*;

public class Fly extends Module
{
    private Setting mode;
    private Setting vanillaLine;
    private Setting motionLine;
    private Setting glider;
    private Setting autoUp;
    private Setting autoDown;
    private Setting motionSpeed;
    private Setting glide;
    private float flySpeed;
    
    public Fly() {
        super("Fly", "Your flying", Category.MOVEMENT);
        this.mode = new Setting("Mode", this, "Vanilla", new ArrayList<String>(Arrays.asList("Vanilla", "Matrix")));
        this.vanillaLine = new Setting("VanullaLine", this, "Vanilla");
        this.motionLine = new Setting("MotionLine", this, "Motion");
        this.glider = new Setting("Glider", this, true);
        this.autoUp = new Setting("AutoUp", this, true);
        this.autoDown = new Setting("AutoDown", this, true);
        this.motionSpeed = new Setting("Speed", this, 5.0, 1.0, 8.0, false);
        this.glide = new Setting("Glide", this, 0.03, 0.01, 0.1, false);
        Kisman.instance.settingsManager.rSetting(new Setting("Mode", this, "Vanilla", new ArrayList<String>(Arrays.asList("Vanilla", "Motion", "Matrix"))));
        Fly.setmgr.rSetting(this.vanillaLine);
        Kisman.instance.settingsManager.rSetting(new Setting("FlySpeed", this, 0.10000000149011612, 0.10000000149011612, 100.0, false));
        Fly.setmgr.rSetting(this.motionLine);
        Fly.setmgr.rSetting(this.glider);
        Fly.setmgr.rSetting(this.autoUp);
        Fly.setmgr.rSetting(this.autoDown);
        Fly.setmgr.rSetting(this.motionSpeed);
        Fly.setmgr.rSetting(this.glide);
    }
    
    public void onEnable() {
        if (Fly.mc.player == null && Fly.mc.world == null) {
            return;
        }
        if (this.mode.getValString().equalsIgnoreCase("Vanilla")) {
            Fly.mc.player.capabilities.isFlying = true;
            Fly.mc.player.capabilities.setFlySpeed(this.flySpeed);
        }
    }
    
    public void update() {
        if (Fly.mc.player == null && Fly.mc.world == null) {
            return;
        }
        this.flySpeed = (float)Kisman.instance.settingsManager.getSettingByName(this, "FlySpeed").getValDouble();
        if (this.mode.getValString().equalsIgnoreCase("Vanilla")) {
            Fly.mc.player.capabilities.isFlying = true;
            Fly.mc.player.capabilities.setFlySpeed(this.flySpeed);
        }
        else {
            Fly.mc.player.capabilities.isFlying = false;
            Fly.mc.player.capabilities.setFlySpeed(0.1f);
        }
        if (this.mode.getValString().equalsIgnoreCase("Matrix") && Fly.mc.gameSettings.keyBindJump.pressed) {
            Fly.mc.player.jump();
            final EntityPlayerSP player = Fly.mc.player;
            player.motionY -= 0.25;
            if (Fly.mc.gameSettings.keyBindForward.pressed) {
                Fly.mc.timer.elapsedTicks = 1;
                final EntityPlayerSP player2 = Fly.mc.player;
                player2.motionX *= 1.100000023841858;
                final EntityPlayerSP player3 = Fly.mc.player;
                player3.motionZ *= 1.100000023841858;
                Fly.mc.player.onGround = false;
            }
        }
    }
    
    public void onDisable() {
        if (Fly.mc.player == null && Fly.mc.world == null) {
            return;
        }
        Fly.mc.player.capabilities.isFlying = false;
        Fly.mc.player.capabilities.setFlySpeed(0.1f);
        Fly.mc.timer.elapsedTicks = 1;
    }
}
