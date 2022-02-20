//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraftforge.event.entity.player.*;
import i.gishreloaded.gishcode.utils.*;
import i.gishreloaded.gishcode.wrappers.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class FastBreak extends Module
{
    public FastBreak() {
        super("FastBreak", "fast++", Category.PLAYER);
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
    
    public void update() {
        PlayerControllerUtils.setBlockHitDelay(0);
    }
    
    @SubscribeEvent
    public void onLeftClickBlock(final PlayerInteractEvent.LeftClickBlock event) {
        final float progress = PlayerControllerUtils.getCurBlockDamageMP() + BlockUtils.getHardness(event.getPos());
        if (progress >= 1.0f) {
            return;
        }
        Wrapper.INSTANCE.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, event.getPos(), Wrapper.INSTANCE.mc().objectMouseOver.sideHit));
    }
}
