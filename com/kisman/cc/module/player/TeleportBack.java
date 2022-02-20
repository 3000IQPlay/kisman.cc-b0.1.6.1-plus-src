//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.player;

import com.kisman.cc.module.*;
import com.kisman.cc.util.*;
import i.gishreloaded.gishcode.utils.visual.*;
import com.kisman.cc.module.movement.*;

public class TeleportBack extends Module
{
    private double x;
    private double y;
    private double z;
    
    public TeleportBack() {
        super("TeleportBack", "TeleportBack", Category.PLAYER);
    }
    
    public void onEnable() {
        if (TeleportBack.mc.player != null && TeleportBack.mc.world != null) {
            CheckUtil.savecord();
            ChatUtils.complete((Object)"Position saved!");
        }
    }
    
    public void onDisable() {
        if (TeleportBack.mc.player != null && TeleportBack.mc.world != null) {
            CheckUtil.loadcord();
            ChatUtils.complete((Object)"Teleported!");
        }
    }
    
    public void update() {
        if (TeleportBack.mc.player != null && TeleportBack.mc.world != null) {
            this.x = TeleportBack.mc.player.posX;
            this.y = TeleportBack.mc.player.posY;
            this.z = TeleportBack.mc.player.posZ;
            if (Sprint.instance.isToggled()) {
                Sprint.instance.setToggled(false);
            }
            if (TeleportBack.mc.player.isSprinting()) {
                TeleportBack.mc.player.setSprinting(false);
            }
            if (TeleportBack.mc.gameSettings.keyBindSneak.isKeyDown()) {
                CheckUtil.loadcord();
            }
            TeleportBack.mc.player.onGround = false;
        }
    }
}
