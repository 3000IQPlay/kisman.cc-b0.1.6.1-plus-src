//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.settings.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoEZ extends Module
{
    String[] no_team;
    
    public AutoEZ() {
        super("AutoEZ", "", Category.CHAT);
        this.no_team = new String[] { "mudonna", "magisteroff", "momkilla", "ebatte_sratte", "azazel", "tem4ik" };
        Kisman.instance.settingsManager.rSetting(new Setting("voidsetting", this, "void", "setting"));
    }
    
    @SubscribeEvent
    public void onLivingDeathEvent(final LivingDeathEvent event) {
        if (event.getEntity().isDead) {
            for (int i = 0; i < this.no_team.length; ++i) {
                if (event.getEntity().getName().equalsIgnoreCase(this.no_team[i])) {
                    AutoEZ.mc.player.sendChatMessage("I fuck NO team member " + this.no_team[i] + " and all NO team! | kisman.cc on top!");
                    return;
                }
            }
            AutoEZ.mc.player.sendChatMessage(event.getEntity().getName() + " ez! " + "kisman.cc+" + " " + "b0.1.6.1" + "on Top!");
        }
    }
}
