//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import net.minecraft.entity.player.*;
import i.gishreloaded.gishcode.utils.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraftforge.client.event.*;
import net.minecraft.world.*;
import com.kisman.cc.util.manager.*;
import net.minecraft.entity.item.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import java.util.*;
import java.util.function.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.util.*;
import com.kisman.cc.mixin.mixins.accessor.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;

public class AutoCrystalRewrite extends Module
{
    public static AutoCrystalRewrite instance;
    private final Setting placeRange;
    private final Setting breakRange;
    private final Setting wallPlaceRange;
    private final Setting wallBreakRange;
    private final Setting targetRange;
    private final Setting rayTrace;
    private final Setting swing;
    private final Setting packetSwing;
    private final Setting instant;
    private final Setting inhibit;
    private final Setting delayLine;
    private final Setting placeDelay;
    private final Setting breakDelay;
    private final Setting clearDelay;
    private final Setting placeLine;
    private final Setting place;
    private final Setting newVersionPlace;
    private final Setting multiPlace;
    private final Setting terrainIgnore;
    private final Setting syns;
    private final Setting breakLine;
    private final Setting _break;
    private final Setting breakMode;
    private final Setting breakCalc;
    private final Setting rotateLine;
    private final Setting rotate;
    private final Setting clientSide;
    private final Setting randomize;
    private final Setting yawStep;
    private final Setting steps;
    private final Setting damage;
    private final Setting minDMG;
    private final Setting maxSelfDMG;
    private final Setting facePlaceHP;
    private final Setting armor;
    private final Setting switchLine;
    private final Setting switchMode;
    private final Setting antiWeaknessSwitchMode;
    private final Setting pauseLine;
    private final Setting pauseIfEating;
    private final Setting pauseIfHealth;
    private final Setting requirestHealth;
    private final Setting renderLine;
    private final Setting render;
    private final Random random;
    private final Set<BlockPos> placedCrystals;
    private EntityPlayer target;
    private Entity lastHitEntity;
    private BlockPos current;
    private Rotation rotation;
    private final TimerUtils breakTimer;
    private final TimerUtils placeTimer;
    private final TimerUtils clearTimer;
    @EventHandler
    private final Listener<PacketEvent.Send> listener2;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener1;
    
