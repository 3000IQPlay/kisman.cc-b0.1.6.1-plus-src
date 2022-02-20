//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import net.minecraft.entity.player.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;
import com.kisman.cc.module.chat.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.client.entity.*;
import net.minecraft.item.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;

public class AutoCrystalBypass extends Module
{
    private Setting modeLine;
    private Setting mode;
    private Setting placeMode;
    private Setting breakMode;
    private Setting logicPlace;
    private Setting logicMode;
    private Setting rangeLine;
    private Setting placeRange;
    private Setting breakRange;
    private Setting targetRange;
    private Setting wallRange;
    private Setting damageLine;
    private Setting minDMG;
    private Setting maxSelfDMG;
    private Setting placeLine;
    private Setting place;
    private Setting placeHand;
    private Setting placeDelay;
    private Setting placeUnderBlock;
    private Setting holePlace;
    private Setting raytrace;
    private Setting onlyPlaceWithCrystal;
    private Setting placeObsidianIfNoValidSpots;
    private Setting rotate;
    private Setting breakLine;
    private Setting _break;
    private Setting breakHand;
    private Setting breakDelay;
    private Setting antiWeakness;
    private Setting onlyBreakWithPlace;
    private Setting switchLine;
    private Setting switchMode;
    private Setting weaknessSwitchMode;
    private Setting swingLine;
    private Setting placeSwing;
    private Setting breakSwing;
    private Setting packetSwing;
    private Setting pauseLine;
    private Setting pauseWhileEating;
    private Setting pauseIfHittingBlock;
    private Setting minHealthPause;
    private Setting requireHealth;
    private Setting multiLine;
    private Setting autoMultiPlace;
    private Setting multiPlace;
    private Setting multiPlaceHP;
    private Setting faceLine;
    private Setting facePlace;
    private Setting facePlaceHP;
    private Setting armorLine;
    private Setting armorBreaker;
    private Setting armorPercent;
    private Setting singlePlaceLine;
    private Setting secondCheck;
    private Setting otherLine;
    private Setting antiSuicide;
    private Setting syns;
    private Setting placeCalculate;
    private int placeTicks;
    private int breakTicks;
    public ArrayList<BlockPos> placedCrystal;
    public EntityPlayer target;
    private AimBot aimBot;
    public static AutoCrystalBypass instance;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener1;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public AutoCrystalBypass() {
        super("AutoCrystalBypass", "AutoCrystalBypass", Category.COMBAT);
        this.modeLine = new Setting("ModeLine", this, "Mode");
        this.mode = new Setting("Mode", this, "ClientTick", new ArrayList<String>(Arrays.asList("ClientTick", "MotionTick")));
        this.placeMode = new Setting("PlaceMode", this, "MostDamage", new ArrayList<String>(Arrays.asList("Nearest", "Priority", "MostDamage")));
        this.breakMode = new Setting("BreakMode", this, "Always", new ArrayList<String>(Arrays.asList("Always", "OnlyOwn")));
        this.logicPlace = new Setting("LogicPlace", this, "1", new ArrayList<String>(Arrays.asList("1", "2")));
        this.logicMode = new Setting("LogicMode", this, "PlaceBreak", new ArrayList<String>(Arrays.asList("PlaceBreak", "BreakPlace")));
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
        this.placeHand = new Setting("PlaceHand", this, Hands.Mainhand);
        this.placeDelay = new Setting("PlaceDelay", this, 1.0, 0.0, 20.0, true);
        this.placeUnderBlock = new Setting("PlaceUnderBlock", this, false);
        this.holePlace = new Setting("HolePlace", this, true);
        this.raytrace = new Setting("RayTrace", this, true);
        this.onlyPlaceWithCrystal = new Setting("OnlyPlaceWithCrystal", this, true);
        this.placeObsidianIfNoValidSpots = new Setting("PlaceObsidianIfNoValidSpots", this, false);
        this.rotate = new Setting("Rotate", this, false);
        this.breakLine = new Setting("BreakLine", this, "Break");
        this._break = new Setting("Break", this, true);
        this.breakHand = new Setting("BreakHand", this, Hands.Mainhand);
        this.breakDelay = new Setting("BreakDelay", this, 1.0, 0.0, 20.0, true);
        this.antiWeakness = new Setting("AntiWeakness", this, false);
        this.onlyBreakWithPlace = new Setting("OnlyBreakWithPlace", this, false);
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
        this.multiLine = new Setting("MultiLine", this, "MultiPlace");
        this.autoMultiPlace = new Setting("AutoMultiPlace", this, false);
        this.multiPlace = new Setting("MultiPlace", this, MultiPlaceModes.None);
        this.multiPlaceHP = new Setting("MultiPlaceHP", this, 10.0, 0.0, 36.0, true);
        this.faceLine = new Setting("FaceLine", this, "FacePlace");
        this.facePlace = new Setting("FacePlace", this, true);
        this.facePlaceHP = new Setting("FacePlaceHP", this, 10.0, 0.0, 36.0, true);
        this.armorLine = new Setting("ArmorLine", this, "ArmorBreaker");
        this.armorBreaker = new Setting("ArmorBreaker", this, true);
        this.armorPercent = new Setting("ArmorPercent", this, 20.0, 0.0, 100.0, true);
        this.singlePlaceLine = new Setting("SinglePlaceLine", this, "SinglePlace");
        this.secondCheck = new Setting("SecondCheck", this, true);
        this.otherLine = new Setting("OtherLine", this, "Other");
        this.antiSuicide = new Setting("AntiSuicide", this, true);
        this.syns = new Setting("Syns", this, true);
        this.placeCalculate = new Setting("Calculate", this, "kisman.cc", new ArrayList<String>(Arrays.asList("kisman.cc", "Europa")));
        this.placedCrystal = new ArrayList<BlockPos>();
        this.target = null;
        this.listener1 = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (event.getPacket() instanceof SPacketSoundEffect && this.syns.getValBoolean()) {
                final SPacketSoundEffect packet = (SPacketSoundEffect)event.getPacket();
                if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE) {
                    for (final Entity entity : AutoCrystalBypass.mc.world.loadedEntityList) {
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
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (this.mode.getValString().equalsIgnoreCase("MotionTick")) {
                this.doAutoCrystal(event);
            }
        }, new Predicate[0]);
        AutoCrystalBypass.instance = this;
        this.aimBot = AimBot.instance;
        AutoCrystalBypass.setmgr.rSetting(this.modeLine);
        AutoCrystalBypass.setmgr.rSetting(this.mode);
        AutoCrystalBypass.setmgr.rSetting(this.placeMode);
        AutoCrystalBypass.setmgr.rSetting(this.breakMode);
        AutoCrystalBypass.setmgr.rSetting(this.logicPlace);
        AutoCrystalBypass.setmgr.rSetting(this.logicMode);
        AutoCrystalBypass.setmgr.rSetting(this.rangeLine);
        AutoCrystalBypass.setmgr.rSetting(this.placeRange);
        AutoCrystalBypass.setmgr.rSetting(this.breakRange);
        AutoCrystalBypass.setmgr.rSetting(this.targetRange);
        AutoCrystalBypass.setmgr.rSetting(this.wallRange);
        AutoCrystalBypass.setmgr.rSetting(this.damageLine);
        AutoCrystalBypass.setmgr.rSetting(this.minDMG);
        AutoCrystalBypass.setmgr.rSetting(this.maxSelfDMG);
        AutoCrystalBypass.setmgr.rSetting(this.switchLine);
        AutoCrystalBypass.setmgr.rSetting(this.switchMode);
        AutoCrystalBypass.setmgr.rSetting(this.weaknessSwitchMode);
        AutoCrystalBypass.setmgr.rSetting(this.swingLine);
        AutoCrystalBypass.setmgr.rSetting(this.placeSwing);
        AutoCrystalBypass.setmgr.rSetting(this.breakSwing);
        AutoCrystalBypass.setmgr.rSetting(this.packetSwing);
        AutoCrystalBypass.setmgr.rSetting(this.placeLine);
        AutoCrystalBypass.setmgr.rSetting(this.place);
        AutoCrystalBypass.setmgr.rSetting(this.placeHand);
        AutoCrystalBypass.setmgr.rSetting(this.placeDelay);
        AutoCrystalBypass.setmgr.rSetting(this.placeUnderBlock);
        AutoCrystalBypass.setmgr.rSetting(this.holePlace);
        AutoCrystalBypass.setmgr.rSetting(this.raytrace);
        AutoCrystalBypass.setmgr.rSetting(this.onlyPlaceWithCrystal);
        AutoCrystalBypass.setmgr.rSetting(this.placeObsidianIfNoValidSpots);
        AutoCrystalBypass.setmgr.rSetting(this.rotate);
        AutoCrystalBypass.setmgr.rSetting(this.breakLine);
        AutoCrystalBypass.setmgr.rSetting(this._break);
        AutoCrystalBypass.setmgr.rSetting(this.breakHand);
        AutoCrystalBypass.setmgr.rSetting(this.breakDelay);
        AutoCrystalBypass.setmgr.rSetting(this.antiWeakness);
        AutoCrystalBypass.setmgr.rSetting(this.onlyBreakWithPlace);
        AutoCrystalBypass.setmgr.rSetting(this.pauseLine);
        AutoCrystalBypass.setmgr.rSetting(this.pauseWhileEating);
        AutoCrystalBypass.setmgr.rSetting(this.pauseIfHittingBlock);
        AutoCrystalBypass.setmgr.rSetting(this.minHealthPause);
        AutoCrystalBypass.setmgr.rSetting(this.requireHealth);
        AutoCrystalBypass.setmgr.rSetting(this.multiLine);
        AutoCrystalBypass.setmgr.rSetting(this.autoMultiPlace);
        AutoCrystalBypass.setmgr.rSetting(this.multiPlace);
        AutoCrystalBypass.setmgr.rSetting(this.multiPlaceHP);
        AutoCrystalBypass.setmgr.rSetting(this.faceLine);
        AutoCrystalBypass.setmgr.rSetting(this.facePlace);
        AutoCrystalBypass.setmgr.rSetting(this.facePlaceHP);
        AutoCrystalBypass.setmgr.rSetting(this.armorLine);
        AutoCrystalBypass.setmgr.rSetting(this.armorBreaker);
        AutoCrystalBypass.setmgr.rSetting(this.armorPercent);
        AutoCrystalBypass.setmgr.rSetting(this.singlePlaceLine);
        AutoCrystalBypass.setmgr.rSetting(this.secondCheck);
        AutoCrystalBypass.setmgr.rSetting(this.otherLine);
        AutoCrystalBypass.setmgr.rSetting(this.antiSuicide);
        AutoCrystalBypass.setmgr.rSetting(this.syns);
        AutoCrystalBypass.setmgr.rSetting(this.placeCalculate);
    }
    
