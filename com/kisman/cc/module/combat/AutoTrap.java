//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.init.*;
import com.mojang.realmsclient.gui.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;
import net.minecraft.util.*;
import net.minecraft.entity.*;
import com.kisman.cc.module.client.*;
import com.kisman.cc.*;

public class AutoTrap extends Module
{
    public static AutoTrap instance;
    private Setting disableOnComplete;
    private Setting placeDelay;
    private Setting rotate;
    private Setting blocksPerTick;
    private Setting antiScaffold;
    private Setting antiStep;
    private Setting surroundPlacing;
    private Setting range;
    private Setting raytrace;
    private TimerUtils timer;
    private Map<BlockPos, Integer> retries;
    private TimerUtils retryTimer;
    public EntityPlayer target;
    private boolean didPlace;
    private boolean switchedItem;
    private boolean isSneaking;
    private int oldSlot;
    private int placements;
    private boolean smartRotate;
    private BlockPos startPos;
    private boolean isPlacing;
    
    public AutoTrap() {
        super("AutoTrap", "trapping all players", Category.COMBAT);
        this.disableOnComplete = new Setting("DisableOnComplete", this, false);
        this.placeDelay = new Setting("Delay", this, 50.0, 0.0, 100.0, true);
        this.rotate = new Setting("Rotate", this, true);
        this.blocksPerTick = new Setting("BlocksPerTick", this, 8.0, 1.0, 30.0, true);
        this.antiScaffold = new Setting("AntiScaffold", this, false);
        this.antiStep = new Setting("AntiStep", this, false);
        this.surroundPlacing = new Setting("SurroundPlacing", this, true);
        this.range = new Setting("Range", this, 4.0, 1.0, 5.0, false);
        this.raytrace = new Setting("RayTrace", this, false);
        this.timer = new TimerUtils();
        this.retries = new HashMap<BlockPos, Integer>();
        this.retryTimer = new TimerUtils();
        this.didPlace = false;
        this.placements = 0;
        this.smartRotate = false;
        this.startPos = null;
        super.setToggled(false);
        AutoTrap.instance = this;
        AutoTrap.setmgr.rSetting(this.disableOnComplete);
        AutoTrap.setmgr.rSetting(this.placeDelay);
        AutoTrap.setmgr.rSetting(this.rotate);
        AutoTrap.setmgr.rSetting(this.blocksPerTick);
        AutoTrap.setmgr.rSetting(this.antiScaffold);
        AutoTrap.setmgr.rSetting(this.antiStep);
        AutoTrap.setmgr.rSetting(this.surroundPlacing);
        AutoTrap.setmgr.rSetting(this.range);
        AutoTrap.setmgr.rSetting(this.raytrace);
    }
    