    public AutoCrystalRewrite() {
        super("AutoCrystalRewrite", Category.COMBAT);
        this.placeRange = new Setting("PlaceRange", this, 0.0, 4.5, 6.0, false);
        this.breakRange = new Setting("BreakRange", this, 0.0, 4.5, 6.0, false);
        this.wallPlaceRange = new Setting("WallPlaceRange", this, 0.0, 4.0, 6.0, false);
        this.wallBreakRange = new Setting("WallBreakRange", this, 0.0, 4.0, 6.0, false);
        this.targetRange = new Setting("TargetRange", this, 0.0, 30.0, 50.0, false);
        this.rayTrace = new Setting("RayTrace", this, RayTrace.None);
        this.swing = new Setting("Swing", this, SwingMode.Mainhand);
        this.packetSwing = new Setting("PacketSwing", this, false);
        this.instant = new Setting("Instant", this, true);
        this.inhibit = new Setting("Inhibit", this, true);
        this.delayLine = new Setting("DelayLine", this, "Delay");
        this.placeDelay = new Setting("PlaceDelay", this, 20.0, 0.0, 200.0, true);
        this.breakDelay = new Setting("BreakDelay", this, 40.0, 0.0, 200.0, true);
        this.clearDelay = new Setting("ClearDelay", this, 10.0, 1.0, 60.0, true);
        this.placeLine = new Setting("PlaceLine", this, "Place");
        this.place = new Setting("Place", this, true);
        this.newVersionPlace = new Setting("1.13", this, false);
        this.multiPlace = new Setting("MultiPlace", this, false);
        this.terrainIgnore = new Setting("Terrain Ignore", this, true);
        this.syns = new Setting("Syns", this, true);
        this.breakLine = new Setting("BreakLine", this, "Break");
        this._break = new Setting("Break", this, true);
        this.breakMode = new Setting("BreakMode", this, BreakMode.Always);
        this.breakCalc = new Setting("BreakCalc", this, true);
        this.rotateLine = new Setting("RotateLine", this, "Rotate");
        this.rotate = new Setting("Rotate", this, Rotations.None);
        this.clientSide = new Setting("ClientSize", this, false);
        this.randomize = new Setting("Ramdomize", this, true);
        this.yawStep = new Setting("YawStep", this, YawStep.None);
        this.steps = new Setting("Steps", this, 0.30000001192092896, 0.0, 1.0, false);
        this.damage = new Setting("DamageLine", this, "Damage");
        this.minDMG = new Setting("MinDMG", this, 6.0, 0.10000000149011612, 36.0, false);
        this.maxSelfDMG = new Setting("MaxSelfDMG", this, 12.0, 0.10000000149011612, 36.0, false);
        this.facePlaceHP = new Setting("FacePlaceDMG", this, 0.0, 0.0, 37.0, true);
        this.armor = new Setting("Armor Scale", this, 12.0, 0.0, 100.0, true);
        this.switchLine = new Setting("SwitchLine", this, "Switch");
        this.switchMode = new Setting("Switch", this, SwapMode.None);
        this.antiWeaknessSwitchMode = new Setting("AntiWeaknessSwitch", this, SwapMode.None);
        this.pauseLine = new Setting("PauseLine", this, "Pause");
        this.pauseIfEating = new Setting("Pause If Eating", this, false);
        this.pauseIfHealth = new Setting("Pause If Heaith", this, false);
        this.requirestHealth = new Setting("Requirest Health", this, 5.0, 0.0, 37.0, true);
        this.renderLine = new Setting("RenderLine", this, "Render");
        this.render = new Setting("Render", this, true);
        this.random = new Random();
        this.placedCrystals = new HashSet<BlockPos>();
        this.breakTimer = new TimerUtils();
        this.placeTimer = new TimerUtils();
        this.clearTimer = new TimerUtils();
        this.listener2 = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && AutoCrystalRewrite.mc.player.getHeldItem(((CPacketPlayerTryUseItemOnBlock)event.getPacket()).getHand()).getItem() == Items.END_CRYSTAL) {
                this.placedCrystals.add(((CPacketPlayerTryUseItemOnBlock)event.getPacket()).getPos());
            }
        }, new Predicate[0]);
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketSpawnObject && this.instant.getValBoolean()) {
                final SPacketSpawnObject packet = (SPacketSpawnObject)event.getPacket();
                if (packet.getType() == 51) {
                    BlockPos toRemove = null;
                    for (final BlockPos pos : this.placedCrystals) {
                        final boolean canSee = EntityUtil.canSee(pos);
                        if (!canSee) {
                            if (this.rayTrace.getValString().equals(RayTrace.Full.name())) {
                                break;
                            }
                            if (this.rayTrace.getValString().equals(RayTrace.Hit.name())) {
                                break;
                            }
                        }
                        if (AutoCrystalRewrite.mc.player.getDistance(pos.getX() + 0.5, (double)pos.getY(), pos.getZ() + 0.5) >= (canSee ? this.breakRange.getValDouble() : this.wallBreakRange.getValDouble())) {
                            break;
                        }
                        if (this.breakMode.getValString().equals(BreakMode.Own.name()) && Math.sqrt(this.getDistance(pos.getX(), pos.getY(), pos.getZ(), packet.getX(), packet.getY(), packet.getZ())) > 1.5) {
                            continue;
                        }
                        if (this.breakMode.getValString().equals(BreakMode.Smart.name()) && EntityUtil.getHealth((EntityPlayer)AutoCrystalRewrite.mc.player) - CrystalUtils.calculateDamage((World)AutoCrystalRewrite.mc.world, packet.getX(), packet.getY(), packet.getZ(), (Entity)AutoCrystalRewrite.mc.player, this.terrainIgnore.getValBoolean()) < 0.0f) {
                            break;
                        }
                        toRemove = pos;
                        if (this.inhibit.getValBoolean()) {
                            try {
                                this.lastHitEntity = AutoCrystalRewrite.mc.world.getEntityByID(packet.getEntityID());
                            }
                            catch (Exception ex) {}
                        }
                        if (this.rotate.getValString().equals(Rotations.Full.name())) {
                            this.rotation = RotationManager.calcRotation(new BlockPos(packet.getX(), packet.getY(), packet.getZ()));
                        }
                        final AccessorCPacketUseEntity hitPacket = (AccessorCPacketUseEntity)new CPacketUseEntity();
                        hitPacket.setEntityId(packet.getEntityID());
                        hitPacket.setAction(CPacketUseEntity.Action.ATTACK);
                        AutoCrystalRewrite.mc.player.connection.sendPacket((Packet)hitPacket);
                        this.swing();
                        break;
                    }
                    if (toRemove != null) {
                        this.placedCrystals.remove(toRemove);
                    }
                }
            }
            if (event.getPacket() instanceof SPacketSoundEffect && this.inhibit.getValBoolean() && this.lastHitEntity != null) {
                final SPacketSoundEffect packet2 = (SPacketSoundEffect)event.getPacket();
                if (packet2.getCategory() == SoundCategory.BLOCKS && packet2.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE && this.lastHitEntity.getDistance(packet2.getX(), packet2.getY(), packet2.getZ()) <= 6.0) {
                    this.lastHitEntity.setDead();
                }
            }
        }, new Predicate[0]);
        this.listener1 = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (this.rotation != null && !this.rotate.getValString().equals(Rotations.None.name())) {
                if (!this.yawStep.getValString().equals(YawStep.None.name()) && this.steps.getValDouble() < 1.0) {
                    final float packetYaw = ((IEntityPlayerSP)AutoCrystalRewrite.mc.player).getLastReportedYaw();
                    final float diff = MathUtil.wrapDegrees(this.rotation.getYaw() - packetYaw);
                    if (Math.abs(diff) > 180 * this.steps.getValInt()) {
                        this.rotation.setYaw(packetYaw + (diff + 180 * this.steps.getValInt() / Math.abs(diff)));
                    }
                }
                if (this.randomize.getValBoolean()) {
                    this.rotation.setYaw(this.rotation.getYaw() + (this.random.nextInt(4) - 2) / 100.0f);
                }
                if (this.clientSide.getValBoolean()) {
                    AutoCrystalRewrite.mc.player.rotationYaw = this.rotation.getYaw();
                    AutoCrystalRewrite.mc.player.rotationPitch = this.rotation.getPitch();
                }
                else {
                    RotationUtils.setPlayerRotations(this.rotation);
                }
            }
        }, new Predicate[0]);
        AutoCrystalRewrite.instance = this;
        AutoCrystalRewrite.setmgr.rSetting(this.placeRange);
        AutoCrystalRewrite.setmgr.rSetting(this.breakRange);
        AutoCrystalRewrite.setmgr.rSetting(this.wallPlaceRange);
        AutoCrystalRewrite.setmgr.rSetting(this.wallBreakRange);
        AutoCrystalRewrite.setmgr.rSetting(this.targetRange);
        AutoCrystalRewrite.setmgr.rSetting(this.rayTrace);
        AutoCrystalRewrite.setmgr.rSetting(this.packetSwing);
        AutoCrystalRewrite.setmgr.rSetting(this.instant);
        AutoCrystalRewrite.setmgr.rSetting(this.inhibit);
        AutoCrystalRewrite.setmgr.rSetting(this.delayLine);
        AutoCrystalRewrite.setmgr.rSetting(this.placeDelay);
        AutoCrystalRewrite.setmgr.rSetting(this.breakDelay);
        AutoCrystalRewrite.setmgr.rSetting(this.clearDelay);
        AutoCrystalRewrite.setmgr.rSetting(this.placeLine);
        AutoCrystalRewrite.setmgr.rSetting(this.place);
        AutoCrystalRewrite.setmgr.rSetting(this.newVersionPlace);
        AutoCrystalRewrite.setmgr.rSetting(this.multiPlace);
        AutoCrystalRewrite.setmgr.rSetting(this.terrainIgnore);
        AutoCrystalRewrite.setmgr.rSetting(this.syns);
        AutoCrystalRewrite.setmgr.rSetting(this.breakLine);
        AutoCrystalRewrite.setmgr.rSetting(this._break);
        AutoCrystalRewrite.setmgr.rSetting(this.breakMode);
        AutoCrystalRewrite.setmgr.rSetting(this.breakCalc);
        AutoCrystalRewrite.setmgr.rSetting(this.rotateLine);
        AutoCrystalRewrite.setmgr.rSetting(this.rotate);
        AutoCrystalRewrite.setmgr.rSetting(this.clientSide);
        AutoCrystalRewrite.setmgr.rSetting(this.randomize);
        AutoCrystalRewrite.setmgr.rSetting(this.yawStep);
        AutoCrystalRewrite.setmgr.rSetting(this.steps);
        AutoCrystalRewrite.setmgr.rSetting(this.damage);
        AutoCrystalRewrite.setmgr.rSetting(this.minDMG);
        AutoCrystalRewrite.setmgr.rSetting(this.maxSelfDMG);
        AutoCrystalRewrite.setmgr.rSetting(this.facePlaceHP);
        AutoCrystalRewrite.setmgr.rSetting(this.armor);
        AutoCrystalRewrite.setmgr.rSetting(this.switchLine);
        AutoCrystalRewrite.setmgr.rSetting(this.switchMode);
        AutoCrystalRewrite.setmgr.rSetting(this.antiWeaknessSwitchMode);
        AutoCrystalRewrite.setmgr.rSetting(this.pauseLine);
        AutoCrystalRewrite.setmgr.rSetting(this.pauseIfEating);
        AutoCrystalRewrite.setmgr.rSetting(this.pauseIfHealth);
        AutoCrystalRewrite.setmgr.rSetting(this.requirestHealth);
        AutoCrystalRewrite.setmgr.rSetting(this.renderLine);
        AutoCrystalRewrite.setmgr.rSetting(this.render);
    }
    
    @Override
    public void onEnable() {
        this.current = null;
        this.rotation = null;
        this.breakTimer.reset();
        this.placeTimer.reset();
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
    }
    
    @Override
    public void onDisable() {
        this.current = null;
        this.rotation = null;
        this.breakTimer.reset();
        this.placeTimer.reset();
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener2);
    }
    
    @Override
    public void update() {
        if (AutoCrystalRewrite.mc.player == null && AutoCrystalRewrite.mc.world == null) {
            return;
        }
        if (this.clearTimer.passedMillis(this.clearDelay.getValInt() * 1000L)) {
            this.placedCrystals.clear();
            this.clearTimer.reset();
        }
        if (this.needPause()) {
            return;
        }
        this.target = EntityUtil.getTarget(this.targetRange.getValFloat());
        if (this.target == null) {
            this.current = null;
            return;
        }
        super.setDisplayInfo("[" + this.target.getName() + "]");
        this.doPlace();
        this.doBreak();
    }
    
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (this.render.getValBoolean()) {
            if (this.current == null) {
                return;
            }
            RenderUtil.drawBlockESP(this.current, 1.0f, 0.0f, 0.0f);
        }
    }
    
    private boolean needPause() {
        return (this.pauseIfEating.getValBoolean() && AutoCrystalRewrite.mc.player.isHandActive()) || (this.pauseIfHealth.getValBoolean() && AutoCrystalRewrite.mc.player.getHealth() <= this.requirestHealth.getValInt());
    }
    
    private void doPlace() {
        if (!this.place.getValBoolean() || !this.placeTimer.passedMillis((long)this.placeDelay.getValInt())) {
            return;
        }
        if (AutoCrystalRewrite.mc.player == null && AutoCrystalRewrite.mc.world == null) {
            return;
        }
        EnumHand hand = null;
        double max = 0.5;
        for (final BlockPos pos : CrystalUtils.getSphere(this.placeRange.getValFloat(), true)) {
            if (!this.isPosValid(pos) && CrystalUtils.canPlaceCrystal(pos, this.newVersionPlace.getValBoolean(), true, this.multiPlace.getValBoolean())) {
                continue;
            }
            final double damage = CrystalUtils.calculateDamage((World)AutoCrystalRewrite.mc.world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ(), (Entity)this.target, this.terrainIgnore.getValBoolean());
            final double localMinDMG = (EntityUtil.getHealth(this.target) < this.facePlaceHP.getValInt() || InventoryUtil.isArmorLow(this.target, this.armor.getValInt())) ? 0.6 : this.minDMG.getValDouble();
            final double self = CrystalUtils.calculateDamage((World)AutoCrystalRewrite.mc.world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ(), (Entity)AutoCrystalRewrite.mc.player, this.terrainIgnore.getValBoolean()) + 2.0f;
            if (damage <= localMinDMG || self >= this.maxSelfDMG.getValDouble() || EntityUtil.getHealth((EntityPlayer)AutoCrystalRewrite.mc.player) - this.maxSelfDMG.getValDouble() < 0.0) {
                continue;
            }
            if (damage < 0.5) {
                continue;
            }
            if (damage <= max) {
                continue;
            }
            max = damage;
            this.current = pos;
        }
        if (this.current == null || max == 0.6) {
            this.current = null;
            return;
        }
        if (this.syns.getValBoolean() && this.placedCrystals.contains(this.current)) {
            return;
        }
        final boolean offhand = AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem().equals(Items.END_CRYSTAL);
        final int oldSlot = AutoCrystalRewrite.mc.player.inventory.currentItem;
        final int crystalSlot = InventoryUtil.findItem(Items.END_CRYSTAL, 0, 9);
        if (AutoCrystalRewrite.mc.player.isHandActive()) {
            hand = AutoCrystalRewrite.mc.player.getActiveHand();
        }
        final String valString = this.switchMode.getValString();
        switch (valString) {
            case "None": {
                if (AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                    return;
                }
                break;
            }
            case "Normal": {
                if (AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                    break;
                }
                if (crystalSlot == -1) {
                    return;
                }
                InventoryUtil.switchToSlot(crystalSlot, false);
                break;
            }
            case "Silent": {
                if (AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystalRewrite.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                    break;
                }
                if (crystalSlot == -1) {
                    return;
                }
                InventoryUtil.switchToSlot(crystalSlot, true);
                break;
            }
        }
        if (!this.rotate.getValString().equals("None")) {
            this.rotation = RotationManager.calcRotation(this.current);
        }
        this.placeCrystal(this.current, offhand);
        if (hand != null) {
            AutoCrystalRewrite.mc.player.setActiveHand(hand);
        }
        if (oldSlot != -1 && this.switchMode.getValString().equals("Silent")) {
            InventoryUtil.switchToSlot(oldSlot, true);
        }
    }
    
    private void doBreak() {
        if (!this._break.getValBoolean() || !this.breakTimer.passedMillis((long)this.breakDelay.getValInt())) {
            return;
        }
        EntityEnderCrystal crystal = null;
        double max = -1337.0;
        for (final Entity entity : AutoCrystalRewrite.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal && this.isCrystalValid((EntityEnderCrystal)entity)) {
                final EntityEnderCrystal crystal2 = (EntityEnderCrystal)entity;
                if (this.breakCalc.getValBoolean()) {
                    final double dmg = CrystalUtils.calculateDamage((World)AutoCrystalRewrite.mc.world, crystal2.posX, crystal2.posY, crystal2.posZ, (Entity)crystal2, this.terrainIgnore.getValBoolean());
                    if (dmg <= max) {
                        continue;
                    }
                    max = dmg;
                    crystal = crystal2;
                }
                else {
                    final double dist = -AutoCrystalRewrite.mc.player.getDistance((Entity)crystal2);
                    if (dist <= max) {
                        continue;
                    }
                    max = dist;
                    crystal = crystal2;
                }
            }
        }
        if (crystal == null) {
            return;
        }
        final int oldSlot = AutoCrystalRewrite.mc.player.inventory.currentItem;
        if (AutoCrystalRewrite.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            final int toolSlot = InventoryUtil.findAntiWeaknessTool();
            final Item currentItem = AutoCrystalRewrite.mc.player.inventory.getStackInSlot(toolSlot).getItem();
            final String valString = this.antiWeaknessSwitchMode.getValString();
            switch (valString) {
                case "None": {
                    if (!(currentItem instanceof ItemSword) && !(AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe)) {
                        return;
                    }
                    break;
                }
                case "Normal": {
                    if (currentItem instanceof ItemSword || AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                        break;
                    }
                    if (toolSlot == -1) {
                        return;
                    }
                    InventoryUtil.switchToSlot(toolSlot, false);
                    break;
                }
                case "Silent": {
                    if (currentItem instanceof ItemSword || AutoCrystalRewrite.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                        break;
                    }
                    if (toolSlot == -1) {
                        return;
                    }
                    InventoryUtil.switchToSlot(toolSlot, true);
                    break;
                }
            }
        }
        if (this.rotate.getValString().equals("Full")) {
            this.rotation = RotationManager.calcRotation((Entity)crystal);
        }
        this.breakCrystal(crystal);
        if (AutoCrystalRewrite.mc.player.isPotionActive(MobEffects.WEAKNESS) && oldSlot != -1 && this.switchMode.getValString().equals(SwapMode.Silent.name())) {
            InventoryUtil.switchToSlot(oldSlot, true);
        }
    }
    
    private void placeCrystal(final BlockPos pos, final boolean offhand) {
        if (pos == null) {
            return;
        }
        final RayTraceResult result = AutoCrystalRewrite.mc.world.rayTraceBlocks(new Vec3d(AutoCrystalRewrite.mc.player.posX, AutoCrystalRewrite.mc.player.posY + AutoCrystalRewrite.mc.player.getEyeHeight(), AutoCrystalRewrite.mc.player.posZ), new Vec3d(pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5));
        final EnumFacing facing = (result == null || result.sideHit == null) ? EnumFacing.UP : result.sideHit;
        AutoCrystalRewrite.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, facing, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        AutoCrystalRewrite.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.swing.getValString().equals(SwingMode.Mainhand.name()) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND));
        AutoCrystalRewrite.mc.playerController.updateController();
        this.placeTimer.reset();
    }
    
    private void breakCrystal(final EntityEnderCrystal crystal) {
        this.lastHitEntity = (Entity)crystal;
        AutoCrystalRewrite.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)crystal));
        this.swing();
        this.breakTimer.reset();
        BlockPos toRemove = null;
        if (this.syns.getValBoolean()) {
            for (final BlockPos pos : this.placedCrystals) {
                if (crystal.getDistance((double)pos.getX(), (double)pos.getY(), (double)pos.getZ()) <= 3.0) {
                    toRemove = pos;
                }
            }
        }
        if (toRemove != null) {
            this.placedCrystals.remove(toRemove);
        }
    }
    
    public void swing() {
        if (this.swing.getValString().equals(SwingMode.None.name())) {
            return;
        }
        if (this.packetSwing.getValBoolean()) {
            AutoCrystalRewrite.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.swing.getValString().equals(SwingMode.Mainhand.name()) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND));
        }
        else {
            AutoCrystalRewrite.mc.player.swingArm(this.swing.getValString().equals(SwingMode.Mainhand.name()) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
        }
    }
    
    private boolean isCrystalValid(final EntityEnderCrystal e) {
        final boolean canSee = AutoCrystalRewrite.mc.player.canEntityBeSeen((Entity)e);
        if (!canSee && (this.rayTrace.getValString().equals(RayTrace.Full.name()) || this.rayTrace.getValString().equals(RayTrace.Hit.name()))) {
            return false;
        }
        if (this.breakMode.getValString().equals(BreakMode.Own.name())) {
            for (final BlockPos blockPos : this.placedCrystals) {
                if (e.getDistance((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()) <= 3.0) {
                    continue;
                }
                return false;
            }
        }
        else if (this.breakMode.getValString().equals(BreakMode.Smart.name()) && EntityUtil.getHealth((EntityPlayer)AutoCrystalRewrite.mc.player) - CrystalUtils.calculateDamage((World)AutoCrystalRewrite.mc.world, e.posX, e.posY, e.posZ, (Entity)AutoCrystalRewrite.mc.player, this.terrainIgnore.getValBoolean()) < 0.0f) {
            return false;
        }
        return AutoCrystalRewrite.mc.player.getDistance((Entity)e) <= (canSee ? this.breakRange.getValDouble() : this.wallBreakRange.getValDouble());
    }
    
    private boolean isPosValid(final BlockPos pos) {
        final boolean canSee = EntityUtil.canSee(pos);
        return (canSee || (!this.rayTrace.getValString().equals(RayTrace.Full.name()) && !this.rayTrace.getValString().equals(RayTrace.Place.name()))) && AutoCrystalRewrite.mc.player.getDistance(pos.getX() + 0.5, (double)pos.getY(), pos.getZ() + 0.5) <= (canSee ? this.placeRange.getValDouble() : this.wallPlaceRange.getValDouble());
    }
    
    private void findNewTarget() {
        this.target = (EntityPlayer)this.getNearTarget((EntityPlayer)AutoCrystalRewrite.mc.player);
    }
    
    private EntityLivingBase getNearTarget(final EntityPlayer distanceTarget) {
        return (EntityLivingBase)AutoCrystalRewrite.mc.world.loadedEntityList.stream().filter(this::isValidTarget).map(entity -> entity).min(Comparator.comparing((Function<? super T, ? extends Comparable>)distanceTarget::getDistance)).orElse(null);
    }
    
    private boolean isValidTarget(final Entity entity) {
        return entity != null && entity instanceof EntityPlayer && (!Config.instance.friends.getValBoolean() || !Kisman.instance.friendManager.isFriend((EntityPlayer)entity)) && !entity.isDead && ((EntityPlayer)entity).getHealth() > 0.0f && entity.getDistance((Entity)AutoCrystalRewrite.mc.player) <= 20.0f && entity instanceof EntityPlayer && entity != AutoCrystalRewrite.mc.player;
    }
    
    private double getDistance(final double x, final double y, final double z, final double x1, final double y1, final double z1) {
        final double d0 = x - x1;
        final double d2 = y - y1;
        final double d3 = z - z1;
        return d0 * d0 + d2 * d2 + d3 * d3;
    }
    
    public enum RayTrace
    {
        None, 
        Hit, 
        Place, 
        Full;
    }
    
    public enum BreakMode
    {
        Always, 
        Own, 
        Smart;
    }
    
    public enum Rotations
    {
        None, 
        Semi, 
        Full;
    }
    
    public enum SwapMode
    {
        None, 
        Normal, 
        Silent;
    }
    
    public enum SwingMode
    {
        Offhand, 
        Mainhand, 
        None;
    }
    
    public enum YawStep
    {
        None, 
        Full, 
        Semi;
    }
}
