//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.settings.*;
import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import java.util.*;

public class Spammer extends Module
{
    private Setting customMsg;
    private Setting customMessage;
    private ArrayList<String> spam;
    private String[] msg;
    private TimerUtils timer;
    
    public Spammer() {
        super("Spammer", "chat spammer", Category.CHAT);
        this.customMsg = new Setting("CustomMessage", this, false);
        this.customMessage = new Setting("CustomMessage", this, "_kisman_ on top!", "_kisman_ on top!", true);
        this.msg = new String[] { "L3g3ndry on top!", "_kisman_ on top!", "kisman.cc on top!", "DenYoyo on top!", "Buy RusherHack with code \"Robertoss\"!", "kisman.cc owned me((", "Robertoss on top!" };
        this.timer = new TimerUtils();
        Kisman.instance.settingsManager.rSetting(new Setting("GlobalMode", this, false));
        Kisman.instance.settingsManager.rSetting(new Setting("Delay", this, 5000.0, 1000.0, 10000.0, true));
        Spammer.setmgr.rSetting(this.customMsg);
        Spammer.setmgr.rSetting(this.customMessage);
        this.spam = new ArrayList<String>(Arrays.asList(this.msg));
    }
    
    @Override
    public void update() {
        if (Spammer.mc.player == null && Spammer.mc.world == null) {
            return;
        }
        final boolean globalMode = Kisman.instance.settingsManager.getSettingByName(this, "GlobalMode").getValBoolean();
        final long delay = (int)Kisman.instance.settingsManager.getSettingByName(this, "Delay").getValDouble();
        if (this.timer.passedMillis(delay)) {
            if (this.customMsg.getValBoolean()) {
                final String message = this.customMessage.getValString();
                Spammer.mc.player.sendChatMessage(globalMode ? ("!" + message) : message);
            }
            else {
                final Random r = new Random();
                final int index = r.nextInt(this.spam.size());
                final String message2 = this.spam.get(index);
                Spammer.mc.player.sendChatMessage(globalMode ? ("!" + message2) : message2);
            }
            this.timer.reset();
        }
    }
}
