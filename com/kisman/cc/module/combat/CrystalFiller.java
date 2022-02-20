//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.combat.holefiller.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.*;
import net.minecraft.util.text.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import java.util.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;
import net.minecraft.world.*;

public class CrystalFiller extends Module
{
    private Setting range;
    private Setting placeMode;
    private Setting delay;
    private Setting targetHoleRange;
    private Setting switchMode;
    private Setting crystalDMGCheck;
    private Setting minDMG;
    private Setting maxSelfDMG;
    private Setting raytrace;
    private ArrayList<Hole> holes;
    private ArrayList<Hole> blackHoleList;
    private ArrayList<EntityEnderCrystal> crystals;
    private int delayTicks;
    public EntityPlayer target;
    public Hole targetHole;
    
    public CrystalFiller() {
        super("CrystalFiller", "gay's module xd", Category.COMBAT);
        this.range = new Setting("Range", this, 4.8, 1.0, 6.0, false);
        this.placeMode = new Setting("PlaceMode", this, PlaceMode.Always);
        this.delay = new Setting("Delay", this, 2.0, 0.0, 20.0, true);
        this.targetHoleRange = new Setting("TargetHoleRange", this, 4.8, 1.0, 6.0, false);
        this.switchMode = new Setting("SwitchMode", this, SwitchMode.Silent);
        this.crystalDMGCheck = new Setting("CrystalDMGCheck", this, false);
        this.minDMG = new Setting("MinDMG", this, 5.0, 0.0, 36.0, true);
        this.maxSelfDMG = new Setting("MaxSelfDMG", this, 15.0, 0.0, 36.0, true);
        this.raytrace = new Setting("RayTrace", this, true);
        this.holes = new ArrayList<Hole>();
        this.blackHoleList = new ArrayList<Hole>();
        this.crystals = new ArrayList<EntityEnderCrystal>();
        this.delayTicks = 0;
        CrystalFiller.setmgr.rSetting(this.range);
        CrystalFiller.setmgr.rSetting(this.placeMode);
        CrystalFiller.setmgr.rSetting(this.delay);
        CrystalFiller.setmgr.rSetting(this.targetHoleRange);
        CrystalFiller.setmgr.rSetting(this.switchMode);
        CrystalFiller.setmgr.rSetting(this.crystalDMGCheck);
        CrystalFiller.setmgr.rSetting(this.minDMG);
        CrystalFiller.setmgr.rSetting(this.maxSelfDMG);
        CrystalFiller.setmgr.rSetting(this.raytrace);
    }
    
    @Override
    public void onEnable() {
        this.target = null;
        this.targetHole = null;
        this.delayTicks = 0;
        this.holes.clear();
        this.blackHoleList.clear();
        this.crystals.clear();
    }
    
    @Override
    public void onDisable() {
        this.target = null;
        this.targetHole = null;
        this.delayTicks = 0;
        this.holes.clear();
        this.blackHoleList.clear();
        this.crystals.clear();
    }
    
    @Override
    public void update() {
        if (CrystalFiller.mc.player == null && CrystalFiller.mc.world == null) {
            return;
        }
        if (this.target == null) {
            super.setDisplayInfo("");
        }
        else {
            super.setDisplayInfo(TextFormatting.GRAY + "[" + TextFormatting.WHITE + this.target.getDisplayName() + TextFormatting.GRAY + "]");
        }
        this.doCrystalFiller();
    }
    
