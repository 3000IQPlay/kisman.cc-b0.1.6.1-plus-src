//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import net.minecraft.entity.player.*;
import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import com.kisman.cc.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.*;
import com.kisman.cc.module.client.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.util.math.*;
import com.kisman.cc.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.item.*;

public class KillAura extends Module
{
    public static KillAura instance;
    private boolean player;
    private boolean monster;
    private boolean passive;
    private boolean hitsound;
    private float distance;
    public EntityPlayer target;
    private Setting mode;
    private Setting hitLine;
    private Setting shieldBreaker;
    private Setting packetAttack;
    private Setting weapon;
    private Setting invisible;
    private Setting fallDistance;
    private Setting renderLine;
    private Setting targetEsp;
    private Setting calcMode;
    private Setting switchMode;
    private Setting packetSwitch;
    
    public KillAura() {
        super("KillAura", "8", Category.COMBAT);
        this.mode = new Setting("Mode", this, "Sword", Arrays.asList("Single", "Multi"));
        this.hitLine = new Setting("HitLine", this, "Hit");
        this.shieldBreaker = new Setting("ShieldBreaker", this, true);
        this.packetAttack = new Setting("PacketAttack", this, false);
        this.weapon = new Setting("Weapon", this, "Sword", new ArrayList<String>(Arrays.asList("Sword", "Axe", "Both", "None")));
        this.invisible = new Setting("Invisible", this, false);
        this.fallDistance = new Setting("FallDistance", this, 0.1, 0.0, 0.2, false);
        this.renderLine = new Setting("RenderLine", this, "Render");
        this.targetEsp = new Setting("TargetESP", this, true);
        this.calcMode = new Setting("Calc", this, "Multi", new ArrayList<String>(Arrays.asList("Multi", "Single")));
        this.switchMode = new Setting("SwitchMode", this, "None", new ArrayList<String>(Arrays.asList("None", "Normal", "Silent")));
        this.packetSwitch = new Setting("PacketSwitch", this, true);
        KillAura.instance = this;
        KillAura.setmgr.rSetting(this.mode);
        KillAura.setmgr.rSetting(this.hitLine);
        KillAura.setmgr.rSetting(this.shieldBreaker);
        Kisman.instance.settingsManager.rSetting(new Setting("HitSound", this, false));
        KillAura.setmgr.rSetting(this.packetAttack);
        KillAura.setmgr.rSetting(new Setting("WeaponLine", this, "Weapon"));
        KillAura.setmgr.rSetting(this.weapon);
        Kisman.instance.settingsManager.rSetting(new Setting("TargetsLine", this, "Targets"));
        Kisman.instance.settingsManager.rSetting(new Setting("Player", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Monster", this, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Passive", this, true));
        KillAura.setmgr.rSetting(this.invisible);
        Kisman.instance.settingsManager.rSetting(new Setting("DistanceLine", this, "Distance"));
        Kisman.instance.settingsManager.rSetting(new Setting("Distance", this, 4.25, 0.0, 4.25, false));
        KillAura.setmgr.rSetting(this.fallDistance);
        KillAura.setmgr.rSetting(this.renderLine);
        KillAura.setmgr.rSetting(this.targetEsp);
        KillAura.setmgr.rSetting(new Setting("OtherLine", this, "Other"));
        KillAura.setmgr.rSetting(this.calcMode);
        KillAura.setmgr.rSetting(new Setting("SwitchLine", this, "Switch"));
        KillAura.setmgr.rSetting(this.switchMode);
        KillAura.setmgr.rSetting(this.packetSwitch);
    }
    
