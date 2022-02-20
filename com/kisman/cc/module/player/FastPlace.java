//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import com.kisman.cc.event.*;
import com.kisman.cc.util.*;

public class FastPlace extends Module
{
    public static FastPlace instance;
    private Setting all;
    private Setting obby;
    private Setting enderChest;
    private Setting crystal;
    private Setting exp;
    private Setting minecart;
    private Setting feetExp;
    private Setting fastCrystal;
    private BlockPos mousePos;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public FastPlace() {
        super("FastPlace", "FastPlace", Category.PLAYER);
        this.all = new Setting("All", this, false);
        this.obby = new Setting("Obby", this, false);
        this.enderChest = new Setting("EnderChest", this, false);
        this.crystal = new Setting("Crystal", this, true);
        this.exp = new Setting("Exp", this, true);
        this.minecart = new Setting("Minecart", this, false);
        this.feetExp = new Setting("FeetExp", this, false);
        this.fastCrystal = new Setting("PacketCrystal", this, false);
        this.mousePos = null;
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.getEra().equals((Object)Event.Era.PRE) && this.feetExp.getValBoolean()) {
                final boolean mainHand = FastPlace.mc.player.getHeldItemMainhand().getItem() == Items.EXPERIENCE_BOTTLE;
                final boolean offHand = FastPlace.mc.player.getHeldItemOffhand().getItem() == Items.EXPERIENCE_BOTTLE;
                if (FastPlace.mc.gameSettings.keyBindUseItem.isKeyDown() && ((FastPlace.mc.player.getActiveHand() == EnumHand.MAIN_HAND && mainHand) || (FastPlace.mc.player.getActiveHand() == EnumHand.OFF_HAND && offHand))) {
                    RotationUtils.lookAtVec3d(FastPlace.mc.player.getPositionVector());
                }
            }
        }, new Predicate[0]);
        FastPlace.instance = this;
        FastPlace.setmgr.rSetting(this.all);
        FastPlace.setmgr.rSetting(this.obby);
        FastPlace.setmgr.rSetting(this.enderChest);
        FastPlace.setmgr.rSetting(this.crystal);
        FastPlace.setmgr.rSetting(this.exp);
        FastPlace.setmgr.rSetting(this.minecart);
        FastPlace.setmgr.rSetting(this.feetExp);
        FastPlace.setmgr.rSetting(this.fastCrystal);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
    }
    
    public void update() {
        if (FastPlace.mc.player == null && FastPlace.mc.world == null) {
            return;
        }
        if (FastPlace.mc.player.inventory.getStackInSlot(FastPlace.mc.player.inventory.currentItem).getItem().equals(Items.EXPERIENCE_BOTTLE) && this.exp.getValBoolean()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (FastPlace.mc.player.inventory.getStackInSlot(FastPlace.mc.player.inventory.currentItem).getItem().equals(Blocks.OBSIDIAN) && this.obby.getValBoolean()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (FastPlace.mc.player.inventory.getStackInSlot(FastPlace.mc.player.inventory.currentItem).getItem().equals(Blocks.ENDER_CHEST) && this.enderChest.getValBoolean()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (FastPlace.mc.player.inventory.getStackInSlot(FastPlace.mc.player.inventory.currentItem).getItem().equals(Items.MINECART) && this.minecart.getValBoolean()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (this.all.getValBoolean()) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (FastPlace.mc.player.inventory.getStackInSlot(FastPlace.mc.player.inventory.currentItem).getItem().equals(Items.END_CRYSTAL) && (this.crystal.getValBoolean() || this.all.getValBoolean())) {
            FastPlace.mc.rightClickDelayTimer = 0;
        }
        if (this.fastCrystal.getValBoolean() && FastPlace.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            final boolean offhand = FastPlace.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
            if (offhand || FastPlace.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                final RayTraceResult result = FastPlace.mc.objectMouseOver;
                if (result == null) {
                    return;
                }
                switch (result.typeOfHit) {
                    case MISS: {
                        this.mousePos = null;
                        break;
                    }
                    case BLOCK: {
                        this.mousePos = FastPlace.mc.objectMouseOver.getBlockPos();
                        break;
                    }
                    case ENTITY: {
                        if (this.mousePos == null) {
                            break;
                        }
                        final Entity entity;
                        if ((entity = result.entityHit) == null) {
                            break;
                        }
                        if (!this.mousePos.equals((Object)new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ))) {
                            break;
                        }
                        FastPlace.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.mousePos, EnumFacing.DOWN, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                        break;
                    }
                }
            }
        }
    }
}
