//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.combat.autocrystal.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import net.minecraft.util.text.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.network.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.item.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.math.*;
import com.kisman.cc.util.*;
import java.util.*;
import java.util.function.*;
import com.kisman.cc.module.client.*;
import net.minecraft.world.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;

public class AutoCrystal extends Module
{
    public static AutoCrystal instance;
    private final Setting modeLine;
    private final Setting mode;
    private final Setting placeMode;
    private final Setting breakMode;
    private final Setting logicMode;
    private final Setting syns;
    private final Setting rotate;
    private final Setting rotations;
    private final Setting raytrace;
    private final Setting debug;
    private final Setting breakCalc;
    private final Setting rangeLine;
    private final Setting placeRange;
    private final Setting breakRange;
    private final Setting targetRange;
    private final Setting wallRange;
    private final Setting damageLine;
    private final Setting minDMG;
    private final Setting maxSelfDMG;
    private final Setting placeLine;
    private final Setting place;
    private final Setting placeDelay;
    private final Setting placeHand;
    private final Setting secondCheck;
    private final Setting placeObsidianIfNoValidSpots;
    private final Setting placeRotate;
    private final Setting breakLine;
    private final Setting _break;
    private final Setting breakDelay;
    private final Setting breakHand;
    private final Setting antiWeakness;
    private final Setting breakRotate;
    private final Setting switchLine;
    private final Setting switchMode;
    private final Setting weaknessSwitchMode;
    private final Setting swingLine;
    private final Setting placeSwing;
    private final Setting breakSwing;
    private final Setting packetSwing;
    private final Setting pauseLine;
    private final Setting pauseWhileEating;
    private final Setting pauseIfHittingBlock;
    private final Setting minHealthPause;
    private final Setting requireHealth;
    private final Setting faceLine;
    private final Setting facePlace;
    private final Setting facePlaceHP;
    private final Setting armorLine;
    private final Setting armorBreaker;
    private final Setting armorPercent;
    private final Setting renderLine;
    private final Setting render;
    private final Setting color;
    private final Setting renderMode;
    private final Setting renderDamage;
    public EntityPlayer target;
    public Crystal lastPlaceCrystal;
    private int placeTicks;
    private int breakTicks;
    private int rotationPacketsSpoofs;
    private boolean rotating;
    private float yaw;
    private float pitch;
    @EventHandler
    private final Listener<PacketEvent.Send> listener1;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener;
    