    @Override
    public void update() {
        if (KillAura.mc.player == null && KillAura.mc.world == null) {
            return;
        }
        if (KillAura.mc.player.isDead) {
            return;
        }
        this.player = Kisman.instance.settingsManager.getSettingByName(this, "Player").getValBoolean();
        this.monster = Kisman.instance.settingsManager.getSettingByName(this, "Monster").getValBoolean();
        this.passive = Kisman.instance.settingsManager.getSettingByName(this, "Passive").getValBoolean();
        this.hitsound = Kisman.instance.settingsManager.getSettingByName(this, "HitSound").getValBoolean();
        this.distance = Kisman.instance.settingsManager.getSettingByName(this, "Distance").getValFloat();
        if (this.mode.getValString().equalsIgnoreCase("Multi")) {
            for (int i = 0; i < KillAura.mc.world.loadedEntityList.size(); ++i) {
                if (KillAura.mc.world.loadedEntityList.get(i) != null && ((KillAura.mc.world.loadedEntityList.get(i) instanceof EntityPlayer && this.player) || (KillAura.mc.world.loadedEntityList.get(i) instanceof EntityMob && this.monster) || (KillAura.mc.world.loadedEntityList.get(i) instanceof EntityAnimal && this.passive))) {
                    final Entity entity = KillAura.mc.world.loadedEntityList.get(i);
                    if (!Config.instance.friends.getValBoolean() || !(entity instanceof EntityPlayer) || !Kisman.instance.friendManager.isFriend((EntityPlayer)entity)) {
                        if (!this.weaponCheck()) {
                            return;
                        }
                        if (KillAura.mc.player.getDistance((Entity)KillAura.mc.world.loadedEntityList.get(i)) <= 4.15 && KillAura.mc.world.loadedEntityList.get(i).ticksExisted % 20 == 0 && KillAura.mc.world.loadedEntityList.get(i) != KillAura.mc.player) {
                            boolean isShieldActive = false;
                            if (entity instanceof EntityPlayer && this.shieldBreaker.getValBoolean()) {
                                final EntityPlayer entity2 = (EntityPlayer)entity;
                                if ((entity2.getHeldItemMainhand().getItem() instanceof ItemShield || entity2.getHeldItemOffhand().getItem() instanceof ItemShield) && entity2.isHandActive()) {
                                    isShieldActive = true;
                                }
                            }
                            final int oldSlot = KillAura.mc.player.inventory.currentItem;
                            final int weaponSlot = InventoryUtil.findWeaponSlot(0, 9, isShieldActive);
                            boolean isHit = false;
                            if (!this.switchMode.getValString().equalsIgnoreCase("None")) {
                                final String valString = this.switchMode.getValString();
                                switch (valString) {
                                    case "Normal": {
                                        InventoryUtil.switchToSlot(weaponSlot, false);
                                        break;
                                    }
                                    case "Silent": {
                                        InventoryUtil.switchToSlot(weaponSlot, true);
                                        break;
                                    }
                                }
                            }
                            else if (KillAura.mc.player.inventory.currentItem != weaponSlot) {
                                return;
                            }
                            this.attack(entity);
                            isHit = true;
                            if (this.hitsound && isHit) {
                                KillAura.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_STONE_BREAK, 1.0f));
                            }
                            if (this.switchMode.getValString().equalsIgnoreCase("Silent") && oldSlot != -1) {
                                InventoryUtil.switchToSlot(oldSlot, true);
                            }
                        }
                    }
                }
            }
        }
        else if (this.mode.getValString().equalsIgnoreCase("Single")) {
            this.target = EntityUtil.getTarget(this.distance);
            if (this.target == null) {
                return;
            }
            if (!this.weaponCheck()) {
                return;
            }
            if (KillAura.mc.player.getDistance((Entity)this.target) <= 4.15 && this.target.ticksExisted % 20 == 0) {
                boolean isShiendActive = false;
                if (this.shieldBreaker.getValBoolean() && (this.target.getHeldItemMainhand().getItem() instanceof ItemShield || this.target.getHeldItemOffhand().getItem() instanceof ItemShield) && this.target.isHandActive()) {
                    isShiendActive = true;
                }
                final int oldSlot2 = KillAura.mc.player.inventory.currentItem;
                final int weaponSlot2 = InventoryUtil.findWeaponSlot(0, 9, isShiendActive);
                boolean isHit2 = false;
                if (!this.switchMode.getValString().equalsIgnoreCase("None")) {
                    final String valString2 = this.switchMode.getValString();
                    switch (valString2) {
                        case "Normal": {
                            InventoryUtil.switchToSlot(weaponSlot2, false);
                            break;
                        }
                        case "Silent": {
                            InventoryUtil.switchToSlot(weaponSlot2, true);
                            break;
                        }
                    }
                }
                else if (KillAura.mc.player.inventory.currentItem != weaponSlot2) {
                    return;
                }
                this.attack((Entity)this.target);
                isHit2 = true;
                if (this.hitsound && isHit2) {
                    KillAura.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.BLOCK_STONE_BREAK, 1.0f));
                }
                if (this.switchMode.getValString().equalsIgnoreCase("Silent") && oldSlot2 != -1) {
                    InventoryUtil.switchToSlot(oldSlot2, true);
                }
            }
        }
    }
    
    private void attack(final Entity entity) {
        if (this.packetAttack.getValBoolean()) {
            KillAura.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            KillAura.mc.playerController.attackEntity((EntityPlayer)KillAura.mc.player, entity);
        }
        KillAura.mc.player.swingArm(EnumHand.MAIN_HAND);
        KillAura.mc.player.resetCooldown();
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent event) {
        if (!this.targetEsp.getValBoolean() || this.target == null) {
            return;
        }
        if (this.target.getHealth() > 0.0f) {
            GL11.glPushMatrix();
            final int color = (this.target.hurtResistantTime > 15) ? ColorUtils.getColor(255, 100, 100) : ColorUtils.rainbow(1, 10L);
            double x = this.target.lastTickPosX + (this.target.posX - this.target.lastTickPosX) * KillAura.mc.timer.renderPartialTicks - KillAura.mc.renderManager.renderPosX;
            double y = this.target.lastTickPosY + (this.target.posY - this.target.lastTickPosY) * KillAura.mc.timer.renderPartialTicks - KillAura.mc.renderManager.renderPosY;
            double z = this.target.lastTickPosZ + (this.target.posZ - this.target.lastTickPosZ) * KillAura.mc.timer.renderPartialTicks - KillAura.mc.renderManager.renderPosZ;
            final double d = this.target.getEyeHeight() + 0.15;
            final double d2 = this.target.isSneaking() ? 0.25 : 0.0;
            final double mid = 0.5;
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glTranslated((x -= 0.5) + mid, (y += d - d2) + mid, (z -= 0.5) + mid);
            GL11.glRotated((double)(-this.target.rotationYaw % 360.0f), 0.0, 1.0, 0.0);
            GL11.glTranslated(-(x + mid), -(y + mid), -(z + mid));
            GL11.glDisable(3553);
            GL11.glEnable(2848);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GLUtils.glColor(color);
            RenderUtil.drawBoundingBox(new AxisAlignedBB(x, y, z, x + 1.0, y + 0.05, z + 1.0));
            GL11.glDisable(2848);
            GL11.glEnable(3553);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(3042);
            GL11.glPopMatrix();
        }
    }
    
    private boolean weaponCheck() {
        if (this.switchMode.getValString().equals("None")) {
            final String valString = this.weapon.getValString();
            switch (valString) {
                case "Sword": {
                    if (!(KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword)) {
                        return false;
                    }
                }
                case "Axe": {
                    if (!(KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe)) {
                        return false;
                    }
                }
                case "Both": {
                    if (!(KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword) && !(KillAura.mc.player.getHeldItemMainhand().getItem() instanceof ItemAxe)) {
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
}
