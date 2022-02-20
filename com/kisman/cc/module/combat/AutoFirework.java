//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import net.minecraft.entity.player.*;
import com.kisman.cc.module.*;
import net.minecraft.util.text.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.init.*;
import com.mojang.realmsclient.gui.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.util.*;

public class AutoFirework extends Module
{
    public static AutoFirework instance;
    private Setting delayLine;
    private Setting delay;
    private Setting trapDelay;
    private Setting placeLine;
    private Setting placeMode;
    private Setting rotate;
    private Setting blocksPerTick;
    private Setting antiScaffold;
    private Setting antiStep;
    private Setting surroundPlacing;
    private Setting range;
    private Setting raytrace;
    private Setting switchLine;
    private Setting switchMode;
    private Setting switchObbyReturn;
    private Setting switchFireReturn;
    private Setting pauseLine;
    private Setting minHealthPause;
    private Setting requiredHealth;
    private Setting pauseWhileEating;
    private Setting pauseIfHittingBlock;
    private Setting handLine;
    private Setting fireHand;
    private TimerUtils trapTimer;
    private TimerUtils delayTimer;
    private Map<BlockPos, Integer> retries;
    private TimerUtils retryTimer;
    private boolean didPlace;
    private boolean switchedItem;
    private boolean isSneaking;
    private int lastHotbarSlot;
    private int placements;
    private boolean smartRotate;
    private BlockPos startPos;
    private boolean isPlacing;
    private AimBot aimBot;
    public EntityPlayer target;
    
    public AutoFirework() {
        super("AutoFirework", "", Category.COMBAT);
        this.delayLine = new Setting("DLine", this, "Delays");
        this.delay = new Setting("Delay", this, 1.0, 0.0, 20.0, true);
        this.trapDelay = new Setting("PlaceDelay", this, 1000.0, 1.0, 10000.0, true);
        this.placeLine = new Setting("PlaceLine", this, "Place");
        this.placeMode = new Setting("PlaceMode", this, "Normal", new ArrayList<String>(Arrays.asList("Normal", "Packet")));
        this.rotate = new Setting("Rotate", this, true);
        this.blocksPerTick = new Setting("BlocksPerTick", this, 8.0, 1.0, 30.0, true);
        this.antiScaffold = new Setting("AntiScaffold", this, false);
        this.antiStep = new Setting("AntiStep", this, false);
        this.surroundPlacing = new Setting("SurroundPlacing", this, true);
        this.range = new Setting("Range", this, 4.0, 1.0, 5.0, false);
        this.raytrace = new Setting("RayTrace", this, false);
        this.switchLine = new Setting("SwitchLine", this, "Switch");
        this.switchMode = new Setting("SwitchMode", this, InventoryUtil.Switch.NORMAL);
        this.switchObbyReturn = new Setting("SwitchReturnObby", this, true);
        this.switchFireReturn = new Setting("SwitchReturnFirework", this, true);
        this.pauseLine = new Setting("PauseLine", this, "Pause");
        this.minHealthPause = new Setting("MinHealthPause", this, false);
        this.requiredHealth = new Setting("RequiredHealth", this, 11.0, 0.0, 36.0, true);
        this.pauseWhileEating = new Setting("PauseWhileEating", this, false);
        this.pauseIfHittingBlock = new Setting("PauseIfHittingBlock", this, false);
        this.handLine = new Setting("HandLine", this, "Hand");
        this.fireHand = new Setting("FireworkHand", this, "Default", new ArrayList<String>(Arrays.asList("Default", "MainHand", "OffHand")));
        this.trapTimer = new TimerUtils();
        this.delayTimer = new TimerUtils();
        this.retries = new HashMap<BlockPos, Integer>();
        this.retryTimer = new TimerUtils();
        this.didPlace = false;
        this.placements = 0;
        this.smartRotate = false;
        this.startPos = null;
        this.target = null;
        this.aimBot = AimBot.instance;
        AutoFirework.instance = this;
        AutoFirework.setmgr.rSetting(this.delayLine);
        AutoFirework.setmgr.rSetting(this.delay);
        AutoFirework.setmgr.rSetting(this.trapDelay);
        AutoFirework.setmgr.rSetting(this.placeLine);
        AutoFirework.setmgr.rSetting(this.placeMode);
        AutoFirework.setmgr.rSetting(this.rotate);
        AutoFirework.setmgr.rSetting(this.blocksPerTick);
        AutoFirework.setmgr.rSetting(this.antiScaffold);
        AutoFirework.setmgr.rSetting(this.antiStep);
        AutoFirework.setmgr.rSetting(this.surroundPlacing);
        AutoFirework.setmgr.rSetting(this.range);
        AutoFirework.setmgr.rSetting(this.raytrace);
        AutoFirework.setmgr.rSetting(this.switchLine);
        AutoFirework.setmgr.rSetting(this.switchMode);
        AutoFirework.setmgr.rSetting(this.switchObbyReturn);
        AutoFirework.setmgr.rSetting(this.switchFireReturn);
        AutoFirework.setmgr.rSetting(this.pauseLine);
        AutoFirework.setmgr.rSetting(this.minHealthPause);
        AutoFirework.setmgr.rSetting(this.requiredHealth);
        AutoFirework.setmgr.rSetting(this.pauseWhileEating);
        AutoFirework.setmgr.rSetting(this.pauseIfHittingBlock);
        AutoFirework.setmgr.rSetting(this.handLine);
        AutoFirework.setmgr.rSetting(this.fireHand);
    }
    
