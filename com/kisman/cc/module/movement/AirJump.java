//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import java.util.*;

public class AirJump extends Module
{
    private Setting mode;
    
    public AirJump() {
        super("AirJump", "Category", Category.MOVEMENT);
        this.mode = new Setting("Mode", this, "Vanilla", new ArrayList<String>(Arrays.asList("Vanilla", "NCP")));
        AirJump.setmgr.rSetting(this.mode);
    }
    
    public void update() {
        if (AirJump.mc.player == null && AirJump.mc.world == null) {
            return;
        }
        if (this.mode.getValString().equalsIgnoreCase("Vanilla") && AirJump.mc.gameSettings.keyBindJump.isPressed()) {
            AirJump.mc.player.motionY = 0.7;
        }
        if (this.mode.getValString().equalsIgnoreCase("NCP")) {
            AirJump.mc.player.onGround = true;
            AirJump.mc.player.isAirBorne = false;
        }
    }
}
