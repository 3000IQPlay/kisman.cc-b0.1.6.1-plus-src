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
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import java.util.*;
import com.kisman.cc.util.gamesense.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import com.kisman.cc.event.*;
import com.kisman.cc.util.*;
import net.minecraft.util.math.*;

public class CevBreaker extends Module
{
    private Setting target;
    private Setting breakCrystal;
    private Setting breakBlock;
    private Setting range;
    private Setting preRotationDelay;
    private Setting afterRotationDelay;
    private Setting supDelay;
    private Setting crystalDelay;
    private Setting blockPerTick;
    private Setting hitDelay;
    private Setting midHitDelay;
    private Setting endDelay;
    private Setting pickSwitchTick;
    private Setting rotate;
    private Setting confirmBreak;
    private Setting confirmPlace;
    private Setting antiWeakness;
    private Setting switchSword;
    private Setting fastPlace;
    private Setting fastBreak;
    private Setting trapPlayer;
    private Setting antiStep;
    private Setting placeCrystal;
    private Setting forceRotation;
    private Setting forceBreak;
    public static int cur_item;
    public static boolean isActive;
    public static boolean forceBrk;
    private boolean noMaterials;
    private boolean hasMoved;
    private boolean isSneaking;
    private boolean isHole;
    private boolean enoughSpace;
    private boolean broken;
    private boolean stoppedCa;
    private boolean deadPl;
    private boolean rotationPlayerMoved;
    private boolean prevBreak;
    private boolean preRotationBol;
    private int oldSlot;
    private int stage;
    private int delayTimeTicks;
    private int hitTryTick;
    private int tickPick;
    private int afterRotationTick;
    private int preRotationTick;
    private final int[][] model;
    public static boolean isPossible;
    private int[] slot_mat;
    private int[] delayTable;
    private int[] enemyCoordsInt;
    private double[] enemyCoordsDouble;
    private structureTemp toPlace;
    Double[][] sur_block;
    private EntityPlayer aimTarget;
    private Vec3d lastHitVec;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    @EventHandler
    private final Listener<PacketEvent.Receive> listener1;
    @EventHandler
    private final Listener<DestroyBlockEvent> listener2;
    