    @Override
    public void onEnable() {
        this.target = null;
        this.placedCrystal.clear();
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
        this.target = null;
        this.placedCrystal.clear();
    }
    
    @Override
    public void update() {
        if (AutoCrystalBypass.mc.player == null && AutoCrystalBypass.mc.world == null) {
            return;
        }
        if (this.target != null) {
            super.setDisplayInfo("[" + this.target.getDisplayName().getFormattedText() + TextFormatting.GRAY + "]");
        }
        else {
            super.setDisplayInfo("");
        }
        if (this.mode.getValString().equalsIgnoreCase("ClientTick")) {
            this.doAutoCrystal(null);
        }
    }
    
    private void doAutoCrystal(final EventPlayerMotionUpdate event) {
        if (AutoCrystalBypass.mc.player == null && AutoCrystalBypass.mc.world == null) {
            return;
        }
        final String valString = this.logicMode.getValString();
        switch (valString) {
            case "PlaceBreak": {
                this.doPlaceCrystal((event == null) ? null : event);
                this.breakCrystal(null);
            }
            case "BreakPlace": {
                this.breakCrystal(null);
                this.doPlaceCrystal((event == null) ? null : event);
                break;
            }
        }
    }
    
    private void doPlaceCrystal(final EventPlayerMotionUpdate event) {
        if (this.place.getValBoolean()) {
            if (this.logicMode.getValString().equalsIgnoreCase("1")) {
                if (this.placeTicks++ <= this.placeDelay.getValInt()) {
                    return;
                }
                if (this.needPause()) {
                    this.aimBot.rotationSpoof = null;
                    return;
                }
                this.placeCrystal(event);
                this.placeTicks = 0;
            }
            else {
                this.placeSingleCrystal(event);
            }
        }
    }
    
