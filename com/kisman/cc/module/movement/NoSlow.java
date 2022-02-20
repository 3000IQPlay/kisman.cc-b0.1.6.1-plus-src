//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.event.events.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import net.minecraft.network.play.client.*;
import com.kisman.cc.util.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.*;
import com.kisman.cc.console.*;
import com.kisman.cc.oldclickgui.*;
import org.lwjgl.input.*;
import net.minecraft.client.settings.*;

public class NoSlow extends Module
{
    private Setting invMove;
    private Setting items;
    private Setting ncpStrict;
    private Setting slimeBlocks;
    private Setting invLine;
    private Setting ignoreChat;
    private Setting ignoreConsole;
    private Setting ignoreClickGui;
    @EventHandler
    private final Listener<EventPlayerUpdateMoveState> listener;
    @EventHandler
    private final Listener<EventPlayerUpdateMoveState> listener1;
    @EventHandler
    private final Listener<PacketEvent.PostSend> listener2;
    
    public NoSlow() {
        super("NoSlow", "NoSlow", Category.MOVEMENT);
        this.invMove = new Setting("InvMove", this, true);
        this.items = new Setting("Items", this, true);
        this.ncpStrict = new Setting("NCPStrict", this, true);
        this.slimeBlocks = new Setting("SlimeBlocks", this, true);
        this.invLine = new Setting("InvLine", this, "InvMode");
        this.ignoreChat = new Setting("IgnoreChat", this, true);
        this.ignoreConsole = new Setting("IgnoreConsole", this, true);
        this.ignoreClickGui = new Setting("IgnoreClickGui", this, false);
        this.listener = (Listener<EventPlayerUpdateMoveState>)new Listener(event -> {
            if (this.invMove.getValBoolean() && NoSlow.mc.currentScreen != null) {
                if (NoSlow.mc.currentScreen instanceof GuiChat && this.ignoreChat.getValBoolean()) {
                    return;
                }
                if (NoSlow.mc.currentScreen instanceof GuiConsole && this.ignoreConsole.getValBoolean()) {
                    return;
                }
                if (NoSlow.mc.currentScreen instanceof ClickGui && this.ignoreClickGui.getValBoolean()) {
                    return;
                }
                NoSlow.mc.player.movementInput.moveStrafe = 0.0f;
                NoSlow.mc.player.movementInput.moveForward = 0.0f;
                KeyBinding.setKeyBindState(NoSlow.mc.gameSettings.keyBindForward.getKeyCode(), Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindForward.getKeyCode()));
                if (Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindForward.getKeyCode())) {
                    final MovementInput movementInput = NoSlow.mc.player.movementInput;
                    ++movementInput.moveForward;
                    NoSlow.mc.player.movementInput.forwardKeyDown = true;
                }
                else {
                    NoSlow.mc.player.movementInput.forwardKeyDown = false;
                }
                KeyBinding.setKeyBindState(NoSlow.mc.gameSettings.keyBindBack.getKeyCode(), Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindBack.getKeyCode()));
                if (Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindBack.getKeyCode())) {
                    final MovementInput movementInput2 = NoSlow.mc.player.movementInput;
                    --movementInput2.moveForward;
                    NoSlow.mc.player.movementInput.backKeyDown = true;
                }
                else {
                    NoSlow.mc.player.movementInput.backKeyDown = false;
                }
                KeyBinding.setKeyBindState(NoSlow.mc.gameSettings.keyBindLeft.getKeyCode(), Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindLeft.getKeyCode()));
                if (Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindLeft.getKeyCode())) {
                    final MovementInput movementInput3 = NoSlow.mc.player.movementInput;
                    ++movementInput3.moveStrafe;
                    NoSlow.mc.player.movementInput.leftKeyDown = true;
                }
                else {
                    NoSlow.mc.player.movementInput.leftKeyDown = false;
                }
                KeyBinding.setKeyBindState(NoSlow.mc.gameSettings.keyBindRight.getKeyCode(), Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindRight.getKeyCode()));
                if (Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindRight.getKeyCode())) {
                    final MovementInput movementInput4 = NoSlow.mc.player.movementInput;
                    --movementInput4.moveStrafe;
                    NoSlow.mc.player.movementInput.rightKeyDown = true;
                }
                else {
                    NoSlow.mc.player.movementInput.rightKeyDown = false;
                }
                KeyBinding.setKeyBindState(NoSlow.mc.gameSettings.keyBindJump.getKeyCode(), Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindJump.getKeyCode()));
                NoSlow.mc.player.movementInput.jump = Keyboard.isKeyDown(NoSlow.mc.gameSettings.keyBindJump.getKeyCode());
            }
        }, new Predicate[0]);
        this.listener1 = (Listener<EventPlayerUpdateMoveState>)new Listener(event -> {
            if (this.items.getValBoolean() && NoSlow.mc.player.isHandActive() && !NoSlow.mc.player.isRiding()) {
                final MovementInput movementInput = NoSlow.mc.player.movementInput;
                movementInput.moveForward /= (float)0.2;
                final MovementInput movementInput2 = NoSlow.mc.player.movementInput;
                movementInput2.moveStrafe /= (float)0.2;
            }
        }, new Predicate[0]);
        this.listener2 = (Listener<PacketEvent.PostSend>)new Listener(event -> {
            if (event.getPacket() instanceof CPacketPlayer && this.ncpStrict.getValBoolean() && this.items.getValBoolean() && NoSlow.mc.player.isHandActive() && !NoSlow.mc.player.isRiding()) {
                NoSlow.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, PlayerUtil.GetLocalPlayerPosFloored(), EnumFacing.DOWN));
            }
        }, new Predicate[0]);
        NoSlow.setmgr.rSetting(this.invMove);
        NoSlow.setmgr.rSetting(this.items);
        NoSlow.setmgr.rSetting(this.ncpStrict);
        NoSlow.setmgr.rSetting(this.invLine);
        NoSlow.setmgr.rSetting(this.ignoreChat);
        NoSlow.setmgr.rSetting(this.ignoreConsole);
        NoSlow.setmgr.rSetting(this.ignoreClickGui);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.listener);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.subscribe((Listener)this.listener2);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener1);
        Kisman.EVENT_BUS.unsubscribe((Listener)this.listener2);
    }
    
    public void update() {
        if (NoSlow.mc.player == null && NoSlow.mc.world == null) {
            return;
        }
        if (NoSlow.mc.player.isHandActive() && this.items.getValBoolean() && NoSlow.mc.player.getHeldItem(NoSlow.mc.player.getActiveHand()).getItem() instanceof ItemShield && (NoSlow.mc.player.movementInput.moveStrafe != 0.0f || (NoSlow.mc.player.movementInput.moveForward != 0.0f && NoSlow.mc.player.getItemInUseMaxCount() >= 8))) {
            NoSlow.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, NoSlow.mc.player.getHorizontalFacing()));
        }
        if (this.slimeBlocks.getValBoolean()) {
            if (NoSlow.mc.player.getRidingEntity() != null) {
                Blocks.SLIME_BLOCK.setDefaultSlipperiness(0.8f);
            }
            else {
                Blocks.SLIME_BLOCK.setDefaultSlipperiness(0.6f);
            }
        }
    }
}
