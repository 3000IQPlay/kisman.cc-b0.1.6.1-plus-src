//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import com.kisman.cc.settings.*;
import net.minecraft.util.math.*;
import com.kisman.cc.module.*;
import net.minecraft.init.*;
import i.gishreloaded.gishcode.utils.visual.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import com.kisman.cc.util.*;

public class Burrow extends Module
{
    private Setting rotate;
    private Setting offset;
    private Setting sneak;
    private BlockPos oldPos;
    private int oldSlot;
    
    public Burrow() {
        super("Burrow", "random skid moment", Category.COMBAT);
        this.rotate = new Setting("Rotate", this, false);
        this.offset = new Setting("Offset", this, -7.0, 20.0, 20.0, false);
        this.sneak = new Setting("Sneak", this, false);
        this.oldSlot = -1;
        Burrow.setmgr.rSetting(this.rotate);
        Burrow.setmgr.rSetting(this.offset);
        Burrow.setmgr.rSetting(this.sneak);
    }
    
    @Override
    public void onEnable() {
        if (Burrow.mc.player == null && Burrow.mc.world == null) {
            super.setToggled(false);
            return;
        }
        this.oldPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
        if (Burrow.mc.world.getBlockState(this.oldPos).getBlock() == Blocks.OBSIDIAN || EntityUtil.intersectsWithEntity(this.oldPos)) {
            super.setToggled(false);
            return;
        }
        this.oldSlot = Burrow.mc.player.inventory.currentItem;
    }
    
    @Override
    public void update() {
        if (InventoryUtil.findBlock(Blocks.OBSIDIAN, 0, 9) != -1) {
            ChatUtils.error((Object)"Can't find obsidian in hotbar!");
            super.setToggled(false);
            return;
        }
        InventoryUtil.switchToSlot(InventoryUtil.findBlock(Blocks.OBSIDIAN, 0, 9), true);
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.41999998688698, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.7531999805211997, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.00133597911214, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.16610926093821, Burrow.mc.player.posZ, true));
        final boolean sneaking = Burrow.mc.player.isSneaking();
        if (this.sneak.getValBoolean() && sneaking) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Burrow.mc.player, CPacketEntityAction.Action.START_SNEAKING));
        }
        BlockUtil.placeBlockSmartRotate(this.oldPos, EnumHand.MAIN_HAND, this.rotate.getValBoolean(), true, false);
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + this.offset.getValDouble(), Burrow.mc.player.posZ, false));
        InventoryUtil.switchToSlot(this.oldSlot, true);
        if (this.sneak.getValBoolean() && sneaking) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Burrow.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        super.setToggled(false);
    }
}