    public CevBreaker() {
        super("CevBreaker", "CevBreaker", Category.COMBAT);
        this.target = new Setting("Target", this, "Nearest", new ArrayList<String>(Arrays.asList("Nearest", "Looking")));
        this.breakCrystal = new Setting("Break", this, "Packet", new ArrayList<String>(Arrays.asList("Vanilla", "Packet", "None")));
        this.breakBlock = new Setting("BreakBlock", this, "Packet", new ArrayList<String>(Arrays.asList("Packet", "Normal")));
        this.range = new Setting("Range", this, 4.900000095367432, 0.0, 6.0, false);
        this.preRotationDelay = new Setting("PreRotstionDelay", this, 0.0, 0.0, 20.0, true);
        this.afterRotationDelay = new Setting("AfterRotationDelay", this, 0.0, 0.0, 20.0, true);
        this.supDelay = new Setting("SupportDelay", this, 1.0, 0.0, 4.0, true);
        this.crystalDelay = new Setting("CrystalDelay", this, 2.0, 0.0, 20.0, true);
        this.blockPerTick = new Setting("BlockPerTick", this, 4.0, 2.0, 6.0, true);
        this.hitDelay = new Setting("HitDelay", this, 2.0, 0.0, 20.0, true);
        this.midHitDelay = new Setting("MidHitDelay", this, 1.0, 0.0, 20.0, true);
        this.endDelay = new Setting("EndDelay", this, 1.0, 0.0, 20.0, true);
        this.pickSwitchTick = new Setting("PickSwitchTick", this, 100.0, 0.0, 500.0, true);
        this.rotate = new Setting("Rotate", this, false);
        this.confirmBreak = new Setting("NoGlitchBreak", this, true);
        this.confirmPlace = new Setting("NoGlitchPlace", this, true);
        this.antiWeakness = new Setting("AntiWeakness", this, true);
        this.switchSword = new Setting("SwitchSword", this, false);
        this.fastPlace = new Setting("FastPlace", this, false);
        this.fastBreak = new Setting("FastBreak", this, true);
        this.trapPlayer = new Setting("TrapPlayer", this, false);
        this.antiStep = new Setting("AntiStep", this, false);
        this.placeCrystal = new Setting("PlaceCrystal", this, true);
        this.forceRotation = new Setting("ForceRotation", this, false);
        this.forceBreak = new Setting("ForceBreak", this, false);
        this.noMaterials = false;
        this.hasMoved = false;
        this.isSneaking = false;
        this.isHole = true;
        this.enoughSpace = true;
        this.oldSlot = -1;
        this.model = new int[][] { { 1, 1, 0 }, { -1, 1, 0 }, { 0, 1, 1 }, { 0, 1, -1 } };
        this.sur_block = new Double[4][3];
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.getEra() != Event.Era.PRE || !this.rotate.getValBoolean() || this.lastHitVec == null || !this.forceRotation.getValBoolean()) {
                return;
            }
            final Vec2f rotation = RotationUtils.getRotationTo(this.lastHitVec);
        }, new Predicate[0]);
        this.listener1 = (Listener<PacketEvent.Receive>)new Listener(event -> {
            if (this.enemyCoordsInt == null) {
                return;
            }
            if (event.getPacket() instanceof SPacketSoundEffect) {
                final SPacketSoundEffect packet = (SPacketSoundEffect)event.getPacket();
                if (packet.getCategory() == SoundCategory.BLOCKS && packet.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE && (int)packet.getX() == this.enemyCoordsInt[0] && (int)packet.getZ() == this.enemyCoordsInt[2]) {
                    this.stage = 1;
                }
            }
        }, new Predicate[0]);
        this.listener2 = (Listener<DestroyBlockEvent>)new Listener(event -> {
            try {
                if (this.enemyCoordsInt == null) {
                    return;
                }
                if (event.getBlockPos().getX() + ((event.getBlockPos().getX() < 0) ? 1 : 0) == this.enemyCoordsInt[0] && event.getBlockPos().getZ() + ((event.getBlockPos().getZ() < 0) ? 1 : 0) == this.enemyCoordsInt[2]) {
                    this.destroyCrystalAlgo();
                }
            }
            catch (Exception ex) {}
        }, new Predicate[0]);
        CevBreaker.setmgr.rSetting(this.target);
        CevBreaker.setmgr.rSetting(this.breakCrystal);
        CevBreaker.setmgr.rSetting(this.breakBlock);
        CevBreaker.setmgr.rSetting(this.range);
        CevBreaker.setmgr.rSetting(this.preRotationDelay);
        CevBreaker.setmgr.rSetting(this.afterRotationDelay);
        CevBreaker.setmgr.rSetting(this.supDelay);
        CevBreaker.setmgr.rSetting(this.crystalDelay);
        CevBreaker.setmgr.rSetting(this.blockPerTick);
        CevBreaker.setmgr.rSetting(this.hitDelay);
        CevBreaker.setmgr.rSetting(this.midHitDelay);
        CevBreaker.setmgr.rSetting(this.endDelay);
        CevBreaker.setmgr.rSetting(this.pickSwitchTick);
        CevBreaker.setmgr.rSetting(this.rotate);
        CevBreaker.setmgr.rSetting(this.confirmBreak);
        CevBreaker.setmgr.rSetting(this.confirmPlace);
        CevBreaker.setmgr.rSetting(this.antiWeakness);
        CevBreaker.setmgr.rSetting(this.switchSword);
        CevBreaker.setmgr.rSetting(this.fastPlace);
        CevBreaker.setmgr.rSetting(this.fastBreak);
        CevBreaker.setmgr.rSetting(this.trapPlayer);
        CevBreaker.setmgr.rSetting(this.antiStep);
        CevBreaker.setmgr.rSetting(this.placeCrystal);
        CevBreaker.setmgr.rSetting(this.forceRotation);
        CevBreaker.setmgr.rSetting(this.forceBreak);
    }
    
    @Override
    public void onEnable() {
        SpoofRotationUtil.ROTATION_UTIL.onEnable();
        this.initValues();
        if (this.getAimTarget()) {
            return;
        }
        this.playerChecks();
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
        try {
            Kisman.EVENT_BUS.unsubscribe((Listener)this.listener2);
        }
        catch (Exception ex) {}
        SpoofRotationUtil.ROTATION_UTIL.onDisable();
        if (CevBreaker.mc.player == null) {
            return;
        }
        String output = "";
        String materialsNeeded = "";
        if (this.aimTarget == null) {
            output = "No target found...";
        }
        else if (this.noMaterials) {
            output = "No Materials Detected...";
            materialsNeeded = this.getMissingMaterials();
        }
        else if (!this.isHole) {
            output = "The enemy is not in a hole...";
        }
        else if (!this.enoughSpace) {
            output = "Not enough space...";
        }
        else if (this.hasMoved) {
            output = "Out of range...";
        }
        else if (this.deadPl) {
            output = "Enemy is dead, gg! ";
        }
        ChatUtils.complete((Object)(output + "CevBreaker turned OFF!"));
        if (!materialsNeeded.equals("")) {
            ChatUtils.warning((Object)("Materials missing:" + materialsNeeded));
        }
        if (this.stoppedCa) {
            AutoCrystalBypass.instance.setToggled(false);
            this.stoppedCa = false;
        }
        if (this.isSneaking) {
            CevBreaker.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)CevBreaker.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            this.isSneaking = false;
        }
        if (this.oldSlot != CevBreaker.mc.player.inventory.currentItem && this.oldSlot != -1) {
            CevBreaker.mc.player.inventory.currentItem = this.oldSlot;
            this.oldSlot = -1;
        }
        AutoCrystalBypass.instance.setToggled(false);
        this.noMaterials = (CevBreaker.isPossible = (CevBreaker.isActive = (CevBreaker.forceBrk = false)));
    }
    
    private String getMissingMaterials() {
        final StringBuilder output = new StringBuilder();
        if (this.slot_mat[0] == -1) {
            output.append(" Obsidian");
        }
        if (this.slot_mat[1] == -1) {
            output.append(" Crystal");
        }
        if ((this.antiWeakness.getValBoolean() || this.switchSword.getValBoolean()) && this.slot_mat[3] == -1) {
            output.append(" Sword");
        }
        if (this.slot_mat[2] == -1) {
            output.append(" Pick");
        }
        return output.toString();
    }
    
    private boolean getAimTarget() {
        if (this.target.getValString().equalsIgnoreCase("Nearest")) {
            this.aimTarget = PlayerUtil.findClosestTarget(this.range.getValDouble(), this.aimTarget);
        }
        else {
            this.aimTarget = PlayerUtil.findLookingPlayer(this.range.getValDouble());
        }
        if (this.aimTarget == null || !this.target.getValString().equalsIgnoreCase("Looking")) {
            if (!this.target.getValString().equalsIgnoreCase("Looking") && this.aimTarget == null) {
                super.onDisable();
            }
            if (this.aimTarget == null) {
                return true;
            }
        }
        return false;
    }
    
    private boolean createStructure() {
        if (Objects.requireNonNull(BlockUtil.getBlock(this.enemyCoordsDouble[0], this.enemyCoordsDouble[1] + 2.0, this.enemyCoordsDouble[2]).getRegistryName()).toString().toLowerCase().contains("bedrock") || !(BlockUtil.getBlock(this.enemyCoordsDouble[0], this.enemyCoordsDouble[1] + 3.0, this.enemyCoordsDouble[2]) instanceof BlockAir) || !(BlockUtil.getBlock(this.enemyCoordsDouble[0], this.enemyCoordsDouble[1] + 4.0, this.enemyCoordsDouble[2]) instanceof BlockAir)) {
            return false;
        }
        double max_found = Double.MIN_VALUE;
        int cor = 0;
        int i = 0;
        for (final Double[] cord_b : this.sur_block) {
            final double distance_now;
            if ((distance_now = CevBreaker.mc.player.getDistanceSq(new BlockPos((double)cord_b[0], (double)cord_b[1], (double)cord_b[2]))) > max_found) {
                max_found = distance_now;
                cor = i;
            }
            ++i;
        }
        this.toPlace.to_place.add(new Vec3d((double)this.model[cor][0], 1.0, (double)this.model[cor][2]));
        this.toPlace.to_place.add(new Vec3d((double)this.model[cor][0], 2.0, (double)this.model[cor][2]));
        this.toPlace.supportBlock = 2;
        if (this.trapPlayer.getValBoolean() || this.antiStep.getValBoolean()) {
            for (int high = 1; high < 3; ++high) {
                if (high != 2 || this.antiStep.getValBoolean()) {
                    for (final int[] modelBas : this.model) {
                        final Vec3d toAdd = new Vec3d((double)modelBas[0], (double)high, (double)modelBas[2]);
                        if (!this.toPlace.to_place.contains(toAdd)) {
                            this.toPlace.to_place.add(toAdd);
                            final structureTemp toPlace = this.toPlace;
                            ++toPlace.supportBlock;
                        }
                    }
                }
            }
        }
        this.toPlace.to_place.add(new Vec3d(0.0, 2.0, 0.0));
        this.toPlace.to_place.add(new Vec3d(0.0, 2.0, 0.0));
        return true;
    }
    
    private boolean getMaterialsSlot() {
        if (CevBreaker.mc.player.getHeldItemOffhand().getItem() instanceof ItemEndCrystal) {
            this.slot_mat[1] = 11;
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack stack = CevBreaker.mc.player.inventory.getStackInSlot(i);
            if (stack != ItemStack.EMPTY) {
                if (this.slot_mat[1] == -1 && stack.getItem() instanceof ItemEndCrystal) {
                    this.slot_mat[1] = i;
                }
                else if ((this.antiWeakness.getValBoolean() || this.switchSword.getValBoolean()) && stack.getItem() instanceof ItemSword) {
                    this.slot_mat[3] = i;
                }
                else if (stack.getItem() instanceof ItemPickaxe) {
                    this.slot_mat[2] = i;
                }
                if (stack.getItem() instanceof ItemBlock) {
                    final Block block = ((ItemBlock)stack.getItem()).getBlock();
                    if (block instanceof BlockObsidian) {
                        this.slot_mat[0] = i;
                    }
                }
            }
        }
        int count = 0;
        for (final int val : this.slot_mat) {
            if (val != -1) {
                ++count;
            }
        }
        return count >= 3 + ((this.antiWeakness.getValBoolean() || this.switchSword.getValBoolean()) ? 1 : 0);
    }
    
    private boolean is_in_hole() {
        this.sur_block = new Double[][] { { this.aimTarget.posX + 1.0, this.aimTarget.posY, this.aimTarget.posZ }, { this.aimTarget.posX - 1.0, this.aimTarget.posY, this.aimTarget.posZ }, { this.aimTarget.posX, this.aimTarget.posY, this.aimTarget.posZ + 1.0 }, { this.aimTarget.posX, this.aimTarget.posY, this.aimTarget.posZ - 1.0 } };
        return GameSenseHoleUtil.isHole(EntityUtil.getPosition((Entity)this.aimTarget), true, true).getType() != GameSenseHoleUtil.HoleType.NONE;
    }
    
    private void playerChecks() {
        if (this.getMaterialsSlot()) {
            if (this.is_in_hole()) {
                this.enemyCoordsDouble = new double[] { this.aimTarget.posX, this.aimTarget.posY, this.aimTarget.posZ };
                this.enemyCoordsInt = new int[] { (int)this.enemyCoordsDouble[0], (int)this.enemyCoordsDouble[1], (int)this.enemyCoordsDouble[2] };
                this.enoughSpace = this.createStructure();
            }
            else {
                this.isHole = false;
            }
        }
        else {
            this.noMaterials = true;
        }
    }
    
    private void initValues() {
        this.preRotationBol = false;
        final int n = 0;
        this.preRotationTick = n;
        this.afterRotationTick = n;
        CevBreaker.isPossible = false;
        this.aimTarget = null;
        this.lastHitVec = null;
        this.delayTable = new int[] { (int)this.supDelay.getValDouble(), (int)this.crystalDelay.getValDouble(), (int)this.hitDelay.getValDouble(), (int)this.endDelay.getValDouble() };
        this.toPlace = new structureTemp(0.0, 0, new ArrayList<Vec3d>());
        this.isHole = (CevBreaker.isActive = true);
        final boolean b = false;
        this.broken = b;
        this.deadPl = b;
        this.rotationPlayerMoved = b;
        this.hasMoved = b;
        this.slot_mat = new int[] { -1, -1, -1, -1 };
        final int n2 = 0;
        this.delayTimeTicks = n2;
        this.stage = n2;
        if (CevBreaker.mc.player == null) {
            super.onDisable();
            return;
        }
        this.oldSlot = CevBreaker.mc.player.inventory.currentItem;
        this.stoppedCa = false;
        CevBreaker.cur_item = -1;
        if (AutoCrystalBypass.instance.isToggled()) {
            AutoCrystalBypass.instance.setToggled(false);
            this.stoppedCa = true;
        }
        CevBreaker.forceBrk = this.forceBreak.getValBoolean();
    }
    
    public void destroyCrystalAlgo() {
        CevBreaker.isPossible = false;
        final Entity crystal = this.getCrystal();
        if (this.confirmBreak.getValBoolean() && this.broken && crystal == null) {
            this.stage = 1;
            this.broken = false;
        }
        if (crystal != null) {
            this.breakCrystalPiston(crystal);
            if (this.confirmBreak.getValBoolean()) {
                this.broken = true;
            }
            else {
                this.stage = 1;
            }
        }
        else {
            this.stage = 1;
        }
    }
    
    private Entity getCrystal() {
        for (final Entity t : CevBreaker.mc.world.loadedEntityList) {
            if (t instanceof EntityEnderCrystal && (int)t.posX == this.enemyCoordsInt[0] && (int)t.posZ == this.enemyCoordsInt[2] && t.posY - this.enemyCoordsInt[1] == 3.0) {
                return t;
            }
        }
        return null;
    }
    
    private void breakCrystalPiston(final Entity crystal) {
        if (this.hitTryTick++ < this.midHitDelay.getValDouble()) {
            return;
        }
        this.hitTryTick = 0;
        if (this.antiWeakness.getValBoolean()) {
            CevBreaker.mc.player.inventory.currentItem = this.slot_mat[3];
        }
        final Vec3d vecCrystal = crystal.getPositionVector().add(new Vec3d(0.5, 0.5, 0.5));
        if (!this.breakCrystal.getValString().equalsIgnoreCase("None") && this.rotate.getValBoolean()) {
            SpoofRotationUtil.ROTATION_UTIL.lookAtPacket(vecCrystal.x, vecCrystal.y, vecCrystal.z, (EntityPlayer)CevBreaker.mc.player);
            if (this.forceRotation.getValBoolean()) {
                this.lastHitVec = vecCrystal;
            }
        }
        try {
            final String valString = this.breakCrystal.getValString();
            switch (valString) {
                case "Vanilla": {
                    CrystalUtil.breakCrystal(crystal);
                    break;
                }
                case "Packet": {
                    CrystalUtil.breakCrystalPacket(crystal);
                    break;
                }
            }
        }
        catch (NullPointerException ex) {}
        if (this.rotate.getValBoolean()) {
            SpoofRotationUtil.ROTATION_UTIL.resetRotation();
        }
    }
    
    static {
        CevBreaker.cur_item = -1;
        CevBreaker.isActive = false;
        CevBreaker.forceBrk = false;
        CevBreaker.isPossible = false;
    }
    
    private static class structureTemp
    {
        public double distance;
        public int supportBlock;
        public ArrayList<Vec3d> to_place;
        public int direction;
        
        public structureTemp(final double distance, final int supportBlock, final ArrayList<Vec3d> to_place) {
            this.distance = distance;
            this.supportBlock = supportBlock;
            this.to_place = to_place;
            this.direction = -1;
        }
    }
}
