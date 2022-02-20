//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.util.text.*;
import i.gishreloaded.gishcode.wrappers.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;

public class Speed extends Module
{
    public static Speed instance;
    private float yPortSpeed;
    public Setting speedMode;
    private Setting yPortLine;
    private Setting yWater;
    private Setting yLava;
    private Setting stiLine;
    private Setting stiSpeed;
    private float ovverideSpeed;
    @EventHandler
    private final Listener<EventPlayerUpdate> listener;
    
    public Speed() {
        super("Speed", "SPID", Category.MOVEMENT);
        this.speedMode = new Setting("SpeedMode", this, "Strafe", new ArrayList<String>(Arrays.asList("Strafe", "YPort", "Sti")));
        this.yPortLine = new Setting("YPortLine", this, "YPort");
        this.yWater = new Setting("Water", this, false);
        this.yLava = new Setting("Lava", this, false);
        this.stiLine = new Setting("StiLine", this, "Sti");
        this.stiSpeed = new Setting("StiSpeed", this, 4.0, 0.1, 10.0, true);
        this.ovverideSpeed = 1.0f;
        this.listener = (Listener<EventPlayerUpdate>)new Listener(event -> {
            if (this.speedMode.getValString().equalsIgnoreCase("Sti")) {
                if (this.ovverideSpeed != 1.0f && this.ovverideSpeed > 1.0f) {
                    Speed.mc.timer.tickLength = 50.0f / this.ovverideSpeed;
                    return;
                }
                Speed.mc.timer.tickLength = 50.0f / this.getSpeed();
            }
        }, new Predicate[0]);
        Speed.instance = this;
        Speed.setmgr.rSetting(this.speedMode);
        Speed.setmgr.rSetting(this.yPortLine);
        Kisman.instance.settingsManager.rSetting(new Setting("YPortSpeed", this, 0.05999999865889549, 0.009999999776482582, 0.15000000596046448, false));
        Speed.setmgr.rSetting(this.yWater);
        Speed.setmgr.rSetting(this.yLava);
        Speed.setmgr.rSetting(this.stiLine);
        Speed.setmgr.rSetting(this.stiSpeed);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        EntityUtil.resetTimer();
        Speed.mc.timer.tickLength = 50.0f;
    }
    
    public void update() {
        if (Speed.mc.player == null && Speed.mc.world == null) {
            return;
        }
        super.setDisplayInfo("[" + this.speedMode.getValString() + TextFormatting.GRAY + "]");
        final boolean boost = Math.abs(Speed.mc.player.rotationYawHead - Wrapper.INSTANCE.player().rotationYaw) < 90.0f;
        this.yPortSpeed = (float)Kisman.instance.settingsManager.getSettingByName(this, "YPortSpeed").getValDouble();
        if (Speed.mc.player.moveForward > 0.0f && Speed.mc.player.hurtTime < 5 && this.speedMode.getValString().equalsIgnoreCase("Strafe")) {
            if (Speed.mc.player.onGround) {
                Speed.mc.player.motionY = 0.405;
                final float f = Utils.getDirection();
                final EntityPlayerSP player = Speed.mc.player;
                player.motionX -= MathHelper.sin(f) * 0.2f;
                final EntityPlayerSP player2 = Speed.mc.player;
                player2.motionZ += MathHelper.cos(f) * 0.2f;
            }
            else {
                final double currentSpeed = Math.sqrt(Speed.mc.player.motionX * Speed.mc.player.motionX + Speed.mc.player.motionZ * Speed.mc.player.motionZ);
                final double speed = boost ? 1.0064 : 1.001;
                final double direction = Utils.getDirection();
                Speed.mc.player.motionX = -Math.sin(direction) * speed * currentSpeed;
                Speed.mc.player.motionZ = Math.cos(direction) * speed * currentSpeed;
            }
        }
        if (this.speedMode.getValString().equalsIgnoreCase("YPort")) {
            this.doYPortSpeed();
        }
    }
    
    private void doYPortSpeed() {
        if (!PlayerUtil.isMoving((EntityLivingBase)Speed.mc.player) || (Speed.mc.player.isInWater() && !this.yWater.getValBoolean() && Speed.mc.player.isInLava() && !this.yLava.getValBoolean()) || Speed.mc.player.collidedHorizontally) {
            return;
        }
        if (Speed.mc.player.onGround) {
            EntityUtil.setTimer(1.15f);
            Speed.mc.player.jump();
            PlayerUtil.setSpeed((EntityLivingBase)Speed.mc.player, PlayerUtil.getBaseMoveSpeed() + this.yPortSpeed);
        }
        else {
            Speed.mc.player.motionY = -1.0;
            EntityUtil.resetTimer();
        }
    }
    
    private float getSpeed() {
        return Math.max((float)this.stiSpeed.getValDouble(), 0.1f);
    }
}
