//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import net.minecraft.init.*;
import com.kisman.cc.util.*;
import com.kisman.cc.util.manager.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;

public class AntiBow extends Module
{
    private Setting packet;
    private Setting range;
    private Setting checkUse;
    private Setting maxUse;
    private Setting bowInHandCheck;
    private boolean bool;
    
    public AntiBow() {
        super("AntiBow", Category.COMBAT);
        this.packet = new Setting("Packet", this, false);
        this.range = new Setting("Range", this, 40.0, 0.0, 40.0, false);
        this.checkUse = new Setting("CheckUse", this, false);
        this.maxUse = new Setting("MaxUse", this, 10.0, 0.0, 20.0, true);
        this.bowInHandCheck = new Setting("BowInHandCheck", this, true);
        AntiBow.setmgr.rSetting(this.packet);
        AntiBow.setmgr.rSetting(this.range);
        AntiBow.setmgr.rSetting(this.checkUse);
        AntiBow.setmgr.rSetting(this.maxUse);
        AntiBow.setmgr.rSetting(this.bowInHandCheck);
    }
    
    @Override
    public void onEnable() {
        this.bool = false;
    }
    
    @Override
    public void update() {
        if (AntiBow.mc.player == null && AntiBow.mc.world == null) {
            return;
        }
        EntityPlayer target = EntityUtil.getTarget(this.range.getValFloat());
        int oldSlot = -1;
        final int shieldSlot = InventoryUtil.findItem(Items.SHIELD, 0, 9);
        if (target == null) {
            if (this.bool) {
                AntiBow.mc.gameSettings.keyBindUseItem.pressed = false;
                if (oldSlot != -1) {
                    InventoryUtil.switchToSlot(oldSlot, true);
                }
                target = null;
                this.bool = false;
            }
        }
        else {
            if (shieldSlot == -1) {
                target = null;
                return;
            }
            oldSlot = AntiBow.mc.player.inventory.currentItem;
            if (this.bowInHandCheck.getValBoolean() && !target.getHeldItemMainhand().getItem().equals(Items.BOW)) {
                return;
            }
            if (this.checkUse.getValBoolean() && target.getItemInUseMaxCount() <= this.maxUse.getValDouble()) {
                return;
            }
            if (!AntiBow.mc.player.getHeldItemMainhand().getItem().equals(Items.SHIELD)) {
                InventoryUtil.switchToSlot(shieldSlot, true);
            }
            AntiBow.mc.gameSettings.keyBindUseItem.pressed = true;
            RotationManager.look((Entity)target, this.packet.getValBoolean());
            this.bool = true;
        }
    }
}
