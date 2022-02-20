//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.combat;

import net.minecraft.util.math.*;
import i.gishreloaded.gishcode.utils.*;
import com.kisman.cc.module.*;
import com.kisman.cc.*;
import com.kisman.cc.util.*;
import net.minecraft.client.entity.*;

public class BurrowBypass extends Module
{
    private BlockPos pos;
    private int delay;
    private int placeDelay;
    private int stage;
    private int jumpdelay;
    private int toggleDelay;
    private boolean jump;
    private TimerUtils timer;
    
    public BurrowBypass() {
        super("BurrowBypass", "get bypass for cc", Category.COMBAT);
        this.timer = new TimerUtils();
    }
    
    @Override
    public void onEnable() {
        this.pos = BurrowBypass.mc.player.getPosition();
        this.placeDelay = 0;
        this.stage = 1;
        this.toggleDelay = 0;
        this.jumpdelay = 0;
        this.timer.reset();
        this.jump = false;
        Kisman.TICK_TIMER = 1.0f;
        this.delay = 0;
    }
    
    @Override
    public void onDisable() {
        this.placeDelay = 0;
        this.stage = 1;
        this.toggleDelay = 0;
        this.jumpdelay = 0;
        this.timer.reset();
        this.jump = false;
        Kisman.TICK_TIMER = 1.0f;
        this.pos = null;
        this.delay = 0;
    }
    
    @Override
    public void update() {
        if (this.stage == 1) {
            ++this.delay;
            BurrowBypass.mc.gameSettings.keyBindJump.pressed = true;
            Kisman.TICK_TIMER = 30.0f;
            if (this.delay >= 42) {
                this.stage = 2;
                this.delay = 0;
                Kisman.TICK_TIMER = 1.0f;
                BurrowBypass.mc.gameSettings.keyBindJump.pressed = false;
            }
        }
        if (this.stage == 2) {
            Kisman.TICK_TIMER = 1.0f;
            if (BurrowBypass.mc.player.onGround) {
                BurrowBypass.mc.gameSettings.keyBindJump.pressed = true;
            }
            BlockUtil.placeBlock(this.pos);
            ++this.placeDelay;
            if (this.placeDelay >= 30) {
                this.stage = 3;
                this.placeDelay = 0;
                BurrowBypass.mc.gameSettings.keyBindJump.pressed = false;
                Kisman.TICK_TIMER = 1.0f;
            }
        }
        if (this.stage == 3) {
            ++this.toggleDelay;
            Kisman.TICK_TIMER = 30.0f;
            BurrowBypass.mc.gameSettings.keyBindJump.pressed = true;
            if (this.toggleDelay >= 25) {
                final EntityPlayerSP player = BurrowBypass.mc.player;
                player.motionY -= 0.4;
                Kisman.TICK_TIMER = 1.0f;
                this.setToggled(BurrowBypass.mc.gameSettings.keyBindJump.pressed = false);
            }
        }
    }
}
