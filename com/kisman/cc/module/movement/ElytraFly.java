//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.util.*;
import net.minecraft.client.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;

public class ElytraFly extends Module
{
    private Setting mode;
    private Setting speedLine;
    private Setting speed;
    private Setting upSpeed;
    private Setting downSpeed;
    private Setting glideSpeed;
    private Setting cancelLine;
    private Setting cancelInWater;
    private Setting cancelAtHeight;
    private Setting otherLine;
    private Setting instantFly;
    private Setting equipElytra;
    private Setting pitchSpoof;
    private TimerUtils packetTimer;
    private TimerUtils accelerationTimer;
    private TimerUtils accelerationResetTimer;
    private TimerUtils instantFlyTimer;
    private boolean sendMessage;
    private int elytraSlot;
    @EventHandler
    private final Listener<EventPlayerTravel> listener;
    
    public ElytraFly() {
        super("ElytraFly", "ElytraFly", Category.MOVEMENT);
        this.mode = new Setting("Mode", this, Mode.Control);
        this.speedLine = new Setting("SpeedLine", this, "Speed");
        this.speed = new Setting("Speed", this, 1.82, 0.0, 10.0, false);
        this.upSpeed = new Setting("UpSpeed", this, 2.0, 0.0, 10.0, false);
        this.downSpeed = new Setting("DownSpeed", this, 1.82, 0.0, 10.0, false);
        this.glideSpeed = new Setting("GlideSpeed", this, 1.0, 0.0, 10.0, false);
        this.cancelLine = new Setting("CancelLine", this, "Cancel");
        this.cancelInWater = new Setting("CancelInWater", this, true);
        this.cancelAtHeight = new Setting("CancelAtHeight", this, 5.0, 0.0, 10.0, true);
        this.otherLine = new Setting("OtherLine", this, "Other");
        this.instantFly = new Setting("InstantFly", this, false);
        this.equipElytra = new Setting("EquipElytra", this, true);
        this.pitchSpoof = new Setting("PitchSpoof", this, false);
        this.packetTimer = new TimerUtils();
        this.accelerationTimer = new TimerUtils();
        this.accelerationResetTimer = new TimerUtils();
        this.instantFlyTimer = new TimerUtils();
        this.sendMessage = false;
        this.elytraSlot = -1;
        this.listener = (Listener<EventPlayerTravel>)new Listener(event -> {
            if (ElytraFly.mc.player == null) {
                return;
            }
            if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                return;
            }
            if (!ElytraFly.mc.player.isElytraFlying()) {
                if (!ElytraFly.mc.player.onGround && this.instantFly.getValBoolean()) {
                    if (!this.instantFlyTimer.passedMillis(1000L)) {
                        return;
                    }
                    this.instantFlyTimer.reset();
                    ElytraFly.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)ElytraFly.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                }
                return;
            }
            final String valString = this.mode.getValString();
            switch (valString) {
                case "Normal": {
                    this.handleImmediateModeElytra(event);
                    break;
                }
                case "Control": {
                    this.handleControlMode(event);
                    break;
                }
            }
        }, new Predicate[0]);
        ElytraFly.setmgr.rSetting(this.mode);
        ElytraFly.setmgr.rSetting(this.speedLine);
        ElytraFly.setmgr.rSetting(this.speed);
        ElytraFly.setmgr.rSetting(this.upSpeed);
        ElytraFly.setmgr.rSetting(this.downSpeed);
        ElytraFly.setmgr.rSetting(this.glideSpeed);
        ElytraFly.setmgr.rSetting(this.cancelLine);
        ElytraFly.setmgr.rSetting(this.cancelInWater);
        ElytraFly.setmgr.rSetting(this.cancelAtHeight);
        ElytraFly.setmgr.rSetting(this.otherLine);
        ElytraFly.setmgr.rSetting(this.instantFly);
        ElytraFly.setmgr.rSetting(this.equipElytra);
        ElytraFly.setmgr.rSetting(this.pitchSpoof);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        this.elytraSlot = -1;
        if (ElytraFly.mc.player == null && ElytraFly.mc.world == null) {
            return;
        }
        if (this.equipElytra.getValBoolean()) {
            if (ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.ELYTRA) {
                this.elytraSlot = InventoryUtil.findItem(Items.ELYTRA, 0, 36);
            }
            if (this.elytraSlot != -1) {
                final boolean armorOnChest = ElytraFly.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() != Items.AIR;
                ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, this.elytraSlot, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
                ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, 6, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
                if (armorOnChest) {
                    ElytraFly.mc.playerController.windowClick(ElytraFly.mc.player.inventoryContainer.windowId, this.elytraSlot, 0, ClickType.PICKUP, (EntityPlayer)ElytraFly.mc.player);
                }
            }
        }
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    public void handleImmediateModeElytra(final EventPlayerTravel travel) {
        if (!ElytraFly.mc.player.movementInput.jump) {
            ElytraFly.mc.player.setVelocity(0.0, 0.0, 0.0);
            travel.cancel();
            final double[] dir = MathUtil.directionSpeed(this.speed.getValDouble());
            if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
                ElytraFly.mc.player.motionX = dir[0];
                ElytraFly.mc.player.motionY = -(this.glideSpeed.getValDouble() / 10000.0);
                ElytraFly.mc.player.motionZ = dir[1];
            }
            if (ElytraFly.mc.player.movementInput.sneak) {
                ElytraFly.mc.player.motionY = -this.downSpeed.getValDouble();
            }
            ElytraFly.mc.player.prevLimbSwingAmount = 0.0f;
            ElytraFly.mc.player.limbSwingAmount = 0.0f;
            ElytraFly.mc.player.limbSwing = 0.0f;
            return;
        }
        final double motionSq = Math.sqrt(ElytraFly.mc.player.motionX * ElytraFly.mc.player.motionX + ElytraFly.mc.player.motionZ * ElytraFly.mc.player.motionZ);
        if (motionSq > 1.0) {
            return;
        }
        final double[] dir2 = MathUtil.directionSpeedNoForward(this.speed.getValDouble());
        ElytraFly.mc.player.motionX = dir2[0];
        ElytraFly.mc.player.motionY = -(this.glideSpeed.getValDouble() / 10000.0);
        ElytraFly.mc.player.motionZ = dir2[1];
        travel.cancel();
    }
    
    private void handleControlMode(final EventPlayerTravel event) {
        final double[] dir = MathUtil.directionSpeed(this.speed.getValDouble());
        if (ElytraFly.mc.player.movementInput.moveStrafe != 0.0f || ElytraFly.mc.player.movementInput.moveForward != 0.0f) {
            ElytraFly.mc.player.motionX = dir[0];
            ElytraFly.mc.player.motionZ = dir[1];
            final EntityPlayerSP player = ElytraFly.mc.player;
            player.motionX -= ElytraFly.mc.player.motionX * (Math.abs(ElytraFly.mc.player.rotationPitch) + 90.0f) / 90.0 - ElytraFly.mc.player.motionX;
            final EntityPlayerSP player2 = ElytraFly.mc.player;
            player2.motionZ -= ElytraFly.mc.player.motionZ * (Math.abs(ElytraFly.mc.player.rotationPitch) + 90.0f) / 90.0 - ElytraFly.mc.player.motionZ;
        }
        else {
            ElytraFly.mc.player.motionX = 0.0;
            ElytraFly.mc.player.motionZ = 0.0;
        }
        ElytraFly.mc.player.motionY = -MathUtil.degToRad(ElytraFly.mc.player.rotationPitch) * ElytraFly.mc.player.movementInput.moveForward;
        ElytraFly.mc.player.prevLimbSwingAmount = 0.0f;
        ElytraFly.mc.player.limbSwingAmount = 0.0f;
        ElytraFly.mc.player.limbSwing = 0.0f;
        event.cancel();
    }
    
    private enum Mode
    {
        Normal, 
        Control;
    }
}
