//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.module.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoSlowBypass extends Module
{
    private boolean sneaking;
    
    public NoSlowBypass() {
        super("NoSlowBypass", "NoSlowBypass", Category.MOVEMENT);
    }
    
    public void update() {
        if (NoSlowBypass.mc.player != null && NoSlowBypass.mc.world != null) {
            final Item item = NoSlowBypass.mc.player.getActiveItemStack().getItem();
            if (this.sneaking && ((!NoSlowBypass.mc.player.isHandActive() && item instanceof ItemFood) || item instanceof ItemBow || item instanceof ItemPotion || !(item instanceof ItemFood) || !(item instanceof ItemBow) || !(item instanceof ItemPotion))) {
                NoSlowBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowBypass.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                this.sneaking = false;
            }
        }
    }
    
    @SubscribeEvent
    public void onUseItem(final LivingEntityUseItemEvent event) {
        if (!this.sneaking) {
            NoSlowBypass.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoSlowBypass.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            this.sneaking = true;
        }
    }
}