    public AutoCrystal() {
        super("AutoCrystal", "super.gay();", Category.COMBAT);
        this.modeLine = new Setting("ModeLine", this, "Mode");
        this.mode = new Setting("Mode", this, "ClientTick", new ArrayList<String>(Arrays.asList("ClientTick", "MotionTick")));
        this.placeMode = new Setting("PlaceMode", this, "MostDamage", new ArrayList<String>(Arrays.asList("Nearest", "Priority", "MostDamage")));
        this.breakMode = new Setting("BreakMode", this, "Always", new ArrayList<String>(Arrays.asList("Always", "Smart")));
        this.logicMode = new Setting("LogicMode", this, "PlaceBreak", new ArrayList<String>(Arrays.asList("PlaceBreak", "BreakPlace")));
        this.syns = new Setting("Syns", this, true);
        this.rotate = new Setting("Rotate", this, false);
        this.rotations = new Setting("Spoofs", this, 1.0, 1.0, 20.0, true);
        this.raytrace = new Setting("Raytrace", this, false);
        this.debug = new Setting("Debug", this, false);
        this.breakCalc = new Setting("Break Calc", this, true);
        this.rangeLine = new Setting("RangeLine", this, "Range");
        this.placeRange = new Setting("PlaceRange", this, 4.199999809265137, 0.0, 6.0, false);
        this.breakRange = new Setting("BreakRange", this, 4.199999809265137, 0.0, 6.0, false);
        this.targetRange = new Setting("TargetRange", this, Float.intBitsToFloat(Float.floatToIntBits(1.5514623f) ^ 0x7EB69651), Float.intBitsToFloat(Float.floatToIntBits(2.1071864E38f) ^ 0x7F1E86EF), Float.intBitsToFloat(Float.floatToIntBits(0.59863883f) ^ 0x7EE94065), false);
        this.wallRange = new Setting("WallRange", this, 3.5, 0.0, 5.0, false);
        this.damageLine = new Setting("DanageLine", this, "Damage");
        this.minDMG = new Setting("MinDMG", this, 4.0, 0.0, 20.0, true);
        this.maxSelfDMG = new Setting("MaxSelfDMG", this, 4.0, 0.0, 20.0, true);
        this.placeLine = new Setting("PlaceLine", this, "Place");
        this.place = new Setting("Place", this, true);
        this.placeDelay = new Setting("PlaceDelay", this, 1.0, 0.0, 20.0, true);
        this.placeHand = new Setting("PlaceHand", this, Hands.Mainhand);
        this.secondCheck = new Setting("SecondCheck", this, true);
        this.placeObsidianIfNoValidSpots = new Setting("PlaceObsidianIfNoValidSpots", this, false);
        this.placeRotate = new Setting("PlaceRotate", this, false);
        this.breakLine = new Setting("BreakLine", this, "Break");
        this._break = new Setting("Break", this, true);
        this.breakDelay = new Setting("BreakDelay", this, 1.0, 0.0, 20.0, true);
        this.breakHand = new Setting("BreakHand", this, Hands.Mainhand);
        this.antiWeakness = new Setting("AntiWeakness", this, false);
        this.breakRotate = new Setting("BreakRotate", this, false);
        this.switchLine = new Setting("SwitchLine", this, "Switch");
        this.switchMode = new Setting("SwitchMode", this, SwitchModes.None);
        this.weaknessSwitchMode = new Setting("WeaknessSwitchMode", this, SwitchModes.None);
        this.swingLine = new Setting("SwingLine", this, "Swing");
        this.placeSwing = new Setting("PlaceSwing", this, true);
        this.breakSwing = new Setting("BreakSwing", this, true);
        this.packetSwing = new Setting("PacketSwing", this, false);
        this.pauseLine = new Setting("PauseLine", this, "Pause");
        this.pauseWhileEating = new Setting("PauseWhileEating", this, false);
        this.pauseIfHittingBlock = new Setting("PauseIfHittingBlock", this, false);
        this.minHealthPause = new Setting("MinHealthPause", this, false);
        this.requireHealth = new Setting("RequireHealth", this, false);
        this.faceLine = new Setting("FaceLine", this, "FacePlace");
        this.facePlace = new Setting("FacePlace", this, true);
        this.facePlaceHP = new Setting("FacePlaceHP", this, 10.0, 0.0, 36.0, true);
        this.armorLine = new Setting("ArmorLine", this, "ArmorBreaker");
        this.armorBreaker = new Setting("ArmorBreaker", this, true);
        this.armorPercent = new Setting("ArmorPercent", this, 20.0, 0.0, 100.0, true);
        this.renderLine = new Setting("RenderLine", this, "Render");
        this.render = new Setting("Render", this, true);
        this.color = new Setting("Color", this, "Color", new float[] { 0.9f, 0.11f, 0.11f, 1.0f });
        this.renderMode = new Setting("RenderMode", this, Renders.Fill);
        this.renderDamage = new Setting("RenderDamage", this, true);
        this.target = null;
        this.lastPlaceCrystal = null;
        this.rotationPacketsSpoofs = 0;
        this.rotating = false;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.listener1 = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (this.rotate.getValBoolean() && this.rotating && event.getPacket() instanceof CPacketPlayer) {
                final CPacketPlayer packet = (CPacketPlayer)event.getPacket();
                packet.yaw = this.yaw;
                packet.pitch = this.pitch;
                ++this.rotationPacketsSpoofs;
                if (this.rotationPacketsSpoofs >= this.rotations.getValInt()) {
                    this.rotating = false;
                    this.rotationPacketsSpoofs = 0;
                }
            }
        }, new Predicate[0]);
        this.listener = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketSoundEffect && this.syns.getValBoolean()) {
                final SPacketSoundEffect packet = (SPacketSoundEffect)event.getPacket();
                if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    for (final Entity entity : AutoCrystal.mc.world.loadedEntityList) {
                        if (entity instanceof EntityEnderCrystal) {
                            if (entity.getDistanceSq(packet.getX(), packet.getY(), packet.getZ()) > Double.longBitsToDouble(Double.doubleToLongBits(0.03533007623236061) ^ 0x7FE016C8A3F762CFL)) {
                                continue;
                            }
                            entity.setDead();
                        }
                    }
                }
            }
        }, new Predicate[0]);
        AutoCrystal.instance = this;
        AutoCrystal.setmgr.rSetting(this.modeLine);
        AutoCrystal.setmgr.rSetting(this.mode);
        AutoCrystal.setmgr.rSetting(this.placeMode);
        AutoCrystal.setmgr.rSetting(this.breakMode);
        AutoCrystal.setmgr.rSetting(this.logicMode);
        AutoCrystal.setmgr.rSetting(this.syns);
        AutoCrystal.setmgr.rSetting(this.rotate);
        AutoCrystal.setmgr.rSetting(this.rotations);
        AutoCrystal.setmgr.rSetting(this.raytrace);
        AutoCrystal.setmgr.rSetting(this.debug);
        AutoCrystal.setmgr.rSetting(this.breakCalc);
        AutoCrystal.setmgr.rSetting(this.rangeLine);
        AutoCrystal.setmgr.rSetting(this.placeRange);
        AutoCrystal.setmgr.rSetting(this.breakRange);
        AutoCrystal.setmgr.rSetting(this.targetRange);
        AutoCrystal.setmgr.rSetting(this.wallRange);
        AutoCrystal.setmgr.rSetting(this.damageLine);
        AutoCrystal.setmgr.rSetting(this.minDMG);
        AutoCrystal.setmgr.rSetting(this.maxSelfDMG);
        AutoCrystal.setmgr.rSetting(this.switchLine);
        AutoCrystal.setmgr.rSetting(this.switchMode);
        AutoCrystal.setmgr.rSetting(this.weaknessSwitchMode);
        AutoCrystal.setmgr.rSetting(this.swingLine);
        AutoCrystal.setmgr.rSetting(this.placeSwing);
        AutoCrystal.setmgr.rSetting(this.breakSwing);
        AutoCrystal.setmgr.rSetting(this.packetSwing);
        AutoCrystal.setmgr.rSetting(this.placeLine);
        AutoCrystal.setmgr.rSetting(this.place);
        AutoCrystal.setmgr.rSetting(this.placeDelay);
        AutoCrystal.setmgr.rSetting(this.placeHand);
        AutoCrystal.setmgr.rSetting(this.secondCheck);
        AutoCrystal.setmgr.rSetting(this.placeObsidianIfNoValidSpots);
        AutoCrystal.setmgr.rSetting(this.placeRotate);
        AutoCrystal.setmgr.rSetting(this.breakLine);
        AutoCrystal.setmgr.rSetting(this._break);
        AutoCrystal.setmgr.rSetting(this.breakDelay);
        AutoCrystal.setmgr.rSetting(this.breakHand);
        AutoCrystal.setmgr.rSetting(this.antiWeakness);
        AutoCrystal.setmgr.rSetting(this.breakRotate);
        AutoCrystal.setmgr.rSetting(this.pauseLine);
        AutoCrystal.setmgr.rSetting(this.pauseWhileEating);
        AutoCrystal.setmgr.rSetting(this.pauseIfHittingBlock);
        AutoCrystal.setmgr.rSetting(this.minHealthPause);
        AutoCrystal.setmgr.rSetting(this.requireHealth);
        AutoCrystal.setmgr.rSetting(this.faceLine);
        AutoCrystal.setmgr.rSetting(this.facePlace);
        AutoCrystal.setmgr.rSetting(this.facePlaceHP);
        AutoCrystal.setmgr.rSetting(this.armorLine);
        AutoCrystal.setmgr.rSetting(this.armorBreaker);
        AutoCrystal.setmgr.rSetting(this.armorPercent);
        AutoCrystal.setmgr.rSetting(this.renderLine);
        AutoCrystal.setmgr.rSetting(this.render);
        AutoCrystal.setmgr.rSetting(this.color);
        AutoCrystal.setmgr.rSetting(this.renderMode);
        AutoCrystal.setmgr.rSetting(this.renderDamage);
    }
    
    @Override
    public void onEnable() {
        this.placeTicks = 0;
        this.breakTicks = 0;
        this.lastPlaceCrystal = null;
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        this.lastPlaceCrystal = null;
    }
    
    @Override
    public void update() {
        if (AutoCrystal.mc.player == null && AutoCrystal.mc.world == null) {
            return;
        }
        this.findNewTarget();
        if (this.target != null) {
            super.setDisplayInfo("[" + this.target.getDisplayName().getFormattedText() + TextFormatting.GRAY + "]");
        }
        else {
            super.setDisplayInfo("");
        }
        this.doAutoCrystal();
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (this.render.getValBoolean() && this.lastPlaceCrystal != null && this.lastPlaceCrystal.pos != null) {
            final String valString = this.renderMode.getValString();
            switch (valString) {
                case "Both": {
                    RenderUtil.drawBlockESP(this.lastPlaceCrystal.pos, this.color.getR() / 255.0f, this.color.getG() / 255.0f, this.color.getB() / 255.0f);
                    break;
                }
                case "Fill": {
                    RenderUtil.drawBox(this.lastPlaceCrystal.pos, 1.0, new Colour(this.color.getR(), this.color.getG(), this.color.getB(), this.color.getA()), 63);
                }
                case "Outline": {
                    RenderUtil.drawBlockOutlineESP(this.lastPlaceCrystal.pos, this.color.getR() / 255.0f, this.color.getG() / 255.0f, this.color.getB() / 255.0f);
                    break;
                }
            }
        }
    }
    
    private void doAutoCrystal() {
        this.doAutoCrystalLogic();
    }
    
    private void doAutoCrystalLogic() {
        final String valString = this.logicMode.getValString();
        switch (valString) {
            case "PlaceBreak": {
                if (this.place.getValBoolean() && this.target != null) {
                    this.placeCrystal();
                }
                if (this._break.getValBoolean()) {
                    this.breakCrystal();
                    break;
                }
                break;
            }
            case "BreakPlace": {
                if (this._break.getValBoolean()) {
                    this.breakCrystal();
                }
                if (this.place.getValBoolean() && this.target != null) {
                    this.placeCrystal();
                    break;
                }
                break;
            }
        }
    }
    
    private BlockPos placeCrystal() {
        if (this.placeTicks++ <= this.placeDelay.getValInt()) {
            return BlockPos.ORIGIN;
        }
        this.placeTicks = 0;
        if (!this.isValidItemsInHand()) {
            return BlockPos.ORIGIN;
        }
        EnumHand hand = null;
        BlockPos placePos = null;
        double maxDamage = 0.5;
        for (final BlockPos pos : CrystalUtils.getSphere((float)this.placeRange.getValDouble(), true, false)) {
            final double targetDMG = EntityUtil.calculate(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (EntityLivingBase)this.target);
            final double selfDMG = EntityUtil.calculate(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (EntityLivingBase)AutoCrystal.mc.player) + 2.0;
            if (CrystalUtils.canPlaceCrystal(pos, this.secondCheck.getValBoolean()) && (targetDMG >= this.minDMG.getValInt() || (((targetDMG >= this.target.getHealth() && this.target.getHealth() <= this.facePlaceHP.getValInt()) || this.target.getHealth() <= this.armorPercent.getValDouble()) && EntityUtil.calculate(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (EntityLivingBase)AutoCrystal.mc.player) + 2.0 < AutoCrystal.mc.player.getHealth() && selfDMG < targetDMG))) {
                if (maxDamage > targetDMG) {
                    continue;
                }
                if (this.target.isDead) {
                    continue;
                }
                placePos = pos;
                maxDamage = targetDMG;
            }
        }
        if (maxDamage == 0.5) {
            return BlockPos.ORIGIN;
        }
        final int crystalSlot = InventoryUtil.findItem(Items.END_CRYSTAL, 0, 9);
        final int oldSlot = AutoCrystal.mc.player.inventory.currentItem;
        switch ((SwitchModes)this.switchMode.getValEnum()) {
            case None: {
                if (AutoCrystal.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && AutoCrystal.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                    return BlockPos.ORIGIN;
                }
                break;
            }
            case Normal: {
                if (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                    break;
                }
                if (crystalSlot == -1) {
                    return BlockPos.ORIGIN;
                }
                InventoryUtil.switchToSlot(crystalSlot, false);
                break;
            }
            case Silent: {
                if (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                    break;
                }
                if (crystalSlot == -1) {
                    return BlockPos.ORIGIN;
                }
                InventoryUtil.switchToSlot(crystalSlot, true);
                break;
            }
        }
        if (AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            if (AutoCrystal.mc.player.isHandActive()) {
                hand = AutoCrystal.mc.player.getActiveHand();
            }
            this.rotateToPos(placePos, true);
            final EnumFacing facing = EnumFacing.UP;
            final boolean offhand = AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
            if (this.raytrace.getValBoolean()) {}
            AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(placePos, facing, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            AutoCrystal.mc.playerController.updateController();
            if (this.placeSwing.getValBoolean()) {
                this.swingItem(false);
            }
            if (this.switchMode.getValEnum().equals(SwitchModes.Silent) && oldSlot != -1) {
                InventoryUtil.switchToSlot(oldSlot, true);
            }
            this.lastPlaceCrystal = new Crystal(placePos, maxDamage);
            if (this.debug.getValBoolean()) {
                ChatUtils.message((Object)("Placed crystal at: " + placePos.getX() + " " + placePos.getY() + " " + placePos.getZ()));
            }
            return placePos;
        }
        return BlockPos.ORIGIN;
    }
    
    private void breakCrystal() {
        if (this.breakTicks++ <= this.breakDelay.getValInt()) {
            return;
        }
        final EntityEnderCrystal crystal = null;
        final double max = -1337.0;
        if (crystal == null) {
            return;
        }
        final int swordSlot = InventoryUtil.findItem(Items.DIAMOND_SWORD, 0, 9);
        final int oldSlot = AutoCrystal.mc.player.inventory.currentItem;
        if (this.antiWeakness.getValBoolean() && AutoCrystal.mc.player.isPotionActive(MobEffects.WEAKNESS) && swordSlot != -1 && !(AutoCrystal.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
            switch ((SwitchModes)this.weaknessSwitchMode.getValEnum()) {
                case Normal: {
                    InventoryUtil.switchToSlot(swordSlot, false);
                    break;
                }
                case Silent: {
                    InventoryUtil.switchToSlot(swordSlot, true);
                    break;
                }
            }
        }
        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)crystal));
        if (this.packetSwing.getValBoolean()) {
            AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(this.breakHand.getValString().equals(Hands.Mainhand.name()) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND));
        }
        else {
            AutoCrystal.mc.player.swingArm(this.breakHand.getValString().equals(AutoCrystalRewrite.SwingMode.Mainhand.name()) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
        }
        if (oldSlot != -1 && this.weaknessSwitchMode.getValEnum().equals(SwitchModes.Silent)) {
            InventoryUtil.switchToSlot(oldSlot, true);
        }
        this.breakTicks = 0;
    }
    
    private void rotateToPos(final BlockPos pos, final boolean place) {
        final boolean isOnRotate = this.placeRotate.getValBoolean() && this.breakRotate.getValBoolean();
        if (this.rotate.getValBoolean()) {
            if (this.placeRotate.getValBoolean() && place) {
                final float[] angle = AngleUtil.calculateAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() - 0.5f), (double)(pos.getZ() + 0.5f)));
                if (isOnRotate) {
                    RotationUtils.setPlayerRotations(angle[0], angle[1]);
                }
                else {
                    this.yaw = angle[0];
                    this.pitch = angle[1];
                    this.rotating = true;
                }
            }
            if (this.breakRotate.getValBoolean() && !place) {
                final float[] angle = AngleUtil.calculateAngle(AutoCrystal.mc.player.getPositionEyes(AutoCrystal.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() - 0.5f), (double)(pos.getZ() + 0.5f)));
                if (isOnRotate) {
                    RotationUtils.setPlayerRotations(angle[0], angle[1]);
                }
                else {
                    this.yaw = angle[0];
                    this.pitch = angle[1];
                    this.rotating = true;
                }
            }
        }
        else {
            this.rotating = false;
        }
        if (!isOnRotate) {
            this.rotating = false;
        }
    }
    
    private boolean isValidItemsInHand() {
        return AutoCrystal.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL || !this.switchMode.getValEnum().equals(SwitchModes.None);
    }
    
    public void findNewTarget() {
        this.target = (EntityPlayer)this.getNearTarget((EntityPlayer)AutoCrystal.mc.player);
    }
    
    public EntityLivingBase getNearTarget(final EntityPlayer distanceTarget) {
        return (EntityLivingBase)AutoCrystal.mc.world.loadedEntityList.stream().filter(this::isValidTarget).map(entity -> entity).min(Comparator.comparing((Function<? super T, ? extends Comparable>)distanceTarget::getDistance)).orElse(null);
    }
    
    public boolean isValidTarget(final Entity entity) {
        return entity != null && entity instanceof EntityPlayer && (!Config.instance.friends.getValBoolean() || !Kisman.instance.friendManager.isFriend((EntityPlayer)entity)) && !entity.isDead && ((EntityPlayer)entity).getHealth() > 0.0f && entity.getDistance((Entity)AutoCrystal.mc.player) <= 20.0f && entity instanceof EntityPlayer && entity != AutoCrystal.mc.player;
    }
    
    private boolean isValidCrystal(final Entity entity) {
        if (!(entity instanceof EntityEnderCrystal)) {
            return false;
        }
        if (entity.getDistance((Entity)AutoCrystal.mc.player) > (AutoCrystal.mc.player.canEntityBeSeen(entity) ? this.breakRange.getValDouble() : this.wallRange.getValDouble())) {
            return false;
        }
        final String valString = this.breakMode.getValString();
        switch (valString) {
            case "Always": {
                return true;
            }
            case "Smart": {
                if (this.target == null) {
                    return false;
                }
                final float targetDMG = CrystalUtils.calculateDamage((World)AutoCrystal.mc.world, entity.posX + 0.5, entity.posY + 1.0, entity.posZ + 0.5, (Entity)this.target, 0);
                final float selfDMG = CrystalUtils.calculateDamage((World)AutoCrystal.mc.world, entity.posX + 0.5, entity.posY + 1.0, entity.posZ + 0.5, (Entity)AutoCrystal.mc.player, 0);
                float minDMG = (float)this.minDMG.getValDouble();
                if (this.target.getHealth() + this.target.getAbsorptionAmount() <= this.facePlaceHP.getValDouble()) {
                    minDMG = 1.0f;
                }
                if (targetDMG > minDMG && selfDMG < this.maxSelfDMG.getValDouble()) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public void swingItem(final boolean breakSwing) {
        if (breakSwing) {
            if (!this.breakHand.getValEnum().equals(Hands.None)) {
                if (!this.packetSwing.getValBoolean()) {
                    if (!this.breakHand.getValEnum().equals(Hands.Mainhand)) {
                        if (this.breakHand.getValEnum().equals(Hands.Offhand)) {
                            AutoCrystal.mc.player.swingArm(EnumHand.OFF_HAND);
                        }
                    }
                    else {
                        AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
                    }
                }
                else if (!this.breakHand.getValEnum().equals(Hands.Mainhand)) {
                    if (this.breakHand.getValEnum().equals(Hands.Offhand)) {
                        AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
                    }
                }
                else {
                    AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                }
            }
        }
        else if (!this.placeHand.getValEnum().equals(Hands.None)) {
            if (!this.packetSwing.getValBoolean()) {
                if (!this.placeHand.getValEnum().equals(Hands.Mainhand)) {
                    if (this.placeHand.getValEnum().equals(Hands.Offhand)) {
                        AutoCrystal.mc.player.swingArm(EnumHand.OFF_HAND);
                    }
                }
                else {
                    AutoCrystal.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
            else if (!this.placeHand.getValEnum().equals(Hands.Mainhand)) {
                if (this.placeHand.getValEnum().equals(Hands.Offhand)) {
                    AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
                }
            }
            else {
                AutoCrystal.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
        }
    }
    
    public enum Renders
    {
        Outline, 
        Fill, 
        Both;
    }
    
    public enum Hands
    {
        None, 
        Mainhand, 
        Offhand;
    }
    
    public enum SwitchModes
    {
        None, 
        Normal, 
        Silent;
    }
}