    private BlockPos placeCrystal(final EventPlayerMotionUpdate event) {
        final List<BlockPos> availableBlockPositions = CrystalUtils.findCrystalBlocks((EntityPlayer)AutoCrystalBypass.mc.player, (float)(int)this.placeRange.getValDouble());
        final String valString = this.placeMode.getValString();
        switch (valString) {
            case "Nearest": {
                this.findNewTarget();
                break;
            }
            case "Priority": {
                if (this.target == null || this.target.getDistance((Entity)AutoCrystalBypass.mc.player) > this.placeRange.getValDouble() + 2.0 || this.target.isDead || this.target.getHealth() <= 0.0f) {
                    this.findNewTarget();
                    break;
                }
                break;
            }
            case "MostDamage": {
                if (availableBlockPositions.isEmpty()) {
                    this.findNewTarget();
                    break;
                }
                EntityPlayer l_Target = null;
                final float minDMG = (float)this.minDMG.getValDouble();
                final float maxSelfDMG = (float)this.maxSelfDMG.getValDouble();
                float dmg = 0.0f;
                for (final EntityPlayer player : AutoCrystalBypass.mc.world.playerEntities) {
                    if (!this.isValidTarget((Entity)player)) {
                        continue;
                    }
                    for (final BlockPos pos : availableBlockPositions) {
                        if (player.getDistanceSq(pos) >= 169.0) {
                            continue;
                        }
                        final float l_TempDMG = CrystalUtils.calculateDamage((World)AutoCrystalBypass.mc.world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (Entity)player, 0);
                        if (l_TempDMG < minDMG) {
                            continue;
                        }
                        final float l_SelfTempDMG = CrystalUtils.calculateDamage((World)AutoCrystalBypass.mc.world, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (Entity)AutoCrystalBypass.mc.player, 0);
                        if (l_SelfTempDMG > maxSelfDMG) {
                            continue;
                        }
                        if (this.wallRange.getValDouble() > 0.0 && !PlayerUtil.CanSeeBlock(pos) && pos.getDistance((int)AutoCrystalBypass.mc.player.posX, (int)AutoCrystalBypass.mc.player.posY, (int)AutoCrystalBypass.mc.player.posZ) > this.wallRange.getValDouble()) {
                            continue;
                        }
                        if (l_TempDMG <= dmg) {
                            continue;
                        }
                        dmg = l_TempDMG;
                        l_Target = player;
                    }
                }
                if (this.target == null) {
                    this.findNewTarget();
                }
                if (this.target != null && l_Target != this.target && l_Target != null && Notification.instance.target.getValBoolean()) {
                    final String newTarget = String.format("Found new target %s", l_Target.getName());
                    ChatUtils.complete((Object)newTarget);
                }
                this.target = l_Target;
                break;
            }
        }
        if (availableBlockPositions.isEmpty()) {
            return BlockPos.ORIGIN;
        }
        if (this.target == null) {
            return BlockPos.ORIGIN;
        }
        if (this.autoMultiPlace.getValBoolean()) {
            if (this.target.getHealth() + this.target.getAbsorptionAmount() <= this.multiPlaceHP.getValDouble()) {
                this.multiPlace.setValEnum(MultiPlaceModes.Dynamic);
            }
            else {
                this.multiPlace.setValEnum(MultiPlaceModes.None);
            }
        }
        float l_MinDmg = (float)this.minDMG.getValDouble();
        float l_MaxSelfDmg = (float)this.maxSelfDMG.getValDouble();
        final float l_FacePlaceHealth = (float)this.facePlaceHP.getValDouble();
        if (this.target.getHealth() <= l_FacePlaceHealth) {
            l_MinDmg = 1.0f;
        }
        if (this.antiSuicide.getValBoolean()) {
            while (AutoCrystalBypass.mc.player.getHealth() + AutoCrystalBypass.mc.player.getAbsorptionAmount() < l_MaxSelfDmg) {
                l_MaxSelfDmg /= 2.0f;
            }
        }
        BlockPos bestPosition = null;
        float l_DMG = 0.0f;
        float l_SelfDMG = 0.0f;
        for (final BlockPos l_Pos : availableBlockPositions) {
            if (this.target.getDistanceSq(l_Pos) >= 169.0) {
                continue;
            }
            final float l_TempDMG2 = CrystalUtils.calculateDamage((World)AutoCrystalBypass.mc.world, l_Pos.getX() + 0.5, l_Pos.getY() + 1.0, l_Pos.getZ() + 0.5, (Entity)this.target, 0);
            if (l_TempDMG2 < l_MinDmg) {
                continue;
            }
            final float l_SelfTempDMG2 = CrystalUtils.calculateDamage((World)AutoCrystalBypass.mc.world, l_Pos.getX() + 0.5, l_Pos.getY() + 1.0, l_Pos.getZ() + 0.5, (Entity)AutoCrystalBypass.mc.player, 0);
            if (l_SelfTempDMG2 > l_MaxSelfDmg) {
                continue;
            }
            if (this.wallRange.getValDouble() > 0.0 && !PlayerUtil.CanSeeBlock(l_Pos) && l_Pos.getDistance((int)AutoCrystalBypass.mc.player.posX, (int)AutoCrystalBypass.mc.player.posY, (int)AutoCrystalBypass.mc.player.posZ) > this.wallRange.getValDouble()) {
                continue;
            }
            if (l_TempDMG2 <= l_DMG) {
                continue;
            }
            l_DMG = l_TempDMG2;
            l_SelfDMG = l_SelfTempDMG2;
            bestPosition = l_Pos;
        }
        if (bestPosition == null) {
            return BlockPos.ORIGIN;
        }
        final int crystalSlot = InventoryUtil.findItem(Items.END_CRYSTAL, 0, 9);
        final int oldSlot = AutoCrystalBypass.mc.player.inventory.currentItem;
        Label_1232: {
            switch ((SwitchModes)this.switchMode.getValEnum()) {
                case None: {
                    if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                        return BlockPos.ORIGIN;
                    }
                }
                case Normal: {
                    if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                        break Label_1232;
                    }
                    if (crystalSlot == -1) {
                        return BlockPos.ORIGIN;
                    }
                    InventoryUtil.switchToSlot(crystalSlot, false);
                    break Label_1232;
                }
                case Silent: {
                    if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                        break;
                    }
                    if (crystalSlot == -1) {
                        return BlockPos.ORIGIN;
                    }
                    InventoryUtil.switchToSlot(crystalSlot, true);
                    break;
                }
            }
        }
        final double[] pos2 = EntityUtil.calculateLookAt(bestPosition.getX() + 0.5, bestPosition.getY() - 0.5, bestPosition.getZ() + 0.5, (Entity)AutoCrystalBypass.mc.player);
        if (this.mode.getValString().equalsIgnoreCase("ClientTick")) {
            this.aimBot.rotationSpoof = new RotationSpoof((float)pos2[0], (float)pos2[1]);
            final Random rand = new Random(2L);
            final RotationSpoof rotationSpoof = this.aimBot.rotationSpoof;
            rotationSpoof.yaw += rand.nextFloat() / 100.0f;
            final RotationSpoof rotationSpoof2 = this.aimBot.rotationSpoof;
            rotationSpoof2.pitch += rand.nextFloat() / 100.0f;
        }
        EnumFacing facing = null;
        if (this.raytrace.getValBoolean()) {
            final RayTraceResult result = AutoCrystalBypass.mc.world.rayTraceBlocks(new Vec3d(AutoCrystalBypass.mc.player.posX, AutoCrystalBypass.mc.player.posY + AutoCrystalBypass.mc.player.getEyeHeight(), AutoCrystalBypass.mc.player.posZ), new Vec3d(bestPosition.getX() + 0.5, bestPosition.getY() - 0.5, bestPosition.getZ() + 0.5));
            if (result == null || result.sideHit == null) {
                facing = EnumFacing.UP;
            }
            else {
                facing = result.sideHit;
            }
        }
        if (this.mode.getValString().equalsIgnoreCase("MotionTick") && event != null) {
            event.cancel();
            this.spoofRotationsTo((float)pos2[0], (float)pos2[1]);
        }
        final boolean offhand = AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        final boolean validateSilentSwitch = AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL && this.switchMode.getValEnum().equals(SwitchModes.Silent);
        if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            AutoCrystalBypass.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(bestPosition, facing, validateSilentSwitch ? EnumHand.MAIN_HAND : (offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND), 0.0f, 0.0f, 0.0f));
        }
        this.swingItem(false);
        this.placedCrystal.add(bestPosition);
        if (oldSlot != -1 && this.switchMode.getValEnum().equals(SwitchModes.Silent)) {
            InventoryUtil.switchToSlot(oldSlot, true);
        }
        return bestPosition;
    }
    
    private void breakCrystal(final BlockPos pos) {
        if (!this._break.getValBoolean()) {
            return;
        }
        if (this.breakTicks++ <= this.breakDelay.getValInt()) {
            return;
        }
        final EntityEnderCrystal crystal = (EntityEnderCrystal)AutoCrystalBypass.mc.world.loadedEntityList.stream().filter(entity -> this.isValidCrystal(entity)).map(entity -> entity).min(Comparator.comparing(entityEnderCrystal -> AutoCrystalBypass.mc.player.getDistance(entityEnderCrystal))).orElse(null);
        if (crystal == null) {
            return;
        }
        final int swordSlot = InventoryUtil.findItem(Items.DIAMOND_SWORD, 0, 9);
        final int oldSlot = AutoCrystalBypass.mc.player.inventory.currentItem;
        if (this.antiWeakness.getValBoolean() && AutoCrystalBypass.mc.player.isPotionActive(MobEffects.WEAKNESS) && swordSlot != -1 && AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() != Items.DIAMOND_SWORD) {
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
        AutoCrystalBypass.mc.playerController.attackEntity((EntityPlayer)AutoCrystalBypass.mc.player, (Entity)crystal);
        this.swingItem(true);
        if (oldSlot != -1 && this.weaknessSwitchMode.getValEnum().equals(SwitchModes.Silent)) {
            InventoryUtil.switchToSlot(oldSlot, true);
        }
        this.breakTicks = 0;
    }
    
    private BlockPos placeSingleCrystal(final EventPlayerMotionUpdate event) {
        if (this.placeTicks++ <= this.placeDelay.getValInt()) {
            return BlockPos.ORIGIN;
        }
        if (!this.isValidItemsInHand()) {
            return BlockPos.ORIGIN;
        }
        EnumHand hand = null;
        BlockPos placePos = null;
        double maxDamage = 0.5;
        for (final BlockPos pos : CrystalUtils.getSphere((float)this.placeRange.getValDouble(), true, false)) {
            final double targetDMG = EntityUtil.calculate(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (EntityLivingBase)this.target);
            final double selfDMG = EntityUtil.calculate(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (EntityLivingBase)AutoCrystalBypass.mc.player) + 2.0;
            if (CrystalUtils.canPlaceCrystal(pos, this.secondCheck.getValBoolean()) && (targetDMG >= this.minDMG.getValInt() || (((targetDMG >= this.target.getHealth() && this.target.getHealth() <= this.facePlaceHP.getValInt()) || this.target.getHealth() <= this.armorPercent.getValDouble()) && EntityUtil.calculate(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (EntityLivingBase)AutoCrystalBypass.mc.player) + 2.0 < AutoCrystalBypass.mc.player.getHealth() && selfDMG < targetDMG))) {
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
        final int oldSlot = AutoCrystalBypass.mc.player.inventory.currentItem;
        Label_0520: {
            switch ((SwitchModes)this.switchMode.getValEnum()) {
                case None: {
                    if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                        return BlockPos.ORIGIN;
                    }
                }
                case Normal: {
                    if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
                        break Label_0520;
                    }
                    if (crystalSlot == -1) {
                        return BlockPos.ORIGIN;
                    }
                    InventoryUtil.switchToSlot(crystalSlot, false);
                    break Label_0520;
                }
                case Silent: {
                    if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
                        break;
                    }
                    if (crystalSlot == -1) {
                        return BlockPos.ORIGIN;
                    }
                    InventoryUtil.switchToSlot(crystalSlot, true);
                    break;
                }
            }
        }
        if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
            return BlockPos.ORIGIN;
        }
        if (AutoCrystalBypass.mc.player.isHandActive()) {
            hand = AutoCrystalBypass.mc.player.getActiveHand();
        }
        if (this.rotate.getValBoolean()) {}
        final EnumFacing facing = EnumFacing.UP;
        final boolean offhand = AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(placePos, facing, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        AutoCrystalBypass.mc.playerController.updateController();
        if (this.switchMode.getValEnum().equals(SwitchModes.Silent) && oldSlot != -1) {
            InventoryUtil.switchToSlot(oldSlot, true);
        }
        this.placeTicks = 0;
        return placePos;
    }
    
    private boolean isValidItemsInHand() {
        int i = 0;
        if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            ++i;
        }
        if (AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && AutoCrystalBypass.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL && !this.switchMode.getValEnum().equals(SwitchModes.None)) {
            ++i;
        }
        return i > 0;
    }
    
    public void findNewTarget() {
        this.target = (EntityPlayer)this.getNearTarget((EntityPlayer)AutoCrystalBypass.mc.player);
    }
    
    public EntityLivingBase getNearTarget(final EntityPlayer distanceTarget) {
        return (EntityLivingBase)AutoCrystalBypass.mc.world.loadedEntityList.stream().filter(entity -> this.isValidTarget(entity)).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    private EntityLivingBase getNearTargetCrystal(final EntityEnderCrystal distanceTarget) {
        return (EntityLivingBase)AutoCrystalBypass.mc.world.loadedEntityList.stream().filter(entity -> this.isValidTarget(entity)).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    public boolean isValidTarget(final Entity entity) {
        return entity != null && entity instanceof EntityLivingBase && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f && entity.getDistance((Entity)AutoCrystalBypass.mc.player) <= 20.0f && entity instanceof EntityPlayer && entity != AutoCrystalBypass.mc.player;
    }
    
    private boolean isValidCrystal(final Entity entity) {
        if (!(entity instanceof EntityEnderCrystal)) {
            return false;
        }
        if (entity.getDistance((Entity)AutoCrystalBypass.mc.player) > (AutoCrystalBypass.mc.player.canEntityBeSeen(entity) ? this.breakRange.getValDouble() : this.wallRange.getValDouble())) {
            return false;
        }
        final String valString = this.breakMode.getValString();
        switch (valString) {
            case "Always": {
                return true;
            }
            case "OnlyOwn": {
                for (final BlockPos pos : new ArrayList<BlockPos>(this.placedCrystal)) {
                    if (pos != null && pos.getDistance((int)entity.posX, (int)entity.posY, (int)entity.posZ) <= 3.0) {
                        return true;
                    }
                }
                break;
            }
            case "Smart": {
                final EntityLivingBase target = (EntityLivingBase)((this.target != null) ? this.target : this.getNearTargetCrystal((EntityEnderCrystal)entity));
                if (target == null) {
                    return false;
                }
                final float targetDMG = CrystalUtils.calculateDamage((World)AutoCrystalBypass.mc.world, entity.posX + 0.5, entity.posY + 1.0, entity.posZ + 0.5, (Entity)target, 0);
                final float selfDMG = CrystalUtils.calculateDamage((World)AutoCrystalBypass.mc.world, entity.posX + 0.5, entity.posY + 1.0, entity.posZ + 0.5, (Entity)AutoCrystalBypass.mc.player, 0);
                float minDMG = (float)this.minDMG.getValDouble();
                if (target.getHealth() + target.getAbsorptionAmount() <= this.facePlaceHP.getValDouble()) {
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
                            AutoCrystalBypass.mc.player.swingArm(EnumHand.OFF_HAND);
                        }
                    }
                    else {
                        AutoCrystalBypass.mc.player.swingArm(EnumHand.MAIN_HAND);
                    }
                }
                else if (!this.breakHand.getValEnum().equals(Hands.Mainhand)) {
                    if (this.breakHand.getValEnum().equals(Hands.Offhand)) {
                        AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
                    }
                }
                else {
                    AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                }
            }
        }
        else if (!this.placeHand.getValEnum().equals(Hands.None)) {
            if (!this.packetSwing.getValBoolean()) {
                if (!this.placeHand.getValEnum().equals(Hands.Mainhand)) {
                    if (this.placeHand.getValEnum().equals(Hands.Offhand)) {
                        AutoCrystalBypass.mc.player.swingArm(EnumHand.OFF_HAND);
                    }
                }
                else {
                    AutoCrystalBypass.mc.player.swingArm(EnumHand.MAIN_HAND);
                }
            }
            else if (!this.placeHand.getValEnum().equals(Hands.Mainhand)) {
                if (this.placeHand.getValEnum().equals(Hands.Offhand)) {
                    AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
                }
            }
            else {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
            }
        }
    }
    
    private void spoofRotationsTo(final float _yaw, final float _pitch) {
        final boolean isSprinting = AutoCrystalBypass.mc.player.isSprinting();
        if (isSprinting != AutoCrystalBypass.mc.player.serverSprintState) {
            if (isSprinting) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoCrystalBypass.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            }
            else {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoCrystalBypass.mc.player, CPacketEntityAction.Action.STOP_SPRINTING));
            }
            AutoCrystalBypass.mc.player.serverSprintState = isSprinting;
        }
        final boolean isSneaking = AutoCrystalBypass.mc.player.isSneaking();
        if (isSneaking != AutoCrystalBypass.mc.player.serverSneakState) {
            if (isSneaking) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoCrystalBypass.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            }
            else {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)AutoCrystalBypass.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            }
            AutoCrystalBypass.mc.player.serverSneakState = isSneaking;
        }
        if (PlayerUtil.isCurrentViewEntity()) {
            final float pitch = _pitch;
            final float yaw = _yaw;
            final AxisAlignedBB axisalignedbb = AutoCrystalBypass.mc.player.getEntityBoundingBox();
            final double posXDifference = AutoCrystalBypass.mc.player.posX - AutoCrystalBypass.mc.player.lastReportedPosX;
            final double posYDifference = axisalignedbb.minY - AutoCrystalBypass.mc.player.lastReportedPosY;
            final double posZDifference = AutoCrystalBypass.mc.player.posZ - AutoCrystalBypass.mc.player.lastReportedPosZ;
            final double yawDifference = yaw - AutoCrystalBypass.mc.player.lastReportedYaw;
            final double rotationDifference = pitch - AutoCrystalBypass.mc.player.lastReportedPitch;
            final EntityPlayerSP player = AutoCrystalBypass.mc.player;
            ++player.positionUpdateTicks;
            boolean movedXYZ = posXDifference * posXDifference + posYDifference * posYDifference + posZDifference * posZDifference > 9.0E-4 || AutoCrystalBypass.mc.player.positionUpdateTicks >= 20;
            final boolean movedRotation = yawDifference != 0.0 || rotationDifference != 0.0;
            if (AutoCrystalBypass.mc.player.isRiding()) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(AutoCrystalBypass.mc.player.motionX, -999.0, AutoCrystalBypass.mc.player.motionZ, yaw, pitch, AutoCrystalBypass.mc.player.onGround));
                movedXYZ = false;
            }
            else if (movedXYZ && movedRotation) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketPlayer.PositionRotation(AutoCrystalBypass.mc.player.posX, axisalignedbb.minY, AutoCrystalBypass.mc.player.posZ, yaw, pitch, AutoCrystalBypass.mc.player.onGround));
            }
            else if (movedXYZ) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(AutoCrystalBypass.mc.player.posX, axisalignedbb.minY, AutoCrystalBypass.mc.player.posZ, AutoCrystalBypass.mc.player.onGround));
            }
            else if (movedRotation) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(yaw, pitch, AutoCrystalBypass.mc.player.onGround));
            }
            else if (AutoCrystalBypass.mc.player.prevOnGround != AutoCrystalBypass.mc.player.onGround) {
                AutoCrystalBypass.mc.player.connection.sendPacket((Packet)new CPacketPlayer(AutoCrystalBypass.mc.player.onGround));
            }
            if (movedXYZ) {
                AutoCrystalBypass.mc.player.lastReportedPosX = AutoCrystalBypass.mc.player.posX;
                AutoCrystalBypass.mc.player.lastReportedPosY = axisalignedbb.minY;
                AutoCrystalBypass.mc.player.lastReportedPosZ = AutoCrystalBypass.mc.player.posZ;
                AutoCrystalBypass.mc.player.positionUpdateTicks = 0;
            }
            if (movedRotation) {
                AutoCrystalBypass.mc.player.lastReportedYaw = yaw;
                AutoCrystalBypass.mc.player.lastReportedPitch = pitch;
            }
            AutoCrystalBypass.mc.player.prevOnGround = AutoCrystalBypass.mc.player.onGround;
            AutoCrystalBypass.mc.player.autoJumpEnabled = AutoCrystalBypass.mc.player.mc.gameSettings.autoJump;
        }
    }
    
    private boolean needPause() {
        return (this.minHealthPause.getValBoolean() && AutoCrystalBypass.mc.player.getHealth() + AutoCrystalBypass.mc.player.getAbsorptionAmount() < (int)this.requireHealth.getValDouble()) || (this.pauseIfHittingBlock.getValBoolean() && AutoCrystalBypass.mc.playerController.isHittingBlock && AutoCrystalBypass.mc.player.getHeldItemMainhand().getItem() instanceof ItemTool) || (this.pauseWhileEating.getValBoolean() && PlayerUtil.IsEating());
    }
    
    public enum Renders
    {
        None, 
        Normal;
    }
    
    public enum MultiPlaceModes
    {
        None, 
        Dynamic, 
        Static;
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
