//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.module.*;
import net.minecraft.init.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

public class WeaknessLog extends Module
{
    public WeaknessLog() {
        super("WeaknessLog", "WeaknessLog", Category.MISC);
    }
    
    @Override
    public void update() {
        if (WeaknessLog.mc.player == null && WeaknessLog.mc.world == null) {
            return;
        }
        if (WeaknessLog.mc.player.isPotionActive(MobEffects.WEAKNESS)) {
            WeaknessLog.mc.getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("weakness")));
            this.toggle();
        }
    }
}
