//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.chat;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;

public class Notification extends Module
{
    public static Notification instance;
    private Setting autoCrystal;
    public Setting target;
    public Setting placeObby;
    
    public Notification() {
        super("Notification", "kgdrklbdf", Category.CHAT);
        this.autoCrystal = new Setting("AutoCrystal", this, "AutoCrystal");
        this.target = new Setting("Target", this, true);
        this.placeObby = new Setting("Obby", this, true);
        Notification.instance = this;
        Notification.setmgr.rSetting(this.autoCrystal);
        Notification.setmgr.rSetting(this.target);
        Notification.setmgr.rSetting(this.placeObby);
    }
}
