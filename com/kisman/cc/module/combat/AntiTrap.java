//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import java.util.*;
import com.kisman.cc.util.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import com.kisman.cc.event.*;

public class AntiTrap extends Module
{
    public static AntiTrap instance;
    private final Setting mode;
    private final Setting delay;
    private final Setting switchMode;
    private final Setting rotate;
    private final Setting sortY;
    private final Setting onlyInHole;
    private final TimerUtils timer;
    public Set<BlockPos> placedPos;
    private final Vec3d[] surroundTargets;
    private int oldSlot;
    private boolean switchedItem;
    private boolean offhand;
    @EventHandler
    private final Listener<EventPlayerMotionUpdate> listener;
    
    public AntiTrap() {
        super("AntiTrap", "", Category.COMBAT);
        this.mode = new Setting("Mode", this, "MotionTick", new ArrayList<String>(Arrays.asList("MotionTick", "ClientTick")));
        this.delay = new Setting("Delay", this, 400.0, 0.0, 1000.0, true);
        this.switchMode = new Setting("SwitchMode", this, SwitchModes.None);
        this.rotate = new Setting("Rotate", this, Rotate.NONE);
        this.sortY = new Setting("SortY", this, true);
        this.onlyInHole = new Setting("OnlyInHole", this, true);
        this.timer = new TimerUtils();
        this.placedPos = new HashSet<BlockPos>();
        this.surroundTargets = new Vec3d[] { new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, -1.0), new Vec3d(-1.0, 0.0, 1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, -1.0), new Vec3d(-1.0, 1.0, 1.0) };
        this.oldSlot = -1;
        this.offhand = false;
        this.listener = (Listener<EventPlayerMotionUpdate>)new Listener(event -> {
            if (event.getEra() == Event.Era.PRE && this.mode.getValString().equalsIgnoreCase("MotionTick")) {
                this.doAntiTrap();
            }
        }, new Predicate[0]);
        AntiTrap.instance = this;
        AntiTrap.setmgr.rSetting(this.mode);
        AntiTrap.setmgr.rSetting(this.delay);
        AntiTrap.setmgr.rSetting(this.switchMode);
        AntiTrap.setmgr.rSetting(this.rotate);
        AntiTrap.setmgr.rSetting(this.sortY);
        AntiTrap.setmgr.rSetting(this.onlyInHole);
    }
    
    @Override
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        if (AntiTrap.mc.player == null && AntiTrap.mc.world == null) {
            super.setToggled(false);
            return;
        }
        this.oldSlot = AntiTrap.mc.player.inventory.currentItem;
    }
    
    @Override
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        if (AntiTrap.mc.player == null && AntiTrap.mc.world == null) {
            return;
        }
        this.switchItem();
    }
    
    @Override
    public void update() {
        if (AntiTrap.mc.player == null && AntiTrap.mc.world == null) {
            return;
        }
        if (this.mode.getValString().equalsIgnoreCase("ClientTick")) {
            this.doAntiTrap();
        }
    }
    
    private void doAntiTrap() {
        if (!this.timer.passedMillis((long)this.delay.getValInt())) {
            super.setToggled(false);
            return;
        }
        this.timer.reset();
        this.offhand = (AntiTrap.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL);
        if (!this.offhand && InventoryUtil.findItem(Items.END_CRYSTAL, 0, 9) == -1) {
            super.onDisable();
            return;
        }
        this.oldSlot = AntiTrap.mc.player.inventory.currentItem;
        final ArrayList<Vec3d> targets = new ArrayList<Vec3d>();
        Collections.addAll(targets, BlockUtil.convertVec3ds(AntiTrap.mc.player.getPositionVector(), this.surroundTargets));
        final EntityPlayer closestPlayer = (EntityPlayer)this.getNearTarget((Entity)AntiTrap.mc.player);
        if (closestPlayer != null) {
            targets.sort((vec3d, vec3d2) -> Double.compare(AntiTrap.mc.player.getDistanceSq(vec3d2.x, vec3d2.y, vec3d2.z), AntiTrap.mc.player.getDistanceSq(vec3d.x, vec3d.y, vec3d.z)));
            if (this.sortY.getValBoolean()) {
                targets.sort(Comparator.comparingDouble(vec3d -> vec3d.y));
            }
        }
        for (final Vec3d vec3d3 : targets) {
            final BlockPos pos = new BlockPos(vec3d3);
            if (!CrystalUtils.canPlaceCrystal(pos)) {
                continue;
            }
            this.placeCrystal(pos);
            super.onDisable();
            break;
        }
    }
    
    private void placeCrystal(final BlockPos pos) {
        final boolean mainhand = AntiTrap.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        if (!mainhand && !this.offhand && !this.switchItem()) {
            super.onDisable();
            return;
        }
        final RayTraceResult result = AntiTrap.mc.world.rayTraceBlocks(new Vec3d(AntiTrap.mc.player.posX, AntiTrap.mc.player.posY + AntiTrap.mc.player.getEyeHeight(), AntiTrap.mc.player.posZ), new Vec3d(pos.getX() + 0.5, pos.getY() - 0.5, pos.getZ() + 0.5));
        final EnumFacing facing = (result == null || result.sideHit == null) ? EnumFacing.UP : result.sideHit;
        final float[] angle = AngleUtil.calculateAngle(AntiTrap.mc.player.getPositionEyes(AntiTrap.mc.getRenderPartialTicks()), new Vec3d((double)(pos.getX() + 0.5f), (double)(pos.getY() - 0.5f), (double)(pos.getZ() + 0.5f)));
        final String valString = this.rotate.getValString();
        switch (valString) {
            case "NORMAL": {
                RotationUtils.setPlayerRotations(angle[0], angle[1]);
                break;
            }
            case "PACKET": {
                AntiTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(angle[0], (float)MathHelper.normalizeAngle((int)angle[1], 360), AntiTrap.mc.player.onGround));
                break;
            }
        }
        this.placedPos.add(pos);
        AntiTrap.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(pos, facing, this.offhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
        AntiTrap.mc.player.swingArm(EnumHand.MAIN_HAND);
        this.timer.reset();
    }
    
    private boolean switchItem() {
        if (this.offhand) {
            return true;
        }
        InventoryUtil.switchToSlot(InventoryUtil.findItem(Items.END_CRYSTAL, 0, 9), this.switchMode.getValString().equals("Silent"));
        return this.switchedItem = true;
    }
    
    private EntityLivingBase getNearTarget(final Entity distanceTarget) {
        return (EntityLivingBase)AntiTrap.mc.world.loadedEntityList.stream().filter(this::isValidTarget).map(entity -> entity).min(Comparator.comparing(entity -> distanceTarget.getDistance(entity))).orElse(null);
    }
    
    public boolean isValidTarget(final Entity entity) {
        return entity != null && entity instanceof EntityLivingBase && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f && entity.getDistance((Entity)AntiTrap.mc.player) <= 6.0f && entity instanceof EntityPlayer && entity != AntiTrap.mc.player;
    }
    
    public enum Rotate
    {
        NONE, 
        NORMAL, 
        PACKET;
    }
    
    public enum SwitchModes
    {
        None, 
        Normal, 
        Silent;
    }
}
