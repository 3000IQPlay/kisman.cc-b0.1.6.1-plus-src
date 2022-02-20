//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.settings.*;
import com.kisman.cc.event.events.*;
import me.zero.alpine.listener.*;
import com.kisman.cc.module.*;
import java.util.function.*;
import com.kisman.cc.*;
import net.minecraft.network.play.client.*;
import net.minecraft.init.*;
import net.minecraft.entity.item.*;
import net.minecraft.block.*;

public class NoInteract extends Module
{
    private Setting enderChest;
    private Setting craft;
    private Setting chest;
    private Setting furnace;
    private Setting armorStand;
    private Setting anvil;
    @EventHandler
    private final Listener<PacketEvent.Send> sendListener;
    
    public NoInteract() {
        super("NoInteract", "NoInteract", Category.PLAYER);
        this.enderChest = new Setting("EnderChest", this, true);
        this.craft = new Setting("CraftingTable", this, true);
        this.chest = new Setting("Chest", this, true);
        this.furnace = new Setting("Furnace", this, true);
        this.armorStand = new Setting("ArmorStand", this, true);
        this.anvil = new Setting("Anvil", this, true);
        this.sendListener = (Listener<PacketEvent.Send>)new Listener(event -> {
            if (NoInteract.mc.player == null && NoInteract.mc.world == null) {
                return;
            }
            if (event.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                if (NoInteract.mc.world.getBlockState(NoInteract.mc.objectMouseOver.getBlockPos()).getBlock() == null) {
                    return;
                }
                final Block block = NoInteract.mc.world.getBlockState(NoInteract.mc.objectMouseOver.getBlockPos()).getBlock();
                if ((block == Blocks.CRAFTING_TABLE && this.craft.getValBoolean()) || (block == Blocks.FURNACE && this.furnace.getValBoolean()) || (block == Blocks.ENDER_CHEST && this.enderChest.getValBoolean()) || (block == Blocks.CHEST && this.chest.getValBoolean()) || (block == Blocks.ANVIL && this.anvil.getValBoolean()) || (NoInteract.mc.objectMouseOver.entityHit != null && NoInteract.mc.objectMouseOver.entityHit instanceof EntityArmorStand && this.armorStand.getValBoolean())) {
                    event.cancel();
                }
            }
        }, new Predicate[0]);
        NoInteract.setmgr.rSetting(this.enderChest);
        NoInteract.setmgr.rSetting(this.craft);
        NoInteract.setmgr.rSetting(this.chest);
        NoInteract.setmgr.rSetting(this.furnace);
        NoInteract.setmgr.rSetting(this.armorStand);
        NoInteract.setmgr.rSetting(this.anvil);
    }
    
    public void onEnable() {
        Kisman.EVENT_BUS.subscribe((Listener)this.sendListener);
    }
    
    public void onDisable() {
        Kisman.EVENT_BUS.unsubscribe((Listener)this.sendListener);
    }
}
