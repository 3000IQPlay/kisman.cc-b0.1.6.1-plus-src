//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiKnokBack extends Module
{
    public AntiKnokBack() {
        super("AntiKnokBack", "i hate being knocked back", Category.PLAYER);
        Kisman.instance.settingsManager.rSetting(new Setting("Horizontal", this, 90.0, 0.0, 100.0, true));
        Kisman.instance.settingsManager.rSetting(new Setting("Vertical", this, 100.0, 0.0, 100.0, true));
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent e) {
        if (AntiKnokBack.mc.player == null && AntiKnokBack.mc.world == null) {
            return;
        }
        final float horizontal = (float)Kisman.instance.settingsManager.getSettingByName(this, "Horizontal").getValDouble();
        final float vertical = (float)Kisman.instance.settingsManager.getSettingByName(this, "Vertical").getValDouble();
        try {
            if (AntiKnokBack.mc.player.hurtTime == AntiKnokBack.mc.player.maxHurtTime && AntiKnokBack.mc.player.maxHurtTime > 0) {
                final EntityPlayerSP player = AntiKnokBack.mc.player;
                player.motionX *= horizontal / 100.0f;
                final EntityPlayerSP player2 = AntiKnokBack.mc.player;
                player2.motionY *= vertical / 100.0f;
                final EntityPlayerSP player3 = AntiKnokBack.mc.player;
                player3.motionZ *= horizontal / 100.0f;
            }
        }
        catch (Exception ex) {}
    }
}
