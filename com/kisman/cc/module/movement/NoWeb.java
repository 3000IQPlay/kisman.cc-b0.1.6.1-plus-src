//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\User\Desktop\1.12 stable mappings"!

//Decompiled by Procyon!

package com.kisman.cc.module.movement;

import com.kisman.cc.settings.*;
import com.kisman.cc.module.*;
import org.lwjgl.input.*;
import net.minecraft.client.entity.*;

public class NoWeb extends Module
{
    public Setting onGround;
    public Setting motionX;
    public Setting motionY;
    
    public NoWeb() {
        super("NoWeb", "", Category.MOVEMENT);
        this.onGround = new Setting("OnGround", this, true);
        this.motionX = new Setting("MotionX", this, 0.84, -1.0, 5.0, false);
        this.motionY = new Setting("MotionY", this, 1.0, 0.0, 20.0, false);
        NoWeb.setmgr.rSetting(this.onGround);
        NoWeb.setmgr.rSetting(this.motionX);
        NoWeb.setmgr.rSetting(this.motionY);
    }
    
    public void update() {
        if (NoWeb.mc.player == null && NoWeb.mc.world == null) {
            return;
        }
        if (NoWeb.mc.player.isInWeb && !Step.instance.isToggled()) {
            if (Keyboard.isKeyDown(NoWeb.mc.gameSettings.keyBindSneak.getKeyCode())) {
                NoWeb.mc.player.isInWeb = true;
                final EntityPlayerSP player = NoWeb.mc.player;
                player.motionY *= this.motionY.getValDouble();
            }
            else if (this.onGround.getValBoolean()) {
                NoWeb.mc.player.onGround = false;
            }
            if (Keyboard.isKeyDown(NoWeb.mc.gameSettings.keyBindForward.keyCode) || Keyboard.isKeyDown(NoWeb.mc.gameSettings.keyBindBack.keyCode) || Keyboard.isKeyDown(NoWeb.mc.gameSettings.keyBindLeft.keyCode) || Keyboard.isKeyDown(NoWeb.mc.gameSettings.keyBindRight.keyCode)) {
                NoWeb.mc.player.isInWeb = false;
                final EntityPlayerSP player2 = NoWeb.mc.player;
                player2.motionX *= this.motionX.getValDouble();
                final EntityPlayerSP player3 = NoWeb.mc.player;
                player3.motionZ *= this.motionX.getValDouble();
            }
        }
    }
}
