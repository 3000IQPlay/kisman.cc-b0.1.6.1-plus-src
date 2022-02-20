//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.combat.holefiller.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.*;
import net.minecraft.util.text.*;
import net.minecraft.init.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import java.util.*;
import com.kisman.cc.util.*;
import net.minecraft.entity.*;

public class HoleFiller extends Module
{
    private Setting range;
    private Setting placeMode;
    private Setting delay;
    private Setting targetHoleRange;
    private Setting switchMode;
    private Setting smartWeb;
    private Setting render;
    public static HoleFiller instance;
    private ArrayList<Hole> holes;
    public EntityPlayer target;
    public Hole targetHole;
    
    public HoleFiller() {
        super("HoleFiller", "HoleFiller", Category.COMBAT);
        this.range = new Setting("Range", this, 4.8, 1.0, 6.0, false);
        this.placeMode = new Setting("PlaceMode", this, PlaceMode.Always);
        this.delay = new Setting("Delay", this, 2.0, 0.0, 20.0, true);
        this.targetHoleRange = new Setting("TargetHoleRange", this, 4.8, 1.0, 6.0, false);
        this.switchMode = new Setting("SwitchMode", this, SwitchMode.Silent);
        this.smartWeb = new Setting("SmartWeb", this, false);
        this.render = new Setting("Render", this, true);
        this.holes = new ArrayList<Hole>();
        HoleFiller.instance = this;
        HoleFiller.setmgr.rSetting(this.range);
        HoleFiller.setmgr.rSetting(this.placeMode);
        HoleFiller.setmgr.rSetting(this.delay);
        HoleFiller.setmgr.rSetting(this.targetHoleRange);
        HoleFiller.setmgr.rSetting(this.switchMode);
        HoleFiller.setmgr.rSetting(this.smartWeb);
    }
    
    @Override
    public void onEnable() {
        this.target = null;
        this.targetHole = null;
        this.holes.clear();
    }
    
    @Override
    public void onDisable() {
        this.target = null;
        this.targetHole = null;
        this.holes.clear();
    }
    
    @Override
    public void update() {
        if (HoleFiller.mc.player == null && HoleFiller.mc.world == null) {
            return;
        }
        if (this.target == null) {
            super.setDisplayInfo("");
        }
        else {
            super.setDisplayInfo(TextFormatting.GRAY + "[" + TextFormatting.WHITE + this.target.getDisplayName() + TextFormatting.GRAY + "]");
        }
        if (this.target == null && this.placeMode.getValString().equals(PlaceMode.Smart.name())) {
            this.findNewTarget();
        }
        else {
            this.findHoles((EntityPlayer)HoleFiller.mc.player, (float)this.range.getValDouble());
            this.findTargetHole();
            if (this.targetHole != null) {
                final int obbySlot = InventoryUtil.findBlock(Blocks.OBSIDIAN, 0, 9);
                final int webSlot = InventoryUtil.findBlock(Blocks.WEB, 0, 9);
                final int oldSlot = HoleFiller.mc.player.inventory.currentItem;
                final String valString = this.switchMode.getValString();
                switch (valString) {
                    case "None": {
                        if (obbySlot == -1) {
                            return;
                        }
                        break;
                    }
                    case "Normal": {
                        if (obbySlot != -1) {
                            InventoryUtil.switchToSlot(obbySlot, false);
                            break;
                        }
                        return;
                    }
                    case "Silent": {
                        if (obbySlot != -1) {
                            InventoryUtil.switchToSlot(obbySlot, true);
                            break;
                        }
                        return;
                    }
                }
                BlockUtil.placeBlock(this.targetHole.pos);
                if (HoleFiller.mc.world.getBlockState(this.targetHole.pos).getBlock() != Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(this.targetHole.pos).getBlock() != Blocks.WEB) {
                    final String valString2 = this.switchMode.getValString();
                    switch (valString2) {
                        case "Normal": {
                            InventoryUtil.switchToSlot(webSlot, false);
                            break;
                        }
                        case "Silent": {
                            InventoryUtil.switchToSlot(webSlot, true);
                            break;
                        }
                    }
                    BlockUtil.placeBlock(this.targetHole.pos);
                }
                if (this.switchMode.getValString().equals(SwitchMode.Silent.name()) && oldSlot != -1) {
                    InventoryUtil.switchToSlot(oldSlot, true);
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (this.render.getValBoolean() && this.targetHole != null) {
            RenderUtil.drawBlockESP(this.targetHole.pos, 1.0f, 0.0f, 0.0f);
        }
    }
    
    private void findTargetHole() {
        this.targetHole = this.getNearHole();
    }
    
    private Hole getNearHole() {
        return this.holes.stream().filter(hole -> this.isValidHole(hole)).min(Comparator.comparing(hole -> HoleFiller.mc.player.getDistanceSq(hole.pos))).orElse(null);
    }
    
    private void findHoles(final EntityPlayer player, final float range) {
        this.holes.clear();
        for (final BlockPos pos : CrystalUtils.getSphere(player, range, true, false)) {
            if (HoleFiller.mc.world.getBlockState(pos).getBlock().equals(Blocks.AIR) && this.isBlockHole(pos)) {
                this.holes.add(new Hole(pos, (float)HoleFiller.mc.player.getDistanceSq(pos), (float)player.getDistanceSq(pos)));
            }
        }
    }
    
    private void findNewTarget() {
        this.target = this.getNearTarget((EntityPlayer)HoleFiller.mc.player);
    }
    
    private EntityPlayer getNearTarget(final EntityPlayer distanceTarget) {
        return (EntityPlayer)HoleFiller.mc.world.loadedEntityList.stream().filter(entity -> this.isValidTarget(entity)).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    private boolean isValidHole(final Hole hole) {
        return HoleFiller.mc.player.getDistanceSq(hole.pos) <= this.range.getValDouble() && (!this.placeMode.getValString().equals(PlaceMode.Smart.name()) || WorldUtil.getDistance((Entity)this.target, hole.pos) <= this.targetHoleRange.getValDouble()) && HoleFiller.mc.world.getBlockState(hole.pos.up(1)).getBlock().equals(Blocks.AIR) && HoleFiller.mc.world.getBlockState(hole.pos.up(2)).getBlock().equals(Blocks.AIR) && HoleFiller.mc.world.getBlockState(hole.pos.up(3)).getBlock().equals(Blocks.AIR);
    }
    
    private boolean isBlockHole(final BlockPos blockpos) {
        int holeblocks = 0;
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, 3, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, 1, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, 0)).getBlock() == Blocks.AIR) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.BEDROCK || HoleFiller.mc.world.getBlockState(blockpos.add(0, -1, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.BEDROCK || HoleFiller.mc.world.getBlockState(blockpos.add(1, 0, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.BEDROCK || HoleFiller.mc.world.getBlockState(blockpos.add(-1, 0, 0)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.BEDROCK || HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, 1)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        if (HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN || HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.BEDROCK || HoleFiller.mc.world.getBlockState(blockpos.add(0, 0, -1)).getBlock() == Blocks.ENDER_CHEST) {
            ++holeblocks;
        }
        return holeblocks >= 9;
    }
    
    private boolean isValidTarget(final Entity entity) {
        return entity instanceof EntityPlayer && entity != HoleFiller.mc.player && ((EntityPlayer)entity).getHealth() >= 0.0f && entity.getDistance((Entity)HoleFiller.mc.player) <= this.range.getValDouble();
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