    @Override
    public void onEnable() {
        if (!this.aimBot.isToggled()) {
            this.aimBot.setToggled(true);
        }
        this.aimBot.rotationSpoof = null;
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)AutoFirework.mc.player);
        this.lastHotbarSlot = AutoFirework.mc.player.inventory.currentItem;
        this.retries.clear();
    }
    
    @Override
    public void onDisable() {
        this.aimBot.rotationSpoof = null;
        this.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    @Override
    public void update() {
        if (AutoFirework.mc.player == null && AutoFirework.mc.world == null) {
            return;
        }
        if (this.target != null) {
            super.setDisplayInfo("[" + this.target.getDisplayName().getFormattedText() + TextFormatting.GRAY + "]");
        }
        else {
            super.setDisplayInfo("");
        }
        if (this.needPause()) {
            return;
        }
        if (this.target != null) {
            final BlockPos playerPos = this.target.getPosition();
            this.smartRotate = false;
            this.doTrap();
            if (Math.sqrt(AutoFirework.mc.player.getDistanceSq((double)playerPos.getX(), (double)playerPos.getY(), (double)playerPos.getZ())) <= this.range.getValDouble()) {
                final int oldSlot = AutoFirework.mc.player.inventory.getBestHotbarSlot();
                final int newSlot = InventoryUtil.findItemInHotbar(Items.FIREWORKS.getClass());
                if (this.rotate.getValBoolean()) {
                    final double[] pos = EntityUtil.calculateLookAt(this.target.posX + 0.5, this.target.posY - 0.5, this.target.posZ + 0.5, (Entity)AutoFirework.mc.player);
                    this.aimBot.rotationSpoof = new RotationSpoof((float)pos[0], (float)pos[1]);
                    final Random rand = new Random(2L);
                    final RotationSpoof rotationSpoof = this.aimBot.rotationSpoof;
                    rotationSpoof.yaw += rand.nextFloat() / 100.0f;
                    final RotationSpoof rotationSpoof2 = this.aimBot.rotationSpoof;
                    rotationSpoof2.pitch += rand.nextFloat() / 100.0f;
                }
                EnumFacing facing = null;
                if (this.raytrace.getValBoolean()) {
                    final RayTraceResult result = AutoFirework.mc.world.rayTraceBlocks(new Vec3d(AutoFirework.mc.player.posX, AutoFirework.mc.player.posY + AutoFirework.mc.player.getEyeHeight(), AutoFirework.mc.player.posZ), new Vec3d(this.target.posX + 0.5, this.target.posY - 0.5, this.target.posZ + 0.5));
                    if (result == null || result.sideHit == null) {
                        facing = EnumFacing.UP;
                    }
                    else {
                        facing = result.sideHit;
                    }
                }
                AutoFirework.mc.getConnection().sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(playerPos, facing, this.fireHand.getValString().equalsIgnoreCase("Default") ? ((AutoFirework.mc.player.getHeldItemOffhand().getItem() == Items.FIREWORKS) ? EnumHand.OFF_HAND : EnumHand.OFF_HAND) : (this.fireHand.getValString().equalsIgnoreCase("MainHand") ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND), 0.0f, 0.0f, 0.0f));
                if (this.switchFireReturn.getValBoolean()) {
                    InventoryUtil.switchToSlot(oldSlot, (InventoryUtil.Switch)this.switchMode.getValEnum());
                }
                this.delayTimer.reset();
                this.trapTimer.reset();
            }
        }
        else {
            this.findNewTarget();
        }
    }
    
    private void findNewTarget() {
        this.target = (EntityPlayer)this.getNearTarget((Entity)AutoFirework.mc.player);
    }
    
    private EntityLivingBase getNearTarget(final Entity distanceTarget) {
        return (EntityLivingBase)AutoFirework.mc.world.loadedEntityList.stream().filter(entity -> this.isValidTarget(entity)).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    private boolean needPause() {
        return (this.pauseWhileEating.getValBoolean() && PlayerUtil.IsEating()) || (this.minHealthPause.getValBoolean() && AutoFirework.mc.player.getHealth() + AutoFirework.mc.player.getAbsorptionAmount() < this.requiredHealth.getValDouble()) || (this.pauseIfHittingBlock.getValBoolean() && AutoFirework.mc.playerController.isHittingBlock && AutoFirework.mc.player.getHeldItemMainhand().getItem() instanceof ItemTool);
    }
    
    public boolean isValidTarget(final Entity entity) {
        return entity != null && entity instanceof EntityLivingBase && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f && entity.getDistance((Entity)AutoFirework.mc.player) <= 20.0f && entity instanceof EntityPlayer && entity != AutoFirework.mc.player;
    }
    
    private void doTrap() {
        if (this.check()) {
            return;
        }
        this.doStaticTrap();
        if (this.didPlace) {
            this.trapTimer.reset();
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
        if (AutoFirework.mc.player == null) {
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
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)AutoFirework.mc.player))) {
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
        if (AutoTrap.mc.player.inventory.currentItem != this.lastHotbarSlot && AutoTrap.mc.player.inventory.currentItem != obbySlot3) {
            this.lastHotbarSlot = AutoTrap.mc.player.inventory.currentItem;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.target = (EntityPlayer)this.getNearTarget((Entity)AutoFirework.mc.player);
        return this.target == null || !this.trapTimer.passedMillis((long)this.trapDelay.getValInt());
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
                this.isSneaking = BlockUtil.placeBlockSmartRotate(pos, EnumHand.MAIN_HAND, true, true, this.isSneaking);
                AutoTrap.mc.player.inventory.currentItem = originalSlot;
                AutoTrap.mc.playerController.updateController();
            }
            else {
                AutoTrap.mc.player.inventory.currentItem = ((obbySlot == -1) ? eChestSot : obbySlot);
                AutoTrap.mc.playerController.updateController();
                this.isSneaking = BlockUtil.placeBlockSmartRotate(pos, EnumHand.MAIN_HAND, this.rotate.getValBoolean(), true, this.isSneaking);
                AutoTrap.mc.player.inventory.currentItem = originalSlot;
                AutoTrap.mc.playerController.updateController();
            }
            this.didPlace = true;
            ++this.placements;
        }
    }
}
