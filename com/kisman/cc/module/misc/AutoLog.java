//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.misc;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraft.network.play.server.*;
import net.minecraft.util.text.*;

public class AutoLog extends Module
{
    public AutoLog() {
        super("AutoLog", "5", Category.MISC);
        Kisman.instance.settingsManager.rSetting(new Setting("Health", this, 10.0, 1.0, 36.0, true));
    }
    
    @Override
    public void update() {
        if (AutoLog.mc.player == null && AutoLog.mc.world == null) {
            return;
        }
        final int health = (int)Kisman.instance.settingsManager.getSettingByName(this, "Health").getValDouble();
        if (AutoLog.mc.player.getHealth() < health) {
            AutoLog.mc.getConnection().handleDisconnect(new SPacketDisconnect((ITextComponent)new TextComponentString("your health < " + health)));
            this.toggle();
        }
    }
}
