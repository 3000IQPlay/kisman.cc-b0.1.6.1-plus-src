//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.network.play.server.*;
import com.kisman.cc.event.*;
import com.kisman.cc.util.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

public class Scaffold extends Module
{
    private Setting mode;
    private Setting stopMotion;
    private Setting delay;
    private TimerUtils timer;
    private TimerUtils towerPauseTimer;
    private TimerUtils towerTimer;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener1;
    @EventHandler
    private final Listener<EventPlayerMove> listener2;
    
    public Scaffold() {
        super("Scaffold", "Scaffold", Category.MOVEMENT);
        this.mode = new Setting("Mode", this, Modes.Tower);
        this.stopMotion = new Setting("StopMotion", this, true);
        this.delay = new Setting("Delay", this, 0.0, 0.0, 1.0, false);
        this.timer = new TimerUtils();
        this.towerPauseTimer = new TimerUtils();
        this.towerTimer = new TimerUtils();
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.isCancelled()) {
                return;
            }
            if (event.getEra() != Event.Era.PRE) {
                return;
            }
            if (!this.timer.passedMillis((long)(this.delay.getValDouble() * 1000.0))) {
                return;
            }
            ItemStack stack = Scaffold.mc.player.getHeldItemMainhand();
            int oldSlot = -1;
            if (!this.verifyStack(stack)) {
                for (int i = 0; i < 9; ++i) {
                    stack = Scaffold.mc.player.inventory.getStackInSlot(i);
                    if (this.verifyStack(stack)) {
                        oldSlot = Scaffold.mc.player.inventory.currentItem;
                        Scaffold.mc.player.inventory.currentItem = i;
                        Scaffold.mc.playerController.updateController();
                        break;
                    }
                }
            }
            if (!this.verifyStack(stack)) {
                return;
            }
            this.timer.reset();
            BlockPos toPlaceAt = null;
            final BlockPos feetBlock = PlayerUtil.GetLocalPlayerPosFloored().down();
            final boolean placeAtFeet = this.isValidPlaceBlockState(feetBlock);
            if (this.mode.getValString().equals(Modes.Tower.name()) && placeAtFeet && Scaffold.mc.player.movementInput.jump && this.towerTimer.passedMillis(250L) && !Scaffold.mc.player.isElytraFlying()) {
                if (event.getX() >= 255.0) {
                    return;
                }
                if (this.towerPauseTimer.passedMillis(1500L)) {
                    this.towerPauseTimer.reset();
                    Scaffold.mc.player.motionY = -0.2800000011920929;
                }
                else {
                    final float towerMotion = 0.42f;
                    Scaffold.mc.player.setVelocity(0.0, 0.41999998688697815, 0.0);
                }
            }
            if (placeAtFeet) {
                toPlaceAt = feetBlock;
            }
            else {
                final BlockInteractionHelper.ValidResult result = BlockInteractionHelper.valid(feetBlock);
                if (result != BlockInteractionHelper.ValidResult.Ok && result != BlockInteractionHelper.ValidResult.AlreadyBlockThere) {
                    final BlockPos[] array = { feetBlock.north(), feetBlock.south(), feetBlock.east(), feetBlock.west() };
                    BlockPos toSelect = null;
                    double lastDistance = 420.0;
                    for (final BlockPos pos : array) {
                        if (this.isValidPlaceBlockState(pos)) {
                            final double dist = pos.getDistance((int)Scaffold.mc.player.posX, (int)Scaffold.mc.player.posY, (int)Scaffold.mc.player.posZ);
                            if (lastDistance > dist) {
                                lastDistance = dist;
                                toSelect = pos;
                            }
                        }
                    }
                    if (toSelect != null) {
                        toPlaceAt = toSelect;
                    }
                }
            }
            if (toPlaceAt != null) {
                final Vec3d eyesPos = new Vec3d(Scaffold.mc.player.posX, Scaffold.mc.player.posY + Scaffold.mc.player.getEyeHeight(), Scaffold.mc.player.posZ);
                for (final EnumFacing side : EnumFacing.values()) {
                    final BlockPos neighbor = toPlaceAt.offset(side);
                    final EnumFacing side2 = side.getOpposite();
                    if (Scaffold.mc.world.getBlockState(neighbor).getBlock().canCollideCheck(Scaffold.mc.world.getBlockState(neighbor), false)) {
                        final Vec3d hitVec = new Vec3d((Vec3i)neighbor).add(new Vec3d(0.5, 0.5, 0.5)).add(new Vec3d(side2.getDirectionVec()).scale(0.5));
                        if (eyesPos.distanceTo(hitVec) <= 5.0) {
                            final float[] rotations = BlockInteractionHelper.getFacingRotations(toPlaceAt.getX(), toPlaceAt.getY(), toPlaceAt.getZ(), side);
                            event.cancel();
                            PlayerUtil.packetFacePitchAndYaw(rotations[1], rotations[0]);
                            break;
                        }
                    }
                }
                if (BlockInteractionHelper.place(toPlaceAt, 5.0f, false, false, true) == BlockInteractionHelper.PlaceResult.Placed) {}
            }
            else {
                this.towerPauseTimer.reset();
            }
            if (oldSlot != -1) {
                Scaffold.mc.player.inventory.currentItem = oldSlot;
                Scaffold.mc.playerController.updateController();
            }
        }, new Predicate[0]);
        this.listener1 = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketPlayerPosLook) {
                this.towerTimer.reset();
            }
        }, new Predicate[0]);
        this.listener2 = (Listener<EventPlayerMove>)new Listener(event -> {
            if (!this.stopMotion.getValBoolean()) {
                return;
            }
            double x = event.x;
            final double y = event.y;
            double z = event.z;
            if (Scaffold.mc.player.onGround && !Scaffold.mc.player.noClip) {
                final double increment = 0.05;
                while (x != 0.0 && this.isOffsetBBEmpty(x, -1.0, 0.0)) {
                    if (x < increment && x >= -increment) {
                        x = 0.0;
                    }
                    else if (x > 0.0) {
                        x -= increment;
                    }
                    else {
                        x += increment;
                    }
                }
                while (z != 0.0 && this.isOffsetBBEmpty(0.0, -1.0, z)) {
                    if (z < increment && z >= -increment) {
                        z = 0.0;
                    }
                    else if (z > 0.0) {
                        z -= increment;
                    }
                    else {
                        z += increment;
                    }
                }
                while (x != 0.0 && z != 0.0 && this.isOffsetBBEmpty(x, -1.0, z)) {
                    if (x < increment && x >= -increment) {
                        x = 0.0;
                    }
                    else if (x > 0.0) {
                        x -= increment;
                    }
                    else {
                        x += increment;
                    }
                    if (z < increment && z >= -increment) {
                        z = 0.0;
                    }
                    else if (z > 0.0) {
                        z -= increment;
                    }
                    else {
                        z += increment;
                    }
                }
            }
            event.x = x;
            event.y = y;
            event.z = z;
            event.cancel();
        }, new Predicate[0]);
        Scaffold.setmgr.rSetting(this.mode);
        Scaffold.setmgr.rSetting(this.stopMotion);
        Scaffold.setmgr.rSetting(this.delay);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener2);
    }
    
    private boolean isOffsetBBEmpty(final double x, final double y, final double z) {
        return Scaffold.mc.world.getCollisionBoxes((Entity)Scaffold.mc.player, Scaffold.mc.player.getEntityBoundingBox().offset(x, y, z)).isEmpty();
    }
    
    private boolean isValidPlaceBlockState(final BlockPos pos) {
        final BlockInteractionHelper.ValidResult result = BlockInteractionHelper.valid(pos);
        if (result == BlockInteractionHelper.ValidResult.AlreadyBlockThere) {
            return Scaffold.mc.world.getBlockState(pos).getMaterial().isReplaceable();
        }
        return result == BlockInteractionHelper.ValidResult.Ok;
    }
    
    private boolean verifyStack(final ItemStack stack) {
        return !stack.isEmpty() && stack.getItem() instanceof ItemBlock;
    }
    
    public enum Modes
    {
        Tower, 
        Normal;
    }
}