    private void doCrystalFiller() {
        if (this.target == null && this.placeMode.getValString().equals(PlaceMode.Smart.name())) {
            this.findNewTarget();
        }
        else if (this.delayTicks++ > this.delay.getValInt()) {
            this.findHoles((EntityPlayer)CrystalFiller.mc.player, (float)this.range.getValDouble());
            this.findTargetHole();
            if (this.targetHole != null) {
                final int crystalSlot = InventoryUtil.findItem(Items.END_CRYSTAL, 0, 9);
                final int oldSlot = CrystalFiller.mc.player.inventory.currentItem;
                final String valString = this.switchMode.getValString();
                switch (valString) {
                    case "None": {
                        if (CrystalFiller.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && CrystalFiller.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                            return;
                        }
                        break;
                    }
                    case "Normal": {
                        if (CrystalFiller.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && CrystalFiller.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL && crystalSlot != -1) {
                            InventoryUtil.switchToSlot(crystalSlot, false);
                            break;
                        }
                        break;
                    }
                    case "Silent": {
                        if (CrystalFiller.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && CrystalFiller.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL && crystalSlot != -1) {
                            InventoryUtil.switchToSlot(crystalSlot, true);
                            break;
                        }
                        break;
                    }
                }
                if (CrystalFiller.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && CrystalFiller.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                    return;
                }
                RayTraceResult result = null;
                if (this.range.getValBoolean()) {
                    result = CrystalFiller.mc.world.rayTraceBlocks(new Vec3d(CrystalFiller.mc.player.posX, CrystalFiller.mc.player.posY + CrystalFiller.mc.player.getEyeHeight(), CrystalFiller.mc.player.posZ), new Vec3d(this.targetHole.pos.getX() + Double.longBitsToDouble(Double.doubleToLongBits(2.0585482766064893) ^ 0x7FE077E828AA18A5L), this.targetHole.getDownHoleBlock().getY() - Double.longBitsToDouble(Double.doubleToLongBits(18.64274749914699) ^ 0x7FD2A48B19A06C0FL), this.targetHole.pos.getZ() + Double.longBitsToDouble(Double.doubleToLongBits(3.2686479786919134) ^ 0x7FEA2630E954F239L)));
                }
                final EnumFacing facing = (result == null || result.sideHit == null) ? EnumFacing.UP : result.sideHit;
                final boolean offhand = CrystalFiller.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
                CrystalFiller.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(this.targetHole.getDownHoleBlock(), facing, offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                CrystalFiller.mc.playerController.updateController();
                if (this.switchMode.getValString().equals(SwitchMode.Silent.name()) && oldSlot != -1) {
                    InventoryUtil.switchToSlot(oldSlot, true);
                }
                this.delayTicks = 0;
            }
        }
    }
    
    private void findTargetHole() {
        this.targetHole = this.getNearHole();
    }
    
    private Hole getNearHole() {
        return this.holes.stream().filter(hole -> this.isValidHole(hole)).min(Comparator.comparing(hole -> CrystalFiller.mc.player.getDistanceSq(hole.pos))).orElse(null);
    }
    
    private void findHoles(final EntityPlayer player, final float range) {
        this.holes.clear();
        for (final BlockPos pos : CrystalUtils.getSphere(player, range, true, false)) {
            if (CrystalFiller.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) && this.isBlockHole(pos)) {
                this.holes.add(new Hole(pos, (float)CrystalFiller.mc.player.getDistanceSq(pos), (float)player.getDistanceSq(pos)));
            }
        }
    }
    
    private void findNewTarget() {
        this.target = this.getNearTarget((EntityPlayer)CrystalFiller.mc.player);
    }
    
    private EntityPlayer getNearTarget(final EntityPlayer distanceTarget) {
        return (EntityPlayer)CrystalFiller.mc.world.loadedEntityList.stream().filter(entity -> this.isValidTarget(entity)).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    private boolean isValidHole(final Hole hole) {
        if (WorldUtil.getDistance((Entity)CrystalFiller.mc.player, hole.pos) > this.range.getValDouble()) {
            return false;
        }
        if (this.placeMode.getValString().equals(PlaceMode.Smart.name()) && this.target != null) {
            if (WorldUtil.getDistance((Entity)this.target, hole.pos) > this.targetHoleRange.getValDouble()) {
                return false;
            }
            if (this.crystalDMGCheck.getValBoolean()) {
                final float tempDMG = CrystalUtils.calculateDamage((World)CrystalFiller.mc.world, hole.pos, (Entity)this.target);
                final float tempSelfDMG = CrystalUtils.calculateDamage((World)CrystalFiller.mc.world, hole.pos, (Entity)CrystalFiller.mc.player);
                if (tempDMG < this.minDMG.getValDouble()) {
                    return false;
                }
                if (tempSelfDMG > this.maxSelfDMG.getValDouble()) {
                    return false;
                }
            }
        }
        return CrystalFiller.mc.world.getBlockState(hole.pos.up(1)).getBlock().equals(Blocks.AIR) && CrystalFiller.mc.world.getBlockState(hole.pos.up(2)).getBlock().equals(Blocks.AIR) && CrystalFiller.mc.world.getBlockState(hole.pos.up(3)).getBlock().equals(Blocks.AIR);
    }
    
    private boolean isBlockHole(final BlockPos blockpos) {
        int holeblocks = 0;
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || CrystalFiller.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK || CrystalFiller.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN || CrystalFiller.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK || CrystalFiller.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || CrystalFiller.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK || CrystalFiller.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK || CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK || CrystalFiller.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        return holeblocks >= 9;
    }
    
    private boolean isValidTarget(final Entity entity) {
        return entity instanceof EntityPlayer && entity != CrystalFiller.mc.player && ((EntityPlayer)entity).getHealth() >= 0.0f && entity.getDistance((Entity)CrystalFiller.mc.player) <= this.range.getValDouble();
    }
    
    public enum PlaceMode
    {
        Always, 
        Smart;
    }
    
    public enum SwitchMode
    {
        None, 
        Normal, 
        Silent;
    }
}
