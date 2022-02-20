//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.event.entity.living.*;
import java.util.*;
import net.minecraft.entity.item.*;
import com.kisman.cc.module.combat.*;
import net.minecraft.network.play.server.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;

public class Tracker extends Module
{
    private Setting autoEnable;
    private Setting autoDisable;
    private final TimerUtils timer;
    private final Set<BlockPos> manuallyPlaced;
    private EntityPlayer trackedPlayer;
    private int usedExp;
    private int usedStacks;
    private int usedCrystals;
    private int usedCStacks;
    private boolean shouldEnable;
    @EventHandler
    private final Listener<PacketEvent.Send> listener1;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener2;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener3;
    @EventHandler
    private final Listener<EventSpawnEntity> listener4;
    
    public Tracker() {
        super("Tracker", "Tracks players in 1v1s. Only good in duels tho!", Category.MISC);
        this.autoEnable = new Setting("AutoEnable", this, false);
        this.autoDisable = new Setting("AutoDisable", this, true);
        this.timer = new TimerUtils();
        this.manuallyPlaced = new HashSet<BlockPos>();
        this.usedExp = 0;
        this.usedStacks = 0;
        this.usedCrystals = 0;
        this.usedCStacks = 0;
        this.shouldEnable = false;
        this.listener1 = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (Tracker.mc.player != null && Tracker.mc.world != null && event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                final CPacketPlayerTryUseItemOnBlock packet = (CPacketPlayerTryUseItemOnBlock)event.getPacket();
                if (Tracker.mc.player.getHeldItem(packet.hand).getItem() == Items.END_CRYSTAL && !AntiTrap.instance.placedPos.contains(packet.position) && !AutoCrystalBypass.instance.placedCrystal.contains(packet.position)) {
                    this.manuallyPlaced.add(packet.position);
                }
            }
        }, new Predicate[0]);
        this.listener2 = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (Tracker.mc.player != null && Tracker.mc.world != null && (this.autoEnable.getValBoolean() || this.autoDisable.getValBoolean()) && event.getPacket() instanceof SPacketChat) {
                final SPacketChat packet = (SPacketChat)event.getPacket();
                final String message = packet.getChatComponent().getFormattedText();
                if (this.autoEnable.getValBoolean() && (message.contains("has accepted your duel request") || message.contains("Accepted the duel request from")) && !message.contains("<")) {
                    ChatUtils.message((Object)"Tracker will enable in 5 seconds.");
                    this.timer.reset();
                    this.shouldEnable = true;
                }
                else if (this.autoDisable.getValBoolean() && message.contains("has defeated") && message.contains(Tracker.mc.player.getName()) && !message.contains("<")) {
                    super.setToggled(false);
                }
            }
        }, new Predicate[0]);
        this.listener3 = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (this.shouldEnable && this.timer.passedSec(5L) && !super.isToggled()) {
                super.setToggled(true);
            }
        }, new Predicate[0]);
        this.listener4 = (Listener<EventSpawnEntity>)new Listener(event -> {
            if (event.entity instanceof EntityExpBottle && Objects.equals(Tracker.mc.world.getClosestPlayerToEntity(event.entity, 3.0), this.trackedPlayer)) {
                ++this.usedExp;
            }
            if (event.entity instanceof EntityEnderCrystal) {
                if (AntiTrap.instance.placedPos.contains(event.entity.getPosition().down())) {
                    AntiTrap.instance.placedPos.remove(event.entity.getPosition().down());
                }
                else if (this.manuallyPlaced.contains(event.entity.getPosition().down())) {
                    this.manuallyPlaced.remove(event.entity.getPosition().down());
                }
                else if (!AutoCrystalBypass.instance.placedCrystal.contains(event.entity.getPosition().down())) {
                    ++this.usedCrystals;
                }
            }
        }, new Predicate[0]);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener3);
        Tracker.setmgr.rSetting(this.autoEnable);
        Tracker.setmgr.rSetting(this.autoDisable);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
        this.manuallyPlaced.clear();
        this.shouldEnable = false;
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
        this.usedCrystals = 0;
        this.usedCStacks = 0;
    }
    
    @Override
    public void onDisable() {
        this.manuallyPlaced.clear();
        this.shouldEnable = false;
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
        this.usedCrystals = 0;
        this.usedCStacks = 0;
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener2);
    }
    
    @Override
    public void update() {
        if (this.trackedPlayer != null) {
            if (this.usedStacks != this.usedExp / 64) {
                this.usedStacks = this.usedExp / 64;
                ChatUtils.message((Object)(this.trackedPlayer.getName() + " used: " + this.usedStacks + " Stacks of EXP."));
            }
            if (this.usedCStacks != this.usedCrystals / 64) {
                this.usedCStacks = this.usedCrystals / 64;
                ChatUtils.message((Object)(this.trackedPlayer.getName() + " used: " + this.usedCStacks + " Stacks of Crystals."));
            }
        }
    }
    
    @SubscribeEvent
    public void onDisconnect(final FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (this.autoDisable.getValBoolean()) {
            super.setToggled(false);
        }
    }
    
    @SubscribeEvent
    public void onDeath(final LivingDeathEvent event) {
        if (event.getEntity().equals((Object)Tracker.mc.player) || event.getEntity().equals((Object)this.trackedPlayer)) {
            this.usedExp = 0;
            this.usedStacks = 0;
            this.usedCrystals = 0;
            this.usedCStacks = 0;
            if (this.autoDisable.getValBoolean()) {
                super.setToggled(false);
            }
        }
    }
}
