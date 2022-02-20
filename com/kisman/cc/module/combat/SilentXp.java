//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;

public class SilentXp extends Module
{
    private Setting lookPitch;
    private Setting switchMode;
    private int delayCount;
    private int prvSlot;
    
    public SilentXp() {
        super("SilentXP", "SilentXp", Category.COMBAT);
        this.lookPitch = new Setting("LookPitch", this, 90.0, 0.0, 100.0, false);
        this.switchMode = new Setting("SwitchMode", this, "Packet", new ArrayList<String>(Arrays.asList("Packet", "Client")));
        SilentXp.setmgr.rSetting(this.lookPitch);
        SilentXp.setmgr.rSetting(this.switchMode);
    }
    
    @Override
    public void onEnable() {
        this.delayCount = 0;
    }
    
    @Override
    public void update() {
        if (SilentXp.mc.currentScreen == null && SilentXp.mc.player != null && SilentXp.mc.world != null) {
            this.usedXp();
        }
    }
    
    private int findExpInHotbar() {
        int slot = 0;
        for (int i = 0; i < 9; ++i) {
            if (SilentXp.mc.player.inventory.getStackInSlot(i).getItem() == Items.EXPERIENCE_BOTTLE) {
                slot = i;
                break;
            }
        }
        return slot;
    }
    
    private void usedXp() {
        final int oldPitch = (int)SilentXp.mc.player.rotationPitch;
        this.prvSlot = SilentXp.mc.player.inventory.currentItem;
        final String valString = this.switchMode.getValString();
        switch (valString) {
            case "Packet": {
                SilentXp.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.findExpInHotbar()));
                break;
            }
            case "Client": {
                SilentXp.mc.player.inventory.currentItem = this.findExpInHotbar();
                break;
            }
        }
        SilentXp.mc.player.rotationPitch = (float)this.lookPitch.getValDouble();
        SilentXp.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(SilentXp.mc.player.rotationYaw, (float)this.lookPitch.getValDouble(), true));
        SilentXp.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        SilentXp.mc.player.rotationPitch = (float)oldPitch;
        final String valString2 = this.switchMode.getValString();
        switch (valString2) {
            case "Packet": {
                SilentXp.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.prvSlot));
                break;
            }
            case "Client": {
                SilentXp.mc.player.inventory.currentItem = this.prvSlot;
                break;
            }
        }
    }
}