    @Override
    public void onEnable() {
        if (AutoTrap.mc.player == null && AutoTrap.mc.world == null) {
            return;
        }
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)AutoTrap.mc.player);
        this.oldSlot = AutoTrap.mc.player.inventory.currentItem;
        this.retries.clear();
    }
    
    @Override
    public void onDisable() {
        this.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    @Override
    public void update() {
        if (AutoTrap.mc.player == null && AutoTrap.mc.world == null) {
            return;
        }
        this.smartRotate = false;
        this.doTrap();
    }
    
    private void doTrap() {
        if (this.check()) {
            return;
        }
        this.doStaticTrap();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
    
    private void doStaticTrap() {
        final List<Vec3d> placeTargets = BlockUtil.targets(this.target.getPositionVector(), this.antiScaffold.getValBoolean(), this.antiStep.getValBoolean(), this.surroundPlacing.getValBoolean(), false, false, this.raytrace.getValBoolean());
        this.placeList(placeTargets);
    }
    
    private void placeList(final List<Vec3d> list) {
        list.sort((vec3d, vec3d2) -> Double.compare(AutoTrap.mc.player.getDistanceSq(vec3d2.x, vec3d2.y, vec3d2.z), AutoTrap.mc.player.getDistanceSq(vec3d.x, vec3d.y, vec3d.z)));
        list.sort(Comparator.comparingDouble(vec3d -> vec3d.y));
        for (final Vec3d vec3d3 : list) {
            final BlockPos position = new BlockPos(vec3d3);
            final int placeability = BlockUtil.isPositionPlaceable(position, this.raytrace.getValBoolean());
            if (placeability == 1 && (this.retries.get(position) == null || this.retries.get(position) < 4)) {
                this.placeBlock(position);
                this.retries.put(position, (this.retries.get(position) == null) ? 1 : (this.retries.get(position) + 1));
                this.retryTimer.reset();
            }
            else {
                if (placeability != 3) {
                    continue;
                }
                this.placeBlock(position);
            }
        }
    }
    
    private boolean check() {
        if (AutoTrap.mc.player == null) {
            return false;
        }
        if (this.startPos == null) {
            return false;
        }
        this.isPlacing = false;
        this.didPlace = false;
        this.placements = 0;
        final int obbySlot2 = InventoryUtil.findBlock(Blocks.OBSIDIAN, 0, 9);
        if (obbySlot2 == -1) {
            this.setToggled(false);
        }
        final int obbySlot3 = InventoryUtil.findBlock(Blocks.OBSIDIAN, 0, 9);
        if (!super.isToggled()) {
            return true;
        }
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)AutoTrap.mc.player))) {
            this.setToggled(false);
            return true;
        }
        if (this.retryTimer.passedMillis(2000L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (obbySlot3 == -1) {
            ChatUtils.error((Object)(ChatFormatting.RED + "No Obsidian in hotbar, AutoTrap disabling..."));
            this.setToggled(false);
            return true;
        }
        if (AutoTrap.mc.player.inventory.currentItem != this.oldSlot && AutoTrap.mc.player.inventory.currentItem != obbySlot3) {
            this.oldSlot = AutoTrap.mc.player.inventory.currentItem;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.target = (EntityPlayer)this.getNearTarget((EntityPlayer)AutoTrap.mc.player);
        return this.target == null || !this.timer.passedMillis((long)this.placeDelay.getValInt());
    }
    
    private void placeBlock(final BlockPos pos) {
        if (this.placements < this.blocksPerTick.getValInt() && AutoTrap.mc.player.getDistanceSq(pos) <= MathUtil.square(5.0)) {
            this.isPlacing = true;
            final int originalSlot = AutoTrap.mc.player.inventory.currentItem;
            final int obbySlot = InventoryUtil.findBlock(Blocks.OBSIDIAN, 0, 9);
            final int eChestSot = InventoryUtil.findBlock(Blocks.ENDER_CHEST, 0, 9);
            if (obbySlot == -1 && eChestSot == -1) {
                this.toggle();
            }
            if (this.smartRotate) {
                AutoTrap.mc.player.inventory.currentItem = ((obbySlot == -1) ? eChestSot : obbySlot);
                AutoTrap.mc.playerController.updateController();
                this.isSneaking = BlockUtil.placeBlockSmartRotate(pos, EnumHand.MAIN_HAND, this.rotate.getValBoolean(), true, this.isSneaking);
                AutoTrap.mc.player.inventory.currentItem = originalSlot;
                AutoTrap.mc.playerController.updateController();
            }
            else {
                AutoTrap.mc.player.inventory.currentItem = ((obbySlot == -1) ? eChestSot : obbySlot);
                AutoTrap.mc.playerController.updateController();
                this.isSneaking = BlockUtil.placeBlockSmartRotate(pos, EnumHand.MAIN_HAND, this.rotate.getValBoolean(), this.rotate.getValBoolean(), this.isSneaking);
                AutoTrap.mc.player.inventory.currentItem = originalSlot;
                AutoTrap.mc.playerController.updateController();
            }
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    private EntityLivingBase getNearTarget(final EntityPlayer distanceTarget) {
        return (EntityLivingBase)AutoTrap.mc.world.loadedEntityList.stream().filter(entity -> this.isValidTarget(entity)).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    private boolean isValidTarget(final Entity entity) {
        return entity != null && entity instanceof EntityLivingBase && (!Config.instance.friends.getValBoolean() || !Kisman.instance.friendManager.isFriend((EntityPlayer)entity)) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f && entity.getDistance((Entity)AutoTrap.mc.player) <= this.range.getValDouble() && entity instanceof EntityPlayer && entity != AutoTrap.mc.player;
    }
}
